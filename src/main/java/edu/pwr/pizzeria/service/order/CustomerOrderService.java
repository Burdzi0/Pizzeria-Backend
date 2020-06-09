package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.exception.UserNotFoundException;
import edu.pwr.pizzeria.model.order.*;
import edu.pwr.pizzeria.model.order.dto.CustomPizzaDto;
import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.model.order.dto.StandardPizzaDto;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.AddressDto;
import edu.pwr.pizzeria.model.user.CustomerUser;
import edu.pwr.pizzeria.repository.*;
import edu.pwr.pizzeria.service.ingredient.IngredientNotFoundException;
import edu.pwr.pizzeria.service.mail.MailApplicationService;
import edu.pwr.pizzeria.service.order.calculator.PriceCalculator;
import edu.pwr.pizzeria.service.order.dto.CustomerOrderViewDto;
import edu.pwr.pizzeria.service.pizza.PizzaNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static edu.pwr.pizzeria.model.order.CustomerOrderStatus.*;
import static java.time.Instant.now;

@Service
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;
    private CustomerUserRepository customerUserRepository;
    private PizzaRepository pizzaRepository;
    private OrderedPizzaRepository orderedPizzaRepository;
    private IngredientRepository ingredientRepository;
    private PriceCalculator priceCalculator;
    private MailApplicationService mailApplicationService;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, CustomerUserRepository customerUserRepository, PizzaRepository pizzaRepository, OrderedPizzaRepository orderedPizzaRepository, IngredientRepository ingredientRepository, PriceCalculator priceCalculator, MailApplicationService mailApplicationService) {
        this.customerOrderRepository = customerOrderRepository;
        this.customerUserRepository = customerUserRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderedPizzaRepository = orderedPizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.priceCalculator = priceCalculator;
        this.mailApplicationService = mailApplicationService;
    }

    @Transactional
    public void advanceStatus(Long id, String newStatus) {
        final CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderNotFoundException("CustomerOrder with id:" + id + " not found"));

        switch (newStatus) {
            case "W TRAKCIE REALIZACJI":
                if (customerOrder.getStatus().equals(COOK_AWAITING)) customerOrder.setStatus(COOK_IN_PROGRESS);
                break;
            case "OCZEKUJĄCE NA DOSTAWĘ":
                if (customerOrder.getStatus().equals(COOK_IN_PROGRESS)) customerOrder.setStatus(DELIVERY_AWAITING);
                break;
            case "W DRODZE":
                if (customerOrder.getStatus().equals(DELIVERY_AWAITING)) customerOrder.setStatus(DELIVERY_IN_PROGRESS);
                break;
            case "DOSTARCZONE":
                if (customerOrder.getStatus().equals(DELIVERY_IN_PROGRESS)) customerOrder.setStatus(DELIVERY_READY);
                break;
        }

        customerOrderRepository.save(customerOrder);
    }

    @Transactional
    public void createOrder(CustomerOrderDto customerOrderDto, String mail) {
        final CustomerUser customerUser = customerUserRepository.getCustomerUserByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        final var newCustomerOrder = new CustomerOrder();
        newCustomerOrder.setCustoms(createCustoms(customerOrderDto.getCustoms()));
        newCustomerOrder.setPizzas(getStandards(customerOrderDto.getStandards()));
        newCustomerOrder.setDate(now());
        newCustomerOrder.setAddress(createAddress(customerOrderDto.getAddress()));
        newCustomerOrder.setCustomerUser(customerUser);
        newCustomerOrder.setTotal(priceCalculator.calculate(newCustomerOrder));
        newCustomerOrder.setPayment(customerOrderDto.getPayment());

        customerOrderRepository.save(newCustomerOrder);

        final AddressDto addressDto = customerOrderDto.getAddress();
        mailApplicationService.sendConfirmOrderMail(addressDto.getEmail(), newCustomerOrder);
    }

    private Address createAddress(AddressDto addressDto) {
        return new Address(addressDto.getStreet(), addressDto.getNumber(), addressDto.getPhoneNumber(), addressDto.getEmail());
    }

    private List<OrderedPizza> getStandards(List<StandardPizzaDto> standardPizzas) {
        return standardPizzas.stream()
                .map(standardPizzaDto -> {
                    final Pizza pizza = new Pizza(pizzaRepository.findById(standardPizzaDto.getId())
                            .orElseThrow(() -> new PizzaNotFoundException("Pizza with id: " + standardPizzaDto.getId() + " not found")));

                    final OrderedPizza orderedPizza = new OrderedPizza(pizza);
                    return orderedPizzaRepository.save(orderedPizza);
                }).collect(Collectors.toUnmodifiableList());
    }

    private List<CustomPizza> createCustoms(List<CustomPizzaDto> customs) {
        return customs.stream()
                .map(this::newCustomPizza)
                .collect(Collectors.toList());
    }

    private CustomPizza newCustomPizza(CustomPizzaDto customPizzaDto) {
        final var pizzaIngredients = customPizzaDto.getPizzaIngredients()
                .stream()
                .map(pizzaIngredientDto -> {
                    final var ingredient = ingredientRepository.findById(pizzaIngredientDto.getId())
                            .orElseThrow(() -> new IngredientNotFoundException("Ingredient with id: " + pizzaIngredientDto.getId() + " not found"));
                    return new OrderedIngredient(ingredient.getName(), ingredient.getPrice(), ingredient.isIfAllergen(), pizzaIngredientDto.getQuantity());
                }).collect(Collectors.toList());

        final CustomPizza customPizza = new CustomPizza(customPizzaDto.getDiameter(), customPizzaDto.getCrust(), pizzaIngredients);
        customPizza.setPrice(priceCalculator.calculatePizza(customPizza));
        return customPizza;
    }

    @Transactional(readOnly = true)
    public List<CustomerOrderViewDto> getOrdersForCook() {
        return customerOrderRepository.getAllByStatusIsIn(Set.of(COOK_AWAITING, COOK_IN_PROGRESS))
                .stream()
                .map(CustomerOrderViewDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public List<CustomerOrderViewDto> getOrdersForDeliver() {
        return customerOrderRepository.getAllByStatusIsIn(Set.of(DELIVERY_AWAITING, DELIVERY_IN_PROGRESS, DELIVERY_READY))
                .stream()
                .map(CustomerOrderViewDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public String getPayment(Long id) {
        final CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderNotFoundException("CustomerOrder with id:" + id + " not found"));

        return customerOrder.getPayment();
    }
}


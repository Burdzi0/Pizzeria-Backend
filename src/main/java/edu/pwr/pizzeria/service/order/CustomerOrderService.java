package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.*;
import edu.pwr.pizzeria.model.order.dto.CustomPizzaDto;
import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.model.order.dto.StandardPizzaDto;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.AddressDto;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import edu.pwr.pizzeria.repository.IngredientRepository;
import edu.pwr.pizzeria.repository.OrderedPizzaRepository;
import edu.pwr.pizzeria.repository.PizzaRepository;
import edu.pwr.pizzeria.service.MailApplicationService;
import edu.pwr.pizzeria.service.ingredient.IngredientNotFoundException;
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
    private PizzaRepository pizzaRepository;
    private OrderedPizzaRepository orderedPizzaRepository;
    private IngredientRepository ingredientRepository;
    private PriceCalculator priceCalculator;
    private MailApplicationService mailApplicationService;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, PizzaRepository pizzaRepository, OrderedPizzaRepository orderedPizzaRepository, IngredientRepository ingredientRepository, PriceCalculator priceCalculator, MailApplicationService mailApplicationService) {
        this.customerOrderRepository = customerOrderRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderedPizzaRepository = orderedPizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.priceCalculator = priceCalculator;
        this.mailApplicationService = mailApplicationService;
    }

    @Transactional
    public void advanceStatus(Long id, CustomerOrderStatus newStatus) {
        final CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderNotFoundException("CustomerOrder with id:" + id + " not found"));

        if (ifCorrectOrder(customerOrder.getStatus(), newStatus)) {
            customerOrder.setStatus(newStatus);
        } // TODO what to do if the status is wrong?

        customerOrderRepository.save(customerOrder);
    }

    private boolean ifCorrectOrder(CustomerOrderStatus currentStatus, CustomerOrderStatus newStatus) {
        return currentStatus.getOrdinalNumber() == newStatus.getOrdinalNumber() - 1;
    }

    @Transactional
    public void createOrder(CustomerOrderDto customerOrderDto) {
        final var newCustomerOrder = new CustomerOrder();
        newCustomerOrder.setCustoms(createCustoms(customerOrderDto.getCustoms()));
        newCustomerOrder.setPizzas(getStandards(customerOrderDto.getPizzas()));
        newCustomerOrder.setDate(now());
        newCustomerOrder.setAddress(createAddress(customerOrderDto.getAddressDto()));
        priceCalculator.calculate(newCustomerOrder);

        customerOrderRepository.save(newCustomerOrder);

        final AddressDto addressDto = customerOrderDto.getAddressDto();
        mailApplicationService.sendConfirmOrderMail(addressDto.getEmail());
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

        return new CustomPizza(customPizzaDto.getDiameter(), customPizzaDto.getCrust(), pizzaIngredients);
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
}

package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.model.pizza.StandardPizza;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;
import edu.pwr.pizzeria.model.order.dto.CustomPizzaDto;
import edu.pwr.pizzeria.model.order.dto.StandardPizzaDto;
import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.AddressDto;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import edu.pwr.pizzeria.repository.IngredientRepository;
import edu.pwr.pizzeria.repository.StandardPizzaRepository;
import edu.pwr.pizzeria.service.MailApplicationService;
import edu.pwr.pizzeria.service.ingredient.IngredientNotFoundException;
import edu.pwr.pizzeria.service.order.calculator.PriceCalculator;
import edu.pwr.pizzeria.service.pizza.PizzaNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.Instant.now;

@Service
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;
    private StandardPizzaRepository standardPizzaRepository;
    private IngredientRepository ingredientRepository;
    private PriceCalculator priceCalculator;
    private MailApplicationService mailApplicationService;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository,
                                StandardPizzaRepository standardPizzaRepository,
                                IngredientRepository ingredientRepository,
                                PriceCalculator priceCalculator,
                                MailApplicationService mailApplicationService) {
        this.customerOrderRepository = customerOrderRepository;
        this.standardPizzaRepository = standardPizzaRepository;
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
        newCustomerOrder.setStandardPizzas(getStandards(customerOrderDto.getPizzas()));
        newCustomerOrder.setDate(now());
        newCustomerOrder.setAddress(createAddress(customerOrderDto.getAddressDto()));
        priceCalculator.calculate(newCustomerOrder);

        System.out.println(newCustomerOrder);
        customerOrderRepository.save(newCustomerOrder);

        final AddressDto addressDto = customerOrderDto.getAddressDto();
//        mailApplicationService.sendConfirmOrderMail(addressDto.getEmail());
    }

    private Address createAddress(AddressDto addressDto) {
        return new Address(addressDto.getStreet(), addressDto.getNumber(), addressDto.getPhoneNumber(), addressDto.getEmail());
    }

    private List<StandardPizza> getStandards(List<StandardPizzaDto> standardPizzas) {
        return standardPizzas.stream()
                .map(standardPizzaDto -> {
                    final StandardPizza standardPizza = new StandardPizza(standardPizzaRepository.findById(standardPizzaDto.getId())
                            .orElseThrow(() -> new PizzaNotFoundException("Pizza with id: " + standardPizzaDto.getId() + " not found")));

                    standardPizza.setDiameter(standardPizzaDto.getDiameter());
                    return standardPizza;
                }).collect(Collectors.toUnmodifiableList());
    }

    private List<CustomPizza> createCustoms(List<CustomPizzaDto> customs) {
        return customs.stream()
                .map(this::newCustomPizza)
                .collect(Collectors.toUnmodifiableList());
    }

    private CustomPizza newCustomPizza(CustomPizzaDto customPizzaDto) {
        final var custom = new CustomPizza();

        final var pizzaIngredients = customPizzaDto.getPizzaIngredients()
                .stream()
                .map(pizzaIngredientDto -> {
                    final var ingredient = ingredientRepository.findById(pizzaIngredientDto.getId())
                            .orElseThrow(() -> new IngredientNotFoundException("Ingredient with id: " + pizzaIngredientDto.getId() + " not found"));

                    return new PizzaIngredient(custom, ingredient, pizzaIngredientDto.getQuantity());
                }).collect(Collectors.toUnmodifiableList());

        custom.setCrust(customPizzaDto.getCrust());
        custom.setDiameter(customPizzaDto.getDiameter());
        custom.setIngredients(pizzaIngredients);
        priceCalculator.calculatePizza(custom);

        return custom;
    }
}

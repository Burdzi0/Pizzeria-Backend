package edu.pwr.pizzeria.service.user;

import edu.pwr.pizzeria.model.order.dto.CustomerOrderFullDto;
import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.AddressDto;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import edu.pwr.pizzeria.service.order.CustomerOrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerUserService {

    private final CustomerUserRepository customerUserRepository;
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerUserService(CustomerUserRepository customerUserRepository, CustomerOrderRepository customerOrderRepository) {
        this.customerUserRepository = customerUserRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional
    public void updateAddress(String mail, AddressDto addressDto) {
        final var customerUser = customerUserRepository.getCustomerUserByMail(mail)
                .orElseThrow(() -> new CustomerOrderNotFoundException("Customer does not exist!"));

        customerUser.setAddress(new Address(addressDto.getStreet(), addressDto.getNumber(), addressDto.getPhoneNumber(), addressDto.getEmail()));
        customerUserRepository.save(customerUser);
    }

    @Transactional
    public Address getAddress(String mail){
        final var customerUser = customerUserRepository.getCustomerUserByMail(mail)
                .orElseThrow(() -> new CustomerOrderNotFoundException("Customer does not exist!"));
        return customerUser.getAddress();
    }

    @Transactional(readOnly = true)
    public List<CustomerOrderFullDto> getCustomerOrders(String name) {
        final var customerUser = customerUserRepository.getCustomerUserByMail(name)
                .orElseThrow(() -> new CustomerOrderNotFoundException("Customer does not exist!"));
        return customerOrderRepository.getAllByCustomerUser(customerUser)
                .stream()
                .map(CustomerOrderFullDto::toDto)
                .collect(toList());
    }
}

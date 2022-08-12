package com.example.chinookjava.runners;

import com.example.chinookjava.models.Customer;
import com.example.chinookjava.repository.customer.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerRunner implements ApplicationRunner {
  private final CustomerRepository customerRepository;

  public CustomerRunner(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    /*Prints out all costumers*/
    List<Customer> customerList = customerRepository.findAll();
    System.out.println(customerList);

    /*Prints out costumer with id = 1*/
    Customer customer = customerRepository.findById(1);
    System.out.println(customer.customer_id() + " " + customer.first_name() + " " +
            customer.last_name() + " " + customer.country() + " " +
            customer.postal_code() + " " + customer.phone() + " " + customer.email());
  }
}

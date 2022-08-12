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
    List<Customer> customerListLimit = customerRepository.findAll(2, 12);
    System.out.println(customerListLimit);




    customerRepository.insert(new Customer(0, "Adrian", "Mattsson", "Sweden", "54134", "824674-23423", "adrian@mattsson.com"));

    Customer customerName = customerRepository.findByName("Adrian");
    System.out.println(customerName.customer_id() + " " + customerName.first_name() + " " +
            customerName.last_name() + " " + customerName.country() + " " +
            customerName.postal_code() + " " + customerName.phone() + " " + customerName.email());

    customerRepository.update(new Customer(2, "Oliver", "Dahlqvist", "Sweden", "54144", "224256-2343", "oliver@dalhqvist.com"));


    /*Prints out costumer with id = 1*/
    Customer customer = customerRepository.findById(2);
    System.out.println(customer.customer_id() + " " + customer.first_name() + " " +
            customer.last_name() + " " + customer.country() + " " +
            customer.postal_code() + " " + customer.phone() + " " + customer.email());

  }
}

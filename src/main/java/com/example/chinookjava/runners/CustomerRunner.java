package com.example.chinookjava.runners;

import com.example.chinookjava.models.Customer;
import com.example.chinookjava.models.CustomerCountry;
import com.example.chinookjava.models.CustomerGenre;
import com.example.chinookjava.models.CustomerSpender;
import com.example.chinookjava.repository.customer.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRunner implements ApplicationRunner {
  private final CustomerRepository customerRepository;

  public CustomerRunner(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    /*01. Prints out all customers.*/
    System.out.println("\n_______________________ Find all customers. _______________________");
    List<Customer> customerList = customerRepository.findAllCustomers();
    customerList.forEach(customer -> System.out.println(customer.getCustomerInformation()));

    /*02. Prints out customer by id*/
    System.out.println("\n_______________________ Find customer by id. _______________________");
    Customer customerId = customerRepository.findCustomerById(2);
    System.out.println("\n" + customerId.getCustomerInformation());

    /*03. Prints out customer by first name*/
    System.out.println("\n_______________________ Find customer by name. _______________________");
    List <Customer> customerName = customerRepository.findCustomerByName("Adrian");
//    System.out.println("\n" + customerName.getCustomerInformation());
    customerName.forEach(customer -> System.out.println(customer.getCustomerInformation()));

    /*04. Prints out all customers with limit & offset.*/
    System.out.println("\n_______________________ Find all customers with limits & offsets. _______________________");
    List<Customer> customerListLimit = customerRepository.findAllCustomers(2, 12);
    customerListLimit.forEach(customer -> System.out.println(customer.getCustomerInformation()));

    /* 05. Insert a new customer.*/
//    customerRepository.insertCustomer(new Customer(0, "Adrian", "Mattsson", "Sweden", "54134", "824674-23423", "adrian@mattsson.com"));

    /* 06. Update an existing customer*/
    customerRepository.updateCustomer(new Customer(2, "Oliver", "Dahlqvist", "Sweden", "54144", "224256-2343", "oliver@dalhqvist.com"));

    /*07. Prints out country with most customers*/
    System.out.println("\n_______________________ Find country with most customers. _______________________");
    CustomerCountry mostCustomersCountry = customerRepository.countryWithMostCustomers();
    System.out.println(mostCustomersCountry.findCountryWithMostCustomers());

    /*08. Prints out customer with the highest spender total on invoices.*/
    System.out.println("\n_______________________ Find customer that spends most. _______________________");
    CustomerSpender customerSpender = customerRepository.customerThatSpendsMost();
    System.out.println(customerSpender.findHighestSpender());

    /*09. Prints out the greatest genre for the specific customer.*/
    System.out.println("\n_______________________ Find customer greatest genre. _______________________");
    List <CustomerGenre> genreList = customerRepository.customerGreatestGenre(customerRepository.findCustomerById(12));
    genreList.forEach(genre -> System.out.println(genre.findCustomersGreatestGenre()));

  }
}

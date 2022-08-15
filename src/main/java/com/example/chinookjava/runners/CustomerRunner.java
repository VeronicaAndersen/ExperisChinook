package com.example.chinookjava.runners;

import com.example.chinookjava.models.Customer;
import com.example.chinookjava.repository.customer.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
		/*Insert a new customer.*/
//		customerRepository.insert(new Customer(0, "Adrian", "Mattsson", "Sweden", "54134", "824674-23423", "adrian@mattsson.com"));

		/*Update an existing customer*/
//		customerRepository.update(new Customer(2, "Oliver", "Dahlqvist", "Sweden", "54144", "224256-2343", "oliver@dalhqvist.com"));

		/*Prints out all customers.*/
		System.out.println("\n_______________________ Find all customers. _______________________");
		List<Customer> customerList = customerRepository.findAll();
		customerList.forEach(customer -> System.out.println(customer.getCustomerInformation()));

		/*Prints out all customers with limit & offset.*/
		System.out.println("\n_______________________ Find all customers with limits & offsets. _______________________");
		List<Customer> customerListLimit = customerRepository.findAll(2, 12);
		customerListLimit.forEach(customer -> System.out.println(customer.getCustomerInformation()));

		/*Prints out customer by first name*/
		System.out.println("\n_______________________ Find customer by name. _______________________");
		Customer customerName = customerRepository.findByName("Adrian");
		System.out.println("\n" + customerName.getCustomerInformation());
		
		/*Prints out country with most customers*/
		System.out.println("\n_______________________ Find country with most customers. _______________________");
		System.out.println(customerRepository.countCountry());

		/*Prints out customer with the highest spender total on invoices.*/
		System.out.println("\n_______________________ Find customer that spends most._______________________");
		Customer customerSpender = customerRepository.highestSpender();
		System.out.println(customerSpender.getCustomerInformation());
	}
}

package com.example.chinookjava.runners;

import com.example.chinookjava.repository.customer.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerRunner implements ApplicationRunner {
  private final CustomerRepository customerRepository;

  public CustomerRunner(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }
  @Override
  public void run(ApplicationArguments args) throws Exception{
    System.out.println(customerRepository.findById(1).customer_id());
    System.out.println(customerRepository.findById(1).first_name());
    System.out.println(customerRepository.findById(1).last_name());
    System.out.println(customerRepository.findById(1).country());
    System.out.println(customerRepository.findById(1).postal_code());
    System.out.println(customerRepository.findById(1).phone());
    System.out.println(customerRepository.findById(1).email());
  }
}

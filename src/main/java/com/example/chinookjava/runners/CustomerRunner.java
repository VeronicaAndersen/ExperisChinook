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
    System.out.println(customerRepository.findById(1).first_name());
  }
}

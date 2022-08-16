package com.example.chinookjava.repository;

import com.example.chinookjava.models.Customer;
import com.example.chinookjava.models.CustomerCountry;
import com.example.chinookjava.models.CustomerGenre;
import com.example.chinookjava.models.CustomerSpender;

import java.util.List;

public interface CrudRespository <T, ID> {
    List <T> findAllCustomers();
    List <T> findAllCustomers(int rowLimit, int offsetLimit);

    Customer findCustomerById(ID id);
    Customer findCustomerByName(String name);
    void insertCustomer(T object);
    int updateCustomer(T object);
    CustomerCountry countryWithMostCustomers();
    CustomerSpender customerThatSpendsMost();
    List <CustomerGenre> customerGreatestGenre(T object);
    int delete (T object);
    int deleteByID (ID id);
}

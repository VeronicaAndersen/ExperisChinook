package com.example.chinookjava.repository;

import com.example.chinookjava.models.Customer;
import com.example.chinookjava.models.CustomerCountry;
import com.example.chinookjava.models.CustomerGenre;
import com.example.chinookjava.models.CustomerSpender;

import java.util.List;

public interface CrudRespository <T, ID> {
    List <T> findAll();
    List <T> findAll(int rowLimit, int offsetLimit);

    Customer findById (ID id);
    Customer findByName (String name);
    void insert (T object);
    int update (T object);
    CustomerCountry countCountry();
    CustomerSpender highestSpender();
    List <CustomerGenre> getPopularGenre(T object);
    int delete (T object);
    int deleteByID (ID id);
}

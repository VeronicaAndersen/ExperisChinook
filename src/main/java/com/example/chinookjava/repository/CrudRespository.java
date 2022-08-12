package com.example.chinookjava.repository;

import com.example.chinookjava.models.Customer;

import javax.naming.Name;
import java.util.List;

public interface CrudRespository <T, ID> {
    List <T> findAll(int rowLimit, int offsetLimit);
    Customer findById (ID id);
    Customer findByName (String name);
    int insert (T object);
    int update (T object);
    int delete (T object);
    int deleteByID (ID id);
}

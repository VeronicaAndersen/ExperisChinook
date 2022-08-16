package com.example.chinookjava.repository;

import com.example.chinookjava.models.Customer;
import com.example.chinookjava.models.CustomerCountry;
import com.example.chinookjava.models.CustomerGenre;
import com.example.chinookjava.models.CustomerSpender;

import java.util.List;

/**
 * Base Interface called CrudRepository that contains basic crud operations.
 * @param <T> Type of the object that will be handled.
 * @param <ID> Object that is the same as the primary key to specify the type.
 */
public interface CrudRepository<T, ID> {
    /**
     * Find all customers that´s connected to the database.
     * @return List with all customers.
     */
    List <T> findAllCustomers();
    /**
     * Find all customers that´s connected to the database with limits & offsets.
     * @return List with all customers.
     */
    List <T> findAllCustomers(int rowLimit, int offsetLimit);

    /**
     * Finds customer based on primary key id.
     * @param id primary key to query for.
     * @return Customer with specified id otherwise throw error.
     */
    Customer findCustomerById(ID id);

    /**
     * Finds object based on name.
     * @param name the object to query for.
     * @return Customer by name otherwise throw error.
     */
    Customer findCustomerByName(String name);

    /**
     * Creates a new object.
     * @param object the type to query for.
     */
    void insertCustomer(T object);

    /**
     * Updates an already existing object.
     * @param object the type to query for.
     * @return updates customer otherwise throws error.
     */
    int updateCustomer(T object);

    /**
     * Finds country with most customers.
     * @return country that has most customers.
     */
    CustomerCountry countryWithMostCustomers();

    /**
     * Finds customer that spends most.
     * @return customer that spends most.
     */
    CustomerSpender customerThatSpendsMost();

    /**
     * Finds customers greatest genre.
     * @param object the type to query for.
     * @return top 1 genre for specific customer.
     */
    List <CustomerGenre> customerGreatestGenre(T object);

    /**
     * Deletes object.
     * @param object the type to query for.
     * @return Deletes object.
     */
    int delete (T object);

    /**
     * Deletes customer.
     * @param id the type to query for.
     * @return deletes customer based on id.
     */
    int deleteByID (ID id);
}

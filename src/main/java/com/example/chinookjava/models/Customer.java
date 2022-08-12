package com.example.chinookjava.models;
/*Read all the customers in the database, this should display their:
Id, first name, last name, country, postal code, phone number and email.*/
public record Customer(int customer_id, String first_name, String last_name, String country, String postal_code, String phone, String email) {


}

package com.example.chinookjava.models;

public record Customer(int customer_id, String first_name, String last_name, String country, String postal_code,
                       String phone, String email) {
  public String getCustomerInformation() {
    StringBuilder customerInfo = new StringBuilder();
    customerInfo.append(customer_id + " ");
    customerInfo.append(first_name + " ");
    customerInfo.append(last_name + " ");
    customerInfo.append(country + " ");
    customerInfo.append(postal_code + " ");
    customerInfo.append(phone + " ");
    customerInfo.append(email + " ");
    return customerInfo.toString();
  }

}

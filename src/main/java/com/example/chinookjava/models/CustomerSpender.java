package com.example.chinookjava.models;

public record CustomerSpender(int customer_id, String first_name, String last_name) {
  public String getCustomerSpender() {
    StringBuilder customerInfo = new StringBuilder();
    customerInfo.append(customer_id + " ");
    customerInfo.append(first_name + " ");
    customerInfo.append(last_name + " ");
    return customerInfo.toString();
  }
}

package com.example.chinookjava.models;

public record CustomerCountry(String country) {

  public String findCountryWithMostCustomers() {
    StringBuilder country = new StringBuilder();
    country.append(this.country + " ");
    return country.toString();
  }


}

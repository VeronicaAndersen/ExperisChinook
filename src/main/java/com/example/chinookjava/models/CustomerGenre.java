package com.example.chinookjava.models;

public record CustomerGenre(int genre_id, String name) {
  public String getCustomerGenre() {
    StringBuilder getGenre = new StringBuilder();
    getGenre.append(genre_id + " ");
    getGenre.append(name + " ");
    return getGenre.toString();
  }
}
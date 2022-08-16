package com.example.chinookjava.models;

public record CustomerGenre(int genre_id, String name, int count) {
  public String findCustomersGreatestGenre() {
    StringBuilder getGenre = new StringBuilder();
    getGenre.append(genre_id + " ");
    getGenre.append(name + " ");
    getGenre.append(count + " ");
    return getGenre.toString();
  }
}
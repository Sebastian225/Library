package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class Author{
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private ArrayList<Book> books;

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}

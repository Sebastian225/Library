package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class Section {
    private int id;
    private String name;
    private ArrayList<Book> books = new ArrayList<Book>();
    private boolean isFiction;

    public int getId() { return id; }
    public String getName(){ return name; }
    public ArrayList<Book> getBooks(){ return books; }
    public boolean getIsFiction(){ return isFiction; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setIsFiction(boolean fiction) {
        isFiction = fiction;
    }

    public void addBook(Book book){
        books.add(book);
    }
}

package com.example.library.model;

import com.example.library.enums.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private Author author;
    private Section section; //genre
    private Language language;
    private int year;
    private String publisher;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Section getSection() {
        return section;
    }

    public Language getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}


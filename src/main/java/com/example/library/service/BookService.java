package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() { return bookRepository.getBooks(); }

    public Book getBookById(int id) { return bookRepository.getBookById(id); }

    public void addBook(Book entity) { bookRepository.addBook(entity); }

    public void updateBook(int id, Book entity) { bookRepository.updateBook(id, entity); }

    public void deleteBook(int id) { bookRepository.deleteBook(id); }
}

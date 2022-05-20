package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService readerService) {
        this.bookService = readerService;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }

    @PostMapping(value = "/book", consumes = {"application/json"})
    public void addBook(@RequestBody Book entity){
        bookService.addBook(entity);
    }

    @PatchMapping("/book/{id}")
    public void updateBook(@PathVariable int id, @RequestBody Book entity){
        bookService.updateBook(id, entity);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
    }
}

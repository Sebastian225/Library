package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable int id){
        return authorService.getAuthorById(id);
    }

    @PostMapping(value = "/author", consumes = {"application/json"})
    public void addAuthor(@RequestBody Author entity){
        authorService.addAuthor(entity);
    }

    @PatchMapping("/author/{id}")
    public void updateAuthor(@PathVariable int id, @RequestBody Author entity){
        authorService.updateAuthor(id, entity);
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }
}

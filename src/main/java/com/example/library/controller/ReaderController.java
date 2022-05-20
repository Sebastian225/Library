package com.example.library.controller;

import com.example.library.model.Reader;
import com.example.library.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/readers")
    public List<Reader> getAuthors(){
        return readerService.getReaders();
    }

    @GetMapping("/reader/{id}")
    public Reader getAuthorById(@PathVariable int id){
        return readerService.getReaderById(id);
    }

    @PostMapping(value = "/reader", consumes = {"application/json"})
    public void addAuthor(@RequestBody Reader entity){
        readerService.addReader(entity);
    }

    @PatchMapping("/reader/{id}")
    public void updateAuthor(@PathVariable int id, @RequestBody Reader entity){
        readerService.updateReader(id, entity);
    }

    @DeleteMapping("/reader/{id}")
    public void deleteAuthor(@PathVariable int id){
        readerService.deleteReader(id);
    }
}

package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() { return authorRepository.getAuthors(); }

    public Author getAuthorById(int id) { return authorRepository.getAuthorById(id); }

    public void addAuthor(Author entity) { authorRepository.addAuthor(entity); }

    public void updateAuthor(int id, Author entity) { authorRepository.updateAuthor(id, entity); }

    public void deleteAuthor(int id) { authorRepository.deleteAuthor(id); }
}

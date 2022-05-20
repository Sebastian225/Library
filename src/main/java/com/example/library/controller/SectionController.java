package com.example.library.controller;

import com.example.library.model.Section;
import com.example.library.service.SectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/sections")
    public List<Section> getAuthors(){
        return sectionService.getSections();
    }

    @GetMapping("/section/{id}")
    public Section getAuthorById(@PathVariable int id){
        return sectionService.getSectionById(id);
    }

    @PostMapping(value = "/section", consumes = {"application/json"})
    public void addAuthor(@RequestBody Section entity){
        sectionService.addSection(entity);
    }

    @PatchMapping("/section/{id}")
    public void updateAuthor(@PathVariable int id, @RequestBody Section entity){
        sectionService.updateSection(id, entity);
    }

    @DeleteMapping("/section/{id}")
    public void deleteAuthor(@PathVariable int id){
        sectionService.deleteSection(id);
    }
}

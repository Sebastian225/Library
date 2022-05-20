package com.example.library.service;

import com.example.library.model.Section;
import com.example.library.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getSections() { return sectionRepository.getSections(); }

    public Section getSectionById(int id) { return sectionRepository.getSectionById(id); }

    public void addSection(Section entity) { sectionRepository.addSection(entity); }

    public void updateSection(int id, Section entity) { sectionRepository.updateSection(id, entity); }

    public void deleteSection(int id) { sectionRepository.deleteSection(id); }
}

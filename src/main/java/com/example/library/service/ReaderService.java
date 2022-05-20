package com.example.library.service;

import com.example.library.model.Reader;
import com.example.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> getReaders() { return readerRepository.getReaders(); }

    public Reader getReaderById(int id) { return readerRepository.getReaderById(id); }

    public void addReader(Reader entity) { readerRepository.addReader(entity); }

    public void updateReader(int id, Reader entity) { readerRepository.updateReader(id, entity); }

    public void deleteReader(int id) { readerRepository.deleteReader(id); }
}

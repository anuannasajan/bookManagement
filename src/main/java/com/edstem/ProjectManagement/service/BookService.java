package com.edstem.ProjectManagement.service;

import com.edstem.ProjectManagement.contract.BookResponse;
import com.edstem.ProjectManagement.exception.ResourceNotFoundException;
import com.edstem.ProjectManagement.model.Book;
import com.edstem.ProjectManagement.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    public List<BookResponse> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return books
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() ->{
            log.error("Book with id: {} not found",id);
            return new ResourceNotFoundException(id);
        });
        return modelMapper.map(book, BookResponse.class);
    }

    public BookResponse addBook(BookResponse book) {
        Book savedBookEntity = bookRepository.save(modelMapper.map(book, Book.class));
        return modelMapper.map(savedBookEntity, BookResponse.class);
    }

    public BookResponse updateBook(Long id, BookResponse book) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> {
            log.error("Book with id: {} not found", id);
            return new ResourceNotFoundException(id);
        });
        modelMapper.map(book, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookResponse.class);
    }


    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
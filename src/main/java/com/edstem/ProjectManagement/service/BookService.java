package com.edstem.ProjectManagement.service;

import com.edstem.ProjectManagement.model.Book;
import com.edstem.ProjectManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;

        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        Book bookEntity = modelMapper.map(book, Book.class);
        Book savedBookEntity = bookRepository.save(bookEntity);
        return modelMapper.map(savedBookEntity, Book.class);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            Book bookEntity = optionalBookEntity.get();
            modelMapper.map(updatedBook, bookEntity);
            Book updatedBookEntity = bookRepository.save(bookEntity);
            return modelMapper.map(updatedBookEntity, Book.class);
        } else {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
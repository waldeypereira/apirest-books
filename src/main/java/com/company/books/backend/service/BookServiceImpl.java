package com.company.books.backend.service;

import com.company.books.backend.model.Book;
import com.company.books.backend.repository.IBookRepository;
import com.company.books.backend.response.BookResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final IBookRepository bookRepository;

    public BookServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseRest findAll() {
        log.info("Fetching all books");
        BookResponseRest response = new BookResponseRest();
        try {
            List<Book> books = new ArrayList<>();
            bookRepository.findAll().forEach(books::add);

            response.getBookResponse().setBooks(books);
            response.addMetadata("OK", "00", "Successful query");
        } catch (Exception e) {
            response.getBookResponse().setBooks(new ArrayList<>());
            response.addMetadata("ERROR", "-1", "Error fetching books");
            log.error("Error fetching books", e);
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseRest findById(Long id) {
        log.info("Fetching book by ID: {}", id);
        BookResponseRest response = new BookResponseRest();
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                response.getBookResponse().setBooks(List.of(book));
                response.addMetadata("OK", "00", "Successful query");
            } else {
                response.getBookResponse().setBooks(new ArrayList<>());
                response.addMetadata("ERROR", "-1", "Book not found");
            }
        } catch (Exception e) {
            response.getBookResponse().setBooks(new ArrayList<>());
            response.addMetadata("ERROR", "-1", "Error fetching book");
            log.error("Error fetching book", e);
        }
        return response;
    }

    @Override
    @Transactional
    public BookResponseRest create(Book book) {
        log.info("Creating new book");
        BookResponseRest response = new BookResponseRest();
        try {
            Book savedBook = bookRepository.save(book);
            response.getBookResponse().setBooks(List.of(savedBook));
            response.addMetadata("OK", "00", "Book created successfully");
        } catch (Exception e) {
            response.getBookResponse().setBooks(new ArrayList<>());
            response.addMetadata("ERROR", "-1", "Error creating book");
            log.error("Error creating book", e);
        }
        return response;
    }

    @Override
    @Transactional
    public BookResponseRest update(Book book, Long id) {
        log.info("Updating book with ID: {}", id);
        BookResponseRest response = new BookResponseRest();
        try {
            Book existingBook = bookRepository.findById(id).orElse(null);
            if (existingBook != null) {
                existingBook.setTitle(book.getTitle());
                existingBook.setAuthor(book.getAuthor());
                existingBook.setCategory(book.getCategory());

                Book updatedBook = bookRepository.save(existingBook);
                response.getBookResponse().setBooks(List.of(updatedBook));
                response.addMetadata("OK", "00", "Book updated successfully");
            } else {
                response.getBookResponse().setBooks(new ArrayList<>());
                response.addMetadata("ERROR", "-1", "Book not found");
            }
        } catch (Exception e) {
            response.getBookResponse().setBooks(new ArrayList<>());
            response.addMetadata("ERROR", "-1", "Error updating book");
            log.error("Error updating book", e);
        }
        return response;
    }

    @Override
    @Transactional
    public BookResponseRest delete(Long id) {
        log.info("Deleting book with ID: {}", id);
        BookResponseRest response = new BookResponseRest();
        try {
            if (bookRepository.existsById(id)) {
                bookRepository.deleteById(id);
                response.addMetadata("OK", "00", "Book deleted successfully");
            } else {
                response.addMetadata("ERROR", "-1", "Book not found");
            }
        } catch (Exception e) {
            response.addMetadata("ERROR", "-1", "Error deleting book");
            log.error("Error deleting book", e);
        }
        return response;
    }
}

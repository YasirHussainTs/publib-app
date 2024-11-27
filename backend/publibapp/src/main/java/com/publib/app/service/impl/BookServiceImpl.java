package com.publib.app.service.impl;

import com.publib.app.dto.BookDTO;
import com.publib.app.entity.Book;
import com.publib.app.repository.BookRepository;
import com.publib.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setTitle(bookDTO.getTitle());
            bookToUpdate.setAuthor(bookDTO.getAuthor());
            bookToUpdate.setPublicationYear(bookDTO.getPublicationYear());
            bookToUpdate.setIsbn(bookDTO.getIsbn());

            Book updatedBook = bookRepository.save(bookToUpdate);
            return convertToDTO(updatedBook);
        } else {
            throw new RuntimeException("Book with ID " + id + " not found");
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Helper methods to convert between Book entity and BookDTO
    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getIsbn());
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setIsbn(bookDTO.getIsbn());
        return book;
    }
}
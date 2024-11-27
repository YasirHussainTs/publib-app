package com.publib.app.service.impl;

import com.publib.app.dto.BorrowingRecordDTO;
import com.publib.app.entity.BorrowingRecord;
import com.publib.app.entity.Book;
import com.publib.app.entity.Patron;
import com.publib.app.exception.BookNotAvailableException;
import com.publib.app.exception.BookNotFoundException;
import com.publib.app.exception.PatronNotFoundException;
import com.publib.app.repository.BorrowingRecordRepository;
import com.publib.app.repository.BookRepository;
import com.publib.app.repository.PatronRepository;
import com.publib.app.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingRecordDTO getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new IllegalArgumentException("Borrowing record not found"));
    }

    @Override
    public BorrowingRecordDTO createBorrowingRecord(BorrowingRecordDTO borrowingRecordDTO) {
        Book book = bookRepository.findById(borrowingRecordDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(borrowingRecordDTO.getPatronId())
                .orElseThrow(() -> new PatronNotFoundException("Patron not found"));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(borrowingRecordDTO.getBorrowedDate());
        record.setReturnDate(borrowingRecordDTO.getReturnedDate());

        BorrowingRecord savedRecord = borrowingRecordRepository.save(record);
        return convertToDto(savedRecord);
    }

    @Override
    public BorrowingRecordDTO updateBorrowingRecord(Long id, BorrowingRecordDTO borrowingRecordDTO) {
        BorrowingRecord record = borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Borrowing record not found"));

        record.setBorrowDate(borrowingRecordDTO.getBorrowedDate());
        record.setReturnDate(borrowingRecordDTO.getReturnedDate());

        BorrowingRecord updatedRecord = borrowingRecordRepository.save(record);
        return convertToDto(updatedRecord);
    }

    @Override
    @Transactional
    public BorrowingRecordDTO borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron not found"));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is already borrowed");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setDueDate(LocalDate.now().plusDays(14));

        book.setAvailable(false);
        bookRepository.save(book);

        borrowingRecord = borrowingRecordRepository.save(borrowingRecord);
        return convertToDto(borrowingRecord);
    }

    @Override
    public BorrowingRecordDTO returnBook(Long borrowingRecordId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(borrowingRecordId)
                .orElseThrow(() -> new IllegalArgumentException("No active borrowing record found with the given ID"));

        Book book = borrowingRecord.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);

        return convertToDto(borrowingRecord);
    }

    @Override
    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }

    private BorrowingRecordDTO convertToDto(BorrowingRecord record) {
        return new BorrowingRecordDTO(
                record.getId(),
                record.getBook().getId(),
                record.getPatron().getId(),
                record.getBorrowedDate(),
                record.getDueDate(),
                record.getReturnedDate()
        );
    }
}
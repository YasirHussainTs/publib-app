package com.publib.app.controller;

import com.publib.app.dto.BorrowingRecordDTO;
import com.publib.app.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowingRecords")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public ResponseEntity<List<BorrowingRecordDTO>> getAllBorrowingRecords() {
        List<BorrowingRecordDTO> records = borrowingRecordService.getAllBorrowingRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecordDTO> getBorrowingRecordById(@PathVariable Long id) {
        BorrowingRecordDTO record = borrowingRecordService.getBorrowingRecordById(id);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BorrowingRecordDTO> createBorrowingRecord(@RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        BorrowingRecordDTO newRecord = borrowingRecordService.createBorrowingRecord(borrowingRecordDTO);
        return newRecord != null ? new ResponseEntity<>(newRecord, HttpStatus.CREATED) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecordDTO> updateBorrowingRecord(@PathVariable Long id, @RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        BorrowingRecordDTO updatedRecord = borrowingRecordService.updateBorrowingRecord(id, borrowingRecordDTO);
        return updatedRecord != null ? ResponseEntity.ok(updatedRecord) : ResponseEntity.notFound().build();
    }

    @PostMapping("/borrow")
    public BorrowingRecordDTO borrowBook(@RequestParam Long bookId, @RequestParam Long patronId) {
        return borrowingRecordService.borrowBook(bookId, patronId);
    }

    @PostMapping("/return")
    public BorrowingRecordDTO returnBook(@RequestParam Long borrowingRecordId) {
        return borrowingRecordService.returnBook(borrowingRecordId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.noContent().build();
    }
}
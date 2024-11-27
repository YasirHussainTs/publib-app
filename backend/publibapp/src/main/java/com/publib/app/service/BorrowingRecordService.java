package com.publib.app.service;

import com.publib.app.dto.BorrowingRecordDTO;
import java.util.List;

public interface BorrowingRecordService {
    List<BorrowingRecordDTO> getAllBorrowingRecords();
    BorrowingRecordDTO getBorrowingRecordById(Long id);
    BorrowingRecordDTO createBorrowingRecord(BorrowingRecordDTO borrowingRecordDTO);
    BorrowingRecordDTO updateBorrowingRecord(Long id, BorrowingRecordDTO borrowingRecordDTO);
    BorrowingRecordDTO borrowBook(Long bookId, Long patronId);
    BorrowingRecordDTO returnBook(Long borrowingRecordId);
    void deleteBorrowingRecord(Long id);
}
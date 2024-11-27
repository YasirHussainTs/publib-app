package com.publib.app.controller;

import com.publib.app.dto.PatronDTO;
import com.publib.app.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<PatronDTO> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        PatronDTO patron = patronService.getPatronById(id);
        return patron != null ? ResponseEntity.ok(patron) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PatronDTO> addPatron(@RequestBody PatronDTO patronDTO) {
        PatronDTO newPatron = patronService.addPatron(patronDTO);
        return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @RequestBody PatronDTO patronDTO) {
        PatronDTO updatedPatron = patronService.updatePatron(id, patronDTO);
        return updatedPatron != null ? ResponseEntity.ok(updatedPatron) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
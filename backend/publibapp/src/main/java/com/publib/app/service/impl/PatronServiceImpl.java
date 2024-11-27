package com.publib.app.service.impl;

import com.publib.app.dto.PatronDTO;
import com.publib.app.entity.Patron;
import com.publib.app.repository.PatronRepository;
import com.publib.app.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public List<PatronDTO> getAllPatrons() {
        return patronRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatronDTO getPatronById(Long id) {
        Optional<Patron> patron = patronRepository.findById(id);
        return patron.map(this::convertToDto).orElse(null);
    }

    @Override
    public PatronDTO addPatron(PatronDTO patronDTO) {
        Patron patron = convertToEntity(patronDTO);
        Patron savedPatron = patronRepository.save(patron);
        return convertToDto(savedPatron);
    }

    @Override
    public PatronDTO updatePatron(Long id, PatronDTO patronDTO) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patron = optionalPatron.get();
            patron.setName(patronDTO.getName());
            patron.setContactInfo(patronDTO.getContactInfo());
            Patron updatedPatron = patronRepository.save(patron);
            return convertToDto(updatedPatron);
        }
        return null;
    }

    @Override
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    private PatronDTO convertToDto(Patron patron) {
        return new PatronDTO(patron.getId(), patron.getName(), patron.getContactInfo());
    }

    private Patron convertToEntity(PatronDTO patronDTO) {
        Patron patron = new Patron();
        patron.setName(patronDTO.getName());
        patron.setContactInfo(patronDTO.getContactInfo());
        return patron;
    }
}
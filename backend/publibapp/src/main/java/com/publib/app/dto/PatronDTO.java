package com.publib.app.dto;

public class PatronDTO {
    private Long id;
    private String name;
    private String contactInfo;

    public PatronDTO(Long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Method to convert to DTO
    public PatronDTO toDTO() {
        return new PatronDTO(id, name, contactInfo);
    }
}
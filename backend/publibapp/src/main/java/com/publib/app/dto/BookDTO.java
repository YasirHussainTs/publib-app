package com.publib.app.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;

    public BookDTO(Long id, String title, String author, int publicationYear, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}
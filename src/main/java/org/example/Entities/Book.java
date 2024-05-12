package org.example.Entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
@Table
public class Book {

    @Id
    @Column
    private int ID;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private int publication_year;
    @Column
    private int ISBN;

    public Book() {
    }

    public Book(int ID, String title, String author, int publication_year, int ISBN) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.ISBN = ISBN;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
}

package org.example.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Table
public class BorrowingRecord {
    @Id
    @Column
    private int id;
    @Column
    private Book book;
    @Column
    private Patron patron;
    @Column
    private Date borrowDate;


    public BorrowingRecord() {}
    public BorrowingRecord(Book book, Patron patron, Date borrowDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

}

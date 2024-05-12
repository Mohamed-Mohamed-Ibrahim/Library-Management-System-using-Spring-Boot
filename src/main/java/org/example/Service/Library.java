package org.example.Service;

import org.example.Entities.Book;
import org.example.Entities.BorrowingRecord;
import org.example.Entities.Patron;
import org.example.Repository.BookRepository;
import org.example.Repository.BorrowingRecordRepository;
import org.example.Repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
@Transactional(propagation = Propagation.MANDATORY)
@Service
public class Library {

    private ArrayList<Book> books;
    private ArrayList<Patron> patrons;
    private ArrayList<BorrowingRecord> borrowing_records;
    private Integer no_of_books;
    private Integer no_of_patrons;
    private Integer no_of_borrowing_records;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PatronRepository patronRepository;
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    public Library() {
        no_of_books = no_of_patrons = no_of_borrowing_records = 0;
        books = new ArrayList<>();
        patrons = new ArrayList<>();
        borrowing_records = new ArrayList<>();
        bookRepository.findAll().forEach(item -> books.add(item));
        patronRepository.findAll().forEach(item -> patrons.add(item));
        borrowingRecordRepository.findAll().forEach(item -> borrowing_records.add(item));
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book getBook(int id) {

        for(int i=0; i<no_of_books; i++) {
            if( books.get(i).getID() == id )
                return books.get(i);
        }
        return null;
    }

    public Boolean addBook(Book book) {
        no_of_books++;
        books.add(book);
        bookRepository.save(book);
        return true;
    }

    public Boolean updateBook(int id, Book book) {
        for(int i=0; i<no_of_books; i++) {
            if( books.get(i).getID() == id ){
                books.remove(i);
                break;
            }
        }
        if( no_of_books == books.size() )
            return false;
        bookRepository.deleteById(id);
        books.add(book);
        bookRepository.save(book);
        return true;
    }

    public Boolean deleteBook(int id) {
        for(int i=0; i<no_of_books; i++) {
            if( books.get(i).getID() == id ){
                books.remove(i);
                break;
            }
        }
        if( no_of_books == books.size() )
            return false;
        bookRepository.deleteById(id);
        no_of_books--;
        return true;
    }
    public ArrayList<Patron> getPatrons() {
        return patrons;
    }

    public Patron getPatron(int id) {

        for(int i=0; i<no_of_patrons; i++) {
            if( patrons.get(i).getID() == id )
                return patrons.get(i);
        }
        return null;
    }

    public Boolean addPatron(Patron patron) {
        no_of_patrons++;
        patrons.add(patron);
        patronRepository.save(patron);
        return true;
    }

    public Boolean updatePatron(int id, Patron patron) {
        for(int i=0; i<no_of_patrons; i++) {
            if( patrons.get(i).getID() == id ){
                patrons.remove(i);
                break;
            }
        }
        if( no_of_patrons == patrons.size() )
            return false;
        patronRepository.deleteById(id);
        patrons.add(patron);
        patronRepository.save(patron);
        return true;
    }

    public Boolean deletePatron(int id) {
        for(int i=0; i<no_of_patrons; i++) {
            if( patrons.get(i).getID() == id ){
                patrons.remove(i);
                break;
            }
        }
        if( no_of_patrons == patrons.size() )
            return false;
        patronRepository.deleteById(id);
        no_of_patrons--;
        return true;
    }

    public Boolean addBorrow(int bookId, int patronId) {

        BorrowingRecord borrowingRecord = new BorrowingRecord(
                getBook(bookId),
                getPatron(patronId),
                new Date()
        );
        borrowing_records.add(borrowingRecord);
        no_of_borrowing_records++;
        borrowingRecordRepository.save(borrowingRecord);
        return true;
    }
    public Boolean removeBorrow(int bookId, int patronId) {
        BorrowingRecord borrowingRecordTemp = null;
        for(int i=0; i<no_of_borrowing_records; i++) {
            if( borrowing_records.get(i).getBook().getID() == bookId && borrowing_records.get(i).getPatron().getID() == patronId ){
                borrowingRecordTemp = borrowing_records.remove(i);
                break;
            }
        }
        if( no_of_borrowing_records == borrowing_records.size() )
            return false;
        borrowingRecordRepository.deleteById(borrowingRecordTemp.getId());
        no_of_borrowing_records--;
        return true;
    }
}

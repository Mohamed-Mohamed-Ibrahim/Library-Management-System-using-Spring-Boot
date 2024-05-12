import org.example.Entities.Book;
import org.example.Service.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.example.controller.BookController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private Library library;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveBooks() {
        ArrayList<Book> books = new ArrayList<>();
        when(library.getBooks()).thenReturn(books);

        ArrayList<Book> result = bookController.retrieveBooks();

        assertEquals(books, result);
    }

    @Test
    public void testGetBookFound() {
        int id = 1;
        Book book = new Book();
        when(library.getBook(id)).thenReturn(book);

        ResponseEntity<Book> responseEntity = bookController.getBook(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(book, responseEntity.getBody());
    }

    @Test
    public void testGetBookNotFound() {
        int id = 1;
        when(library.getBook(id)).thenReturn(null);

        ResponseEntity<Book> responseEntity = bookController.getBook(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        when(library.addBook(book)).thenReturn(true);

        ResponseEntity<Object> responseEntity = bookController.addBook(book, mock(BindingResult.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testAddBookValidationError() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Object> responseEntity = bookController.addBook(new Book(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateBookSuccess() {
        int id = 1;
        Book book = new Book();
        when(library.updateBook(eq(id), any(Book.class))).thenReturn(true);

        ResponseEntity<Object> responseEntity = bookController.updateBook(id, book, mock(BindingResult.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateBookNotFound() {
        int id = 1;
        when(library.updateBook(eq(id), any(Book.class))).thenReturn(false);

        ResponseEntity<Object> responseEntity = bookController.updateBook(id, new Book(), mock(BindingResult.class));

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateBookValidationError() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Object> responseEntity = bookController.updateBook(1, new Book(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBookSuccess() {
        int id = 1;
        when(library.deleteBook(id)).thenReturn(true);

        ResponseEntity<Object> responseEntity = bookController.deleteBook(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBookNotFound() {
        int id = 1;
        when(library.deleteBook(id)).thenReturn(false);

        ResponseEntity<Object> responseEntity = bookController.deleteBook(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

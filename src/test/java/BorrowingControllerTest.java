import org.example.Service.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.controller.BorrowingController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BorrowingControllerTest {

    @Mock
    private Library library;

    @InjectMocks
    private BorrowingController borrowingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBorrowSuccess() {
        int bookId = 1;
        int patronId = 1;
        when(library.addBorrow(bookId, patronId)).thenReturn(true);

        ResponseEntity<Object> responseEntity = borrowingController.addBorrow(bookId, patronId);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testAddBorrowNotFound() {
        int bookId = 1;
        int patronId = 1;
        when(library.addBorrow(bookId, patronId)).thenReturn(false);

        ResponseEntity<Object> responseEntity = borrowingController.addBorrow(bookId, patronId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testRemoveBorrowSuccess() {
        int bookId = 1;
        int patronId = 1;
        when(library.removeBorrow(bookId, patronId)).thenReturn(true);

        ResponseEntity<Object> responseEntity = borrowingController.removeBorrow(bookId, patronId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testRemoveBorrowNotFound() {
        int bookId = 1;
        int patronId = 1;
        when(library.removeBorrow(bookId, patronId)).thenReturn(false);

        ResponseEntity<Object> responseEntity = borrowingController.removeBorrow(bookId, patronId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

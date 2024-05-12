import org.example.Entities.Patron;
import org.example.Service.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.example.controller.PatronController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @Mock
    private Library library;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrievePatrons() {
        ArrayList<Patron> patrons = new ArrayList<>();
        when(library.getPatrons()).thenReturn(patrons);

        ArrayList<Patron> result = patronController.retrievePatrons();

        assertEquals(patrons, result);
    }

    @Test
    public void testGetPatronFound() {
        int id = 1;
        Patron patron = new Patron();
        when(library.getPatron(id)).thenReturn(patron);

        ResponseEntity<Patron> responseEntity = patronController.getPatron(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(patron, responseEntity.getBody());
    }

    @Test
    public void testGetPatronNotFound() {
        int id = 1;
        when(library.getPatron(id)).thenReturn(null);

        ResponseEntity<Patron> responseEntity = patronController.getPatron(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testAddPatron() {
        Patron patron = new Patron();
        when(library.addPatron(patron)).thenReturn(true);

        ResponseEntity<Object> responseEntity = patronController.addPatron(patron, mock(BindingResult.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testAddPatronValidationError() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Object> responseEntity = patronController.addPatron(new Patron(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdatePatronSuccess() {
        int id = 1;
        Patron patron = new Patron();
        when(library.updatePatron(eq(id), any(Patron.class))).thenReturn(true);

        ResponseEntity<Object> responseEntity = patronController.updatePatron(id, patron, mock(BindingResult.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdatePatronNotFound() {
        int id = 1;
        when(library.updatePatron(eq(id), any(Patron.class))).thenReturn(false);

        ResponseEntity<Object> responseEntity = patronController.updatePatron(id, new Patron(), mock(BindingResult.class));

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdatePatronValidationError() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Object> responseEntity = patronController.updatePatron(1, new Patron(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletePatronSuccess() {
        int id = 1;
        when(library.deletePatron(id)).thenReturn(true);

        ResponseEntity<Object> responseEntity = patronController.deletePatron(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletePatronNotFound() {
        int id = 1;
        when(library.deletePatron(id)).thenReturn(false);

        ResponseEntity<Object> responseEntity = patronController.deletePatron(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

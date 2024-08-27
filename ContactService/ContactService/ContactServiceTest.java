import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("12345", "John", "Doe", "0123456789", "123 Main St");
        assertTrue(service.addContact(contact));
    }

    @Test
    public void testAddDuplicateContact() {
        Contact contact1 = new Contact("12345", "John", "Doe", "0123456789", "123 Main St");
        Contact contact2 = new Contact("12345", "Jane", "Doe", "9876543210", "456 Elm St");
        service.addContact(contact1);
        assertFalse(service.addContact(contact2));
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("12345", "John", "Doe", "0123456789", "123 Main St");
        service.addContact(contact);
        assertTrue(service.deleteContact("12345"));
    }

    @Test
    public void testDeleteNonexistentContact() {
        assertFalse(service.deleteContact("12345"));
    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("12345", "John", "Doe", "0123456789", "123 Main St");
        service.addContact(contact);
        assertTrue(service.updateContact("12345", "Jane", "Smith", "9876543210", "456 Elm St"));
        Contact updatedContact = service.getContact("12345");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Smith", updatedContact.getLastName());
        assertEquals("9876543210", updatedContact.getPhone());
        assertEquals("456 Elm St", updatedContact.getAddress());
    }

    @Test
    public void testUpdateNonexistentContact() {
        assertFalse(service.updateContact("12345", "Jane", "Smith", "9876543210", "456 Elm St"));
    }
}
package com.example.unittest;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class TestTruyXuatUser {

    TinhToanService service = new TinhToanService();
    @Test
    public void testGetUserName() {
        User user = new User("John Doe");
        assertEquals("John Doe", service.getUserName(user));
    }
    @Test
    public void testGetUserName1() {
        User user = new User("John Cena");
        assertEquals("John Cena", service.getUserName(user));
    }

    @Test
    public void testGetUserName3() {
        User user = null;
        assertNull(service.getUserName(user));
    }

    @Test
    public void testGetUserName2() {
        assertThrows(NullPointerException.class, () -> service.getUserName(null));
    }
}

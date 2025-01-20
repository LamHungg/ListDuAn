package com.example.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestTinhHieu {
    TinhToanService service = new TinhToanService();

    @Test
    public void testTinhHieu1() {
        assertEquals(5, service.tinhHieu(10, 5));
    }

    @Test
    public void testTinhHieu2() {
        assertEquals(-20, service.tinhHieu(-10, 10));
    }

    @Test
    public void testTinhHieu3() {
        assertEquals(0, service.tinhHieu(50, 50));
    }

    @Test
    public void testTinhHieu4() {
        assertEquals(10, service.tinhHieu(-10, -20));
    }

    @Test
    public void testTinhHieu5() {
        assertEquals(-1, service.tinhHieu(0, 1));
    }
}

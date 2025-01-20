package com.example.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTinhTich {
    TinhToanService service = new TinhToanService();

    @Test
    public void testTinhTich1() {
        assertEquals(50, service.tinhTich(10, 5));
    }

    @Test
    public void testTinhTich2() {
        assertEquals(-100, service.tinhTich(-10, 10));
    }

    @Test
    public void testTinhTich3() {
        assertEquals(2500, service.tinhTich(50, 50));
    }

    @Test
    public void testTinhTich4() {
        assertEquals(200, service.tinhTich(-10, -20));
    }

    @Test
    public void testTinhTich5() {
        assertEquals(0, service.tinhTich(0, 1));
    }

    @Test
    public void testTinhTich6() {
        assertEquals(6, service.tinhTich(2, 3));
    }

    @Test
    public void testTinhTich7() {
        assertEquals(0, service.tinhTich(0, 5));
    }

    @Test
    public void testTinhTich8() {
        assertEquals(-10, service.tinhTich(-2, 5));
    }

    @Test
    public void testTinhTich9() {
        assertEquals(10, service.tinhTich(-2, -5));
    }

    @Test
    public void testTinhTich10() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("abc"));
    }
}

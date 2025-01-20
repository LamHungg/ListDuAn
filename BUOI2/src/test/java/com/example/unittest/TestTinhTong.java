package com.example.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TestTinhTong {
    TinhToanService service = new TinhToanService();

    @Test
    public void testTinhTong1() {
        assertEquals(15, service.tinhTong(5, 10));
    }

    @Test
    public void testTinhTong2() {
        assertEquals(0, service.tinhTong(-10, 10));
    }

    @Test
    public void testTinhTong3() {
        assertEquals(100, service.tinhTong(50, 50));
    }

    @Test
    public void testTinhTong4() {
        assertEquals(-30, service.tinhTong(-10, -20));
    }

    @Test
    public void testTinhTong5() {
        assertEquals(1, service.tinhTong(0, 1));
    }
}
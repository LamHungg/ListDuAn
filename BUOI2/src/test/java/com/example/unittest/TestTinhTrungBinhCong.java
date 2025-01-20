package com.example.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestTinhTrungBinhCong {

    TinhToanService service = new TinhToanService();

    @Test
    public void testTinhTrungBinhCong1() {
        assertEquals(7.5, service.tinhTrungBinhCong(5, 10));
    }

    @Test
    public void testTinhTrungBinhCong2() {
        assertEquals(0, service.tinhTrungBinhCong(-10, 10));
    }

    @Test
    public void testTinhTrungBinhCong3() {
        assertEquals(50, service.tinhTrungBinhCong(50, 50));
    }

    @Test
    public void testTinhTrungBinhCong4() {
        assertEquals(-15, service.tinhTrungBinhCong(-10, -20));
    }

    @Test
    public void testTinhTrungBinhCong5() {
        assertEquals(0.5, service.tinhTrungBinhCong(0, 1));
    }
}

package com.example.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTinhThuong {
    TinhToanService service = new TinhToanService();

    @Test
    public void testTinhThuong1() {
        assertEquals(2.0, service.tinhThuong(10, 5));
    }

    @Test
    public void testTinhThuong2() {
        assertEquals(-1.0, service.tinhThuong(-10, 10));
    }

    @Test
    public void testTinhThuong3() {
        assertEquals(1.0, service.tinhThuong(50, 50));
    }

    @Test
    public void testTinhThuong4() {
        assertEquals(0.5, service.tinhThuong(10, 20));
    }

    @Test
    public void testTinhThuong5() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.tinhThuong(10, 0);
        });
        assertEquals("Không thể chia cho 0", exception.getMessage());
    }


    @Test
    public void testChia1() {
        assertEquals(2, service.tinhThuong(6, 3));
    }

    @Test
    public void testChia2() {
        assertEquals(-2, service.tinhThuong(-6, 3));
    }

    @Test
    public void testChia3() {
        assertEquals(0, service.tinhThuong(0, 5));
    }

    @Test
    public void testChia4() {
        assertEquals(3, service.tinhThuong(10, 3));
    }

    @Test
    public void testChia5() {
        assertThrows(ArithmeticException.class, () -> service.tinhThuong(6, 0));
    }


}

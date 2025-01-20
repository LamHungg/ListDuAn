package com.example.unittest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TestKiemTraSoDuongHayAm {
    @Test
    public void testKiemTraSo1() {
        assertEquals("Dương", TinhToanService.kiemTraSo(5));
    }

    @Test
    public void testKiemTraSo2() {
        assertEquals("Âm", TinhToanService.kiemTraSo(-5));
    }

    @Test
    public void testKiemTraSo3() {
        assertEquals("Không", TinhToanService.kiemTraSo(0));
    }

    @Test
    public void testKiemTraSo4() {
        assertEquals("Dương", TinhToanService.kiemTraSo(1));
    }

    @Test
    public void testKiemTraSo5() {
        assertEquals("Âm", TinhToanService.kiemTraSo(-1));
    }
}

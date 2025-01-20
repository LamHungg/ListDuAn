package com.example.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TestTongSoNguyen {

    @Test
    public void testTongNSoNguyen1() {
        assertEquals(10, TinhToanService.tinhTongNSoNguyen(new int[] {1, 2, 3, 4}));
    }

    @Test
    public void testTongNSoNguyen2() {
        assertEquals(-10, TinhToanService.tinhTongNSoNguyen(new int[] {-1, -2, -3, -4}));
    }

    @Test
    public void testTongNSoNguyen3() {
        assertEquals(0, TinhToanService.tinhTongNSoNguyen(new int[] {}));
    }

    @Test
    public void testTongNSoNguyen4() {
        assertEquals(15, TinhToanService.tinhTongNSoNguyen(new int[] {10, 5}));
    }

    @Test
    public void testTongNSoNguyen5() {
        assertEquals(0, TinhToanService.tinhTongNSoNguyen(new int[] {-5, 5}));
    }
}

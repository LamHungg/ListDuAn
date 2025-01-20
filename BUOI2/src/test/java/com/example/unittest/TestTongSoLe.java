package com.example.unittest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TestTongSoLe {

    @Test
    public void testTongSoLe1() {
        assertEquals(4, TinhToanService.tinhTongSoLe(new int[] {1, 2, 3}));
    }

    @Test
    public void testTongSoLe2() {
        assertEquals(0, TinhToanService.tinhTongSoLe(new int[] {2, 4, 6}));
    }

    @Test
    public void testTongSoLe3() {
        assertEquals(0, TinhToanService.tinhTongSoLe(new int[] {}));
    }

    @Test
    public void testTongSoLe4() {
        assertEquals(-1, TinhToanService.tinhTongSoLe(new int[] {-1, 0}));
    }

    @Test
    public void testTongSoLe5() {
        assertEquals(10, TinhToanService.tinhTongSoLe(new int[] {1, 3, 5, 1}));
    }
}

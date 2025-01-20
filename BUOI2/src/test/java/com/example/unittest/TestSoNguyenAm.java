package com.example.unittest;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
public class TestSoNguyenAm {

    @Test
    public void testTinhSoNguyenAm1() {
        assertTrue(TinhToanService.tinhSoNguyenAm(new int[] {-5, 6, -7, -9}));
    }

    @Test
    public void testTinhSoNguyenAm2() {
        assertFalse(TinhToanService.tinhSoNguyenAm(new int[] {5, 6, 7, 9}));
    }

    @Test
    public void testTinhSoNguyenAm3() {
        assertTrue(TinhToanService.tinhSoNguyenAm(new int[] {0, -1, 1}));
    }

    @Test
    public void testTinhSoNguyenAm4() {
        assertFalse(TinhToanService.tinhSoNguyenAm(new int[] {}));
    }

    @Test
    public void testTinhSoNguyenAm5() {
        assertTrue(TinhToanService.tinhSoNguyenAm(new int[] {-1}));
    }
}

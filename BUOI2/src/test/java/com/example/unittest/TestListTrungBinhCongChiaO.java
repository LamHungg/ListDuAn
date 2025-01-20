package com.example.unittest;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestListTrungBinhCongChiaO {

    TinhToanService Calculator = new TinhToanService();
    @Test
    public void testTrungBinhCong1() {
        assertEquals(3.0, Calculator.tinhTrungBinhCong(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testTrungBinhCong2() {
        assertEquals(-1.5, Calculator.tinhTrungBinhCong(Arrays.asList(-2, -1, 0)));
    }

    @Test
    public void testTrungBinhCong3() {
        assertEquals(0.0, Calculator.tinhTrungBinhCong(Arrays.asList(0, 0, 0)));
    }

    @Test
    public void testTrungBinhCong4() {
        assertThrows(ArithmeticException.class, () -> Calculator.tinhTrungBinhCong(Collections.emptyList()));
    }

    @Test
    public void testTrungBinhCong5() {
        assertThrows(ArithmeticException.class, () -> Calculator.tinhTrungBinhCong(null));
    }
}

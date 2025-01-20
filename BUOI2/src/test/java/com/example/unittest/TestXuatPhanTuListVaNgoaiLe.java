package com.example.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestXuatPhanTuListVaNgoaiLe {

    TinhToanService service = new TinhToanService();

    @Test
    public void testGetElementAtIndex1() {
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(3, service.getElementAtIndex(array, 2));
    }

    @Test
    public void testGetElementAtIndex2() {
        int[] array = {1, 2, 3, 4, 5};
        assertThrows(IndexOutOfBoundsException.class, () -> service.getElementAtIndex(array, 5));
    }

    @Test
    public void testGetElementAtIndex3() {
        int[] array = {1, 2, 3, 4, 5};
        assertThrows(IndexOutOfBoundsException.class, () -> service.getElementAtIndex(array, -1));
    }

    @Test
    public void testGetElementAtIndex4() {
        int[] array = {};
        assertThrows(IndexOutOfBoundsException.class, () -> service.getElementAtIndex(array, 0));
    }

    @Test
    public void testGetElementAtIndex5() {
        int[] array = {10};
        assertEquals(10, service.getElementAtIndex(array, 0));
    }
}

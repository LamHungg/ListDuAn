package com.example.unittest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TestTimViTriMang {
    @Test
    public void testTimViTriMang1() {
        // Trường hợp thông thường: tìm thấy giá trị
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(2, TinhToanService.timViTriMang(array, 3));
    }

    @Test
    public void testTimViTriMang2() {
        // Trường hợp không tìm thấy giá trị
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(-1, TinhToanService.timViTriMang(array, 6));
    }

    @Test
    public void testTimViTriMang3() {
        // Trường hợp mảng rỗng
        int[] array = {};
        assertEquals(-1, TinhToanService.timViTriMang(array, 3));
    }

    @Test
    public void testTimViTriMang4() {
        // Trường hợp mảng chỉ có 1 phần tử (khớp giá trị)
        int[] array = {7};
        assertEquals(0, TinhToanService.timViTriMang(array, 7));
    }

    @Test
    public void testTimViTriMang5() {
        // Trường hợp mảng chỉ có 1 phần tử (không khớp giá trị)
        int[] array = {7};
        assertEquals(-1, TinhToanService.timViTriMang(array, 5));
    }
}

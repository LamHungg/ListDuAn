package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class XeOTOTest {
    private XeOTOService service;

    @BeforeEach
    public void setup() {
        service = new XeOTOService();
    }

    // Test thêm xe hợp lệ
    @Test
    public void testAddXeOto_Valid() {
        XeOTO xe = new XeOTO(1, "Toyota", 50000f, "Mới");
        service.addXeOTO(xe);
        Assertions.assertEquals(1, service.getAll().size());
    }

    @Test
    public void testAddXeOto_Invalid() {
        XeOTO xe = new XeOTO(1, "Toyota", 10000f, "Mới");
        service.addXeOTO(xe);
        Assertions.assertEquals(1, service.getAll().size());
    }

    // Test thêm xe có ID trùng
    @Test
    public void testAddXeOto_DuplicateId() {
        service.addXeOTO(new XeOTO(1, "Toyota", 50000f, "Mới"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.addXeOTO(new XeOTO(1, "Honda", 60000f, "Cũ"))
        );
    }

    // Test thêm xe có giá âm
    @Test
    public void testAddXeOto_InvalidPrice() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.addXeOTO(new XeOTO(2, "BMW", -1000f, "Lỗi"))
        );
    }

    // Test cập nhật xe hợp lệ
    @Test
    public void testUpdateXeOto_Valid() {
        service.addXeOTO(new XeOTO(1, "Toyota", 50000f, "Mới"));
        XeOTO xeUpdate = new XeOTO(1, "Toyota Altis", 55000f, "Nâng cấp");
        service.updateXeOTO(xeUpdate, 1);
        Assertions.assertEquals("Toyota Altis", service.getAll().get(0).getTen());
    }

    // Test xóa xe hợp lệ
    @Test
    public void testDeleteXeOto_Valid() {
        service.addXeOTO(new XeOTO(1, "Toyota", 50000f, "Mới"));
        service.deleteXeOTO(1);
        Assertions.assertEquals(0, service.getAll().size());
    }

    // Test tìm kiếm xe hợp lệ
    @Test
    public void testSearchXeOto_Valid() {
        service.addXeOTO(new XeOTO(1, "Toyota", 50000f, "Mới"));
        XeOTO foundXe = service.searchXeOTO(1);
        Assertions.assertNotNull(foundXe);
        Assertions.assertEquals("Toyota", foundXe.getTen());
    }

    // Test tìm kiếm xe không tồn tại
    @Test
    public void testSearchXeOto_NotExist() {
        XeOTO foundXe = service.searchXeOTO(99);
        Assertions.assertNull(foundXe);
    }

    // Test thêm nhiều xe
    @Test
    public void testAddMultipleXeOto() {
        service.addXeOTO(new XeOTO(1, "Toyota", 50000f, "Mới"));
        service.addXeOTO(new XeOTO(2, "Honda", 45000f, "Mới"));
        Assertions.assertEquals(2, service.getAll().size());
    }

    // Test xóa tất cả xe
    @Test
    public void testDeleteAllXeOto() {
        service.addXeOTO(new XeOTO(1, "Toyota", 50000f, "Mới"));
        service.addXeOTO(new XeOTO(2, "Honda", 45000f, "Mới"));
        service.deleteXeOTO(1);
        service.deleteXeOTO(2);
        Assertions.assertEquals(0, service.getAll().size());
    }
}


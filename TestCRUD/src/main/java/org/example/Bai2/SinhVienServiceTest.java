package org.example.Bai2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SinhVienServiceTest {
    private SinhVienService service;

    @BeforeEach
    void setUp() {
        service = new SinhVienService();
    }

    @AfterEach
    void tearDown() {
        service.xoaTatCa();
    }

    @Test
    void testThemSinhVien_HopLe() {
        SinhVien sv = new SinhVien(1, "Nguyen Van A", "10A", "2024", "Toán");
        assertDoesNotThrow(() -> service.themSinhVien(sv));
        SinhVien sv2 = new SinhVien(1, "Nguyen Van A", "10A", "2024", "Toán");
        assertDoesNotThrow(() -> service.themSinhVien(sv2));
    }

    @Test
    void testThemSinhVien_TrungMaSV() {
        SinhVien sv1 = new SinhVien(1, "Nguyen Van A", "10A", "2024", "Toán");
        SinhVien sv2 = new SinhVien(1, "Tran Van B", "10B", "2024", "Vật Lý");

        service.themSinhVien(sv1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.themSinhVien(sv2));
        assertEquals("Mã sinh viên đã tồn tại", exception.getMessage());
    }

    @Test
    void testThemSinhVien_Null() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.themSinhVien(null));
        assertEquals("Sinh viên không được null", exception.getMessage());
    }

    @Test
    void testTimSinhVien_TonTai() {
        SinhVien sv = new SinhVien(1, "Nguyen Van A", "10A", "2024", "Toán");
        service.themSinhVien(sv);
        assertNotNull(service.timSinhVien(1));
    }

    @Test
    void testTimSinhVien_KhongTonTai() {
        assertNull(service.timSinhVien(99));
    }

    @Test
    void testThemNhieuSinhVien() {
        service.themSinhVien(new SinhVien(1, "A", "10A", "2024", "Toán"));
        service.themSinhVien(new SinhVien(2, "B", "10B", "2024", "Lý"));
        service.themSinhVien(new SinhVien(3, "C", "10C", "2024", "Hóa"));

        assertNotNull(service.timSinhVien(1));
        assertNotNull(service.timSinhVien(2));
        assertNotNull(service.timSinhVien(3));
    }

    @Test
    void testTimSinhVien_MaSVAm() {
        assertNull(service.timSinhVien(-1));
    }

    @Test
    void testThemSinhVien_TenRong() {
        SinhVien sv = new SinhVien(4, "", "10A", "2024", "Toán");
        service.themSinhVien(sv);
        assertEquals("", service.timSinhVien(4).getTenSV());
    }

    @Test
    void testThemSinhVien_MaSVBang0() {
        SinhVien sv = new SinhVien(0, "Nguyen Van A", "10A", "2024", "Toán");
        service.themSinhVien(sv);
        assertNotNull(service.timSinhVien(0));
    }

    @Test
    void testTimSinhVien_SauKhiXoaTatCa() {
        service.themSinhVien(new SinhVien(1, "A", "10A", "2024", "Toán"));
        service.xoaTatCa();
        assertNull(service.timSinhVien(1));
    }

    @Test
    void testThemSinhVien_LopNull() {
        SinhVien sv = new SinhVien(5, "Nguyen Van B", null, "2024", "Hóa");
        service.themSinhVien(sv);
        assertNull(service.timSinhVien(5).getLop());
    }

    @Test
    void testTimSinhVien_MaSVRatLon() {
        assertNull(service.timSinhVien(999999));
    }

    @Test
    void testThemSinhVien_KhoaHocRong() {
        SinhVien sv = new SinhVien(6, "Tran Van C", "10A", "", "Văn");
        service.themSinhVien(sv);
        assertEquals("", service.timSinhVien(6).getKhoaHoc());
    }

    @Test
    void testTimSinhVien_SauKhiXoaMotPhan() {
        SinhVien sv1 = new SinhVien(7, "A", "10A", "2024", "Toán");
        SinhVien sv2 = new SinhVien(8, "B", "10B", "2024", "Lý");
        service.themSinhVien(sv1);
        service.themSinhVien(sv2);

        service.danhSachSinhVien.remove(7);
        assertNull(service.timSinhVien(7));
        assertNotNull(service.timSinhVien(8));
    }

    @Test
    void testThemSinhVien_MonHocNull() {
        SinhVien sv = new SinhVien(9, "Nguyen Van D", "10C", "2024", null);
        service.themSinhVien(sv);
        assertNull(service.timSinhVien(9).getMonHoc());
    }
}


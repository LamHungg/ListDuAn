package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import util.DBConnect;

public class NhanVienService {

    // Lấy danh sách tất cả nhân viên
   public List<NhanVien> getAll() throws Exception {
        String sql = "SELECT MaNhanVien, TenNhanVien, GioiTinh, SoDienThoai, DiaChi, Email, CCCD, NgayVaoLam, Password, ChucVu, TrangThai FROM Nhan_Vien";
        List<NhanVien> lists = new ArrayList<>();

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                nv.setPassword(rs.getString("Password"));
                nv.setChucVu(rs.getBoolean("ChucVu"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                lists.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    // Thêm mới nhân viên
    public int themNhanVien(NhanVien nv) throws Exception {
        String sql = "INSERT INTO Nhan_Vien (MaNhanVien, TenNhanVien, GioiTinh, SoDienThoai, DiaChi, Email, CCCD, NgayVaoLam, Password, ChucVu, TrangThai) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTenNhanVien());
            ps.setBoolean(3, nv.isGioiTinh());
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getCccd());
            ps.setDate(8, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            ps.setString(9, nv.getPassword());
            ps.setBoolean(10, nv.isChucVu());
            ps.setBoolean(11, nv.isTrangThai());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Cập nhật thông tin nhân viên
    public int updateNhanVien(NhanVien nv) throws Exception {
        String sql = "UPDATE Nhan_Vien SET TenNhanVien = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ?, Email = ?, CCCD = ?, NgayVaoLam = ?, Password = ?, ChucVu = ?, TrangThai = ? "
                   + "WHERE MaNhanVien = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getTenNhanVien());
            ps.setBoolean(2, nv.isGioiTinh());
            ps.setString(3, nv.getSoDienThoai());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getCccd());
            ps.setDate(7, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            ps.setString(8, nv.getPassword());
            ps.setBoolean(9, nv.isChucVu());
            ps.setBoolean(10, nv.isTrangThai());
            ps.setString(11, nv.getMaNhanVien());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   public int xoaNhanVien(String maNhanVien) throws Exception {
    String sql = "UPDATE Nhan_Vien SET TrangThai = 0 WHERE MaNhanVien = ?"; // Dùng 0 cho false
    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maNhanVien);
        return ps.executeUpdate(); // Trả về số hàng bị ảnh hưởng
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Trả về 0 nếu không thành công
}

    // Kiểm tra mã nhân viên đã tồn tại
    public static boolean kiemTraTonTaiMa(String maNhanVien) {
        String sql = "SELECT MaNhanVien FROM Nhan_Vien WHERE MaNhanVien = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có kết quả, trả về true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra số điện thoại đã tồn tại
    public static boolean kiemTraTonTaiSdt(String sdt) {
        String sql = "SELECT SoDienThoai FROM Nhan_Vien WHERE SoDienThoai = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có kết quả, nghĩa là số điện thoại đã tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra CCCD đã tồn tại
    public static boolean kiemTraTonTaiCCCD(String cccd) {
        String sql = "SELECT CCCD FROM Nhan_Vien WHERE CCCD = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có kết quả, nghĩa là CCCD đã tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm nhân viên theo từ khóa
    public List<NhanVien> timKiem(String keyword) throws Exception {
        String sql = "SELECT MaNhanVien, TenNhanVien, GioiTinh, SoDienThoai, DiaChi, Email, CCCD, NgayVaoLam, Password, ChucVu, TrangThai "
                   + "FROM Nhan_Vien WHERE MaNhanVien LIKE ? OR TenNhanVien LIKE ? OR SoDienThoai LIKE ?";
        List<NhanVien> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                nv.setPassword(rs.getString("Password"));
                nv.setChucVu(rs.getBoolean("ChucVu"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                lists.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }
    public String getLastMaNhanVien() {
    String sql = "SELECT TOP 1 maNhanVien FROM Nhan_Vien ORDER BY maNhanVien DESC";
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("maNhanVien"); // Lấy mã nhân viên cuối cùng
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Trả về null nếu không tìm thấy
}



public String generateMaNhanVien() {
    String lastMaNV = getLastMaNhanVien(); // Lấy mã nhân viên cuối cùng từ DB
    if (lastMaNV == null || lastMaNV.isEmpty()) {
        return "NV001"; // Nếu chưa có nhân viên nào, bắt đầu từ NV01
    }
    // Tách phần số từ mã cuối cùng (VD: NV01 -> 01)
    int lastNumber = Integer.parseInt(lastMaNV.substring(2)); 
    // Tăng lên 1 và định dạng lại với 3 chữ số
    return String.format("NV%03d", lastNumber + 1); 
}


   

}

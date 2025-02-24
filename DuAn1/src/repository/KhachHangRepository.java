package repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import model.KhachHang;
import util.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<KhachHang> getAll() throws Exception {
        ArrayList<KhachHang> list_Khachhang = new ArrayList<>();
        sql = "select * from Khach_Hang";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String MaKH;
                String TenKH;
                boolean GioiTinh;
                String SDT;
                String Diachi;
                String Email;
                boolean Trangthai;

                id = rs.getInt(1);
                MaKH = rs.getString(2);
                TenKH = rs.getString(3);
                GioiTinh = rs.getBoolean(4);
                Diachi = rs.getString(5);
                SDT = rs.getString(6);
                Email = rs.getString(7);
                Trangthai = rs.getBoolean(8);

                KhachHang m = new KhachHang(id, MaKH, TenKH, GioiTinh, Diachi, SDT, Email, Trangthai);
                list_Khachhang.add(m);

            }
            return list_Khachhang;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteKhachHang(String maKH) throws SQLException {

        String sql = "DELETE FROM Khach_Hang WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKH);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int themKhachHang(KhachHang kh) throws Exception {
        String sql = "INSERT INTO Khach_Hang ( TenKhachHang, GioiTinh, DiaChi, SoDienThoai, Email, TrangThai) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getTenKH());
            ps.setString(4, kh.getSDT());
            ps.setBoolean(2, kh.isGioiTinh());
            ps.setString(5, kh.getEmail());
            ps.setString(3, kh.getDiaChi());
            ps.setBoolean(6, kh.isTrangThai());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Update(KhachHang kh, String maKH) throws Exception {
        String sql = "UPDATE Khach_Hang SET TenKhachHang = ?, GioiTinh = ?, DiaChi = ?, SoDienThoai = ?, Email = ?, TrangThai = ? WHERE MaKhachHang = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKH());
            ps.setBoolean(2, kh.isGioiTinh());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSDT());
            ps.setString(5, kh.getEmail());
            ps.setBoolean(6, kh.isTrangThai());
            ps.setString(7, kh.getMaKH());
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<KhachHang> timKhachHang(String makh) {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE MaKhachHang LIKE ? OR TenKhachHang LIKE ? OR Email LIKE ? OR SoDienThoai LIKE ?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            String searchKeyword = "%" + makh + "%"; // Thêm dấu "%" để tìm kiếm theo kiểu LIKE
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);
            ps.setString(4, searchKeyword);

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String MaKH = rs.getString(2);
                String TenKH = rs.getString(3);
                boolean GioiTinh = rs.getBoolean(4);
                String Diachi = rs.getString(5);
                String SDT = rs.getString(6);
                String Email = rs.getString(7);
                boolean Trangthai = rs.getBoolean(8);

                KhachHang kh = new KhachHang(id, MaKH, TenKH, GioiTinh, Diachi, SDT, Email, Trangthai);
                listKH.add(kh);
            }

            // Trả về danh sách nếu tìm thấy kết quả, nếu không thì danh sách rỗng
            return listKH.isEmpty() ? new ArrayList<>() : listKH;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    public boolean isMaKhachHangExists(String maKH) throws Exception {
        String sql = "SELECT COUNT(*) FROM Khach_Hang WHERE MaKhachHang = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu tồn tại mã khách hàng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

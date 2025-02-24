/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnect;
import model.NhanVien;

/**
 *
 * @author Admin
 */
public class login {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

     public login() throws Exception {
        con = DBConnect.getConnection(); // Giả sử bạn đã có một phương thức ConnectDb.getConnection() để kết nối với cơ sở dữ liệu
    }

    public String getRole(String maNhanVien, String pass) {
        String sql = "SELECT ChucVu FROM Nhan_Vien WHERE MaNhanVien = ? AND Password = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);  // Sử dụng MaNhanVien làm tên đăng nhập
            ps.setString(2, pass);         // Sử dụng Password để xác thực mật khẩu
            rs = ps.executeQuery();
            
            String role = "N"; // Mặc định nếu không tìm thấy tài khoản hợp lệ
            if (rs.next()) {
                boolean chucVu = rs.getBoolean("ChucVu");
                role = chucVu ? "1" : "0"; // Nếu ChucVu là true, trả về "1", nếu false trả về "0"
            }
            return role;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu có lỗi hoặc không tìm thấy
    }
    
  public NhanVien getNhanVien(String maNhanVien, String pass) {
    String sql = "SELECT * FROM Nhan_Vien WHERE MaNhanVien = ? AND Password = ?";
    NhanVien nv = null;
    
    try {
        ps = con.prepareStatement(sql);
        ps.setString(1, maNhanVien);
        ps.setString(2, pass);
        rs = ps.executeQuery();

        if (rs.next()) {
            nv = new NhanVien();
            nv.setMaNhanVien(rs.getString("MaNhanVien"));
            nv.setTenNhanVien(rs.getString("TenNhanVien"));
            nv.setChucVu(rs.getBoolean("ChucVu"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return nv;
}
}   

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import util.DBConnect;

import model.ModelSanPham;
import model.ModelThuongHieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author ADMIN
 */
public class RepositorySanPham {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<ModelSanPham> getAll() {
        ArrayList<ModelSanPham> list = new ArrayList<>();
        sql = """
              SELECT 
                  sp.MaSanPham, 
                  sp.TenSanPham, 
                  sp.MoTa, 
                  SUM(spct.SoLuong) as SoLuong, 
                  th.TenThuongHieu,
                  sp.TrangThai 
              FROM 
                  San_Pham sp 
              LEFT JOIN 
                  San_Pham_Chi_Tiet spct ON sp.ID = spct.MaSanPham 
              JOIN 
                  Thuong_Hieu th ON th.ID = sp.MaThuongHieu
              GROUP BY 
                  sp.MaSanPham, 
                  sp.TenSanPham, 
                  sp.MoTa, 
                  sp.TrangThai, 
                  th.TenThuongHieu;
              """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString(1);
                String tenSanPham = rs.getNString(2);
                String moTa = rs.getNString(3);
                int soLuong = rs.getInt(4);
                String tenTH = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                ModelSanPham SP = new ModelSanPham(maSanPham, tenSanPham, tenTH, moTa, soLuong, trangThai);
                list.add(SP);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkTrung(String tenSP) {
        sql = "SELECT * FROM San_Pham WHERE TenSanPham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tenSP);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ModelSanPham> getCBB(int trangThaii) {
        ArrayList<ModelSanPham> list = new ArrayList<>();
        sql = "SELECT dbo.San_Pham.MaSanPham, dbo.San_Pham.TenSanPham, dbo.San_Pham.MoTa, dbo.San_Pham_Chi_Tiet.SoLuong, dbo.San_Pham.TrangThai\n"
                + "FROM     dbo.San_Pham INNER JOIN\n"
                + "                  dbo.San_Pham_Chi_Tiet ON dbo.San_Pham.MaSanPham = dbo.San_Pham_Chi_Tiet.MaSanPham WHERE dbo.San_Pham.TrangThai = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, trangThaii);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString(1);
                String tenSanPham = rs.getNString(2);
                String moTa = rs.getNString(3);
                int soLuong = rs.getInt(4);
                boolean trangThai = rs.getBoolean(5);
                ModelSanPham SP = new ModelSanPham(maSanPham, tenSanPham, moTa, soLuong, trangThai);
                list.add(SP);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getMaTH(String tenTH) {
        String sql = "SELECT ID FROM Thuong_Hieu WHERE TenThuongHieu = ?";
        int maTH = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    maTH = rs.getInt("ID"); // Lấy giá trị cột theo tên
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maTH;
    }

    public ArrayList<ModelThuongHieu> getTenTHCBB() {
        ArrayList<ModelThuongHieu> list = new ArrayList<>();
        sql = "select TenThuongHieu from Thuong_Hieu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tenTH = rs.getString(1);
                ModelThuongHieu hieu = new ModelThuongHieu(tenTH);
                list.add(hieu);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int them(ModelSanPham sp) {
        sql = "insert into San_Pham(TenSanPham,MoTa,SoLuong,TrangThai, MaThuongHieu) values (?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenSanPham());
            ps.setObject(2, sp.getMoTa());
            ps.setObject(3, 0);
            ps.setObject(4, 1);
            ps.setObject(5, sp.getMaThuongHieu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(ModelSanPham sp, String maSP) {
        sql = "update San_Pham set TenSanPham = ? , MoTa = ? , MaThuongHieu = ? where MaSanPham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenSanPham());
            ps.setObject(2, sp.getMoTa());
            ps.setObject(3, sp.getMaThuongHieu());
            ps.setObject(4, maSP);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoa(String maSP) {
        sql = "update San_Pham set TrangThai = 0 where MaSanPham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maSP);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//    public boolean checkTrung(String maSanPham) {
//        sql = "select MaSanPham,MoTa,TenSanPham,SoLuong,TrangThai from San_Pham where MaSanPham = ?";
//        ModelSanPham modelSanPham = null;
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, maSanPham);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    public ArrayList<ModelSanPham> search(String keyword) {
        ArrayList<ModelSanPham> list = new ArrayList<>();
        String sql = "SELECT sp.MaSanPham, sp.TenSanPham, sp.MoTa, SUM(spct.SoLuong) AS SoLuong, "
                + "th.TenThuongHieu, sp.TrangThai "
                + "FROM San_Pham sp "
                + "LEFT JOIN San_Pham_Chi_Tiet spct ON sp.ID = spct.MaSanPham "
                + "JOIN Thuong_Hieu th ON th.ID = sp.MaThuongHieu "
                + "WHERE sp.TenSanPham LIKE ? "
                + "GROUP BY sp.MaSanPham, sp.TenSanPham, sp.MoTa, th.TenThuongHieu, sp.TrangThai";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%"); // Thêm tham số tìm kiếm
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maSanPham = rs.getString("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham"); // Sử dụng getString để đảm bảo tương thích
                String moTa = rs.getString("MoTa");
                int soLuong = rs.getInt("SoLuong");
                String tenTh = rs.getString("TenThuongHieu");
                boolean trangThai = rs.getBoolean("TrangThai");

                ModelSanPham sp = new ModelSanPham(maSanPham, tenSanPham, tenTh, moTa, soLuong, trangThai);
                list.add(sp);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // Trả về danh sách, ngay cả khi rỗng
    }

}

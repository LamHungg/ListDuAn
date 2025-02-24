/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.KhuyenMai;
import util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LONG PHI
 */
public class KhuyenMaiRepository {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private  String sql = null;
    public ArrayList<KhuyenMai> getAlll() throws Exception {
    ArrayList<KhuyenMai> list_KhuyenMai = new ArrayList<>(); 
    sql = "select * from Voucher";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String MaVoucher = rs.getString(2);
            String TenVoucher = rs.getString(3);
            float MucGiamGia = rs.getFloat(4);
            Date ThoiGianBatDau = rs.getDate(5);
            Date ThoiGianKetThuc = rs.getDate(6);
            String Mota = rs.getString(7);
            boolean TrangThai = rs.getBoolean(8);

            KhuyenMai v = new KhuyenMai(id,MaVoucher, TenVoucher, MucGiamGia, ThoiGianBatDau, ThoiGianKetThuc, Mota, TrangThai);
            list_KhuyenMai.add(v);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list_KhuyenMai;
}
 public int updateVoucher(KhuyenMai voucher, String maVoucher) {
    String sql = "UPDATE Voucher SET TenVoucher = ?, MucGiamGia = ?, ThoiGianBatDau = ?, ThoiGianKetThuc = ?, MoTa = ?, TrangThai = ? WHERE MaVoucher = ?";

    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, voucher.getTenVoucher());                      
        ps.setFloat(2, voucher.getMucGiamGia());                       
        ps.setDate(3, new java.sql.Date(voucher.getThoiGianBatDau().getTime())); 
        ps.setDate(4, new java.sql.Date(voucher.getThoiGianKetThuc().getTime())); 
        ps.setString(5, voucher.getMota());                     
        ps.setBoolean(6, voucher.isTrangThai());                      

        ps.setString(7, maVoucher);                            

    
        return ps.executeUpdate(); 

    } catch (Exception e) {
        e.printStackTrace();
        return 0; 
    }
}



        public int themVoucher(KhuyenMai voucher) throws Exception {
        String sql = "INSERT INTO Voucher ( TenVoucher, MucGiamGia, ThoiGianBatDau, ThoiGianKetThuc, MoTa, TrangThai) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try  {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        
        ps.setObject(1, voucher.getTenVoucher());
        ps.setObject(2, voucher.getMucGiamGia());
        ps.setObject(3, voucher.getThoiGianBatDau());
        ps.setObject(4, voucher.getThoiGianKetThuc());
        ps.setObject(5, voucher.getMota());
        ps.setObject(6, voucher.isTrangThai());

        return ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
       public ArrayList<KhuyenMai> timVoucher(String maVoucher) {
    ArrayList<KhuyenMai> listVoucher = new ArrayList<>();
    String sql = "SELECT * FROM Voucher WHERE MaVoucher LIKE ? OR TenVoucher LIKE ?";
    
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        
        String searchKeyword = "%" + maVoucher + "%"; 
        ps.setString(1, searchKeyword);
        ps.setString(2, searchKeyword);

        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1); 
            String MaVoucher = rs.getString(2); 
            String TenVoucher = rs.getString(3); 
            float MucGiamGia = rs.getFloat(4);
            Date ThoiGianBatDau = rs.getDate(5);
            Date ThoiGianKetThuc = rs.getDate(6);
            String MoTa = rs.getString(7); 
            boolean TrangThai = rs.getBoolean(8); 

            KhuyenMai km = new KhuyenMai(id, MaVoucher, TenVoucher, MucGiamGia, ThoiGianBatDau, ThoiGianKetThuc, MoTa, TrangThai);
            listVoucher.add(km);
        }
        return listVoucher.isEmpty() ? new ArrayList<>() : listVoucher;

    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>(); 
    }
}

        
        public int deleteVoucher(int id) {
        String sql = "DELETE FROM Voucher WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return 0;
    }
    public boolean isMaVoucherExists(String maVoucher) throws Exception {
        String sql = "SELECT COUNT(*) FROM Voucher WHERE MaVoucher = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVoucher);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import util.DBConnect;
import model.ModelHDCT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class RepositoryHDCT {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<ModelHDCT> getAll(int maHD) {
        ArrayList<ModelHDCT> list = new ArrayList<>();
        sql = "SELECT \n" +
"    hdc.MaHoaDonChiTiet, \n" +
"    sp.MaSanPham, \n" +
"    sp.TenSanPham, \n" +
"    hdc.SoLuong, \n" +
"    hdc.DonGia, \n" +
"    cl.TenChatLieu, \n" +
"    ms.TenMauSac, \n" +
"    kt.TenKichThuoc, \n" +
"    xx.TenXuatXu\n" +
"FROM \n" +
"    dbo.Hoa_Don_Chi_Tiet hdc\n" +
"INNER JOIN dbo.San_Pham_Chi_Tiet spct ON hdc.MaSanPhamChiTiet = spct.ID\n" +
"INNER JOIN dbo.San_Pham sp ON spct.MaSanPham = sp.ID\n" +
"INNER JOIN dbo.Chat_Lieu cl ON spct.MaChatLieu = cl.ID\n" +
"INNER JOIN dbo.Mau_Sac ms ON spct.MaMauSac = ms.ID\n" +
"INNER JOIN dbo.Kich_Thuoc kt ON spct.MaKichThuoc = kt.ID\n" +
"INNER JOIN dbo.Xuat_Xu xx ON spct.MaXuatXu = xx.ID\n" +
"WHERE \n" +
"    hdc.MaHoaDon = ? AND\n" +
"    hdc.TrangThai = 1;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maHDCT = rs.getString(1);
                String maSanPham = rs.getString(2);
                String tenSanPham = rs.getString(3);
                int soLuong = rs.getInt(4);
                double donGia = rs.getDouble(5);
                String tenChatLieu = rs.getString(6);
                String tenMauSac = rs.getString(7);
                String tenKichThuoc = rs.getString(8);
                String xuatXu = rs.getString(9);
                ModelHDCT HDCT = new ModelHDCT(maHDCT, maSanPham, tenSanPham, soLuong, donGia, tenChatLieu, tenMauSac, tenKichThuoc, xuatXu);
                list.add(HDCT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

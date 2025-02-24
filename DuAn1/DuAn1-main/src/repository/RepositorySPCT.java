/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import util.DBConnect;
import model.ModelSPCT;
import model.ModelSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class RepositorySPCT {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<ModelSPCT> getAll() {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    spct.MaSanPhamChiTiet,\n"
                + "    sp.MaSanPham,\n"
                + "    sp.TenSanPham,\n"
                + "    ms.TenMauSac,\n"
                + "    cl.TenChatLieu,\n"
                + "    kt.TenKichThuoc,\n"
                + "    xx.TenXuatXu,\n"
                + "    th.TenThuongHieu,\n"
                + "    spct.SoLuong,\n"
                + "    spct.DonGia,\n"
                + "    spct.TrangThai\n"
                + "FROM \n"
                + "    San_Pham_Chi_Tiet spct\n"
                + "INNER JOIN \n"
                + "    San_Pham sp ON spct.MaSanPham = sp.ID\n"
                + "INNER JOIN \n"
                + "    Mau_Sac ms ON spct.MaMauSac = ms.ID\n"
                + "INNER JOIN \n"
                + "    Chat_Lieu cl ON spct.MaChatLieu = cl.ID\n"
                + "INNER JOIN \n"
                + "    Kich_Thuoc kt ON spct.MaKichThuoc = kt.ID\n"
                + "INNER JOIN \n"
                + "    Xuat_Xu xx ON spct.MaXuatXu = xx.ID\n"
                + "INNER JOIN \n"
                + "    Thuong_Hieu th ON sp.MaThuongHieu = th.ID\n"
                + "WHERE \n"
                + "    sp.TrangThai = 1  -- Lọc theo trạng thái sản phẩm\n"
                + "ORDER BY \n"
                + "    spct.MaSanPhamChiTiet;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSPCT = rs.getString(1);
                String maSP = rs.getString(2);
                String tenSanPham = rs.getString(3);
                String tenMauSac = rs.getString(4);
                String tenChatLieu = rs.getString(5);
                String tenKichThuoc = rs.getString(6);
                String tenXuatSu = rs.getString(7);
                String tenThuongHieu = rs.getString(8);
                int soLuong = rs.getInt(9);
                double donGia = rs.getDouble(10);
                boolean trangThai = rs.getBoolean(11);
                ModelSPCT SPCT = new ModelSPCT(maSPCT, maSP, tenSanPham, tenMauSac, tenChatLieu, tenKichThuoc, tenXuatSu, tenThuongHieu, soLuong, donGia, trangThai);
                list.add(SPCT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<ModelSPCT> getCBBTH() {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        sql = "SELECT dbo.Thuong_Hieu.TenThuongHieu\n"
                + "FROM     dbo.San_Pham_Chi_Tiet INNER JOIN\n"
                + "                  dbo.Thuong_Hieu ON dbo.San_Pham_Chi_Tiet.ID = dbo.Thuong_Hieu.ID";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ThuongHieu;
                ModelSPCT modelSPCT = new ModelSPCT();
                modelSPCT.setTenThuongHieu(rs.getString(1));
                list.add(modelSPCT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModelSPCT> getCBBTSP() {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        sql = "select MaSanPham,TenSanPham from San_Pham where TrangThai = 1";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String TenSanPham;
                String maSP;
                maSP = rs.getString(1);
                TenSanPham = rs.getString(2);
                ModelSPCT cT = new ModelSPCT(maSP, TenSanPham);
                list.add(cT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModelSPCT> searchSP(String TenSP) {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        sql = "select MaSanPham,TenSanPham from San_Pham where TrangThai = 1 AND TenSanPham like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + TenSP + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String TenSanPham;
                String maSP;
                maSP = rs.getString(1);
                TenSanPham = rs.getString(2);
                ModelSPCT cT = new ModelSPCT(maSP, TenSanPham);
                list.add(cT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModelSPCT> getCBBMS() throws Exception {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        String sql = "SELECT TenMauSac FROM Mau_Sac";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String TenSanPham;
                TenSanPham = rs.getString(1);
                ModelSPCT cT = new ModelSPCT(TenSanPham);
                list.add(cT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ModelSPCT> getCBBCL() {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        sql = "select TenChatLieu from Chat_Lieu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String TenSanPham;
                TenSanPham = rs.getString(1);
                ModelSPCT cT = new ModelSPCT(TenSanPham);
                list.add(cT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModelSPCT> getCBBKT() {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        sql = "select TenKichThuoc from Kich_Thuoc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String TenSanPham;
                TenSanPham = rs.getString(1);
                ModelSPCT cT = new ModelSPCT(TenSanPham);
                list.add(cT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ModelSPCT> getCBBXX() {
        ArrayList<ModelSPCT> list = new ArrayList<>();
        sql = "	select TenXuatXu from Xuat_Xu";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String TenSanPham;
                TenSanPham = rs.getString(1);
                ModelSPCT cT = new ModelSPCT(TenSanPham);
                list.add(cT);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getIDSP(String tenSP) {
        sql = "	SELECT ID FROM San_Pham WHERE TenSanPham = ?";
        int IDSP = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IDSP = rs.getInt("ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return IDSP;
    }

    public int getIDMS(String tenMS) {
        sql = "	SELECT ID FROM Mau_Sac WHERE TenMauSac = ?";
        int IDMS = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenMS);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IDMS = rs.getInt("ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return IDMS;
    }

    public int getIDCL(String tenCL) {
        sql = "	SELECT ID FROM Chat_Lieu WHERE TenChatLieu = ?";
        int IDCL = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenCL);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IDCL = rs.getInt("ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return IDCL;
    }

    public int getIDKT(String tenKT) {
        sql = "	SELECT ID FROM Kich_Thuoc WHERE TenKichThuoc = ?";
        int IDKT = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenKT);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IDKT = rs.getInt("ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return IDKT;
    }

    public int getIDXX(String tenXX) {
        sql = "	SELECT ID FROM Xuat_Xu WHERE TenXuatXu = ?";
        int IDXX = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenXX);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IDXX = rs.getInt("ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return IDXX;
    }

    public int them(ModelSPCT spct) throws Exception {
        if (checkTrung(spct)) {
            if (getSP(spct)) {
                int soLuongCu = getSL(spct);
                int soLuongNew = spct.getSoLuong();
                int tong = soLuongCu + soLuongNew;
                updateSoLuong(getMaSPCT(spct), tong);
                JOptionPane.showMessageDialog(null, "Update số lượng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại và có giá khác!");
            }
        } else {
            sql = "INSERT INTO San_Pham_Chi_Tiet(MaSanPham, MaChatLieu, MaMauSac, MaKichThuoc, MaXuatXu, SoLuong, DonGia, TrangThai) \n"
                    + "	VALUES(?,?,?,?,?,?,?,?)";
            try {
                con = DBConnect.getConnection();
                ps = con.prepareStatement(sql);
                ps.setObject(1, spct.getMaSanPham());
                ps.setObject(2, spct.getMaChatLieu());
                ps.setObject(3, spct.getMaMauSac());
                ps.setObject(4, spct.getMaKichThuoc());
                ps.setObject(5, spct.getMaXuatXu());
                ps.setObject(6, spct.getSoLuong());
                ps.setObject(7, spct.getDonGia());
                ps.setObject(8, 1);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    return rowsAffected;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public boolean checkTrung(ModelSPCT spct) {
        sql = "select * from San_Pham_Chi_Tiet where MaSanPham= ?  AND MaChatLieu = ? AND MaKichThuoc = ? AND MaMauSac = ? AND MaXuatXu = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaSanPham());
            ps.setObject(2, spct.getMaChatLieu());
            ps.setObject(3, spct.getMaKichThuoc());
            ps.setObject(4, spct.getMaMauSac());
            ps.setObject(5, spct.getMaXuatXu());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getSP(ModelSPCT spct) {
        sql = "select * from San_Pham_Chi_Tiet where MaSanPham= ?  AND MaChatLieu = ? AND MaKichThuoc = ? AND MaMauSac = ? AND MaXuatXu = ? AND DonGia = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaSanPham());
            ps.setObject(2, spct.getMaChatLieu());
            ps.setObject(3, spct.getMaKichThuoc());
            ps.setObject(4, spct.getMaMauSac());
            ps.setObject(5, spct.getMaXuatXu());
            ps.setObject(6, spct.getDonGia());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getSL(ModelSPCT spct) {
        sql = "select * from San_Pham_Chi_Tiet where MaSanPham= ?  AND MaChatLieu = ? AND MaKichThuoc = ? AND MaMauSac = ? AND MaXuatXu = ? AND DonGia = ?";
        int soLuong = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaSanPham());
            ps.setObject(2, spct.getMaChatLieu());
            ps.setObject(3, spct.getMaKichThuoc());
            ps.setObject(4, spct.getMaMauSac());
            ps.setObject(5, spct.getMaXuatXu());
            ps.setObject(6, spct.getDonGia());
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public String getMaSPCT(ModelSPCT spct) {
        sql = "select * from San_Pham_Chi_Tiet where MaSanPham= ?  AND MaChatLieu = ? AND MaKichThuoc = ? AND MaMauSac = ? AND MaXuatXu = ? AND DonGia = ?";
        String soLuong = "";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaSanPham());
            ps.setObject(2, spct.getMaChatLieu());
            ps.setObject(3, spct.getMaKichThuoc());
            ps.setObject(4, spct.getMaMauSac());
            ps.setObject(5, spct.getMaXuatXu());
            ps.setObject(6, spct.getDonGia());
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getString("MaSanPhamChitiet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    private int updateSoLuong(String spct, int soLuong) throws Exception {
        String sql = "UPDATE San_Pham_Chi_Tiet SET SoLuong = ? WHERE MaSanPhamChiTiet = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setString(2, spct);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(String MaSPCT, ModelSPCT spct) {
        sql = "Update San_Pham_Chi_Tiet set MaSanPham = ? , MaChatLieu = ?, MaMauSac = ?, MaKichThuoc = ? , MaXuatXu = ?, SoLuong = ?, DonGia = ?\n"
                + "	Where MaSanPhamChiTiet = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getMaSanPham());
            ps.setObject(2, spct.getMaChatLieu());
            ps.setObject(3, spct.getMaMauSac());
            ps.setObject(4, spct.getMaKichThuoc());
            ps.setObject(5, spct.getMaXuatXu());
            ps.setObject(6, spct.getSoLuong());
            ps.setObject(7, spct.getDonGia());
            ps.setObject(8, MaSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int xoa(String maSPCT) {
        sql = "update San_Pham_Chi_Tiet set TrangThai = 0 where MaSanPhamChiTiet = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import util.DBConnect;
import model.ModelHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class RepositoryHoaDon {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<ModelHoaDon> getAll() {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hd.ID, \n"
                + "    hd.MaHoaDon, \n"
                + "    kh.TenKhachHang,\n"
                + "    kh.SoDienThoai,\n"
                + "    nv.TenNhanVien,\n"
                + "    vc.TenVoucher,\n"
                + "    hd.NgayTao,\n"
                + "    hd.TongTien,\n"
                + "    hd.TongTienGiamGia,\n"
                + "	hd.TongTienSauApVoucher,\n"
                + "    hd.TrangThai\n"
                + "FROM \n"
                + "    dbo.Hoa_Don hd\n"
                + "INNER JOIN \n"
                + "    dbo.Khach_Hang kh ON hd.MaKhachHang = kh.ID\n"
                + "INNER JOIN \n"
                + "    dbo.Nhan_Vien nv ON hd.MaNhanVien = nv.MaNhanVien\n"
                + "LEFT JOIN \n"
                + "    dbo.Voucher vc ON hd.MaVoucher = vc.ID;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String maHoaDon = rs.getString(2);
                String tenKh = rs.getString(3);
                String sdt = rs.getString(4);
                String tenNhanVien = rs.getString(5);
                String tenKm = rs.getString(6);
                Date ngayTao = rs.getDate(7);
                double tongTien = rs.getDouble(8);
                double tienGiamGia = rs.getDouble(9);
                double thanhTien =rs.getDouble(10);
                boolean trangThai = rs.getBoolean(11);
                ModelHoaDon HD = new ModelHoaDon(id, maHoaDon, tenKh, sdt, tenNhanVien, tenKm, ngayTao, tongTien, tienGiamGia,thanhTien, trangThai);
                list.add(HD);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public int getIdTheoMa(String maHoaDon) {
        sql = "SELECT ID FROM Hoa_Don WHERE MaHoaDon = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ModelHoaDon> getCBB(int trangThai) {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
            + "    hd.ID, \n"
            + "    hd.MaHoaDon, \n"
            + "    kh.TenKhachHang,\n"
            + "    kh.SoDienThoai,\n"
            + "    nv.TenNhanVien,\n"
            + "    vc.TenVoucher,\n"
            + "    hd.NgayTao,\n"
            + "    hd.TongTien,\n"
            + "    hd.TongTienGiamGia,\n"
            + "    hd.TongTienSauApVoucher,\n"
            + "    hd.TrangThai\n"
            + "FROM \n"
            + "    dbo.Hoa_Don hd\n"
            + "INNER JOIN \n"
            + "    dbo.Khach_Hang kh ON hd.MaKhachHang = kh.ID\n"
            + "INNER JOIN \n"
            + "    dbo.Nhan_Vien nv ON hd.MaNhanVien = nv.MaNhanVien\n"
            + "LEFT JOIN \n"
            + "    dbo.Voucher vc ON hd.MaVoucher = vc.ID\n"
            + "WHERE \n"  // 
            + "    hd.TrangThai = ?"; 

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, trangThai);  // Thiết lập giá trị cho tham số trangThai
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String maHoaDon = rs.getString(2);
                String tenKh = rs.getString(3);
                String sdt = rs.getString(4);
                String tenNhanVien = rs.getString(5);
                String tenKm = rs.getString(6);
                Date ngayTao = rs.getDate(7);
                double tongTien = rs.getDouble(8);
                double tienGiamGia = rs.getDouble(9);
                double thanhTien =rs.getDouble(10);
                boolean trangThaiHoaDon = rs.getBoolean(11);
                ModelHoaDon HD = new ModelHoaDon(id, maHoaDon, tenKh, sdt, tenNhanVien, tenKm, ngayTao, tongTien, tienGiamGia,thanhTien, trangThaiHoaDon);
                list.add(HD);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra để debug
            return null;
        }
    }

    public ArrayList<ModelHoaDon> search(String searchValue) {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        // SQL truy vấn tìm kiếm với điều kiện LIKE
        sql = """
      SELECT 
          hd.ID, 
          hd.MaHoaDon, 
          kh.TenKhachHang,
          kh.SoDienThoai,
          nv.TenNhanVien,
          vc.TenVoucher,
          hd.NgayTao,
          hd.TongTien,
          hd.TongTienGiamGia,
          hd.TongTienSauApVoucher,    
          hd.TrangThai
      FROM 
          dbo.Hoa_Don hd
      INNER JOIN 
          dbo.Khach_Hang kh ON hd.MaKhachHang = kh.ID
      INNER JOIN 
          dbo.Nhan_Vien nv ON hd.MaNhanVien = nv.MaNhanVien
      LEFT JOIN 
          dbo.Voucher vc ON hd.MaVoucher = vc.ID
      WHERE
          hd.MaHoaDon LIKE ? OR
          kh.TenKhachHang LIKE ? OR
          kh.SoDienThoai LIKE ? OR
          nv.TenNhanVien LIKE ?;
    """;

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            // Thêm dấu % vào trước và sau giá trị tìm kiếm
            String searchTerm = "%" + searchValue + "%";

            // Đặt giá trị tìm kiếm cho tất cả các tham số
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, searchTerm);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
               int id = rs.getInt(1);
                String maHoaDon = rs.getString(2);
                String tenKh = rs.getString(3);
                String sdt = rs.getString(4);
                String tenNhanVien = rs.getString(5);
                String tenKm = rs.getString(6);
                Date ngayTao = rs.getDate(7);
                double tongTien = rs.getDouble(8);
                double tienGiamGia = rs.getDouble(9);
                double thanhTien =rs.getDouble(10);
                boolean trangThai = rs.getBoolean(11);
                ModelHoaDon HD = new ModelHoaDon(id, maHoaDon, tenKh, sdt, tenNhanVien, tenKm, ngayTao, tongTien, tienGiamGia,thanhTien, trangThai);
                list.add(HD);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RepositoryHoaDon().getAll());
    }
}

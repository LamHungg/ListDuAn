/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonBH;
import model.SanPhamBH;
import util.DBConnect;

/**
 *
 * @author ACER
 */
public class BanHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<SanPhamBH> getAllSP() {
        sql = "SELECT \n"
                + "    San_Pham.MaSanPham,\n"
                + "    San_Pham_Chi_Tiet.MaSanPhamChiTiet,\n"
                + "    San_Pham.TenSanPham,\n"
                + "    San_Pham_Chi_Tiet.SoLuong,\n"
                + "    San_Pham_Chi_Tiet.DonGia,\n"
                + "    Thuong_Hieu.TenThuongHieu,\n"
                + "    Mau_Sac.TenMauSac,\n"
                + "    Chat_Lieu.TenChatLieu,\n"
                + "    Kich_Thuoc.TenKichThuoc,\n"
                + "    Xuat_Xu.TenXuatXu\n"
                + "FROM \n"
                + "    San_Pham\n"
                + "INNER JOIN \n"
                + "    San_Pham_Chi_Tiet ON San_Pham.ID = San_Pham_Chi_Tiet.MaSanPham\n"
                + "INNER JOIN \n"
                + "    Mau_Sac ON San_Pham_Chi_Tiet.MaMauSac = Mau_Sac.ID\n"
                + "INNER JOIN \n"
                + "    Chat_Lieu ON San_Pham_Chi_Tiet.MaChatLieu = Chat_Lieu.ID\n"
                + "INNER JOIN \n"
                + "    Thuong_Hieu ON San_Pham.MaThuongHieu = Thuong_Hieu.ID\n"
                + "INNER JOIN \n"
                + "    Kich_Thuoc ON San_Pham_Chi_Tiet.MaKichThuoc = Kich_Thuoc.ID\n"
                + "INNER JOIN \n"
                + "    Xuat_Xu ON San_Pham_Chi_Tiet.MaXuatXu = Xuat_Xu.ID;";
        List<SanPhamBH> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamBH sp = new SanPhamBH(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getDouble(5), rs.getNString(6), rs.getNString(7), rs.getNString(8), rs.getNString(9), rs.getNString(10));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isPhoneNumberExist(String sdt) {
        String sql = "SELECT COUNT(*) FROM Khach_Hang WHERE SoDienThoai = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sdt);  // Gán số điện thoại vào tham số truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Nếu kết quả trả về > 0 thì số điện thoại đã tồn tại
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // Nếu không có lỗi và số điện thoại không tồn tại
    }

    public List<HoaDonBH> getAllGio() {
        List<HoaDonBH> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    San_Pham_Chi_Tiet.MaSanPhamChiTiet,\n"
                + "    San_Pham.TenSanPham,\n"
                + "    Hoa_Don_Chi_Tiet.SoLuong,\n"
                + "    Hoa_Don_Chi_Tiet.DonGia,\n"
                + "    (Hoa_Don_Chi_Tiet.SoLuong * Hoa_Don_Chi_Tiet.DonGia) AS ThanhTien\n"
                + "FROM \n"
                + "    Hoa_Don_Chi_Tiet\n"
                + "INNER JOIN \n"
                + "    San_Pham_Chi_Tiet ON Hoa_Don_Chi_Tiet.MaSanPhamChiTiet = San_Pham_Chi_Tiet.ID\n"
                + "INNER JOIN \n"
                + "    San_Pham ON San_Pham_Chi_Tiet.MaSanPham = San_Pham.ID;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBH hd = new HoaDonBH(rs.getString(1), rs.getNString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDonBH> getAllHDCho(boolean trangThai) {
        sql = "	SELECT \n"
                + "    Hoa_Don.MaHoaDon,\n"
                + "    Nhan_Vien.TenNhanVien,\n"
                + "    Hoa_Don.NgayTao,\n"
                + "    Hoa_Don.TrangThai\n"
                + "FROM \n"
                + "    Hoa_Don\n"
                + "INNER JOIN \n"
                + "    Nhan_Vien ON Hoa_Don.MaNhanVien = Nhan_Vien.MaNhanVien \n"
                + "WHERE \n"
                + "    Hoa_Don.TrangThai = ?;  ";
        List<HoaDonBH> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBH hd = new HoaDonBH(rs.getString(1), rs.getNString(2), rs.getDate(3), rs.getBoolean(4));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDonBH> getAllHDChoBangId(int idHoaDon) {
        sql = "SELECT\n"
                + "    spct.MaSanPhamChiTiet,\n"
                + "    sp.TenSanPham,\n"
                + "    hdc.SoLuong,\n"
                + "    hdc.DonGia,\n"
                + "    (hdc.SoLuong * hdc.DonGia) AS ThanhTien\n"
                + "FROM\n"
                + "    dbo.Hoa_Don_Chi_Tiet hdc\n"
                + "INNER JOIN dbo.San_Pham_Chi_Tiet spct ON hdc.MaSanPhamChiTiet = spct.ID\n"
                + "INNER JOIN dbo.San_Pham sp ON spct.MaSanPham = sp.ID\n"
                + "WHERE\n"
                + "    hdc.MaHoaDon = ? and hdc.TrangThai=1;";
        List<HoaDonBH> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHoaDon);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBH hd = new HoaDonBH(rs.getString(1), rs.getNString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
                list.add(hd);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<HoaDonBH> getAllKH() {
        sql = "SELECT TenKhachHang, SoDienThoai\n"
                + "FROM Khach_Hang;";
        List<HoaDonBH> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBH kh = new HoaDonBH(rs.getNString(1), rs.getNString(2));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public HoaDonBH getKHLeTheoMa() {
//        sql = "Select TenKhachHang from Khach_Hang\n" +
//"where MaKhachHang = 'KH0010'; ";
//        HoaDonBH khLe = null;
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            khLe = new HoaDonBH
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public int getMaKhTheoTen(String ten) {
        int id = 0;
        sql = "SELECT \n"
                + "    ID\n"
                + "FROM \n"
                + "    Khach_Hang\n"
                + "WHERE \n"
                + "    TenKhachHang = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public String getMaNVTheoTen(String ten) {
        sql = "SELECT \n"
                + "    MaNhanvien\n"
                + "FROM \n"
                + "    Nhan_Vien\n"
                + "WHERE \n"
                + "    TenNhanVien = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getNString(1);
            }
            return ten;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String gettenKhTheoid(int idKh) {
        String tenkh = null;
        sql = "select TenKhachHang from Khach_Hang where ID =?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idKh);
            rs = ps.executeQuery();
            while (rs.next()) {
                tenkh = rs.getNString(1);
            }
            return tenkh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getSDTKhTheoid(int idKh) {
        String sdt = null;
        sql = "select SoDienThoai from Khach_Hang where ID =?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idKh);
            rs = ps.executeQuery();
            while (rs.next()) {
                sdt = rs.getNString(1);
            }
            return sdt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public double getGiamTheoTen(String tenKM) {
        double giam = 0;
        sql = "SELECT MucGiamGia \n"
                + "FROM Voucher \n"
                + "WHERE TenVoucher  = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setNString(1, tenKM);
            rs = ps.executeQuery();
            while (rs.next()) {
                giam = rs.getDouble(1);
            }
            return giam;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int getIdTheoMaHD(String maHD) {
        int id = 0;
        sql = "SELECT      ID\n"
                + "            FROM \n"
                + "                   Hoa_Don\n"
                + "            WHERE \n"
                + "                  MaHoaDon = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int getIdKhTheoIDHD(int idHD) {
        int id = 0;
        sql = "select MaKhachHang from Hoa_Don where ID =?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int getIdTheoMaSPCt(String maSpct) {
        int id = 0;
        sql = "SELECT      ID\n"
                + "          FROM \n"
                + "                   San_Pham_Chi_Tiet\n"
                + "            WHERE \n"
                + "                  MaSanPhamChiTiet = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maSpct);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int getIdTheoTenVc(String tenVc) {
        int id = 0;
        sql = "SELECT ID \n"
                + "FROM Voucher \n"
                + "WHERE TenVoucher  = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tenVc);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public void addHoaDon(String maNhanVien) {
        String sql = "INSERT INTO Hoa_Don (MaNhanVien, MaVoucher, MaKhachHang, NgayTao, TongTien, TongTienGiamGia, TongTienSauApVoucher, TienKhachTra, TrangThai)\n"
                + "VALUES \n"
                + "(?, 1, 1, GETDATE(), 0, 0, 0, 0, 0);";
        try {
            con = DBConnect.getConnection(); // Kết nối CSDL
            ps = con.prepareStatement(sql); // Chuẩn bị câu lệnh SQL
            ps.setString(1, maNhanVien); // Gán giá trị cho tham số `?`
            ps.execute(); // Thực thi câu lệnh
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi nếu xảy ra
        }
    }

    public void addHoaDonCoKh(HoaDonBH hd) {
        String sql = "INSERT INTO Hoa_Don (MaNhanVien, MaVoucher, MaKhachHang, NgayTao, TongTien, TongTienGiamGia, TongTienSauApVoucher, TienKhachTra, TrangThai)\n"
                + "VALUES \n"
                + "(?, 1, ?, GETDATE(), 0, 0, 0, 0, 0);";
        try {
            con = DBConnect.getConnection(); // Kết nối CSDL
            ps = con.prepareStatement(sql); // Chuẩn bị câu lệnh SQL
            ps.setString(1, hd.getMaNV());
            ps.setInt(2, hd.getIDKhachHang());
// Gán giá trị cho tham số `?`
            ps.execute(); // Thực thi câu lệnh
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi nếu xảy ra
        }
    }

    public int updateSP(SanPhamBH sp, String maCT) {
        sql = "update San_Pham_Chi_Tiet set SoLuong =? where MaSanPhamChiTiet=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getSoLuong());
            ps.setObject(2, maCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateHDCt(HoaDonBH hd, int maHoaD, int maCT) {
        sql = "UPDATE Hoa_Don_Chi_Tiet  \n"
                + "SET SoLuong = ? \n"
                + "WHERE MaHoaDon = ? AND MaSanPhamChiTiet = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getSoLuong());
            ps.setObject(2, maHoaD);
            ps.setObject(3, maCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateHoaDon(HoaDonBH bh, int idHoaDon) {
        sql = "UPDATE Hoa_Don\n"
                + "SET MaNhanVien = ?, \n"
                + "    MaVoucher = ?, \n"
                + "    MaKhachHang = ?, \n"
                + "    TongTien = ?, \n"
                + "    TongTienGiamGia = ?, \n"
                + "    TongTienSauApVoucher = ?, \n"
                + "    TienKhachTra = ?, \n"
                + "    TrangThai = 1\n"
                + "WHERE ID = ?;";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, bh.getMaNV());
            ps.setObject(2, bh.getIDKhuyenMai());
            ps.setObject(3, bh.getIDKhachHang());
            ps.setObject(4, bh.getTongTien());
            ps.setObject(5, bh.getTienGiamGia());
            ps.setObject(6, bh.getThanhTien());
            ps.setObject(7, bh.getTienKhachtra());
            ps.setObject(8, idHoaDon);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteHDCt(boolean trangThai, int maHd, int maSpct) {
        sql = "update Hoa_Don_Chi_Tiet set TrangThai =? where MaHoaDon = ? AND MaSanPhamChiTiet = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThai);
            ps.setObject(2, maHd);
            ps.setObject(3, maSpct);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void addHDCT(HoaDonBH bh) {
        String sql = "insert into Hoa_Don_Chi_Tiet(MaHoaDon,MaSanPhamChiTiet,SoLuong,DonGia,TrangThai)\n"
                + "values (?,?,?,?,1);";

        try {
            con = DBConnect.getConnection(); // Kết nối CSDL
            ps = con.prepareStatement(sql);
            ps.setInt(1, bh.getIdHoaDon());
            ps.setInt(2, bh.getIdMaSPCT());
            ps.setInt(3, bh.getSoLuong());
            ps.setDouble(4, bh.getGia());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countHoaDonChuaThanhToan() {
        String sql = "SELECT COUNT(*) FROM Hoa_Don WHERE TrangThai = 0";
        int count = 0;
        try {
            con = DBConnect.getConnection(); // Kết nối CSDL
            ps = con.prepareStatement(sql); // Chuẩn bị câu lệnh SQL
            rs = ps.executeQuery(); // Thực thi truy vấn
            if (rs.next()) {
                count = rs.getInt(1); // Lấy kết quả đếm
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void addKHang(HoaDonBH bh) {
        String sqlCheck = "SELECT COUNT(*) FROM Khach_Hang WHERE SoDienThoai = ?";
        String sqlInsert = "INSERT INTO Khach_Hang (TenKhachHang, SoDienThoai) VALUES (?, ?);";
        try {
            con = DBConnect.getConnection();

            // Kiểm tra nếu số điện thoại đã tồn tại
            PreparedStatement psCheck = con.prepareStatement(sqlCheck);
            psCheck.setString(1, bh.getSDT());
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Số điện thoại đã tồn tại. Không thể thêm khách hàng.");
                return;
            }

            // Nếu không tồn tại, thực hiện thêm mới
            PreparedStatement psInsert = con.prepareStatement(sqlInsert);
            psInsert.setString(1, bh.getTenKh());
            psInsert.setString(2, bh.getSDT());
            psInsert.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getKM() {
        sql = "	select TenVoucher from Voucher where TrangThai =1; ";
        List<String> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String km = rs.getNString(1);
                list.add(km);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSLTheoID(String maCT) {
        sql = "select SoLuong from San_Pham_Chi_Tiet where MaSanPhamChiTiet=?";
        String soLuong = "";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getString(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamBH> TimKiemTableSanPham(String a) {
        sql = """
         SELECT 
             San_Pham_Chi_Tiet.MaSanPhamChiTiet, 
             San_Pham.MaSanPham, 
             San_Pham.TenSanPham,  
             San_Pham_Chi_Tiet.SoLuong, 
             San_Pham_Chi_Tiet.DonGia, 
             Thuong_Hieu.TenThuongHieu, 
             Mau_Sac.TenMauSac, 
             Chat_Lieu.TenChatLieu, 
             Kich_Thuoc.TenKichThuoc, 
             Xuat_Xu.TenXuatXu
         FROM 
             San_Pham 
         INNER JOIN 
             San_Pham_Chi_Tiet ON San_Pham.ID = San_Pham_Chi_Tiet.MaSanPham 
         INNER JOIN 
             Mau_Sac ON San_Pham_Chi_Tiet.MaMauSac = Mau_Sac.ID 
         INNER JOIN 
             Chat_Lieu ON San_Pham_Chi_Tiet.MaChatLieu = Chat_Lieu.ID 
         INNER JOIN 
             Thuong_Hieu ON San_Pham.MaThuongHieu = Thuong_Hieu.ID 
         INNER JOIN 
             Kich_Thuoc ON San_Pham_Chi_Tiet.MaKichThuoc = Kich_Thuoc.ID
         INNER JOIN 
             Xuat_Xu ON  San_Pham_Chi_Tiet.MaKichThuoc= Xuat_Xu.ID
         WHERE 
             San_Pham.MaSanPham LIKE ? OR
             San_Pham_Chi_Tiet.MaSanPhamChiTiet LIKE ? OR
             San_Pham.TenSanPham LIKE ? OR 
             Mau_Sac.TenMauSac LIKE ? OR 
             Chat_Lieu.TenChatLieu LIKE ? OR 
             Kich_Thuoc.TenKichThuoc LIKE ? OR 
             Thuong_Hieu.TenThuongHieu LIKE ? OR
             San_Pham_Chi_Tiet.SoLuong LIKE ? OR
             San_Pham_Chi_Tiet.DonGia LIKE ? OR
             Xuat_Xu.TenXuatXu LIKE ?;
          """;

        List<SanPhamBH> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            String searchValue = "%" + a + "%";

            for (int i = 1; i <= 10; i++) {
                ps.setString(i, searchValue);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamBH sp = new SanPhamBH(
                        rs.getString(1), rs.getString(2), rs.getNString(3), rs.getInt(4), rs.getDouble(5),
                        rs.getString(6), rs.getNString(7), rs.getString(8), rs.getString(9), rs.getNString(10)
                );
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        BanHangService bh = new BanHangService();

        System.out.println(bh.getAllGio());
    }
}

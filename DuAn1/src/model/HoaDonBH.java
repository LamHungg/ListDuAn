/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class HoaDonBH {

    private String maHDon;
    private int idHoaDon;
    private int idMaSPCT;
    private String maNV;
    private String tenNhanVien;
    private String tenKh;
    private String SDT;
    private double tongTien;
    private String tenKM;
    private double tienGiamGia;
    private double thanhTien;
    private double tienKhachtra;
    private double tienThua;
    private String MaKH;
    private String MaKM;
    private Date ngayTao;
    private boolean trangThai;
    private String maCTSP;
    private String tenSP;
    private int soLuong;
    private double gia;
    private int IDKhachHang;
    private int IDKhuyenMai;

    public HoaDonBH(int idHoaDon, int idMaSPCT, int soLuong, double gia, boolean trangThai) {
        this.idHoaDon = idHoaDon;
        this.idMaSPCT = idMaSPCT;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public HoaDonBH(int soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDonBH(String maNV, int IDKhachHang) {
        this.maNV = maNV;
        this.IDKhachHang = IDKhachHang;
    }
    

    public HoaDonBH(String maNV, int IDKhuyenMai, int IDKhachHang, double tongTien, double tienGiamGia, double thanhTien, double tienKhachtra) {
        this.maNV = maNV;
        this.IDKhuyenMai = IDKhuyenMai;
        this.IDKhachHang = IDKhachHang;
        this.tongTien = tongTien;
        this.tienGiamGia = tienGiamGia;
        this.thanhTien = thanhTien;
        this.tienKhachtra = tienKhachtra;
    }

    public int getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(int IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public int getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(int IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public HoaDonBH() {
    }

    public HoaDonBH(String maNV, boolean trangThai) {
        this.maNV = maNV;
        this.trangThai = trangThai;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdMaSPCT() {
        return idMaSPCT;
    }

    public void setIdMaSPCT(int idMaSPCT) {
        this.idMaSPCT = idMaSPCT;
    }

    public HoaDonBH(String maHDon, String tenNhanVien, Date ngayTao, boolean trangThai) {
        this.maHDon = maHDon;
        this.tenNhanVien = tenNhanVien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDonBH(String maHDon, String tenNhanVien, String tenKh, String SDT, double tongTien, String tenKM, double tienGiamGia, double thanhTien, double tienKhachtra, double tienThua) {
        this.maHDon = maHDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKh = tenKh;
        this.SDT = SDT;
        this.tongTien = tongTien;
        this.tenKM = tenKM;
        this.tienGiamGia = tienGiamGia;
        this.thanhTien = thanhTien;
        this.tienKhachtra = tienKhachtra;
        this.tienThua = tienThua;
    }

    public HoaDonBH(String maHDon, String tenNhanVien, String tenKh, String tenKM, Date ngayTao, boolean trangThai) {
        this.maHDon = maHDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKh = tenKh;
        this.tenKM = tenKM;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDonBH(String maHDon, String tenNhanVien, double tongTien, String MaKH, String MaKM, Date ngayTao, boolean trangThai) {
        this.maHDon = maHDon;
        this.tenNhanVien = tenNhanVien;
        this.tongTien = tongTien;
        this.MaKH = MaKH;
        this.MaKM = MaKM;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDonBH(String maCTSP, String tenSP, int soLuong, double gia, double tongTien) {
        this.maCTSP = maCTSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tongTien = tongTien;
    }

    public HoaDonBH(String tenKh, String SDT) {
        this.tenKh = tenKh;
        this.SDT = SDT;
    }

    public HoaDonBH(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getMaHDon() {
        return maHDon;
    }

    public void setMaHDon(String maHDon) {
        this.maHDon = maHDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public double getTienGiamGia() {
        return tienGiamGia;
    }

    public void setTienGiamGia(double tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getTienKhachtra() {
        return tienKhachtra;
    }

    public void setTienKhachtra(double tienKhachtra) {
        this.tienKhachtra = tienKhachtra;
    }

    public double getTienThua() {
        return tienThua;
    }

    public void setTienThua(double tienThua) {
        this.tienThua = tienThua;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "HoaDonBH{" + "maHDon=" + maHDon + ", tenNhanVien=" + tenNhanVien + ", tenKh=" + tenKh + ", SDT=" + SDT + ", tongTien=" + tongTien + ", tenKM=" + tenKM + ", tienGiamGia=" + tienGiamGia + ", thanhTien=" + thanhTien + ", tienKhachtra=" + tienKhachtra + ", tienThua=" + tienThua + ", MaKH=" + MaKH + ", MaKM=" + MaKM + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + ", maCTSP=" + maCTSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", gia=" + gia + '}';
    }

}

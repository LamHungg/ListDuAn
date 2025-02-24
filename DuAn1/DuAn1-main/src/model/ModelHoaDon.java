/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ModelHoaDon {

    private int idMaHoaDon;
    private String maHoaDon;
    private String maKhachHang;
    private String tenKhachHang;
    private String sdt;
    private String maNhanVien;
    private String tenNhanVien;
    private String maKm;
    private String tenKm;
    private Date ngayTao;
    private double tongTien;
    private double tienGiamGia;
    private double tienKhachTra;
    private boolean trangThai;

    public ModelHoaDon() {
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public ModelHoaDon(int idMaHoaDon, String maHoaDon, String tenKhachHang, String sdt, String tenNhanVien, String tenKm, Date ngayTao, double tongTien, double tienGiamGia,double tienKhachTra, boolean trangThai) {
        this.idMaHoaDon = idMaHoaDon;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.tenNhanVien = tenNhanVien;
        this.tenKm = tenKm;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.tienGiamGia = tienGiamGia;
        this.tienKhachTra =tienKhachTra;
        this.trangThai = trangThai;
    }

    
    
    public ModelHoaDon(String maHoaDon, String tenNhanVien, String tenKm, String tenKhachHang, Date ngayTao, double tongTien, double tienGiamGia, double tienKhachTra, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKm = tenKm;
        this.tenKhachHang = tenKhachHang;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.tienGiamGia = tienGiamGia;
        this.tienKhachTra = tienKhachTra;
        this.trangThai = trangThai;
    }

    public int getIdMaHoaDon() {
        return idMaHoaDon;
    }

    public void setIdMaHoaDon(int idMaHoaDon) {
        this.idMaHoaDon = idMaHoaDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaKm() {
        return maKm;
    }

    public void setMaKm(String maKm) {
        this.maKm = maKm;
    }

    public String getTenKm() {
        return tenKm;
    }

    public void setTenKm(String tenKm) {
        this.tenKm = tenKm;
    }

    public double getTienGiamGia() {
        return tienGiamGia;
    }

    public void setTienGiamGia(double tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public double getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(double tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}

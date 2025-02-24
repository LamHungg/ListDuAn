/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ModelHDCT {

   
    private String maHDCT;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;
    private String tenChatLieu;
    private String tenMauSac;
    private String tenKichThuoc;
    private String xuatXu;
    

    public ModelHDCT() {
    }

    public ModelHDCT( String maHDCT, String maSanPham, String tenSanPham, int soLuong, double donGia, String tenChatLieu, String tenMauSac, String tenKichThuoc, String xuatXu) {
   
        this.maHDCT = maHDCT;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tenChatLieu = tenChatLieu;
        this.tenMauSac = tenMauSac;
        this.tenKichThuoc = tenKichThuoc;
        this.xuatXu = xuatXu;
    }

  

  

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

   
public Object[] toDataRow(){
        return new Object[]{
           this.maHDCT,this.maSanPham,this.tenSanPham,this.soLuong,this.donGia,
            this.tenChatLieu,this.tenMauSac,this.tenKichThuoc,this.xuatXu
        };
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class SanPhamBH {
    private String maSP;
    private String maSPCT;
    private String tenSp;
    private int soLuong;
    private double donGia;
    private String thuongThieu;
    private String mauSac;
    private String chatLieu;
    private String size;
    private String xuatXu;

    public SanPhamBH() {
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public SanPhamBH(String maSP, String maSPCT, String tenSp, int soLuong, double donGia, String thuongThieu, String mauSac, String chatLieu, String size, String xuatXu) {
        this.maSP = maSP;
        this.maSPCT = maSPCT;
        this.tenSp = tenSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thuongThieu = thuongThieu;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.size = size;
        this.xuatXu = xuatXu;
    }

    

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
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

    public String getThuongThieu() {
        return thuongThieu;
    }

    public void setThuongThieu(String thuongThieu) {
        this.thuongThieu = thuongThieu;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    @Override
    public String toString() {
        return "SanPhamBH{" + "maSP=" + maSP + ", maSPCT=" + maSPCT + ", tenSp=" + tenSp + ", soLuong=" + soLuong + ", donGia=" + donGia + ", thuongThieu=" + thuongThieu + ", mauSac=" + mauSac + ", chatLieu=" + chatLieu + ", size=" + size + ", xuatXu=" + xuatXu + '}';
    }

  
    
  
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ModelSanPham {

    private int id;
    private String maSanPham;
    private String tenSanPham;
    private Integer maThuongHieu;
    private String tenTH;

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public Integer getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(Integer maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }
    private String moTa;
    private int soLuong;
    private boolean trangThai;

    public ModelSanPham() {
    }

    public ModelSanPham(String maSanPham, String tenSanPham, String moTa, int soLuong, boolean trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public ModelSanPham(String maSanPham, String tenSanPham, Integer maThuongHieu, String moTa, int soLuong, boolean trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maThuongHieu = maThuongHieu;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public ModelSanPham(String maSanPham, String tenSanPham, String tenTH, String moTa, int soLuong, boolean trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenTH = tenTH;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.maSanPham, this.tenSanPham, this.moTa, this.soLuong,
            this.trangThai == true ? "Ngừng Kinh Doanh" : "Còn Kinh Doanh"
        };

    }

    @Override
    public String toString() {
        return "ModelSanPham{" + "id=" + id + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", maThuongHieu=" + maThuongHieu + ", moTa=" + moTa + ", soLuong=" + soLuong + ", trangThai=" + trangThai + '}';
    }

}

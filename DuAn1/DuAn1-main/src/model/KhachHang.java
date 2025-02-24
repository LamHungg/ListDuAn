/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LONG PHI
 */
public class KhachHang {

    private int id;
    private String MaKH;
    private String TenKH;
    private boolean GioiTinh;
    private String DiaChi;
    private String SDT;
    private String Email;
    private boolean TrangThai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public KhachHang() {
    }

    public KhachHang(int id, String MaKH, String TenKH, boolean GioiTinh, String DiaChi, String SDT, String Email, boolean TrangThai) {
        this.id = id;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.TrangThai = TrangThai;
    }

    public KhachHang(String MaKH, String TenKH, boolean GioiTinh, String DiaChi, String SDT, String Email, boolean TrangThai) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.TrangThai = TrangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            id, MaKH, TenKH, GioiTinh? "Nam" : "Nữ", DiaChi, SDT, Email, TrangThai? "Đang hoạt động" : "Ngưng hoạt động"
        };
    }
}

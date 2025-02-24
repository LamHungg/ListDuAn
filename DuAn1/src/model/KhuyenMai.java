package model;

import java.util.Date;

/**
 *
 * @author LONG PHI
 */
public class KhuyenMai {
    private int id;
    private String MaVoucher;
    private String TenVoucher;
    private float MucGiamGia;
    private Date ThoiGianBatDau; 
    private Date ThoiGianKetThuc;
    private String Mota;
    private boolean TrangThai;

    public KhuyenMai(int id, String MaVoucher, String TenVoucher, float MucGiamGia, Date ThoiGianBatDau, Date ThoiGianKetThuc, String Mota, boolean TrangThai) {
        this.id = id;
        this.MaVoucher = MaVoucher;
        this.TenVoucher = TenVoucher;
        this.MucGiamGia = MucGiamGia;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.Mota = Mota;
        this.TrangThai = TrangThai;
    }

    public KhuyenMai(String MaVoucher, String TenVoucher, float MucGiamGia, Date ThoiGianBatDau, Date ThoiGianKetThuc, String Mota, boolean TrangThai) {
        this.MaVoucher = MaVoucher;
        this.TenVoucher = TenVoucher;
        this.MucGiamGia = MucGiamGia;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.Mota = Mota;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public String getTenVoucher() {
        return TenVoucher;
    }

    public void setTenVoucher(String TenVoucher) {
        this.TenVoucher = TenVoucher;
    }

    public float getMucGiamGia() {
        return MucGiamGia;
    }

    public void setMucGiamGia(float MucGiamGia) {
        this.MucGiamGia = MucGiamGia;
    }

    public Date getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(Date ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    

    public KhuyenMai() {
    }

    // toDataRow() method to display data
    public Object[] toDataRow() {
        return new Object[]{
            id, MaVoucher, TenVoucher,  
            MucGiamGia,  
            ThoiGianBatDau, ThoiGianKetThuc, 
            Mota, TrangThai ? "Đang Diễn Ra" : "Kết Thúc", 
        };
    }

}

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanVien {
    private int id;                    // ID tự tăng
    private String maNhanVien;         // Mã nhân viên
    private String tenNhanVien;        // Tên nhân viên
    private boolean gioiTinh;          // true: Nam, false: Nữ
    private String soDienThoai;        // Số điện thoại
    private String diaChi;             // Địa chỉ
    private String email;              // Email
    private String cccd;               // CCCD
    private Date ngayVaoLam;           // Ngày vào làm
    private boolean chucVu;            // true: Quản lý, false: Nhân viên
    private boolean trangThai;         // true: Hoạt động, false: Không hoạt động
    private String password;           // Mật khẩu

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    // Constructor không tham số (mặc định)
    public NhanVien() {
        this.ngayVaoLam = new Date(); // Gán ngày vào làm mặc định là ngày hiện tại
        this.password = "1";          // Mật khẩu mặc định là "1"
    }

    // Constructor đầy đủ
    public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, String soDienThoai,
                    String diaChi, String email, String cccd, Date ngayVaoLam,
                    boolean chucVu, boolean trangThai, String password) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.cccd = cccd;
        this.ngayVaoLam = (ngayVaoLam != null) ? ngayVaoLam : new Date(); // Gán ngày hiện tại nếu null
        this.chucVu = chucVu;
        this.trangThai = trangThai;
        this.password = (password != null && !password.isEmpty()) ? password : "1"; // Mật khẩu mặc định nếu null
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getEmail() {
        return email;
    }

    public String getCccd() {
        return cccd;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public String getPassword() {
        return password;
    }

    // Setter
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Phương thức trả về dữ liệu để hiển thị trong bảng (JTable)
    public Object[] toRowTable() {
        return new Object[]{
            maNhanVien,
            tenNhanVien,
            gioiTinh ? "Nam" : "Nữ",
            soDienThoai,
            diaChi,
            email,
            cccd,
            dateFormatter.format(ngayVaoLam),
            chucVu ? "Quản Lý" : "Nhân Viên",
            trangThai ? "Đang Làm" : "Nghỉ Việc",
            password
        };
    }

    // Hiển thị thông tin đối tượng dưới dạng chuỗi
    @Override
    public String toString() {
        return "NhanVien{" +
                "id=" + id +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", gioiTinh=" + (gioiTinh ? "Nam" : "Nữ") +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", email='" + email + '\'' +
                ", cccd='" + cccd + '\'' +
                ", ngayVaoLam=" + dateFormatter.format(ngayVaoLam) +
                ", chucVu=" + (chucVu ? "Quản lý" : "Nhân viên") +
                ", trangThai=" + (trangThai ? "Đang Làm" : "Nghỉ Việc") +
                ", password='" + password + '\'' +
                '}';
    }
}

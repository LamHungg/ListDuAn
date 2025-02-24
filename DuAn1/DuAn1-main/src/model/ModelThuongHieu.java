package model;

public class ModelThuongHieu {

    private String maThuongHieu;

    private String tenThuongHieu;

    private Boolean trangThai;

    public ModelThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    @Override
    public String toString() {
        return "ModelThuongHieu{" + "maThuongHieu=" + maThuongHieu + ", tenThuongHieu=" + tenThuongHieu + ", trangThai=" + trangThai + '}';
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public ModelThuongHieu(String maThuongHieu, String tenThuongHieu, Boolean trangThai) {
        this.maThuongHieu = maThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.trangThai = trangThai;
    }

    public ModelThuongHieu() {
    }

}

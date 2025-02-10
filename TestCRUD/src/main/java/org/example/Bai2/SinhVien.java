package org.example.Bai2;

public class SinhVien {
        private int maSV;
        private String tenSV;
        private String lop;
        private String khoaHoc;
        private String monHoc;

        public SinhVien(int maSV, String tenSV, String lop, String khoaHoc, String monHoc) {
            this.maSV = maSV;
            this.tenSV = tenSV;
            this.lop = lop;
            this.khoaHoc = khoaHoc;
            this.monHoc = monHoc;
        }

        public int getMaSV() { return maSV; }
        public String getTenSV() { return tenSV; }
        public String getLop() { return lop; }
        public String getKhoaHoc() { return khoaHoc; }
        public String getMonHoc() { return monHoc; }

}

package org.example.Bai2;

import java.util.HashMap;
import java.util.Map;

public class SinhVienService {
        public Map<Integer, SinhVien> danhSachSinhVien = new HashMap<>();

        public void themSinhVien(SinhVien sv) {
            if (sv == null) throw new IllegalArgumentException("Sinh viên không được null");
            if (danhSachSinhVien.containsKey(sv.getMaSV()))
                throw new IllegalArgumentException("Mã sinh viên đã tồn tại");
            danhSachSinhVien.put(sv.getMaSV(), sv);
        }

        public SinhVien timSinhVien(int maSV) {
            return danhSachSinhVien.get(maSV);
        }

        public void xoaTatCa() {
            danhSachSinhVien.clear();
        }
}

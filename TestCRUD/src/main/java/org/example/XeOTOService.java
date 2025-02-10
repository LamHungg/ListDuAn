package org.example;

import java.util.ArrayList;
import java.util.List;

public class XeOTOService {
    private final List<XeOTO> danhSachXe = new ArrayList<>();

    // Thêm xe mới
    public void addXeOTO(XeOTO xe) {
        if (xe.getGia() < 0) {
            throw new IllegalArgumentException("Giá xe không thể âm");
        }
        if (xe.getTen() == null || xe.getTen().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên xe không thể trống");
        }
        for (XeOTO x : danhSachXe) {
            if (x.getId() == xe.getId()) {
                throw new IllegalArgumentException("ID xe đã tồn tại");
            }
        }
        danhSachXe.add(xe);
    }

    // Cập nhật xe
    public void updateXeOTO(XeOTO xe, int id) {
        for (XeOTO x : danhSachXe) {
            if (x.getId() == id) {
                x.setTen(xe.getTen());
                x.setGia(xe.getGia());
                x.setGhiChu(xe.getGhiChu());
                return;
            }
        }
        throw new IllegalArgumentException("Xe không tồn tại");
    }

    // Xóa xe theo ID
    public void deleteXeOTO(int id) {
        boolean removed = danhSachXe.removeIf(xe -> xe.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Xe không tồn tại");
        }
    }

    // Tìm kiếm xe theo ID
    public XeOTO searchXeOTO(int id) {
        return danhSachXe.stream().filter(xe -> xe.getId() == id).findFirst().orElse(null);
    }

    // Lấy toàn bộ danh sách xe
    public List<XeOTO> getAll() {
        return danhSachXe;
    }
}


	-- Step 1: Create Database
	--CREATE DATABASE QuanLyBanHang;
	GO
	--drop database QuanLyBanHang
	-- Step 2: Use Database
	USE QuanLyBanHang;

	-- Drop existing tables if they exist to avoid conflicts

	go
	-- Table Creation
	-- Bảng Thuong_Hieu
	CREATE TABLE Thuong_Hieu (
		ID INT PRIMARY KEY IDENTITY(1,1), -- ID tự tăng làm khóa chính
		MaThuongHieu AS 'TH' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		TenThuongHieu NVARCHAR(100) NOT NULL,
		TrangThai BIT
	);
	go
	-- Bảng Chat_Lieu
	CREATE TABLE Chat_Lieu (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaChatLieu AS 'CL' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		TenChatLieu NVARCHAR(100) NOT NULL,
		TrangThai BIT
	);
	go
	-- Bảng Mau_Sac
	CREATE TABLE Mau_Sac (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaMauSac AS 'MS' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		TenMauSac NVARCHAR(100) NOT NULL,
		TrangThai BIT
	);
	go
	-- Bảng Kich_Thuoc
	CREATE TABLE Kich_Thuoc (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaKichThuoc AS 'KT' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		TenKichThuoc NVARCHAR(100) NOT NULL,
		TrangThai BIT
	);
	go
	CREATE TABLE Xuat_Xu (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaXuatXu AS 'XX' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		TenXuatXu NVARCHAR(100) NOT NULL,
		TrangThai BIT
	);
	go
	-- Bảng San_Pham
	CREATE TABLE San_Pham (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaSanPham AS 'SP' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		MaThuongHieu int FOREIGN KEY(MaThuongHieu) REFERENCES Thuong_Hieu(ID),
		SoLuong int ,
		TenSanPham NVARCHAR(100) NOT NULL,
		MoTa NVARCHAR(255),
		TrangThai BIT
	);
	go
	-- Bảng San_Pham_Chi_Tiet
	CREATE TABLE San_Pham_Chi_Tiet (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaSanPhamChiTiet AS 'SPCT' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		MaSanPham int FOREIGN KEY(MaSanPham) REFERENCES San_Pham(ID),
		MaChatLieu int FOREIGN KEY(MaChatLieu) REFERENCES Chat_Lieu(ID),
		MaMauSac int FOREIGN KEY(MaMauSac) REFERENCES Mau_Sac(ID),
		MaKichThuoc int FOREIGN KEY(MaKichThuoc) REFERENCES Kich_Thuoc(ID),
		MaXuatXu int FOREIGN KEY(MaXuatXu) REFERENCES Xuat_Xu(ID),
		SoLuong INT,
		DonGia DECIMAL(18, 2),
		TrangThai BIT
	);
	go
	-- Bảng Nhan_Vien
	-- Tạo bảng Nhan_Vien
	CREATE TABLE Nhan_Vien (
		ID INT PRIMARY KEY IDENTITY(1,1),           -- Tự động tăng
		MaNhanVien NVARCHAR(10) UNIQUE,            -- Mã nhân viên duy nhất
		TenNhanVien NVARCHAR(100) NOT NULL,        -- Tên nhân viên (bắt buộc)
		GioiTinh BIT,                              -- Giới tính (1: Nam, 0: Nữ)
		SoDienThoai NVARCHAR(15),                  -- Số điện thoại
		DiaChi NVARCHAR(255),                      -- Địa chỉ
		Email NVARCHAR(100),                       -- Email
		CCCD NVARCHAR(12),                         -- Căn cước công dân
		NgayVaoLam DATE,                           -- Ngày vào làm
		Password NVARCHAR(100) DEFAULT '1',        -- Mật khẩu mặc định
		 -- Ngày sinh mặc định
		ChucVu BIT,   
		TrangThai bit-- Chức vụ (1: Quản lý, 0: Nhân viên)
	);
	GO
	-- Bảng Khach_Hang
	CREATE TABLE Khach_Hang (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaKhachHang AS 'KH' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		TenKhachHang NVARCHAR(100) NOT NULL,
		GioiTinh BIT,
		DiaChi NVARCHAR(255),
		SoDienThoai NVARCHAR(15),
		Email NVARCHAR(100),
		TrangThai BIT
	);

	go
	-- Bảng Login
	CREATE TABLE Voucher (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaVoucher AS 'VC' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED,
		TenVoucher NVARCHAR(100),
		MucGiamGia DECIMAL(18),
		ThoiGianBatDau DATE,
		ThoiGianKetThuc DATE,
		MoTa NVARCHAR(255),
		TrangThai BIT,
	);
	  go

	  create TABLE Hoa_Don (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaHoaDon AS 'HD' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED,
		MaNhanVien NVARCHAR(10) FOREIGN KEY REFERENCES Nhan_Vien(MaNhanVien),
		MaVoucher int FOREIGN KEY(MaVoucher) REFERENCES Voucher(ID),
		MaKhachHang int FOREIGN KEY (MaKhachHang) REFERENCES Khach_Hang(ID),
		NgayTao DATE NOT NULL DEFAULT GETDATE(),
		TongTien DECIMAL(18),
		TongTienGiamGia DECIMAL(18),
		TongTienSauApVoucher DECIMAL(18),
		TienKhachTra DECIMAL(18),
		TrangThai BIT,
	);
	go
	-- Bảng Hoa_Don_Chi_Tiet
	CREATE TABLE Hoa_Don_Chi_Tiet (
		ID INT PRIMARY KEY IDENTITY(1,1),
		MaHoaDonChiTiet AS 'HDCT' + RIGHT('0000' + CAST(ID AS VARCHAR(4)), 4) PERSISTED ,
		MaHoaDon int FOREIGN KEY(MaHoaDon) REFERENCES Hoa_Don(ID),
		MaSanPhamChiTiet int FOREIGN KEY(MaSanPhamChiTiet) REFERENCES San_Pham_Chi_Tiet(ID),
		SoLuong INT NOT NULL,
		DonGia DECIMAL(18),
		TrangThai BIT
	);
	go
-- Insert Sample Data
-- (Insert data here as done in your original example but adjusting for the new foreign key references)

INSERT INTO Thuong_Hieu (TenThuongHieu, TrangThai)
VALUES 
('Adidas', 1),
('Nike', 1),
('Puma', 1),
('Reebok', 1);
go
INSERT INTO Chat_Lieu (TenChatLieu, TrangThai)
VALUES 
('Cotton', 1),
('Polyester', 1),
('Denim', 1),
('Leather', 1);
go
INSERT INTO Mau_Sac (TenMauSac, TrangThai)
VALUES 
(N'Đỏ', 1),
(N'Xanh', 1),
(N'Vàng', 1),
(N'Đen', 1);
go
INSERT INTO Kich_Thuoc (TenKichThuoc, TrangThai)
VALUES 
('S', 1),
('M', 1),
('L', 1),
('XL', 1);
go
INSERT INTO Xuat_Xu (TenXuatXu, TrangThai)
VALUES 
(N'Việt Nam', 1),
(N'Trung Quốc', 1),
(N'Thái Lan', 1),
(N'Mỹ', 1);
go
INSERT INTO San_Pham (MaThuongHieu, TenSanPham,SoLuong, MoTa, TrangThai)
VALUES 
(1, N'Áo thun Adidas',100, N'Áo thun Adidas nam thoáng mát', 1),
(2, N'Áo thun Nike', 100,N'Áo thun Nike thể thao cao cấp', 1),
(3, N'Áo khoác Adidas', 200,N'Áo khoác Adidas thời trang', 1),
(4, N'Áo thể thao Nike', 150,N'Áo thể thao Nike chất lượng cao', 1);

go
-- Thêm nhiều biến thể cho các sản phẩm chi tiết dựa trên sản phẩm hiện có
INSERT INTO San_Pham_Chi_Tiet (MaSanPham, MaChatLieu, MaMauSac, MaKichThuoc, MaXuatXu, SoLuong, DonGia, TrangThai)
VALUES 
(1, 1, 1, 1, 1, 50, 500000, 1),   -- Áo thun Adidas, Đỏ, S
(1, 2, 2, 2, 1, 50, 520000, 1),   -- Áo thun Adidas, Xanh, M
(2, 2, 3, 3, 2, 50, 300000, 1),   -- Áo thun Nike, Vàng, L
(2, 2, 2, 2, 2, 50, 300000, 1), 
(3, 3, 4, 4, 3, 100, 600000, 1),  -- Áo khoác Adidas, Đen, XL
(4, 4, 4, 4, 4, 75, 400000, 1);   -- Áo thể thao Nike, Đen, XL


go
-- Thêm trạng thái cho nhân viên
INSERT INTO Nhan_Vien (MaNhanVien, TenNhanVien, GioiTinh, SoDienThoai, DiaChi, Email, CCCD, NgayVaoLam, ChucVu, TrangThai)
VALUES 
('NV001', N'Đặng Lâm Hùng', 1, '0912123456', N'Hà Nội', 'hungdlph50551@gmail.com', '123456789012', '2023-01-01', 1, 1),
('NV002', N'Quản Đắc Quang', 0, '0913456789', N'Hà Nội', 'quangqdph51841@gmail.com', '234567890123', '2023-02-01', 0, 1),
('NV003', N'Hoàng Văn Long Phi', 1, '0915678901', N'Đà Nẵng', 'phihnvph50531@gmail.com', '345678901234', '2023-03-01', 0, 1),
('NV004', N'Trần Văn Dũng', 0, '0917890123', N'Huế', 'dunggoku10x@gmail.com', '456789012345', '2023-04-01', 1, 1),
('NV005', N'Nguyễn Đình Đức Lương', 1, '0918901234', N'Nghệ An', 'luongnddph50650@gmail.com', '567890123456', '2023-05-01', 0, 1);

go
INSERT INTO Khach_Hang (TenKhachHang, GioiTinh, DiaChi, SoDienThoai, Email, TrangThai)
VALUES 
(N'Khách lẻ', 1, N'', '', '', 1),
(N'Nguyễn Thị Lan', 0, N'Hà Nội', '0912123456', 'lan.nguyen@example.com', 1),
(N'Trần Văn Hoàng', 1, N'Hải Phòng', '0913456789', 'hoang.tran@example.com', 1),
(N'Phạm Thị Hằng', 0, N'Đà Nẵng', '0915678901', 'hang.pham@example.com', 1),
(N'Lê Văn Hải', 1, N'Huế', '0917890123', 'hai.le@example.com', 1),
(N'Hoàng Thị Nga', 0, N'Nghệ An', '0918901234', 'nga.hoang@example.com', 1);
go
INSERT INTO Voucher (TenVoucher, MucGiamGia, ThoiGianBatDau, ThoiGianKetThuc, MoTa, TrangThai)
VALUES
(N'...', 0, '2024-11-01', '2050-11-30', N'Không áp mã giảm giá', 1),
(N'Giảm 15k', 15000, '2024-11-01', '2024-11-30', N'Giảm trực tiếp 15k', 1),
(N'Giảm 50K', 50000, '2024-11-10', '2024-12-31', N'Giảm trực tiếp 50K', 1),
(N'Giảm 40K', 40000, '2024-11-15', '2024-12-15', N'Giảm trực tiếp 40K', 1),
(N'Giảm 20k', 20000, '2024-11-20', '2024-12-20', N'Giảm trực tiếp 20k', 1),
(N'Giảm 30K', 30000, '2024-11-25', '2024-12-31', N'Giảm trực tiếp 30K', 1);
go
INSERT INTO Hoa_Don (MaNhanVien, MaVoucher, MaKhachHang, NgayTao, TongTien, TongTienGiamGia, TongTienSauApVoucher, TienKhachTra, TrangThai)
VALUES 
('NV001', 1, 1, '2024-11-17', 1000000, 100000, 900000, 950000, 1),
('NV002', 2, 2, '2024-11-18', 600000, 60000, 540000, 550000, 1),
('NV003', 3, 3, '2024-11-19', 800000, 80000, 720000, 750000, 1),
('NV004', 4, 4, '2024-11-20', 400000, 40000, 360000, 400000, 1),
('NV005', 5, 5, '2024-11-21', 500000, 50000, 450000, 500000, 1);
go
INSERT INTO Hoa_Don_Chi_Tiet (MaHoaDon, MaSanPhamChiTiet, SoLuong, DonGia, TrangThai)
VALUES 
(1, 1, 2, 500000, 1),
(1, 2, 1, 300000, 1),
(2, 3, 1, 600000, 1),
(3, 4, 2, 400000, 1),
(4, 1, 3, 450000, 1);



-- Lấy tất cả dữ liệu từ bảng Thuong_Hieu
SELECT * FROM Thuong_Hieu;

-- Lấy tất cả dữ liệu từ bảng Chat_Lieu
SELECT * FROM Chat_Lieu;

-- Lấy tất cả dữ liệu từ bảng Mau_Sac
SELECT * FROM Mau_Sac;

-- Lấy tất cả dữ liệu từ bảng Kich_Thuoc
SELECT * FROM Kich_Thuoc;

-- Lấy tất cả dữ liệu từ bảng Xuat_Xu
SELECT * FROM Xuat_Xu;

-- Lấy tất cả dữ liệu từ bảng San_Pham
SELECT * FROM San_Pham;

-- Lấy tất cả dữ liệu từ bảng San_Pham_Chi_Tiet
SELECT * FROM San_Pham_Chi_Tiet;

-- Lấy tất cả dữ liệu từ bảng Nhan_Vien
SELECT * FROM Nhan_Vien;

-- Lấy tất cả dữ liệu từ bảng Khach_Hang
SELECT * FROM Khach_Hang;

-- Lấy tất cả dữ liệu từ bảng Voucher
SELECT * FROM Voucher;

-- Lấy tất cả dữ liệu từ bảng Hoa_Don
SELECT * FROM Hoa_Don;

-- Lấy tất cả dữ liệu từ bảng Hoa_Don_Chi_Tiet
SELECT * FROM Hoa_Don_Chi_Tiet;
SELECT 
    hd.ID, 
    hd.MaHoaDon, 
    kh.TenKhachHang,
    kh.SoDienThoai,
    nv.TenNhanVien,
    vc.TenVoucher,
    hd.NgayTao,
    hd.TongTien,
    hd.TongTienGiamGia,
    hd.TongTienSauApVoucher,
    hd.TrangThai
FROM 
    dbo.Hoa_Don hd
INNER JOIN 
    dbo.Khach_Hang kh ON hd.MaKhachHang = kh.ID
INNER JOIN 
    dbo.Nhan_Vien nv ON hd.MaNhanVien = nv.MaNhanVien
LEFT JOIN 
    dbo.Voucher vc ON hd.MaVoucher = vc.ID
ORDER BY 
    hd.MaHoaDon DESC;

	SELECT 
    sp.MaSanPham, 
    sp.TenSanPham, 
    sp.MoTa, 
    SUM(spct.SoLuong) as SoLuong, 
    th.TenThuongHieu,
    sp.TrangThai 
FROM 
    San_Pham sp 
LEFT JOIN 
    San_Pham_Chi_Tiet spct ON sp.ID = spct.MaSanPham 
JOIN 
    Thuong_Hieu th ON th.ID = sp.MaThuongHieu
GROUP BY 
    sp.MaSanPham, 
    sp.TenSanPham, 
    sp.MoTa, 
    sp.TrangThai, 
    th.TenThuongHieu
ORDER BY 
    sp.MaSanPham Desc;

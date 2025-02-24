package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SanPhamBH;
import model.HoaDonBH;
import repository.BanHangService;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author phung
 */
public class BanHangView extends javax.swing.JFrame {

    private KhachHangForm hangForm;
    DefaultTableModel model = new DefaultTableModel();
    int index;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    Date dt = new Date();
    BanHangService service = new BanHangService();
    DefaultComboBoxModel<String> modelKM = new DefaultComboBoxModel<>();
    int soluongcon;
    int soLuonghd;
    int thanhtien;
    String ma;
    int a;
    String mahd;
    List<String> list = new ArrayList<>();
    String man;
    String tenNv;

    /**
     * Creates new form BanHang
     */
    public BanHangView() {
        initComponents();
        setLocationRelativeTo(null);
        fillTableSP(service.getAllSP());
        //fillGio(service.getAllGio());
        fillHDCho(service.getAllHDCho(false));
        this.cbxKM();
        this.txtTenKH.setText("Khách lẻ");
    }

    public BanHangView(String tenNv) {
        this.tenNv = tenNv;
        initComponents();
        setLocationRelativeTo(null);
        fillTableSP(service.getAllSP());
        //fillGio(service.getAllGio());
        fillHDCho(service.getAllHDCho(false));
        this.cbxKM();
        txtDangNhap.setText("Xin chào, " + tenNv);
        txttenNhanVien.setText(tenNv);
        this.txtTenKH.setText("Khách lẻ");
    }

    public BanHangView(String tenKhach, String sdt) {
        initComponents();
        setLocationRelativeTo(null);
        fillTableSP(service.getAllSP());
        // fillGio(service.getAllGio());
        fillHDCho(service.getAllHDCho(false));
        this.cbxKM();
        // Gán dữ liệu vào các JTextField
        txtTenKH.setText(tenKhach);
        txtSDT.setText(sdt);
    }

    public void setKhachHang(String tenKhach, String sdt) {
        txtTenKH.setText(tenKhach);
        txtSDT.setText(sdt);
    }

    public void fillTableSP(List<SanPhamBH> list) {
        model = (DefaultTableModel) tableSanpham.getModel();
        model.setRowCount(0);
        for (SanPhamBH sp : list) {
            model.addRow(new Object[]{
                sp.getMaSP(), sp.getMaSPCT(), sp.getTenSp(), sp.getSoLuong(), sp.getDonGia(), sp.getThuongThieu(), sp.getMauSac(), sp.getChatLieu(), sp.getSize(), sp.getXuatXu()
            });
        }
    }

    public void fillGio(List<HoaDonBH> list) {
        model = (DefaultTableModel) tableGio.getModel();
        model.setRowCount(0);
        for (HoaDonBH h : list) {
            model.addRow(new Object[]{
                h.getMaCTSP(), h.getTenSP(), h.getSoLuong(), h.getGia(), h.getTongTien()
            });
        }
    }

    public void fillHDCho(List<HoaDonBH> list) {
        model = (DefaultTableModel) tableHoadon.getModel();
        model.setRowCount(0);
        for (HoaDonBH h : list) {
            model.addRow(new Object[]{
                h.getMaHDon(), h.getTenNhanVien(), h.getNgayTao(), h.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"
            });
        }
    }

    public Boolean check() {
        if (txtTongtien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tổng tiền đang trống");
            return false;
        }
        if (txtThanhtien.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Thành tiền đang trống");
            return false;
        }
        return true;
    }

    public void cbxKM() {
        List<String> list = service.getKM();
        for (String s : list) {
            modelKM.addElement(s);
        }
        cbxKM.setModel(modelKM);
    }

    private String formatCurrency(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(amount); // Trả về kiểu String
    }

    public void updateAmounts(double thanhTien, double tienGiamGia, double tienKhachTra) {
        // Call the formatCurrency method to set formatted text for labels  
        txtThanhtien.setText(formatCurrency(thanhTien));

        txtTienGiamGia.setText(formatCurrency(tienGiamGia));

        // Assuming you want to set the formatted text for txtTienKhachTra as well  
        txtTienKhachTra.setText(formatCurrency(tienKhachTra));

        // If you also need to calculate and format the remaining amount (thừa)  
        double tienThua = thanhTien - tienKhachTra; // Change this logic as per your needs  
        txtTienThua.setText(formatCurrency(tienThua));

        // If you have a total amount  
        double tongTien = thanhTien - tienGiamGia; // Adjust as needed for total  
        txtTongtien.setText(formatCurrency(tongTien));
    }

    public SanPhamBH readSP() {
        SanPhamBH sp = new SanPhamBH();
        sp.setSoLuong(soluongcon);
        return sp;
    }

    public HoaDonBH readHDCT() {
        HoaDonBH hd = new HoaDonBH();
        hd.setSoLuong(soLuonghd);
        return hd;
    }

    public HoaDonBH readformHd() {
        HoaDonBH hd = new HoaDonBH();
        hd.setMaNV(service.getMaNVTheoTen(txttenNhanVien.getText()));
        hd.setIDKhuyenMai(service.getIdTheoTenVc(cbxKM.getSelectedItem() + ""));
        hd.setIDKhachHang(service.getMaKhTheoTen(txtTenKH.getText()));
        hd.setTongTien(Double.parseDouble(txtTongtien.getText()));
        hd.setTienGiamGia(Double.parseDouble(txtTienGiamGia.getText()));
        hd.setThanhTien(Double.parseDouble(txtThanhtien.getText()));
        hd.setTienKhachtra(Double.parseDouble(txtTienKhachTra.getText()));
        return hd;
    }

    private void updateTotalAmount() {
        double total = 0;
        // Lặp qua tất cả các hàng trong bảng để tính tổng  
        for (int i = 0; i < tableGio.getRowCount(); i++) {
            // Chuyển giá trị từ bảng sang kiểu double  
            double tongTien = Double.parseDouble(tableGio.getValueAt(i, 4).toString());
            total += tongTien; // Cộng dồn vào tổng  
        }

        // Định dạng tổng thành chuỗi tiền tệ và cập nhật vào txtTongtien  
        txtTongtien.setText(formatCurrency(total));
    }

    public void clear() {
        txtMaHD.setText("");
        //    txt.setText(man);
        txtTienKhachTra.setText("");
        cbxKM.setSelectedIndex(0);
        txtSDT.setText("");
        txtThanhtien.setText("");
        txtTienThua.setText("");
        txtTienKhachTra.setText("");
        txtTongtien.setText("");
        model = (DefaultTableModel) tableGio.getModel();
        model.setRowCount(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTongtien1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoadon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableGio = new javax.swing.JTable();
        btnXoaItem = new javax.swing.JButton();
        btnSuaSoLuongGio = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSanpham = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtTimkiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnRs = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxKM = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTienKhachTra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnThanhtoan = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnAddKH = new javax.swing.JButton();
        txtTongtien = new javax.swing.JLabel();
        txtThanhtien = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTienGiamGia = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        txtTienThua = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JLabel();
        txtSDT = new javax.swing.JLabel();
        txttenNhanVien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnHoadon = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtDangNhap = new javax.swing.JLabel();

        txtTongtien1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongtien1.setForeground(new java.awt.Color(255, 0, 51));
        txtTongtien1.setText("VND");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("VNĐ");

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("VNĐ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WIKA SHOP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quản Lý Bán Hàng");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn chờ"));

        tableHoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Tên NV", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoadon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tableGio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CT", "Tên SP", "Số Lượng", "Giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableGio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableGio);

        btnXoaItem.setBackground(new java.awt.Color(102, 102, 102));
        btnXoaItem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnXoaItem.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaItem.setText("Xóa khỏi giỏ");
        btnXoaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaItemActionPerformed(evt);
            }
        });

        btnSuaSoLuongGio.setBackground(new java.awt.Color(102, 102, 102));
        btnSuaSoLuongGio.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSuaSoLuongGio.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaSoLuongGio.setText("Sửa số lượng");
        btnSuaSoLuongGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSoLuongGioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSuaSoLuongGio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaItem)
                    .addComponent(btnSuaSoLuongGio))
                .addGap(14, 14, 14))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tableSanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã CT", "Tên SP", "Số Lượng", "Giá", "Thương hiệu", "Màu sắc", "Chất liệu", "Size", "Xuất xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanphamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableSanpham);

        jLabel13.setText("Tìm kiếm sản phẩm:");

        txtTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemActionPerformed(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(102, 102, 102));
        btnTim.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnRs.setBackground(new java.awt.Color(102, 102, 102));
        btnRs.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnRs.setForeground(new java.awt.Color(255, 255, 255));
        btnRs.setText("Reset");
        btnRs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRs, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(btnTim)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo hóa đơn"));

        jLabel5.setText("Tên NV:");

        jLabel6.setText("Tên KH:");

        jLabel7.setText("SĐT:");

        jLabel8.setText("Tổng tiền:");

        jLabel9.setText("Khuyến mãi:");

        cbxKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxKM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKMItemStateChanged(evt);
            }
        });
        cbxKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxKMMouseClicked(evt);
            }
        });
        cbxKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKMActionPerformed(evt);
            }
        });

        jLabel10.setText("Thành tiền:");

        jLabel11.setText("Tiền khách trả:");

        txtTienKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachTraActionPerformed(evt);
            }
        });

        jLabel12.setText("Tiền thừa:");

        btnThanhtoan.setBackground(new java.awt.Color(102, 102, 102));
        btnThanhtoan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThanhtoan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhtoan.setText("Thanh Toán");
        btnThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhtoanActionPerformed(evt);
            }
        });

        jLabel14.setText("Mã HD:");

        btnAddKH.setBackground(new java.awt.Color(102, 102, 102));
        btnAddKH.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAddKH.setForeground(new java.awt.Color(255, 255, 255));
        btnAddKH.setText("+");
        btnAddKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddKHMouseClicked(evt);
            }
        });
        btnAddKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKHActionPerformed(evt);
            }
        });

        txtTongtien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongtien.setForeground(new java.awt.Color(255, 0, 51));

        txtThanhtien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThanhtien.setForeground(new java.awt.Color(255, 0, 51));

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel4.setText("Tiền giảm giá");

        jButton9.setBackground(new java.awt.Color(102, 102, 102));
        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Áp mã");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("VNĐ");

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("VNĐ");

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("VNĐ");

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("VNĐ");

        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("VNĐ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel7))
                            .addComponent(jLabel14)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txttenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(btnAddKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(21, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbxKM, 0, 253, Short.MAX_VALUE)
                                    .addComponent(txtThanhtien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTienGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton9)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTienKhachTra, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                    .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txttenNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnAddKH))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbxKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTienGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtThanhtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16)))
                            .addComponent(jLabel17)))
                    .addComponent(jLabel19))
                .addContainerGap())
        );

        btnHoadon.setBackground(new java.awt.Color(102, 102, 102));
        btnHoadon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnHoadon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoadon.setText("Tạo Hóa Đơn");
        btnHoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnHoadon))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHoadon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.getAccessibleContext().setAccessibleName("Thanh toán");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Trang Chủ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sản Phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Thuộc Tính");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setText("Bán Hàng");

        jButton5.setBackground(new java.awt.Color(102, 102, 102));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Hóa Đơn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 102, 102));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Khách Hàng");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 102, 102));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Nhân Viên");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(102, 102, 102));
        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Khuyến Mãi");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtDangNhap.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        txtDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDangNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TrangChu tc = null;
        tc = new TrangChu();
        this.setVisible(false);
        tc.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXoaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaItemActionPerformed
        // Hiển thị hộp thoại xác nhận
        int chon = JOptionPane.showConfirmDialog(this, "Xóa khỏi giỏ hàng?");
        if (chon == JOptionPane.YES_OPTION) {
            index = tableGio.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
                return;
            }

            // Lấy mã chi tiết sản phẩm và số lượng
            String mact = tableGio.getValueAt(index, 0).toString();
            int soluongban = Integer.parseInt(service.getSLTheoID(mact));
            int soluong = Integer.parseInt(tableGio.getValueAt(index, 2).toString());
            int idChitiet = service.getIdTheoMaSPCt(mact);
            int idHd = service.getIdTheoMaHD(txtMaHD.getText());
            boolean trangThai = false;
            // Cập nhật số lượng tồn kho
            soluongcon = soluongban + soluong;
            SanPhamBH sp = readSP();
            if (service.updateSP(sp, mact) != 0) {
                fillTableSP(service.getAllSP());
            }

            // Xóa hàng khỏi giỏ hàng
            model = (DefaultTableModel) tableGio.getModel();
            model.removeRow(index);

            service.deleteHDCt(trangThai, idHd, idChitiet);
            // Tính lại tổng tiền
            double tongTienMoi = 0;
            for (int i = 0; i < tableGio.getRowCount(); i++) {
                double thanhTien = Double.parseDouble(tableGio.getValueAt(i, 4).toString());
                tongTienMoi += thanhTien;
            }
            txtTongtien.setText(formatCurrency(tongTienMoi));
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm khỏi giỏ hàng thành công!");
        }
    }//GEN-LAST:event_btnXoaItemActionPerformed

    private void tableHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoadonMouseClicked
        index = tableHoadon.getSelectedRow();
        String maHD = tableHoadon.getValueAt(index, 0).toString();
        txtMaHD.setText(maHD);
        String tenNV = tableHoadon.getValueAt(index, 1).toString();
        txttenNhanVien.setText(tenNV);
        int idHoaDon = service.getIdTheoMaHD(maHD);
        int idKh = service.getIdKhTheoIDHD(idHoaDon);
        String tenKh = service.gettenKhTheoid(idKh);
        String sdt = service.getSDTKhTheoid(idKh);

        // Kiểm tra nếu idKh == 1, thì là "Khách lẻ"
        if (idKh == 1) {
            txtTenKH.setText("Khách lẻ");
            txtSDT.setText("");
        } else {
            txtTenKH.setText(tenKh);
            txtSDT.setText(sdt);
        }

        this.fillGio(service.getAllHDChoBangId(idHoaDon));
        updateTotalAmount();
    }//GEN-LAST:event_tableHoadonMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        QuanLySanPham qlsp = null;
        qlsp = new QuanLySanPham(tenNv);
        this.setVisible(false);
        qlsp.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        ThuocTinh tt = null;
//
//        try {
//            tt = new ThuocTinh();
//        } catch (SQLException ex) {
//            Logger.getLogger(BanHangView.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(BanHangView.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        this.setVisible(false);
//        tt.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        HoaDon hd = null;
        hd = new HoaDon(tenNv);
        this.setVisible(false);
        hd.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        KhachHangView kh = null;
        try {
            kh = new KhachHangView(tenNv);
        } catch (Exception ex) {
            Logger.getLogger(BanHangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        kh.setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        NhanVienView nv = null;
        try {
            nv = new NhanVienView(tenNv);
        } catch (Exception ex) {
            Logger.getLogger(BanHangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        nv.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        KhuyenMaiView km = null;
        try {
            km = new KhuyenMaiView(tenNv);
        } catch (Exception ex) {
            Logger.getLogger(BanHangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        km.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnAddKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKHActionPerformed
        new KhachHangForm(true, this).setVisible(true);
    }//GEN-LAST:event_btnAddKHActionPerformed

    private void btnAddKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddKHMouseClicked

    }//GEN-LAST:event_btnAddKHMouseClicked

    private void btnHoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoadonActionPerformed
        // Hiển thị hộp thoại xác nhận
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm hóa đơn mới?", "Xác nhận thêm hóa đơn", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            String maNhanVien = service.getMaNVTheoTen(tenNv);
            String tenkh = txtTenKH.getText().trim(); // Lấy tên khách hàng và loại bỏ khoảng trắng thừa

            // Kiểm tra giới hạn hóa đơn chưa thanh toán
            int soLuongHoaDonChuaThanhToan = service.countHoaDonChuaThanhToan();
            if (soLuongHoaDonChuaThanhToan >= 10) {
                JOptionPane.showMessageDialog(this, "Không thể thêm hóa đơn mới! Đã có 10 hóa đơn chưa thanh toán.");
                return;
            }

            if (!tenkh.isEmpty()) {
                // Nếu có khách hàng
                int idKh = service.getMaKhTheoTen(tenkh);
                HoaDonBH hd = new HoaDonBH(maNhanVien, idKh);
                service.addHoaDonCoKh(hd);
            } else {
                // Nếu không có khách hàng
                service.addHoaDon(maNhanVien);
            }

            // Cập nhật lại bảng hiển thị danh sách hóa đơn chờ
            this.fillHDCho(service.getAllHDCho(false));
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy thao tác thêm hóa đơn.");
        }

    }//GEN-LAST:event_btnHoadonActionPerformed

    private void btnThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        if (check()) {
            if (txtTienKhachTra.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống tiền khách trả");
                return;
            }
            double tienThucTe = Double.parseDouble(txtThanhtien.getText()); // Assuming txtThanhtien holds the total amount due  
            if (tienThucTe <= 0) {
                JOptionPane.showMessageDialog(this, "Chưa có sản phẩm để thanh toán!");
                return;
            }
            try {
                double tienKhachTra = Double.parseDouble(txtTienKhachTra.getText());
                double tienThua = Double.parseDouble(txtTienThua.getText());

                if (tienThua < 0) {
                    JOptionPane.showMessageDialog(this, "Khách trả không đủ tiền");
                    return;
                }

                // Thêm hộp thoại xác nhận thanh toán
                int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán hóa đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int idHd = service.getIdTheoMaHD(txtMaHD.getText());
                    HoaDonBH hd = readformHd();

                    // Kiểm tra dữ liệu của đối tượng HoaDonBH
                    if (hd == null) {
                        JOptionPane.showMessageDialog(this, "Dữ liệu hóa đơn không hợp lệ");
                        return;
                    }

                    if (service.updateHoaDon(hd, idHd) != 0) {
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                        clear();
                        fillHDCho(service.getAllHDCho(false));

                        // Xóa dữ liệu bảng giỏ hàng
                        DefaultTableModel model = (DefaultTableModel) tableGio.getModel();
                        model.setRowCount(0);
                        this.txttenNhanVien.setText(tenNv);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thanh toán được, vui lòng thử lại!");
                        System.out.println("Cập nhật hóa đơn thất bại! ID hóa đơn: " + idHd);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Hủy thanh toán.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Dữ liệu nhập không hợp lệ! Vui lòng kiểm tra lại số tiền.");
                System.out.println("Lỗi chuyển đổi số: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình thanh toán.");
                System.out.println("Lỗi khi thanh toán: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnThanhtoanActionPerformed


    private void txtTienKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachTraActionPerformed
        double thanhTien;
        if (txtThanhtien.getText().isEmpty()) {
            thanhTien = 0;
            txtThanhtien.setText("0");
        } else {
            thanhTien = Double.parseDouble(txtThanhtien.getText());
        }

        // Lấy số tiền khách trả, mặc định 0 nếu trường trống
        double tienKhachTra;
        if (txtTienKhachTra.getText().isEmpty()) {
            tienKhachTra = 0;
            txtTienKhachTra.setText("0");
        } else {
            tienKhachTra = Double.parseDouble(txtTienKhachTra.getText());
        }
        // Tính tiền thừa
        double tienThua = tienKhachTra - thanhTien;

        txtTienThua.setText(String.valueOf(tienThua));    }//GEN-LAST:event_txtTienKhachTraActionPerformed

    private void cbxKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKMActionPerformed

    }//GEN-LAST:event_cbxKMActionPerformed

    private void cbxKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxKMMouseClicked

    }//GEN-LAST:event_cbxKMMouseClicked

    private void cbxKMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKMItemStateChanged

    }//GEN-LAST:event_cbxKMItemStateChanged

    private void tableSanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanphamMouseClicked
        int indexHD = tableHoadon.getSelectedRow();
        if (indexHD == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn thêm vào giỏ hàng!");
            return;
        }

        String maHd = tableHoadon.getValueAt(indexHD, 0).toString();
        int idHoaD = service.getIdTheoMaHD(maHd);
        System.out.println(idHoaD);

        index = tableSanpham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn thêm vào giỏ hàng!");
            return;
        }

        String soLuongg = JOptionPane.showInputDialog(this, "Nhập số lượng ");
        if (soLuongg == null || soLuongg.isEmpty()) {
            return;
        }

        int soLuong = Integer.parseInt(soLuongg);
        int soLuongBan = Integer.parseInt(tableSanpham.getValueAt(index, 3).toString());
        String maCT = tableSanpham.getValueAt(index, 1).toString();
        int idSpct = service.getIdTheoMaSPCt(maCT);
        System.out.println(idSpct);

        String tensp = tableSanpham.getValueAt(index, 2).toString();
        double gia = Double.parseDouble(tableSanpham.getValueAt(index, 4).toString());
        double thanhTien = soLuong * gia;
        String formattedThanhTien = formatCurrency(thanhTien);

// Thêm hộp thoại xác nhận trước khi thêm vào giỏ hàng
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thêm sản phẩm vào giỏ hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (soLuong <= soLuongBan) {
                DefaultTableModel model1 = (DefaultTableModel) tableGio.getModel();
                int rowCount = model1.getRowCount();
                boolean exists = false;

                // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
                for (int j = 0; j < rowCount; j++) {
                    String mass = tableGio.getValueAt(j, 0).toString();
                    if (mass.equalsIgnoreCase(maCT)) {
                        int soLuongCu = Integer.parseInt(tableGio.getValueAt(j, 2).toString());
                        int soLuongMoi = soLuong + soLuongCu;
                        double giaCu = Double.parseDouble(tableGio.getValueAt(j, 3).toString());

                        // Cập nhật chi tiết hóa đơn và giỏ hàng
                        HoaDonBH bh = new HoaDonBH(idHoaD, idSpct, soLuongMoi, giaCu, true);
                        service.updateHDCt(bh, idHoaD, idSpct);

                        tableGio.setValueAt(soLuongMoi, j, 2);
                        tableGio.setValueAt(soLuongMoi * giaCu, j, 4);

                        exists = true;
                        break;
                    }
                }

                // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
                if (!exists) {
                    HoaDonBH bh = new HoaDonBH(idHoaD, idSpct, soLuong, gia, true);
                    service.addHDCT(bh);

                    model1.addRow(new Object[]{maCT, tensp, soLuong, gia, formattedThanhTien});
                }

                // Cập nhật số lượng tồn kho và tổng tiền
                soluongcon = soLuongBan - soLuong;
                SanPhamBH sp = readSP();
                if (service.updateSP(sp, maCT) != 0) {
                    fillTableSP(service.getAllSP());
                }

                updateTotalAmount(); // Hàm cập nhật tổng tiền
                JOptionPane.showMessageDialog(this, "Thêm vào giỏ thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Vượt quá số lượng tồn kho");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hủy thêm sản phẩm vào giỏ hàng.");
        }

    }//GEN-LAST:event_tableSanphamMouseClicked

    private void tableGioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGioMouseClicked

    }//GEN-LAST:event_tableGioMouseClicked

    private void btnSuaSoLuongGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSoLuongGioActionPerformed
        index = tableGio.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.");
            return;
        }

        String soLuongg = JOptionPane.showInputDialog(this, "Nhập số lượng mua");
        if (soLuongg == null || soLuongg.isEmpty()) {
            return;
        }

        int soluong;
        try {
            soluong = Integer.parseInt(soLuongg);
            if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ.");
            return;
        }

// Thêm hộp thoại xác nhận trước khi cập nhật
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật số lượng sản phẩm?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int soluongmua = Integer.parseInt(tableGio.getValueAt(index, 2).toString());
            double gia = Double.parseDouble(tableGio.getValueAt(index, 3).toString());

            tableGio.setValueAt(soluong, index, 2);
            double thanhTien = soluong * gia;
            tableGio.setValueAt(thanhTien, index, 4);

            String maCt = tableGio.getValueAt(index, 0).toString();
            int idMaCt = service.getIdTheoMaSPCt(maCt);
            int soLuongBan = Integer.parseInt(service.getSLTheoID(maCt));

            if (soluong > soluongmua) {
                soluongcon = soLuongBan - (soluong - soluongmua);
            } else if (soluong < soluongmua) {
                soluongcon = soLuongBan + (soluongmua - soluong);
            }

            soLuonghd = soluong;
            SanPhamBH sp = readSP();
            HoaDonBH hd = readHDCT();
            int idHd = service.getIdTheoMaHD(txtMaHD.getText());

            service.updateHDCt(hd, idHd, idMaCt);
            if (service.updateSP(sp, maCt) != 0) {
                fillTableSP(service.getAllSP());
            }

            double u = 0;
            for (int j = 0; j < tableGio.getRowCount(); j++) {
                double tongtien = Double.parseDouble(tableGio.getValueAt(j, 4).toString());
                u += tongtien;
            }
            txtTongtien.setText(formatCurrency(u));
            JOptionPane.showMessageDialog(this, "Cập nhật số lượng thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Hủy cập nhật số lượng.");
        }

    }//GEN-LAST:event_btnSuaSoLuongGioActionPerformed

    private void txtTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemActionPerformed

    }//GEN-LAST:event_txtTimkiemActionPerformed

    private void btnRsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRsActionPerformed
        clear();
    }//GEN-LAST:event_btnRsActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String tim = txtTimkiem.getText().trim();
        List<SanPhamBH> list = new ArrayList<>();
        list = service.TimKiemTableSanPham(tim);
        if (tim.isEmpty()) {
            fillTableSP(service.getAllSP());
        } else {
            fillTableSP(list);
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        String ten = (String) cbxKM.getSelectedItem();
        System.out.println(ten);
        // Lấy giá trị giảm giá từ service
        double giaGiam = service.getGiamTheoTen(ten);
        System.out.println(giaGiam);

        // Hiển thị số tiền giảm giá
        txtTienGiamGia.setText(String.valueOf(giaGiam));

        // Kiểm tra nếu tổng tiền trống, gán tổng tiền bằng 0
        double tongTien;
        if (txtTongtien.getText().isEmpty()) {
            tongTien = 0;
            txtTongtien.setText("0");  // Gán lại giá trị tổng tiền
        } else {
            tongTien = Double.parseDouble(txtTongtien.getText());
        }

        // Giới hạn giảm giá không được lớn hơn tổng tiền
        if (giaGiam > tongTien) {
            giaGiam = tongTien;  // Giảm giá tối đa bằng tổng tiền
            txtTienGiamGia.setText(String.valueOf(giaGiam));
        }

        // Tính toán thành tiền sau giảm giá
        double thanhTien = tongTien - giaGiam;
        txtThanhtien.setText(formatCurrency(thanhTien));
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddKH;
    private javax.swing.JButton btnHoadon;
    private javax.swing.JButton btnRs;
    private javax.swing.JButton btnSuaSoLuongGio;
    private javax.swing.JButton btnThanhtoan;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaItem;
    private javax.swing.JComboBox<String> cbxKM;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableGio;
    private javax.swing.JTable tableHoadon;
    private javax.swing.JTable tableSanpham;
    private javax.swing.JLabel txtDangNhap;
    private javax.swing.JLabel txtMaHD;
    private javax.swing.JLabel txtSDT;
    private javax.swing.JLabel txtTenKH;
    private javax.swing.JLabel txtThanhtien;
    private javax.swing.JLabel txtTienGiamGia;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JLabel txtTienThua;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JLabel txtTongtien;
    private javax.swing.JLabel txtTongtien1;
    private javax.swing.JLabel txttenNhanVien;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.NhanVienService;
import model.NhanVien;

/**
 *
 * @author Admin
 */
public class NhanVienView extends javax.swing.JFrame {

    private NhanVienService nvsv = new NhanVienService();
    private List<NhanVien> list = new ArrayList<>();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private NhanVien nv = new NhanVien();
    private String tenNhanVien; // Lưu tên nhân viên đăng nhập

    // Constructor nhận tên nhân viên

    public NhanVienView() throws Exception {
        initComponents();
        init();
    }
    
    
    public NhanVienView(String tenNhanVien) throws Exception {
    this.tenNhanVien = tenNhanVien;
//    System.out.println("Tên nhân viên: " + this.tenNhanVien); // Kiểm tra tên nhân viên
    initComponents();
    init();
    }

    public void init() throws Exception {
        fillTable();

        // Hiển thị tên nhân viên lên giao diện
        Tennv.setText("Xin chào, " + tenNhanVien + "!"); // lblWelcome là JLabel
        try {
            String newMaNV = nvsv.generateMaNhanVien(); // Tạo mã nhân viên mới
            txtManv.setText(newMaNV); // Gán vào JTextField
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo mã nhân viên: " + e.getMessage());
        }
    }

   public void fillTable() throws Exception {
    // Lấy danh sách nhân viên từ dịch vụ
    list = nvsv.getAll(); // giả sử nvsv.getAll() trả về danh sách nhân viên

    // Đặt model cho bảng
    DefaultTableModel defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
    defaultTableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng

    // Biến đếm số thứ tự
    int stt = 1;
    
    // Duyệt qua danh sách nhân viên và thêm vào bảng
    for (NhanVien nv : list) {
        Object[] row = nv.toRowTable();  // Gọi phương thức toRowTable() để lấy dữ liệu nhân viên
        Object[] rowWithStt = new Object[row.length + 1];
        
        // Thêm số thứ tự vào cột đầu tiên
        rowWithStt[0] = stt++;
        
        // Sao chép dữ liệu của nhân viên vào mảng rowWithStt
        System.arraycopy(row, 0, rowWithStt, 1, row.length);
        
        // Thêm dòng dữ liệu vào bảng
        defaultTableModel.addRow(rowWithStt);
    }
}


   public void readForm(NhanVien nv) {
    // Điền thông tin vào các trường trong form
    txtManv.setText(nv.getMaNhanVien());
    txtTennv.setText(nv.getTenNhanVien());
    txtSdt.setText(nv.getSoDienThoai());
    txtDiachi.setText(nv.getDiaChi());
    txtEmail.setText(nv.getEmail());
    txtCddd.setText(nv.getCccd());
    txtpass.setText(nv.getPassword());

    // Xử lý giới tính
    if (nv.isGioiTinh()) {
        rdoNam.setSelected(true); // Giới tính Nam
    } else {
        rdoNu.setSelected(true); // Giới tính Nữ
    }

    // Xử lý chức vụ
    if (nv.isChucVu()) {
        rdoQuanLy.setSelected(true); // Quản lý
    } else {
        rdoNhanVien.setSelected(true); // Nhân viên
    }
    if (nv.isTrangThai()) {
        rdoDangLam.setSelected(true); // Quản lý
    } else {
        rdoNghiViec.setSelected(true); // Nhân viên
    }

    // Đặt giá trị ngày vào làm lên JDateChooser
    if (nv.getNgayVaoLam() != null) {
        DateNgayTao.setDate(nv.getNgayVaoLam());
    }
}


   private boolean checkEmptyFields() {
    // Kiểm tra các trường thông tin không được bỏ trống
    if (txtTennv.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống!");
        txtTennv.requestFocus();
        return false;
    }
    if (DateNgayTao.getDate() == null) { // Sử dụng getDate() thay vì getText()
        JOptionPane.showMessageDialog(this, "Ngày vào làm không được để trống!");
        DateNgayTao.requestFocus();
        return false;
    }
    if (txtSdt.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
        txtSdt.requestFocus();
        return false;
    }
    if (txtDiachi.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
        txtDiachi.requestFocus();
        return false;
    }
    if (txtEmail.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Email không được để trống!");
        txtEmail.requestFocus();
        return false;
    }
    if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
        JOptionPane.showMessageDialog(this, "Hãy chọn giới tính!");
        return false;
    }
    if (!rdoQuanLy.isSelected() && !rdoNhanVien.isSelected()) {
        JOptionPane.showMessageDialog(this, "Hãy chọn chức vụ!");
        return false;
    }
    return true;
}


   private boolean validateData() {
    // Kiểm tra định dạng số điện thoại (10 chữ số)
    String sdt = txtSdt.getText().trim();
    if (!sdt.matches("^\\d{10}$")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số và không chứa ký tự khác!");
        txtSdt.requestFocus();
        return false;
    }

    // Kiểm tra số điện thoại đã tồn tại trong hệ thống
    if (NhanVienService.kiemTraTonTaiSdt(sdt)) {
        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại trong hệ thống!");
        txtSdt.requestFocus();
        return false;
    }

    // Kiểm tra mã nhân viên đã tồn tại
    String maNhanVien = txtManv.getText().trim();
    if (NhanVienService.kiemTraTonTaiMa(maNhanVien)) {
        JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại trong hệ thống!");
        txtManv.requestFocus();
        return false;
    }

    // Kiểm tra định dạng email
    String email = txtEmail.getText().trim();
    if (!email.matches("^[\\w.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,63}$")) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ! Vui lòng nhập đúng định dạng.");
        txtEmail.requestFocus();
        return false;
    }

    // Kiểm tra định dạng ngày nếu dùng JDateChooser
    if (DateNgayTao.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Ngày vào làm không được để trống!");
        DateNgayTao.requestFocus();
        return false;
    }

    // Kiểm tra CCCD đã tồn tại
    String cccd = txtCddd.getText().trim();
    if (NhanVienService.kiemTraTonTaiCCCD(cccd)) {
        JOptionPane.showMessageDialog(this, "CCCD đã tồn tại trong hệ thống!");
        txtCddd.requestFocus();
        return false;
    }

    return true;
}

    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Tennv = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtGioitinhl = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTennv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTimkiem = new javax.swing.JTextField();
        txtTrangthai = new javax.swing.JLabel();
        rdoQuanLy = new javax.swing.JRadioButton();
        txtDiachi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        rdoNhanVien = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txtCddd = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtpass = new javax.swing.JTextField();
        DateNgayTao = new com.toedter.calendar.JDateChooser();
        txtTrangthai1 = new javax.swing.JLabel();
        rdoDangLam = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();

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
                .addGap(0, 23, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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

        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Bán Hàng");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
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

        Tennv.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Tennv.setForeground(new java.awt.Color(255, 255, 255));
        Tennv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tennv.setText("Admin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tennv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        txtGioitinhl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGioitinhl.setText("Giới tính ");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên ");

        txtManv.setEditable(false);
        txtManv.setEnabled(false);
        txtManv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManvActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Tên NV", "Giới Tính", "SĐT", "Địa chỉ", "Email", "CCCD", "Ngày Làm", "Trạng Thái", "Pass"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNhanVien);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số điện thoại ");

        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Họ tên nhân viên ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Địa chỉ ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Ngày tạo");

        btnThem.setBackground(new java.awt.Color(102, 102, 102));
        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Quản Lý Nhân Viên");

        btnSua.setBackground(new java.awt.Color(102, 102, 102));
        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(102, 102, 102));
        btnMoi.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(102, 102, 102));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm");

        txtTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemActionPerformed(evt);
            }
        });
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        txtTrangthai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTrangthai.setText("Chức Vụ");

        buttonGroup2.add(rdoQuanLy);
        rdoQuanLy.setText("Quản Lí");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Email");

        buttonGroup2.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân Viên");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("CCCD");

        btnsearch.setBackground(new java.awt.Color(102, 102, 102));
        btnsearch.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch.setText("Tìm Kiếm");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Mật Khẩu");

        txtTrangthai1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTrangthai1.setText("Trạng Thái");

        buttonGroup3.add(rdoDangLam);
        rdoDangLam.setText("Đang Làm");
        rdoDangLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangLamActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoNghiViec);
        rdoNghiViec.setText("Nghỉ Việc");
        rdoNghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNghiViecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(386, 386, 386))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(btnsearch))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTennv)
                                            .addComponent(txtManv)
                                            .addComponent(txtEmail)
                                            .addComponent(txtCddd, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                            .addComponent(DateNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel4)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTrangthai1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtSdt)
                                                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtGioitinhl, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdoNam)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(rdoQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(rdoNhanVien)))))))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(btnThem)
                                .addGap(52, 52, 52)
                                .addComponent(btnSua)
                                .addGap(61, 61, 61)
                                .addComponent(btnXoa)
                                .addGap(59, 59, 59)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMoi)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoDangLam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnsearch))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DateNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGioitinhl)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtCddd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrangthai)
                            .addComponent(rdoQuanLy)
                            .addComponent(rdoNhanVien))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTrangthai1)
                        .addComponent(rdoDangLam)
                        .addComponent(rdoNghiViec)))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TrangChu tc = null;
        tc = new TrangChu();
        this.setVisible(false);
        tc.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        QuanLySanPham qlsp = null;
        qlsp = new QuanLySanPham(tenNhanVien);
        this.setVisible(false);
        qlsp.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//         ThuocTinh tt = null;
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
//        tt.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        BanHangView banHangJFrame = null;
        banHangJFrame = new BanHangView(tenNhanVien);
        this.setVisible(false);
        banHangJFrame.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        HoaDon hd = null;
        hd = new HoaDon(tenNhanVien);
        this.setVisible(false);
        hd.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        KhachHangView kh = null;
        try {
            kh = new KhachHangView(tenNhanVien);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        kh.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        KhuyenMaiView km = null;
        try {
            km = new KhuyenMaiView(tenNhanVien);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        km.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // Lấy chỉ số của dòng được chọn
        int index = tblNhanVien.getSelectedRow();

        // Lấy đối tượng NhanVien từ danh sách (list)
        NhanVien selectedNhanVien = list.get(index);

//         Điền dữ liệu vào form
        readForm(selectedNhanVien);
        
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
     // Kiểm tra các trường thông tin có bị bỏ trống hay không
    if (!checkEmptyFields()) {
        return; // Nếu có trường thông tin bị bỏ trống thì dừng lại
    }

    // Kiểm tra tính hợp lệ của dữ liệu nhập vào
    if (!validateData()) {
        return; // Nếu dữ liệu không hợp lệ thì dừng lại
    }

    try {
        // Sinh mã nhân viên mới thông qua dịch vụ
        NhanVienService nvService = new NhanVienService();
        String newMaNV = nvService.generateMaNhanVien(); // Lấy mã nhân viên mới
        txtManv.setText(newMaNV);  // Gán mã nhân viên mới vào TextField

        // Hiển thị hộp thoại xác nhận trước khi thêm nhân viên
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn thêm nhân viên này?", 
                "Xác nhận", 
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return; // Dừng nếu người dùng chọn "Không"
        }

        // Tạo đối tượng NhanVien từ dữ liệu trên form
        NhanVien nv = new NhanVien();
        nv.setMaNhanVien(newMaNV);
        nv.setTenNhanVien(txtTennv.getText().trim());
        nv.setSoDienThoai(txtSdt.getText().trim());
        nv.setDiaChi(txtDiachi.getText().trim());
        nv.setEmail(txtEmail.getText().trim());
        nv.setCccd(txtCddd.getText().trim());
        nv.setChucVu(rdoQuanLy.isSelected()); // Kiểm tra chọn quản lý không
        nv.setGioiTinh(rdoNam.isSelected()); // Kiểm tra chọn giới tính Nam không
        nv.setPassword(txtpass.getText().trim());

        // Kiểm tra và xử lý ngày vào làm
        if (DateNgayTao.getDate() != null) {
            nv.setNgayVaoLam(new java.sql.Date(DateNgayTao.getDate().getTime()));
        } else {
            JOptionPane.showMessageDialog(this, "Ngày vào làm không được để trống!");
            DateNgayTao.requestFocus();
            return;
        }

        // Gửi dữ liệu nhân viên đến service để thêm vào cơ sở dữ liệu
        if (nvService.themNhanVien(nv) > 0) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            fillTable(); // Cập nhật bảng nhân viên
            resetForm(); // Làm sạch form
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
    }
}

// Phương thức reset form
private void resetForm() {
    txtManv.setText("");
    txtTennv.setText("");
    txtSdt.setText("");
    txtDiachi.setText("");
    txtEmail.setText("");
    txtCddd.setText("");
    txtpass.setText("");
    DateNgayTao.setDate(null);
    rdoNam.setSelected(false);
    rdoNu.setSelected(false);
    rdoQuanLy.setSelected(false);
    rdoNhanVien.setSelected(false);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
     // 1. Lấy thông tin từ giao diện
    String maNhanVien = txtManv.getText().trim();
    String tenNhanVien = txtTennv.getText().trim();
    boolean gioiTinh = rdoNam.isSelected(); // true nếu chọn Nam, false nếu chọn Nữ
    String soDienThoai = txtSdt.getText().trim();
    String diaChi = txtDiachi.getText().trim();
    String email = txtEmail.getText().trim();
    String cccd = txtCddd.getText().trim();
    
    // Lấy giá trị ngày từ JDateChooser
    java.util.Date ngayVaoLamDate = DateNgayTao.getDate(); // Lấy ngày từ JDateChooser
    
    if (ngayVaoLamDate == null) {
        JOptionPane.showMessageDialog(this, "Ngày vào làm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Chuyển đổi ngày thành chuỗi nếu cần thiết
    String ngayVaoLamText = new SimpleDateFormat("yyyy-MM-dd").format(ngayVaoLamDate);
    
    boolean chucVu = rdoQuanLy.isSelected(); // true nếu đang làm, false nếu nghỉ làm
    String pass = txtpass.getText().trim();
    
    // 2. Kiểm tra dữ liệu đầu vào
    if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || cccd.isEmpty() || ngayVaoLamText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Chuyển đổi ngày (ngày vào làm) từ String sang java.util.Date
    java.util.Date ngayVaoLam;
    try {
        ngayVaoLam = new SimpleDateFormat("yyyy-MM-dd").parse(ngayVaoLamText);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ngày vào làm không đúng định dạng (yyyy-MM-dd)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 4. Lấy trạng thái nhân viên (ví dụ, trạng thái là "Đang làm việc" nếu checkbox hoặc radio button tương ứng được chọn)
    boolean trangThai = rdoDangLam.isSelected(); // Giả sử có một radio button hoặc checkbox xác định trạng thái

    // 5. Hiển thị hộp thoại xác nhận cập nhật
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật thông tin nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return; // Nếu người dùng chọn "No", không thực hiện cập nhật
    }

    // 6. Tạo đối tượng NhanVien
    NhanVien nv = new NhanVien();
    nv.setMaNhanVien(maNhanVien);
    nv.setTenNhanVien(tenNhanVien);
    nv.setGioiTinh(gioiTinh);
    nv.setSoDienThoai(soDienThoai);
    nv.setDiaChi(diaChi);
    nv.setEmail(email);
    nv.setCccd(cccd);
    nv.setNgayVaoLam(ngayVaoLam);
    nv.setChucVu(chucVu);
    nv.setPassword(pass);
    nv.setTrangThai(trangThai); // Cập nhật trạng thái nhân viên

    // 7. Gọi service để cập nhật nhân viên
    NhanVienService service = new NhanVienService();
    int result = 0;
        try {
            result = service.updateNhanVien(nv);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }

    // 8. Hiển thị kết quả
    if (result > 0) {
        JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        try {
            fillTable(); // Hàm để tải lại danh sách nhân viên trên giao diện
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetForm(); // Làm sạch form sau khi cập nhật
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
       // Làm sạch các trường nhập liệu
    String newMaNV = nvsv.generateMaNhanVien(); // Sinh mã mới
    txtManv.setText(newMaNV); // Gán vào JTextField
    
    // Reset các trường nhập liệu
    txtTennv.setText(""); // Reset tên nhân viên
    txtSdt.setText(""); // Reset số điện thoại
    txtDiachi.setText(""); // Reset địa chỉ
    txtEmail.setText(""); // Reset email
    txtCddd.setText(""); // Reset CCCD
    
    // Đặt lại ngày vào làm
    DateNgayTao.setDate(null); // Reset ngày vào làm, thay vì setText("")
    
    // Đặt lại trạng thái radio button cho giới tính và trạng thái nhân viên
    rdoNam.setSelected(true); // Chọn radio button Nam
    rdoQuanLy.setSelected(true); // Chọn trạng thái "Đang làm"
    
    // Reset mật khẩu
    txtpass.setText(""); // Reset mật khẩu
        try {
            // Cập nhật bảng nhân viên
            fillTable();
            
            // Nếu có các combobox hoặc các trường khác, bạn có thể reset chúng tương tự
            // cmbTrangThai.setSelectedIndex(0); // Nếu có ComboBox hoặc các trường khác
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

      // 1. Lấy mã nhân viên từ giao diện (ví dụ: từ ô nhập liệu txtManv)
    String maNhanVien = txtManv.getText().trim();

    // 2. Kiểm tra nếu mã nhân viên rỗng
    if (maNhanVien.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Hiển thị hộp thoại xác nhận xóa nhân viên
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return; // Nếu người dùng chọn "No", không thực hiện xóa
    }

    // 4. Gọi service để xóa nhân viên (thực tế có thể là cập nhật trạng thái thành "đã nghỉ")
    NhanVienService service = new NhanVienService();
    int result = 0;
        try {
            result = service.xoaNhanVien(maNhanVien);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }

    // 5. Hiển thị thông báo kết quả
    if (result > 0) {
        JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        try {
            fillTable(); // Tải lại danh sách nhân viên sau khi xóa
        } catch (Exception ex) {
            Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetForm(); // Làm sạch form sau khi xóa
    } else {
        JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemActionPerformed
        //        txtTimkiem.setFocusable(true);
        //      txtTimkiem.requestFocusInWindow();
    }//GEN-LAST:event_txtTimkiemActionPerformed

    private void txtTimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            //            loadTable1();
//            //            txtTimkiem.setFocusable(false);
//            //            txtTimkiem.requestFocusInWindow();
    }//GEN-LAST:event_txtTimkiemKeyPressed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
//        DefaultTableModel obj = (DefaultTableModel) tblNhanVien.getModel();
//        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
//        tblNhanVien.setRowSorter(obj1);
//        obj1.setRowFilter(RowFilter.regexFilter(txtTimkiem.getText()));
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
   // Lấy từ khóa tìm kiếm từ giao diện
    String keyword = txtTimkiem.getText().trim(); // txtTimkiem là JTextField cho từ khóa

    // Kiểm tra xem từ khóa có trống không
    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã, số điện thoại hoặc tên nhân viên cần tìm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Gọi service để tìm kiếm nhân viên theo từ khóa
        List<NhanVien> resultList = nvsv.timKiem(keyword); // nvsv là đối tượng service xử lý tìm kiếm

        // Kiểm tra kết quả tìm kiếm
        if (resultList == null || resultList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với từ khóa: " + keyword, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Hiển thị kết quả tìm kiếm lên bảng
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        defaultTableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        int stt = 1; // Biến đếm số thứ tự
        for (NhanVien nv : resultList) {
            // Gọi phương thức toRowTable để lấy dữ liệu nhân viên
            Object[] row = nv.toRowTable();
            Object[] rowWithStt = new Object[row.length + 1];
            rowWithStt[0] = stt++; // Gán STT vào cột đầu tiên
            System.arraycopy(row, 0, rowWithStt, 1, row.length); // Sao chép dữ liệu còn lại
            defaultTableModel.addRow(rowWithStt); // Thêm dòng vào bảng
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnsearchActionPerformed

    private void txtManvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManvActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void txtTennvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTennvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTennvActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void rdoDangLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDangLamActionPerformed

    private void rdoNghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNghiViecActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       /* Set the Nimbus look and feel */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(NhanVienView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                // Sử dụng constructor với tham số
                new NhanVienView("Tên Nhân Viên Mặc Định").setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(NhanVienView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgayTao;
    private javax.swing.JLabel Tennv;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnsearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoDangLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCddd;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtGioitinhl;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTennv;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JLabel txtTrangthai;
    private javax.swing.JLabel txtTrangthai1;
    private javax.swing.JTextField txtpass;
    // End of variables declaration//GEN-END:variables
}

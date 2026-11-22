package ui;

import service.QuanLyThietKeVien;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.ThietKeVien;

import java.util.ArrayList;

public class MainFrame extends JFrame {
    private QuanLyThietKeVien quanLyTKV = new QuanLyThietKeVien();
    private final String FILE_NAME = "thietkevien.txt";

    private JTextField txtMaTKV, txtHoTen, txtNgaySinh, txtSoDT, txtChuyenNganh, txtPhanMemChinh, txtSoNamKN, txtLuongCoBan, txtTimKiem;

    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnSapXep, btnTimKiem;

    private JTable table;

    private DefaultTableModel tableModel;

    public MainFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  

        setTitle("Quan Ly Thiet Ke Vien");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        //BorderLayout.NORTH(Form nhập liệu)
        JPanel pnlForm = new JPanel(new GridLayout(4, 4, 10, 10));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Thong tin thiet ke vien"));

        txtMaTKV = new JTextField();
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtSoDT = new JTextField();
        txtChuyenNganh = new JTextField();
        txtPhanMemChinh = new JTextField();
        txtSoNamKN = new JTextField();
        txtLuongCoBan = new JTextField();
        txtTimKiem = new JTextField(15);

        pnlForm.add(new JLabel("Ma TKV:"));
        pnlForm.add(txtMaTKV);
        pnlForm.add(new JLabel("Ho Ten:"));
        pnlForm.add(txtHoTen);  
        pnlForm.add(new JLabel("Ngay Sinh:"));
        pnlForm.add(txtNgaySinh);
        pnlForm.add(new JLabel("So DT:"));
        pnlForm.add(txtSoDT);
        pnlForm.add(new JLabel("Chuyen Nganh:"));
        pnlForm.add(txtChuyenNganh);
        pnlForm.add(new JLabel("Phan Mem Chinh:"));
        pnlForm.add(txtPhanMemChinh);
        pnlForm.add(new JLabel("So Nam KN:"));
        pnlForm.add(txtSoNamKN);
        pnlForm.add(new JLabel("Luong Co Ban:"));
        pnlForm.add(txtLuongCoBan);
        
        add(pnlForm, BorderLayout.NORTH);
        
        //BorderLayout.CENTER(Thanh nút chức năng & Bảng dữ liệu)
        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
        
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnLamMoi = new JButton("Lam Moi");
        btnSapXep = new JButton("Sap Xep");
        btnTimKiem = new JButton("Tim Kiem");
        
        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnLamMoi);
        pnlButtons.add(btnSapXep);
        pnlButtons.add(new JLabel("Tim theo ten:"));
        pnlButtons.add(txtTimKiem);
        pnlButtons.add(btnTimKiem);

        pnlCenter.add(pnlButtons, BorderLayout.NORTH);

        String[] columns = {"Ma TKV", "Ho ten", "Ngay sinh", "So DT", "Chuyen nganh", "Phan mem", "So nam KN", "Luong CB", "Thuc linh"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);

        pnlCenter.add(scrollTable, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);

        quanLyTKV.docFile(FILE_NAME);
        loadTable(quanLyTKV.getDanhSachTKV());
        initEvents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quanLyTKV.ghiFile(FILE_NAME);
                System.exit(0);
                }
            });
        }

        private ThietKeVien getFormTKV() {
            String maTKV = txtMaTKV.getText().trim();
            String hoTen = txtHoTen.getText().trim();
            String ngaySinh = txtNgaySinh.getText().trim();
            String soDT = txtSoDT.getText().trim();
            String chuyenNganh = txtChuyenNganh.getText().trim();
            String phanMemChinh = txtPhanMemChinh.getText().trim();
            int soNamKN;
            double luongCoBan;

            if (maTKV.isEmpty() || hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap Ma TKV va Ho ten!");
                return null;
            }
                try {
                    soNamKN = Integer.parseInt(txtSoNamKN.getText().trim());

                    if (soNamKN < 0) {
                        JOptionPane.showMessageDialog(this, "Loi: So nam kinh nghiem phai lon hon hoac bang 0!");
                        return null;
                    }

                    luongCoBan = Double.parseDouble(txtLuongCoBan.getText().trim());

                    if (luongCoBan <= 0) {
                        JOptionPane.showMessageDialog(this, "Loi: Luong co ban phai lon hon 0!");
                        return null;
                    }
                
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "So nam kinh nghiem va Luong co ban phai la so hop le!");
                    return null;
                }
                return new ThietKeVien(maTKV, hoTen, ngaySinh, soDT, chuyenNganh, phanMemChinh, soNamKN, luongCoBan);
            }


        private void initEvents(){
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        txtMaTKV.setText(tableModel.getValueAt(row, 0).toString());
                        txtHoTen.setText(tableModel.getValueAt(row, 1).toString());
                        txtNgaySinh.setText(tableModel.getValueAt(row, 2).toString());
                        txtSoDT.setText(tableModel.getValueAt(row, 3).toString());
                        txtChuyenNganh.setText(tableModel.getValueAt(row, 4).toString());
                        txtPhanMemChinh.setText(tableModel.getValueAt(row, 5).toString());
                        txtSoNamKN.setText(tableModel.getValueAt(row, 6).toString());
                        txtLuongCoBan.setText(tableModel.getValueAt(row, 7).toString());

                        txtMaTKV.setEditable(false);
                    }
                }
            });

            btnThem.addActionListener(e -> {
                ThietKeVien tkv = getFormTKV();
                if (tkv != null) {
                    if (quanLyTKV.themTKV(tkv)) {
                        loadTable(quanLyTKV.getDanhSachTKV());
                        clearForm();
                        JOptionPane.showMessageDialog(this, "Them thanh cong!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Ma TKV da ton tai!");
                    }
                }
            });

            btnSua.addActionListener(e -> {
                ThietKeVien tkv = getFormTKV();
                if (tkv != null) {
                    if (quanLyTKV.suaTKV(tkv.getMaTKV(),tkv)) {
                        loadTable(quanLyTKV.getDanhSachTKV());
                        clearForm();
                        JOptionPane.showMessageDialog(this, "Cap nhat bang thanh cong!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Khong tim thay Ma TKV can sua!");
                    }
                }
            });

            btnXoa.addActionListener(e -> {
                String maTKV = txtMaTKV.getText().trim();
                if (maTKV.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui long nhap Ma TKV can xoa!");
                    return;
                }

                int result = JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa TKV nay?", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (quanLyTKV.xoaTKV(maTKV)) {
                        loadTable(quanLyTKV.getDanhSachTKV());
                        clearForm();
                        JOptionPane.showMessageDialog(this, "Xoa thanh cong!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Khong tim thay Ma TKV can xoa!");
                    }
                }
            });

            btnLamMoi.addActionListener(e -> {
                clearForm();
                loadTable(quanLyTKV.getDanhSachTKV());
            });

            btnSapXep.addActionListener(e -> {
                quanLyTKV.sapXepTheoLuongGiamDan();
                loadTable(quanLyTKV.getDanhSachTKV());
            });

            btnTimKiem.addActionListener(e -> {
                String tuKhoa = txtTimKiem.getText().trim();
                loadTable(quanLyTKV.timKiemTheoTen(tuKhoa));
            });

        }
        private void loadTable(ArrayList<ThietKeVien> danhSachTKV) {
            tableModel.setRowCount(0);
            for (ThietKeVien tkv : danhSachTKV) {
                Object[] rowData = {
                    tkv.getMaTKV(),
                    tkv.getHoTen(),
                    tkv.getNgaySinh(),
                    tkv.getSoDT(),
                    tkv.getChuyenNganh(),
                    tkv.getPhanMemChinh(),
                    tkv.getSoNamKN(),
                    String.format("%.0f", tkv.getLuongCoBan()),
                    String.format("%.0f", tkv.tinhThuNhap())
                };
                tableModel.addRow(rowData);
            }
            txtMaTKV.setEditable(true);
        }
    
        private void clearForm() {
            txtMaTKV.setText("");
            txtHoTen.setText("");
            txtNgaySinh.setText("");
            txtSoDT.setText("");
            txtChuyenNganh.setText("");
            txtPhanMemChinh.setText("");
            txtSoNamKN.setText("");
            txtLuongCoBan.setText("");
            txtTimKiem.setText("");
            txtMaTKV.setEditable(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
            });
        }
}


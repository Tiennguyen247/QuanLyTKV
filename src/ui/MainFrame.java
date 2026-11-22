package ui;

import service.QuanLyThietKeVien;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
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
        pnlButtons.add(txtTimKiem);

        pnlCenter.add(pnlButtons, BorderLayout.NORTH);

        String[] columns = {"Ma TKV", "Ho ten", "Ngay sinh", "So DT", "Chuyen nganh", "Phan mem", "So nam KN", "Luong CB", "Thuc linh"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);

        pnlCenter.add(scrollTable, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);

        quanLyTKV.docFile(FILE_NAME);
        loadTable(quanLyTKV.getDanhSachTKV());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quanLyTKV.ghiFile(FILE_NAME);
                System.exit(0);
                }
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
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
            });
        }
}


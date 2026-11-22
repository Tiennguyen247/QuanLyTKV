package ui;
import java.util.ArrayList;
import java.util.Scanner;
import model.ThietKeVien;
import service.QuanLyThietKeVien;

public class Main {   
    private static QuanLyThietKeVien quanLyTKV = new QuanLyThietKeVien();
    private static Scanner scanner = new Scanner(System.in);


    private static ThietKeVien nhapThongTinTKV() {
        int soNamKN = 0;
        double mucLuong = 0;   
        System.out.print("Nhap ma TKV: ");
        String maTKV = scanner.nextLine();

        System.out.print("Nhap ho ten: ");
        String hoTen = scanner.nextLine();

        System.out.print("Nhap ngay sinh: ");
        String ngaySinh = scanner.nextLine();

        System.out.print("Nhap so dien thoai: ");
        String soDT = scanner.nextLine();

        System.out.print("Nhap chuyen nganh: ");
        String chuyenNganh = scanner.nextLine();

        System.out.print("Nhap phan mem chinh: ");
        String phanMemChinh = scanner.nextLine();
        
        while (true) {
            try {
                    System.out.print("Nhap so nam kinh nghiem: ");
                    soNamKN = Integer.parseInt(scanner.nextLine());

                    if (soNamKN < 0) {
                        System.out.println("Loi: So nam kinh nghiem phai lon hon hoac bang 0!");
                        continue;
                    }

                    System.out.print("Nhap muc luong: ");
                    mucLuong = Double.parseDouble(scanner.nextLine());

                    if (mucLuong <= 0) {
                        System.out.println("Loi: Muc luong phai lon hon 0!");
                        continue;
                    }
                    break;
                
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap lai!");
            }
        }
        return new ThietKeVien(maTKV, hoTen, ngaySinh, soDT, chuyenNganh, phanMemChinh, soNamKN, mucLuong);
    }

    private static void hienThiMenu(){
        System.out.println("--Menu--");
        System.out.println("1. Them thiet ke vien");
        System.out.println("2. Hien thi danh sach thiet ke vien");
        System.out.println("3. Sua thong tin thiet ke vien");
        System.out.println("4. Xoa thiet ke vien");
        System.out.println("5. Tim kiem theo ten thiet ke vien");
        System.out.println("6. Sap xep danh sach thiet ke vien theo luong giam dan");
        System.out.println("0. Thoat");
    }

    public static void main(String[] args) {
        int chon;
        quanLyTKV.docFile("thietkevien.txt");
        do {
            hienThiMenu();
            System.out.print("Chon chuc nang: ");
            try {
                chon = Integer.parseInt(scanner.nextLine());
                switch (chon) {
                    case 1:
                        ThietKeVien tkv = nhapThongTinTKV();
                        if (quanLyTKV.themTKV(tkv)) {
                            System.out.println("Them thiet ke vien thanh cong!");
                        } else {
                            System.out.println("Ma TKV da ton tai. Them that bai!");
                        }
                        break;
                    case 2:
                        ArrayList<ThietKeVien> danhSach = quanLyTKV.getDanhSachTKV();
                        if (danhSach.isEmpty()) {
                            System.out.println("Danh sach thiet ke vien rong.");
                        } else {
                            for (ThietKeVien t : danhSach) {
                                System.out.println(t);
                            }
                        }
                        break;
                    case 3:
                        System.out.print("Nhap ma TKV can sua: ");
                        String maCanSua = scanner.nextLine();
                        ThietKeVien tkvSua = nhapThongTinTKV();
                        if (quanLyTKV.suaTKV(maCanSua, tkvSua)) {
                            System.out.println("Sua thong tin thiet ke vien thanh cong!");
                        } else {
                            System.out.println("Khong tim thay thiet ke vien voi ma TKV nay.");
                        }
                        break;
                    case 4:
                        System.out.print("Nhap ma TKV can xoa: ");
                        String maCanXoa = scanner.nextLine();
                        if (quanLyTKV.xoaTKV(maCanXoa)) {
                            System.out.println("Xoa thiet ke vien thanh cong!");
                        } else {
                            System.out.println("Khong tim thay thiet ke vien voi ma TKV nay.");
                        }
                        break;
                    case 5:
                        System.out.print("Nhap tu khoa tim kiem: ");
                        String tuKhoa = scanner.nextLine();
                        ArrayList<ThietKeVien> ketQua = quanLyTKV.timKiemTheoTen(tuKhoa);
                        if (ketQua.isEmpty()) {
                            System.out.println("Khong tim thay thiet ke vien nao voi ten nay.");
                        } else {
                            for (ThietKeVien t : ketQua) {
                                System.out.println(t);
                            }
                        }
                        break;
                    case 6:
                        quanLyTKV.sapXepTheoLuongGiamDan();
                        System.out.println("Danh sach thiet ke vien da duoc sap xep theo luong giam dan.");
                        break;
                    case 0:
                        quanLyTKV.ghiFile("thietkevien.txt");
                        System.out.println("Thoat chuong trinh.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap mot so nguyen!");
                chon = -1;
            }
        } while(chon != 0);
    }
}

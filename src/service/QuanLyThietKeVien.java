package service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import model.ThietKeVien;

public class QuanLyThietKeVien {
    private ArrayList<ThietKeVien> danhSachTKV;
    public QuanLyThietKeVien() {
        this.danhSachTKV = new ArrayList<>();
    }

    public ThietKeVien timKiemTheoMa(String maTKV) {
        for (ThietKeVien tkv : danhSachTKV) {
            if (tkv.getMaTKV() != null && tkv.getMaTKV().equalsIgnoreCase(maTKV)) {
                return tkv;
            }
        }
        return null;    
    }

    public ArrayList<ThietKeVien> timKiemTheoTen(String tuKhoa) {
        ArrayList<ThietKeVien> ketQua = new ArrayList<>();

        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            return ketQua;
        }

        String tuKhoaThuong = tuKhoa.toLowerCase().trim();

        for (ThietKeVien tkv : danhSachTKV) {
            if (tkv.getHoTen() != null && tkv.getHoTen().toLowerCase().contains(tuKhoaThuong)) {
                ketQua.add(tkv);
            }
        }
        return ketQua;
    }

    public boolean themTKV(ThietKeVien tkv) {
        if (timKiemTheoMa(tkv.getMaTKV()) == null) {
            danhSachTKV.add(tkv);
            return true;
        }
        return false;
    }

    public boolean suaTKV(String maTKV,ThietKeVien tkv) {
        ThietKeVien tkvCanSua = timKiemTheoMa(maTKV);
        if (tkvCanSua != null) {
            tkvCanSua.setHoTen(tkv.getHoTen());
            tkvCanSua.setNgaySinh(tkv.getNgaySinh());
            tkvCanSua.setSoDT(tkv.getSoDT());
            tkvCanSua.setChuyenNganh(tkv.getChuyenNganh());
            tkvCanSua.setPhanMemChinh(tkv.getPhanMemChinh());
            tkvCanSua.setSoNamKN(tkv.getSoNamKN());
            tkvCanSua.setLuongCoBan(tkv.getLuongCoBan());
            return true;
        }
        return false;
    }

    public boolean xoaTKV(String maTKV) {
        ThietKeVien tkvCanXoa = timKiemTheoMa(maTKV);
        if (tkvCanXoa != null) {
            danhSachTKV.remove(tkvCanXoa);
            return true;
        }   
        return false; 
    } 

    public void sapXepTheoLuongGiamDan() {
        danhSachTKV.sort((a, b) -> Double.compare(b.getLuongCoBan(), a.getLuongCoBan()));
    }

    public ArrayList<ThietKeVien> getDanhSachTKV() {
        return this.danhSachTKV;
    }    

    public void ghiFile(String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (ThietKeVien tkv : danhSachTKV) {
                String line = tkv.getMaTKV() + ";" + tkv.getHoTen() + ";" + tkv.getNgaySinh() + ";" + tkv.getSoDT() + ";" + tkv.getChuyenNganh() + ";" + tkv.getPhanMemChinh() + ";" + tkv.getSoNamKN() + ";" + tkv.getLuongCoBan();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Ghi file thanh cong!");
        } catch (IOException e) {
            System.err.println("Loi khi ghi file: " + e.getMessage());
        }        
    }

    public void docFile(String tenFile) {
        File file = new File(tenFile);
        
        if (!file.exists()) {
            System.out.println("File chua ton tai");
            return;
        }

        danhSachTKV.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(";");

                if (parts.length == 8) { 
                    String maTKV = parts[0].trim();
                    String hoTen = parts[1].trim();
                    String ngaySinh = parts[2].trim();
                    String soDT = parts[3].trim();                    
                    String chuyenNganh = parts[4].trim();
                    String phanMemChinh = parts[5].trim();
                    int soNamKN = Integer.parseInt(parts[6].trim());
                    double luongCoBan = Double.parseDouble(parts[7].trim());

                    ThietKeVien tkv = new ThietKeVien(maTKV, hoTen, ngaySinh, soDT, chuyenNganh, phanMemChinh, soNamKN, luongCoBan);
                    danhSachTKV.add(tkv);
                }
            }
            System.out.println("Doc du lieu tu file thanh cong!");
        } catch (IOException e) {
            System.err.println("Loi: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Loi: " + e.getMessage());
        }      
    }
    public boolean xuatFileCSV(String tenFile, ArrayList<ThietKeVien> danhSach) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(tenFile), StandardCharsets.UTF_8))) {
            bw.write('\uFEFF');
            bw.write("Ma TKV,Ho Ten,Ngay Sinh,So DT,Chuyen Nganh,Phan Mem Chinh,So Nam KN,Luong Co Ban,Thuc Linh");
            bw.newLine();

            for (ThietKeVien tkv : danhSach) {
                String line = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d,%.0f,%.0f",
                        tkv.getMaTKV(),
                        tkv.getHoTen(),
                        tkv.getNgaySinh(),
                        tkv.getSoDT(),
                        tkv.getChuyenNganh(),
                        tkv.getPhanMemChinh(),
                        tkv.getSoNamKN(),
                        tkv.getLuongCoBan(),
                        tkv.tinhThuNhap());
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Loi khi xuat file CSV: " + e.getMessage());
            return false;
        }
    }
}

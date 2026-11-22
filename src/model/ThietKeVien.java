package model;
public class ThietKeVien {
    private String maTKV;
    private String hoTen;
    private String ngaySinh;
    private String soDT;
    private String chuyenNganh;
    private String phanMemChinh;
    private int soNamKN;
    private double luongCoBan;

    public ThietKeVien() {

    }

    public ThietKeVien(String maTKV, String hoTen, String ngaySinh, String soDT, String chuyenNganh, String phanMemChinh, int soNamKN, double luongCoBan) {
        this.maTKV = maTKV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDT = soDT;
        this.chuyenNganh = chuyenNganh;
        this.phanMemChinh = phanMemChinh;
        this.soNamKN = soNamKN;
        this.luongCoBan = luongCoBan;
    }

    public String getMaTKV() {
        return maTKV;
    }
    public String getHoTen() {
        return hoTen;
    }
    public String getNgaySinh() {
        return ngaySinh;
    }
    public String getSoDT() {
        return soDT;
    }
    public String getChuyenNganh() {
        return chuyenNganh;
    }
    public String getPhanMemChinh() {
        return phanMemChinh;
    }
    public int getSoNamKN() {
        return soNamKN;
    }
    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setMaTKV(String maTKV) {
        this.maTKV = maTKV;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }
    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }
    public void setPhanMemChinh(String phanMemChinh) {
        this.phanMemChinh = phanMemChinh;
    }
    public void setSoNamKN(int soNamKN) {
        this.soNamKN = soNamKN;
    }
    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double tinhThuNhap() {
        return luongCoBan + (soNamKN * 500000);
    }

    @Override public String toString() {
        return "ThietKeVien{" +
                "'maTKV: " + maTKV + '\'' +
                ", hoTen: " + hoTen + '\'' +
                ", ngaySinh: " + ngaySinh + '\'' +
                ", soDT: " + soDT + '\'' +
                ", chuyenNganh: " + chuyenNganh + '\'' +
                ", phanMemChinh: " + phanMemChinh + '\'' +
                ", soNamKN: " + soNamKN +
                ", luongCoBan: " + luongCoBan +
                '}';
    }
}
package T9_hdt;

import java.io.Serializable;

public abstract class Sach implements Serializable {
    protected String maSach;
    protected String tenSach;
    protected String tacGia;
    protected int namXB;
    protected int soLuong;
    protected double donGia;

    public Sach() {}

    public Sach(String maSach, String tenSach, String tacGia, int namXB, int soLuong, double donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public abstract double tinhGiaBan();

    public String getMaSach() { return maSach; }
    public String getTenSach() { return tenSach; }
    public String getTacGia() { return tacGia; }
    public int getNamXB() { return namXB; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-20s %-5d %-5d %-10.2f",
                maSach, tenSach, tacGia, namXB, soLuong, tinhGiaBan());
    }

    // 🔸 MỞ RỘNG: hỗ trợ xuất dữ liệu CSV
    public String toCSV() {
        return this.getClass().getSimpleName().toUpperCase() + "," +
               maSach + "," + tenSach + "," + tacGia + "," + namXB + "," +
               soLuong + "," + donGia;
    }
}

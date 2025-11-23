// MỞ RỘNG: Lớp con kế thừa từ Sach, thêm thể loại và thuộc tính series
// Có cách tính giá riêng → thể hiện tính đa hình

import java.util.Scanner;

public class SachTieuThuyet extends Sach {

    private String theLoai;
    private boolean laSeries;

    public SachTieuThuyet() {
        super();
    }

    public SachTieuThuyet(String ma, String td, String tg, int nam, int sl, double gia,
            String tl, boolean ls) {
        super(ma, td, tg, nam, sl, gia);
        this.theLoai = tl;
        this.laSeries = ls;
    }

    // MỞ RỘNG: Hàm nhập dành riêng cho sách tiểu thuyết
    public void nhap(Scanner sc) {

        // nhập phần chung (đã có validate trong lớp Sach)
        super.nhapThongTinChung(sc);

        // thể loại: chữ + số + khoảng trắng
        do {
            System.out.print("The loai: ");
            theLoai = sc.nextLine().trim();
        } while (!theLoai.matches("[a-zA-Z0-9 ]+"));

        // series: true/false
        System.out.print("Co phai series (true/false): ");
        laSeries = Boolean.parseBoolean(sc.nextLine());
    }

    @Override
    public double tinhGiaBan() {
        // MỞ RỘNG: nếu là series thì cộng thêm 15000
        return giaCoBan + (laSeries ? 15000 : 0);
    }

    // MỞ RỘNG: getter / setter cho theLoai, laSeries (phục vụ cập nhật)
    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public boolean isLaSeries() {
        return laSeries;
    }

    public void setLaSeries(boolean laSeries) {
        this.laSeries = laSeries;
    }

    @Override
    public String toString() {
        return "\n===== SACH TIEU THUYET =====" +
                super.toString() +
                "\nThe loai: " + theLoai +
                "\nLa series: " + (laSeries ? "Co" : "Khong");
    }
}

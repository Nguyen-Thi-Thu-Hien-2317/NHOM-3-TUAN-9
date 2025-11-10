import java.io.*;
import java.util.Scanner;

// Lớp trừu tượng Sach thể hiện tính: trừu tượng + đóng gói
// Dùng để gom nhóm các thuộc tính và hành vi chung cho mọi loại sách
public abstract class SachTuan9 implements IGiaBan, IKiemKe, Serializable {
    // ===== Thuộc tính (Đóng gói dữ liệu) =====
    protected String maSach, tieuDe, tacGia, viTri;
    protected int namXuatBan, soLuong;
    protected double giaCoBan;

    // ===== Constructor =====
    public SachTuan9() {
    }

    public SachTuan9(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.giaCoBan = giaCoBan;
        this.viTri = "Chua xac dinh";
    }

    // Hàm nhập thông tin chung (giúp giảm trùng lặp code)
    public void nhapThongTinChung(Scanner sc) {
        System.out.print("Ma sach: ");
        maSach = sc.nextLine();
        System.out.print("Tua de: ");
        tieuDe = sc.nextLine();
        System.out.print("Tac gia: ");
        tacGia = sc.nextLine();
        System.out.print("Nam XB: ");
        namXuatBan = Integer.parseInt(sc.nextLine());
        System.out.print("So luong: ");
        soLuong = Integer.parseInt(sc.nextLine());
        System.out.print("Gia co ban: ");
        giaCoBan = Double.parseDouble(sc.nextLine());
        System.out.print("Vi tri: ");
        viTri = sc.nextLine();
    }

    // Phương thức trừu tượng → thể hiện tính đa hình
    public abstract double tinhGiaBan();

    // Override từ IKiemKe
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return soLuong >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        this.viTri = viTriMoi;
        System.out.println("Đã cập nhật vị trí: " + viTriMoi);
    }

    // Hỗ trợ lưu file CSV
    public String toCSV() {
        return getClass().getSimpleName() + "," + maSach + "," + tieuDe + "," + tacGia + "," +
                namXuatBan + "," + soLuong + "," + giaCoBan + "," + viTri;
    }

    // Getter / Setter thể hiện tính "đóng gói""""
    public String getMaSach() {
        return maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGiaCoBan() {
        return giaCoBan;
    }

    public String getViTri() {
        return viTri;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaCoBan(double giaCoBan) {
        this.giaCoBan = giaCoBan;
    }

    @Override
    public String toString() {
        return "\nMa sach: " + maSach +
                "\nTua de: " + tieuDe +
                "\nTac gia: " + tacGia +
                "\nNam xuat ban: " + namXuatBan +
                "\nSo luong: " + soLuong +
                "\nGia co ban: " + giaCoBan +
                "\nVi tri: " + viTri +
                "\nGia ban: " + tinhGiaBan();
    }
}

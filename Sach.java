import java.io.*;
import java.util.Scanner;

// LỚP TRỪU TƯỢNG SÁCH – DÙNG CHUNG CHO MỌI LOẠI SÁCH
// MỞ RỘNG: Thêm validate nhập liệu + quản lý vị trí (tầng/kệ/kho)
// Serializable: Cho phép viết đối tượng Sach xuống file, phục hồi dữ liệu kho sách khi mở chương trình lại

public abstract class Sach implements IGiaBan, IKiemKe, Serializable {

    protected String maSach, tieuDe, tacGia, viTri;
    protected int namXuatBan, soLuong;
    protected double giaCoBan;

    public Sach() {
    }

    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.giaCoBan = giaCoBan;
        this.viTri = "chua xac dinh"; // MỞ RỘNG: vị trí mặc định
    }

    // MỞ RỘNG: Validate nhập liệu
    public void nhapThongTinChung(Scanner sc) {

        // ma sach: chu + so, khong ky tu dac biet
        do {
            System.out.print("Ma sach: ");
            maSach = sc.nextLine().trim();
        } while (!maSach.matches("[a-zA-Z0-9]+"));

        // tua de: chu + so + khoang trang
        do {
            System.out.print("Tua de: ");
            tieuDe = sc.nextLine().trim();
        } while (!tieuDe.matches("[a-zA-Z0-9 ]+"));

        // tac gia: chi chu + khoang trang
        do {
            System.out.print("Tac gia: ");
            tacGia = sc.nextLine().trim();
        } while (!tacGia.matches("[a-zA-Z ]+"));

        // nam XB: >= 0 va <= 2025
        while (true) {
            try {
                System.out.print("Nam xuat ban: ");
                namXuatBan = Integer.parseInt(sc.nextLine());
                if (namXuatBan <= 0 || namXuatBan > 2025) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Nam xuat ban phai > 0 va <= 2025!");
            }
        }

        // so luong: khong am
        while (true) {
            try {
                System.out.print("So luong: ");
                soLuong = Integer.parseInt(sc.nextLine());
                if (soLuong < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("So luong phai >= 0!");
            }
        }

        // gia co ban: khong am
        while (true) {
            try {
                System.out.print("Gia co ban: ");
                giaCoBan = Double.parseDouble(sc.nextLine());
                if (giaCoBan < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Gia co ban phai >= 0!");
            }
        }

        // vi tri: phai chua tang/ke/kho
        do {
            System.out.print("Vi tri (tang/ke/kho): ");
            viTri = sc.nextLine().toLowerCase().trim();
        } while (!(viTri.contains("tang") || viTri.contains("ke") || viTri.contains("kho")));
    }

    // phuong thuc tru tuong: moi loai sach tinh gia ban khac nhau
    public abstract double tinhGiaBan();

    // kiem ke
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return soLuong >= soLuongToiThieu;
    }

    // cap nhat vi tri
    @Override
    public void capNhatViTri(String viTriMoi) {
        this.viTri = viTriMoi;
        System.out.println("Da cap nhat vi tri!");
    }

    // MỞ RỘNG: Xuat CSV theo dung thu tu truong yeu cau
    public String toCSV() {
        return getClass().getSimpleName() + "," +
                maSach + "," + tieuDe + "," + tacGia + "," +
                namXuatBan + "," + soLuong + "," + giaCoBan + "," + viTri;
    }

    // getter-setter
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

    public void setViTri(String v) {
        this.viTri = v;
    }

    public void setTieuDe(String t) {
        this.tieuDe = t;
    }

    public void setTacGia(String t) {
        this.tacGia = t;
    }

    public void setSoLuong(int sl) {
        this.soLuong = sl;
    }

    public void setGiaCoBan(double g) {
        this.giaCoBan = g;
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

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IQuanLySach quanLy = new QuanLySachImpl();

        Sach sachGT = new SachGiaoTrinh("GT001", "Toan Cao Cap 1", "Nguyen Van A", 2022, 15, 120000, "Toan", "Dai Hoc");
        Sach sachTT = new SachTieuThuyet("TT001", "Huyet Chien", "Tran Thi B", 2023, 5, 95000, "Hanh Dong", true);

        quanLy.themSach(sachGT);
        quanLy.themSach(sachTT);

        while (true) {
            System.out.println("\n===== MENU QUAN LY SACH =====");
            System.out.println("1. Hien thi tat ca sach");
            System.out.println("2. Kiem tra/Cap nhat vi tri sach (IKiemKe)");
            System.out.println("3. Bao cao ton kho toi thieu");
            System.out.println("4. Xoa sach theo ma");
            System.out.println("5. Tim sach co gia ban cao nhat"); // ✅ mới
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            String chon = sc.nextLine();

            switch (chon) {
                case "1":
                    quanLy.hienThiDanhSach();
                    break;
                case "2":
                    sachGT.capNhatViTri("Kho A1");
                    System.out.print("Nhap so luong toi thieu muon kiem tra: ");
                    int soLuong = Integer.parseInt(sc.nextLine());
                    if (sachTT.kiemTraTonKho(soLuong)) {
                        System.out.println("Sach [" + sachTT.getTieuDe() + "] dat ton kho.");
                    } else {
                        System.out.println("Sach [" + sachTT.getTieuDe() + "] khong dat ton kho.");
                    }
                    break;
                case "3":
                    quanLy.baoCaoTonKho(10);
                    break;
                case "4":
                    System.out.print("Nhap ma sach can xoa: ");
                    quanLy.xoaSach(sc.nextLine());
                    break;
                case "5":
                    quanLy.timSachGiaCaoNhat();
                    break;
                case "0":
                    System.out.println("Thoat chuong trinh...");
                    sc.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }
}

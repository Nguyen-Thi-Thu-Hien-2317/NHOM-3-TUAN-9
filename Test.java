package T9_hdt;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        QuanLySach ql = new QuanLySach();
        Scanner sc = new Scanner(System.in);

        // 🔸 MỞ RỘNG: thêm dữ liệu mẫu
        ql.themSach(new SachGiaoTrinh("GT01", "Lập trình Java", "Nguyễn Văn A", 2022, 5, 100000, "Công nghệ thông tin"));
        ql.themSach(new SachTieuThuyet("TT01", "Mắt biếc", "Nguyễn Nhật Ánh", 2019, 10, 80000, "Tình cảm"));

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Tìm theo mã");
            System.out.println("3. Tìm theo tác giả");
            System.out.println("4. Sắp xếp theo giá");
            System.out.println("5. Tổng giá trị kho");
            System.out.println("6. Lưu CSV");
            System.out.println("7. Nạp CSV");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> ql.hienThi();
                case 2 -> {
                    System.out.print("Nhập mã: ");
                    String ma = sc.nextLine();
                    System.out.println(ql.timSachTheoMa(ma));
                }
                case 3 -> {
                    System.out.print("Nhập tên tác giả: ");
                    String tacGia = sc.nextLine();
                    ql.timSachTheoTacGia(tacGia);
                }
                case 4 -> ql.sapXepTheoGia(true);
                case 5 -> System.out.println("Tổng giá trị kho: " + ql.tongGiaTriKho());
                case 6 -> ql.luuCSV("sach.csv");
                case 7 -> ql.napTuCSV("sach.csv");
                case 0 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}


import java.util.*;

// Lớp Test thể hiện tínhđa hình, đóng gói và xử lý ngoại lệ
// Đã thêm try-catch cho nhập liệu và thao tác file
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IQuanLySach ql = new QuanLySachImpl();
        int chon = -1;

        do {
            try {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Them sach");
                System.out.println("2. Hien thi danh sach");
                System.out.println("3. Tim kiem");
                System.out.println("4. Xoa sach");
                System.out.println("5. Cap nhat thong tin");
                System.out.println("6. Sap xep theo gia");
                System.out.println("7. Tong gia tri kho");
                System.out.println("8. Luu/Nap CSV");
                System.out.println("0. Thoat");
                System.out.print("Chon chuc nang: ");
                chon = Integer.parseInt(sc.nextLine());

                switch (chon) {
                    case 1 -> themSach(sc, ql);
                    case 2 -> ql.hienThiTatCa();
                    case 3 -> timKiem(sc, ql);
                    case 4 -> {
                        System.out.print("Nhap ma can xoa: ");
                        System.out.println(ql.xoaSach(sc.nextLine()) ? "Da xoa thanh cong" : "Khong tim thay sach");
                    }
                    case 5 -> capNhat(sc, ql);
                    case 6 -> ql.sapXepTheoGia(true);
                    case 7 -> System.out.println("Tong gia tri kho: " + ql.tinhTongGiaTriKho());
                    case 8 -> luuVaNap(sc, ql);
                    case 0 -> System.out.println("Tam biet!");
                    default -> System.out.println("Lua chon khong hop le, vui long nhap lai!");
                }
            } catch (NumberFormatException e) {
                // Bắt lỗi nhập sai kiểu số
                System.out.println("Loi: Vui long nhap so hop le!");
            } catch (Exception e) {
                // Bắt các lỗi không mong đợi khác
                System.out.println("Da xay ra loi: " + e.getMessage());
            }
        } while (chon != 0);
    }

    // Hàm thêm sách có xử lý lỗi nhập liệu
    private static void themSach(Scanner sc, IQuanLySach ql) {
        try {
            System.out.println("=== THEM SACH ===");
            System.out.print("Chon loai sach (1. Giao trinh | 2. Tieu thuyet): ");
            int loai = Integer.parseInt(sc.nextLine());
            System.out.print("Nhap so luong sach muon them: ");
            int soLuongNhap = Integer.parseInt(sc.nextLine());

            for (int i = 1; i <= soLuongNhap; i++) {
                System.out.println("\n--- Nhap thong tin sach thu " + i + " ---");

                // Nhập mã và tựa đề trước
                System.out.print("Ma sach: ");
                String ma = sc.nextLine();
                System.out.print("Tua de: ");
                String td = sc.nextLine();

                System.out.print("Tac gia: ");
                String tg = sc.nextLine();
                System.out.print("Nam xuat ban: ");
                int namXB = Integer.parseInt(sc.nextLine());
                System.out.print("So luong: ");
                int sl = Integer.parseInt(sc.nextLine());
                System.out.print("Gia co ban: ");
                double gia = Double.parseDouble(sc.nextLine());
                System.out.print("Vi tri: ");
                String vt = sc.nextLine();

                if (loai == 1) {
                    System.out.print("Mon hoc: ");
                    String mh = sc.nextLine();
                    System.out.print("Cap do: ");
                    String cd = sc.nextLine();
                    ql.themSach(new SachGiaoTrinhTuan9(ma, td, tg, namXB, sl, gia, mh, cd));
                } else {
                    System.out.print("The loai: ");
                    String tl = sc.nextLine();
                    System.out.print("Co phai series (true/false): ");
                    boolean ls = Boolean.parseBoolean(sc.nextLine());
                    ql.themSach(new SachTieuThuyetTuan9(ma, td, tg, namXB, sl, gia, tl, ls));
                }

                System.out.println("Da them sach: " + ma + " - " + td);
            }
        } catch (NumberFormatException e) {
            System.out.println("Loi: Du lieu so khong hop le, vui long nhap lai!");
        } catch (Exception e) {
            System.out.println("Loi khi them sach: " + e.getMessage());
        }
    }

    private static void timKiem(Scanner sc, IQuanLySach ql) {
        try {
            System.out.println("1. Theo ma | 2. Theo tua de | 3. Theo tac gia");
            System.out.print("Chon chuc nang: ");
            int k = Integer.parseInt(sc.nextLine());
            if (k == 1) {
                System.out.print("Nhap ma: ");
                System.out.println(ql.timKiemTheoMa(sc.nextLine()));
            } else if (k == 2) {
                System.out.print("Nhap tu khoa: ");
                ql.timKiemTheoTieuDe(sc.nextLine()).forEach(System.out::println);
            } else if (k == 3) {
                System.out.print("Nhap ten tac gia: ");
                ql.timKiemTheoTacGia(sc.nextLine()).forEach(System.out::println);
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        } catch (Exception e) {
            System.out.println("Loi khi tim kiem: " + e.getMessage());
        }
    }

    private static void capNhat(Scanner sc, IQuanLySach ql) {
        try {
            System.out.print("Nhap ma can cap nhat: ");
            String ma = sc.nextLine();
            SachTuan9 thongTinMoi = new SachGiaoTrinhTuan9();
            thongTinMoi.nhapThongTinChung(sc);
            boolean ok = ql.capNhatSach(ma, thongTinMoi);
            if (ok)
                System.out.println("Cap nhat thanh cong!");
            else
                System.out.println("Khong tim thay ma sach!");
        } catch (Exception e) {
            System.out.println("Loi khi cap nhat: " + e.getMessage());
        }
    }

    private static void luuVaNap(Scanner sc, IQuanLySach ql) {
        try {
            System.out.println("1. Luu CSV | 2. Nap CSV");
            System.out.print("Chon chuc nang: ");
            int c = Integer.parseInt(sc.nextLine());
            if (c == 1)
                ql.luuCSV("sach.csv");
            else if (c == 2)
                ql.napTuCSV("sach.csv");
            else
                System.out.println("Lua chon khong hop le!");
        } catch (Exception e) {
            System.out.println("Loi khi xu ly file: " + e.getMessage());
        }
    }
}

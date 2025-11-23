// CHƯƠNG TRÌNH CHÍNH TEST TOÀN BỘ HỆ THỐNG
// MỞ RỘNG: Có validate nhập liệu, thao tác menu, xử lý ngoại lệ đầy đủ

import java.util.*;

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
                    case 4 -> xoaSach(sc, ql);
                    case 5 -> capNhat(sc, ql);
                    case 6 -> sapXepTheoGiaMenu(sc, ql);
                    case 7 -> tongGiaTriKho(ql);
                    case 8 -> luuVaNap(sc, ql);
                    case 0 -> System.out.println("Tam biet!");
                    default -> System.out.println("Lua chon khong hop le!");
                }

            } catch (NumberFormatException e) {
                // BẮT LỖI: người dùng nhập ký tự không phải số
                System.out.println("Loi: vui long nhap so hop le!");
            }
        } while (chon != 0);
    }

    private static String nhapChuSo(Scanner sc, String msg) {
        String s;
        do {
            System.out.print(msg);
            s = sc.nextLine().trim();
        } while (!s.matches("[a-zA-Z0-9 ]+"));
        return s;
    }

    private static String nhapChu(Scanner sc, String msg) {
        String s;
        do {
            System.out.print(msg);
            s = sc.nextLine().trim();
        } while (!s.matches("[a-zA-Z ]+"));
        return s;
    }

    private static int nhapSoNguyen(Scanner sc, String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int x = Integer.parseInt(sc.nextLine());
                if (x < min || x > max) {
                    throw new Exception();
                }
                return x;
            } catch (Exception e) {
                System.out.println("Gia tri phai nam trong khoang hop le!");
            }
        }
    }

    private static double nhapSoThuc(Scanner sc, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double x = Double.parseDouble(sc.nextLine());
                if (x < 0) {
                    throw new Exception();
                }
                return x;
            } catch (Exception e) {
                System.out.println("Gia tri phai >= 0!");
            }
        }
    }

    private static String nhapViTri(Scanner sc) {
        String s;
        do {
            System.out.print("Vi tri (chua tu tang/ke/kho): ");
            s = sc.nextLine().trim().toLowerCase();
        } while (!(s.contains("tang") || s.contains("ke") || s.contains("kho")));
        return s;
    }

    // Thêm sách
    private static void themSach(Scanner sc, IQuanLySach ql) {

        try {
            System.out.print("Loai sach (1. Giao trinh | 2. Tieu thuyet): ");
            int loai = Integer.parseInt(sc.nextLine());

            System.out.print("So luong sach muon them: ");
            int n = Integer.parseInt(sc.nextLine());

            for (int i = 1; i <= n; i++) {

                System.out.println("\n--- Sach thu " + i + " ---");

                // MỞ RỘNG: Nhập mã và tựa đề trước
                String ma = nhapChuSo(sc, "Ma sach: ");
                String td = nhapChuSo(sc, "Tua de: ");
                String tg = nhapChu(sc, "Tac gia: ");
                int nam = nhapSoNguyen(sc, "Nam XB: ", 0, 2025);
                int sl = nhapSoNguyen(sc, "So luong: ", 0, Integer.MAX_VALUE);
                double gia = nhapSoThuc(sc, "Gia co ban: ");
                String vt = nhapViTri(sc);

                if (loai == 1) {
                    String mh = nhapChuSo(sc, "Mon hoc: ");
                    String cd;
                    do {
                        System.out.print("Cap do (dai hoc/pho thong): ");
                        cd = sc.nextLine().trim().toLowerCase();
                    } while (!(cd.equals("dai hoc") || cd.equals("pho thong")));

                    Sach s = new SachGiaoTrinh(ma, td, tg, nam, sl, gia, mh, cd);
                    s.setViTri(vt);
                    ql.themSach(s);

                } else if (loai == 2) {
                    String tl = nhapChuSo(sc, "The loai: ");
                    System.out.print("Co phai series (true/false): ");
                    boolean ls = Boolean.parseBoolean(sc.nextLine());
                    Sach s = new SachTieuThuyet(ma, td, tg, nam, sl, gia, tl, ls);
                    s.setViTri(vt);
                    ql.themSach(s);
                }

                System.out.println("Da them sach thanh cong: " + ma + " - " + td);
            }

        } catch (Exception e) {
            System.out.println("Loi khi them sach!");
        }
    }

    // Tìm kiếm
    private static void timKiem(Scanner sc, IQuanLySach ql) {
        try {
            System.out.println("1. Theo ma");
            System.out.println("2. Theo tua de");
            System.out.println("3. Theo tac gia");
            System.out.print("Chon: ");

            int c = Integer.parseInt(sc.nextLine());

            switch (c) {
                case 1 -> {
                    System.out.print("Nhap ma: ");
                    System.out.println(ql.timKiemTheoMa(sc.nextLine()));
                }
                case 2 -> {
                    System.out.print("Nhap tu khoa tua de: ");
                    ql.timKiemTheoTieuDe(sc.nextLine()).forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Nhap ten tac gia: ");
                    ql.timKiemTheoTacGia(sc.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Khong hop le!");
            }

        } catch (Exception e) {
            System.out.println("Loi tim kiem!");
        }
    }

    // Xóa sách
    private static void xoaSach(Scanner sc, IQuanLySach ql) {
        System.out.print("Nhap ma sach can xoa: ");
        System.out.println(ql.xoaSach(sc.nextLine())
                ? "Da xoa thanh cong!"
                : "Khong tim thay sach!");
    }

    // Cập nhật
    private static void capNhat(Scanner sc, IQuanLySach ql) {

        System.out.print("Nhap ma sach can cap nhat: ");
        String ma = sc.nextLine();

        Sach s = ql.timKiemTheoMa(ma);

        if (s == null) {
            System.out.println("Khong tim thay sach!");
            return;
        }

        // ===== CHO NGƯỜI DÙNG CHỌN LOẠI SÁCH =====
        System.out.println("Chon loai sach muon cap nhat:");
        System.out.println("1. Sach giao trinh");
        System.out.println("2. Sach tieu thuyet");
        System.out.print("Chon: ");
        int loai = Integer.parseInt(sc.nextLine());

        if (loai == 1) {

            SachGiaoTrinh sg = new SachGiaoTrinh();
            sg.nhap(sc); // nhập toàn bộ dữ liệu mới
            sg.setViTri(s.getViTri()); // giữ lại vị trí cũ nếu không nhập

            ql.capNhatSach(ma, sg);

        } else if (loai == 2) {

            SachTieuThuyet st = new SachTieuThuyet();
            st.nhap(sc);
            st.setViTri(s.getViTri());

            ql.capNhatSach(ma, st);

        } else {
            System.out.println("Lua chon khong hop le!");
            return;
        }

        System.out.println("Cap nhat thanh cong!");
    }

    // Sắp xếp theo giá
    private static void sapXepTheoGiaMenu(Scanner sc, IQuanLySach ql) {
        try {
            System.out.println("1. Sap xep tang dan");
            System.out.println("2. Sap xep giam dan");
            System.out.print("Chon: ");
            int c = Integer.parseInt(sc.nextLine());
            if (c == 1) {
                ql.sapXepTheoGia(true);
            } else if (c == 2) {
                ql.sapXepTheoGia(false);
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        } catch (Exception e) {
            System.out.println("Loi sap xep!");
        }
    }

    // Tổng giá trị kho
    private static void tongGiaTriKho(IQuanLySach ql) {
        double tong = ql.tinhTongGiaTriKho();
        // MỞ RỘNG: định dạng số không dùng dạng khoa học (E)
        System.out.println("Tong gia tri kho: " + String.format("%,.0f", tong));
    }

    // CSV
    private static void luuVaNap(Scanner sc, IQuanLySach ql) {
        try {
            System.out.println("1. Luu CSV");
            System.out.println("2. Nap CSV");
            System.out.print("Chon: ");

            int c = Integer.parseInt(sc.nextLine());

            if (c == 1) {
                ql.luuCSV("sach.csv");
            } else if (c == 2) {
                ql.napTuCSV("sach.csv");
            } else {
                System.out.println("Khong hop le!");
            }

        } catch (Exception e) {
            System.out.println("Loi thao tac file!");
        }
    }
}

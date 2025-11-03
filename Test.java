import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IQuanLySach ql = new QuanLySachImpl();
        int chon = -1;

        do {
            System.out.println("\n========== MENU QUAN LY SACH ==========");
            System.out.println("1. Them sach (Giao trinh / Tieu thuyet)");
            System.out.println("2. Hien thi tat ca sach");
            System.out.println("3. Tim kiem sach (Ma / Tua de / Tac gia)");
            System.out.println("4. Xoa sach theo ma");
            System.out.println("5. Cap nhat thong tin sach");
            System.out.println("6. Kiem tra ton kho + cap nhat vi tri");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
                continue;
            }

            // Case .. ->: viết gọn và tránh lỗi break quên viết
            switch (chon) {
                case 1 -> themSach(sc, ql);
                case 2 -> ql.hienThiTatCa();
                case 3 -> timKiemSach(sc, ql);
                case 4 -> xoaSach(sc, ql);
                case 5 -> capNhatSach(sc, ql);
                case 6 -> kiemTraTonKho(sc, ql);
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);

        sc.close();
    }

    private static void themSach(Scanner sc, IQuanLySach ql) {
        System.out.println("1. Nhap sach giao trinh");
        System.out.println("2. Nhap sach tieu thuyet");
        System.out.print("Chon loai sach: ");
        int loai = Integer.parseInt(sc.nextLine());
        if (loai == 1)
            nhapSachGiaoTrinh(sc, ql);
        else if (loai == 2)
            nhapSachTieuThuyet(sc, ql);
        else
            System.out.println("Lua chon khong hop le!");
    }

    private static void nhapSachGiaoTrinh(Scanner sc, IQuanLySach ql) {
        try {
            System.out.print("Nhap so luong sach giao trinh: ");
            int n = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Sach giao trinh thu " + (i + 1) + " ---");
                System.out.print("Ma sach: ");
                String ma = sc.nextLine();
                System.out.print("Tua de: ");
                String td = sc.nextLine();
                System.out.print("Tac gia: ");
                String tg = sc.nextLine();
                System.out.print("Nam XB (<=2025): ");
                int nam = Integer.parseInt(sc.nextLine());
                if (nam > 2025)
                    throw new IllegalArgumentException("Nam xuat ban khong hop le!");
                System.out.print("So luong (>=0): ");
                int sl = Integer.parseInt(sc.nextLine());
                if (sl < 0)
                    throw new IllegalArgumentException("So luong khong duoc am!");
                System.out.print("Gia co ban (>0): ");
                double gia = Double.parseDouble(sc.nextLine());
                if (gia <= 0)
                    throw new IllegalArgumentException("Gia co ban phai > 0!");
                System.out.print("Mon hoc: ");
                String mh = sc.nextLine();
                System.out.print("Cap do: ");
                String cd = sc.nextLine();
                System.out.print("Nhap vi tri sach: "); // Nhập tầng & kệ sách
                String vt = sc.nextLine();

                SachGiaoTrinh sg = new SachGiaoTrinh(ma, td, tg, nam, sl, gia, mh, cd);
                sg.setViTri(vt);
                ql.themSach(sg);
            }
        } catch (Exception e) {
            System.out.println("Loi nhap lieu: " + e.getMessage());
        }
    }

    private static void nhapSachTieuThuyet(Scanner sc, IQuanLySach ql) {
        try {
            System.out.print("Nhap so luong sach tieu thuyet: ");
            int n = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Sach tieu thuyet thu " + (i + 1) + " ---");
                System.out.print("Ma sach: ");
                String ma = sc.nextLine();
                System.out.print("Tua de: ");
                String td = sc.nextLine();
                System.out.print("Tac gia: ");
                String tg = sc.nextLine();
                System.out.print("Nam XB (<=2025): ");
                int nam = Integer.parseInt(sc.nextLine());
                if (nam > 2025)
                    throw new IllegalArgumentException("Nam xuat ban khong hop le!");
                System.out.print("So luong (>=0): ");
                int sl = Integer.parseInt(sc.nextLine());
                if (sl < 0)
                    throw new IllegalArgumentException("So luong khong duoc am!");
                System.out.print("Gia co ban (>0): ");
                double gia = Double.parseDouble(sc.nextLine());
                if (gia <= 0)
                    throw new IllegalArgumentException("Gia co ban phai > 0!");
                System.out.print("The loai: ");
                String tl = sc.nextLine();
                System.out.print("Co phai series (true/false): ");
                boolean sr = Boolean.parseBoolean(sc.nextLine());
                System.out.print("Nhap vi tri sach: "); // Nhập tầng & kệ sách
                String vt = sc.nextLine();

                SachTieuThuyet st = new SachTieuThuyet(ma, td, tg, nam, sl, gia, tl, sr);
                st.setViTri(vt);
                ql.themSach(st);
            }
        } catch (Exception e) {
            System.out.println("Loi nhap lieu: " + e.getMessage());
        }
    }

    private static void timKiemSach(Scanner sc, IQuanLySach ql) {
        System.out.println("1. Tim theo ma sach");
        System.out.println("2. Tim theo tua de");
        System.out.println("3. Tim theo tac gia");
        System.out.print("Chon kieu tim: ");
        int kieu = Integer.parseInt(sc.nextLine());

        switch (kieu) {
            case 1 -> {
                System.out.print("Nhap ma sach: ");
                String ma = sc.nextLine();
                Sach s = ql.timKiemTheoMa(ma);
                if (s != null)
                    System.out.println(s);
                else
                    System.out.println("Khong tim thay!");
            }
            case 2 -> {
                System.out.print("Nhap tu khoa: ");
                String tk = sc.nextLine();
                var ds = ql.timKiemTheoTieuDe(tk);
                if (ds.isEmpty())
                    System.out.println("Khong co ket qua!");
                else
                    ds.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Nhap ten tac gia: ");
                String tg = sc.nextLine();
                var ds = ql.timKiemTheoTacGia(tg);
                if (ds.isEmpty())
                    System.out.println("Khong co ket qua!");
                else
                    ds.forEach(System.out::println);
            }
            default -> System.out.println("Lua chon khong hop le!");
        }
    }

    private static void xoaSach(Scanner sc, IQuanLySach ql) {
        System.out.print("Nhap ma sach can xoa: ");
        String ma = sc.nextLine();
        if (!ql.xoaSach(ma))
            System.out.println("Khong tim thay sach de xoa!");
        else
            System.out.println("Da xoa thanh cong!");
    }

    private static void capNhatSach(Scanner sc, IQuanLySach ql) {
        System.out.print("Nhap ma sach can cap nhat: ");
        String ma = sc.nextLine();
        Sach tontai = ql.timKiemTheoMa(ma); // Tìm kiếm mã sách đã nhập/tồn tại

        if (tontai == null) {
            System.out.println("Khong tim thay sach co ma " + ma);
            return;
        }

        System.out.print("Nhap tieu de moi: ");
        String td = sc.nextLine();
        System.out.print("Nhap tac gia moi: ");
        String tg = sc.nextLine();
        System.out.print("Nhap nam xuat ban moi: ");
        int nam = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap so luong moi: ");
        int sl = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap gia moi: ");
        double gia = Double.parseDouble(sc.nextLine());

        // Cập nhật trực tiếp thuộc tính
        tontai.setTieuDe(td);
        tontai.setTacGia(tg);
        tontai.setNamXuatBan(nam);
        tontai.setSoLuong(sl);
        tontai.setGiaCoBan(gia);

        if (ql.capNhatSach(ma, tontai)) {
            System.out.println("Cap nhat sach thanh cong!");
        } else {
            System.out.println("Cap nhat sach that bai!");
        }
    }

    private static void kiemTraTonKho(Scanner sc, IQuanLySach ql) {
        System.out.print("Nhap ma sach muon kiem tra: ");
        String ma = sc.nextLine();
        Sach s = ql.timKiemTheoMa(ma);
        if (s == null) {
            System.out.println("Khong tim thay sach!");
            return;
        }
        try {
            System.out.print("Nhap so luong toi thieu: ");
            int min = Integer.parseInt(sc.nextLine());
            if (s.kiemTraTonKho(min))
                System.out.println("Ton kho dat yeu cau!");
            else
                System.out.println("Ton kho KHONG du yeu cau!");
            System.out.print("Nhap vi tri moi: ");
            String vt = sc.nextLine();
            s.capNhatViTri(vt);
        } catch (Exception e) {
            System.out.println("Loi nhap lieu: " + e.getMessage());
        }
    }
}

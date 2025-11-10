import java.io.*;
import java.util.*;

// Lớp hiện thực interface thể hiện: đa hình và đóng gói
// Đã thêm xử lý ngoại lệ (try-catch) cho toàn bộ thao tác với file và dữ liệu
public class QuanLySachImpl implements IQuanLySach {
    private List<SachTuan9> ds = new ArrayList<>();

    @Override
    public void themSach(SachTuan9 s) {
        try {
            ds.add(s);
            System.out.println("Da them: " + s.getTieuDe());
        } catch (Exception e) {
            System.out.println("Loi khi them sach: " + e.getMessage());
        }
    }

    @Override
    public SachTuan9 timKiemTheoMa(String ma) {
        try {
            return ds.stream().filter(s -> s.getMaSach().equalsIgnoreCase(ma)).findFirst().orElse(null);
        } catch (Exception e) {
            System.out.println("Loi khi tim kiem theo ma: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<SachTuan9> timKiemTheoTieuDe(String tuKhoa) {
        List<SachTuan9> kq = new ArrayList<>();
        try {
            tuKhoa = tuKhoa.toLowerCase();
            for (SachTuan9 s : ds) {
                if (s.getTieuDe().toLowerCase().contains(tuKhoa))
                    kq.add(s);
            }
        } catch (Exception e) {
            System.out.println("Loi khi tim kiem theo tua de: " + e.getMessage());
        }
        return kq;
    }

    @Override
    public List<SachTuan9> timKiemTheoTacGia(String tenTG) {
        List<SachTuan9> kq = new ArrayList<>();
        try {
            tenTG = tenTG.toLowerCase();
            for (SachTuan9 s : ds) {
                if (s.getTacGia().toLowerCase().contains(tenTG))
                    kq.add(s);
            }
        } catch (Exception e) {
            System.out.println("Loi khi tim kiem theo tac gia: " + e.getMessage());
        }
        return kq;
    }

    @Override
    public boolean xoaSach(String ma) {
        try {
            SachTuan9 s = timKiemTheoMa(ma);
            if (s == null)
                return false;
            ds.remove(s);
            return true;
        } catch (Exception e) {
            System.out.println("Loi khi xoa sach: " + e.getMessage());
            return false;
        }
    }

    // Chỉ xử lý cập nhật ở 1 nơi, có try-catch
    @Override
    public boolean capNhatSach(String ma, SachTuan9 thongTinMoi) {
        try {
            SachTuan9 s = timKiemTheoMa(ma);
            if (s == null)
                return false;

            if (thongTinMoi.getTieuDe() != null)
                s.setTieuDe(thongTinMoi.getTieuDe());
            if (thongTinMoi.getTacGia() != null)
                s.setTacGia(thongTinMoi.getTacGia());
            if (thongTinMoi.getSoLuong() >= 0)
                s.setSoLuong(thongTinMoi.getSoLuong());
            if (thongTinMoi.getGiaCoBan() > 0)
                s.setGiaCoBan(thongTinMoi.getGiaCoBan());

            return true;
        } catch (Exception e) {
            System.out.println("Loi khi cap nhat sach: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void hienThiTatCa() {
        try {
            if (ds.isEmpty()) {
                System.out.println("Danh sach trong!");
            } else {
                ds.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Loi khi hien thi danh sach: " + e.getMessage());
        }
    }

    @Override
    public void sapXepTheoGia(boolean tangDan) {
        try {
            ds.sort((a, b) -> tangDan ? Double.compare(a.tinhGiaBan(), b.tinhGiaBan())
                    : Double.compare(b.tinhGiaBan(), a.tinhGiaBan()));
            System.out.println("Da sap xep danh sach theo gia " + (tangDan ? "tang dan" : "giam dan"));
        } catch (Exception e) {
            System.out.println("Loi khi sap xep: " + e.getMessage());
        }
    }

    @Override
    public double tinhTongGiaTriKho() {
        try {
            return ds.stream().mapToDouble(s -> s.tinhGiaBan() * s.getSoLuong()).sum();
        } catch (Exception e) {
            System.out.println("Loi khi tinh tong gia tri kho: " + e.getMessage());
            return 0;
        }
    }

    // Lưu file CSV có xử lý ngoại lệ
    @Override
    public void luuCSV(String path) {
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            if (ds.isEmpty()) {
                System.out.println("Danh sach trong, khong co du lieu de luu!");
                return;
            }
            for (SachTuan9 s : ds) {
                pw.println(s.toCSV());
            }
            System.out.println("Da luu du lieu vao file: " + path);
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file hoac duong dan khong hop le!");
        } catch (IOException e) {
            System.out.println("Loi IO khi luu file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi khac khi luu file: " + e.getMessage());
        }
    }

    // Nạp dữ liệu từ CSV có xử lý ngoại lệ
    @Override
    public void napTuCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ds.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 8)
                    continue; // tránh lỗi nếu file thiếu dữ liệu

                if (p[0].equalsIgnoreCase("SachGiaoTrinh")) {
                    ds.add(new SachGiaoTrinhTuan9(p[1], p[2], p[3],
                            Integer.parseInt(p[4]), Integer.parseInt(p[5]),
                            Double.parseDouble(p[6]), "Mon tu file", "Cap do"));
                } else {
                    ds.add(new SachTieuThuyetTuan9(p[1], p[2], p[3],
                            Integer.parseInt(p[4]), Integer.parseInt(p[5]),
                            Double.parseDouble(p[6]), "The loai tu file", false));
                }
            }
            System.out.println("Da nap du lieu tu file: " + path);
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + path);
        } catch (IOException e) {
            System.out.println("Loi IO khi nap file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang so trong file CSV!");
        } catch (Exception e) {
            System.out.println("Loi khac khi nap file: " + e.getMessage());
        }
    }
}

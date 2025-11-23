// MỞ RỘNG: Lớp hiện thực toàn bộ chức năng quản lý sách
// Có tìm kiếm nâng cao, sắp xếp theo giá, thống kê kho, lưu/nạp CSV

import java.io.*;
import java.util.*;

public class QuanLySachImpl implements IQuanLySach {

    private List<Sach> ds = new ArrayList<>();

    @Override
    public void themSach(Sach s) {
        try {
            ds.add(s);
            System.out.println("Da them: " + s.getTieuDe());
        } catch (Exception e) {
            System.out.println("Loi khi them sach: " + e.getMessage());
        }
    }

    @Override
    public Sach timKiemTheoMa(String ma) {
        try {
            // MỞ RỘNG: tìm kiếm bằng Stream API
            return ds.stream()
                    .filter(s -> s.getMaSach().equalsIgnoreCase(ma))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            System.out.println("Loi tim kiem theo ma: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Sach> timKiemTheoTieuDe(String tuKhoa) {
        List<Sach> kq = new ArrayList<>();
        try {
            // MỞ RỘNG: tìm kiếm theo tựa đề
            tuKhoa = tuKhoa.toLowerCase();
            for (Sach s : ds) {
                if (s.getTieuDe().toLowerCase().contains(tuKhoa)) {
                    kq.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Loi tim kiem theo tua de: " + e.getMessage());
        }
        return kq;
    }

    @Override
    public List<Sach> timKiemTheoTacGia(String tenTG) {
        List<Sach> kq = new ArrayList<>();
        try {
            // MỞ RỘNG: tìm kiếm theo tác giả
            tenTG = tenTG.toLowerCase();
            for (Sach s : ds) {
                if (s.getTacGia().toLowerCase().contains(tenTG)) {
                    kq.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Loi tim kiem theo tac gia: " + e.getMessage());
        }
        return kq;
    }

    @Override
    public boolean xoaSach(String ma) {
        try {
            Sach s = timKiemTheoMa(ma);
            if (s == null) {
                return false;
            }
            ds.remove(s);
            return true;
        } catch (Exception e) {
            System.out.println("Loi khi xoa sach: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean capNhatSach(String ma, Sach thongTinMoi) {
        try {
            Sach s = timKiemTheoMa(ma);
            if (s == null) {
                return false;
            }

            // MỞ RỘNG: cập nhật các trường chung
            if (thongTinMoi.getTieuDe() != null) {
                s.setTieuDe(thongTinMoi.getTieuDe());
            }

            if (thongTinMoi.getTacGia() != null) {
                s.setTacGia(thongTinMoi.getTacGia());
            }

            if (thongTinMoi.getSoLuong() >= 0) {
                s.setSoLuong(thongTinMoi.getSoLuong());
            }

            if (thongTinMoi.getGiaCoBan() >= 0) {
                s.setGiaCoBan(thongTinMoi.getGiaCoBan());
            }

            if (thongTinMoi.getViTri() != null) {
                s.setViTri(thongTinMoi.getViTri());
            }

            // MỞ RỘNG: cập nhật thêm thông tin riêng cho sách giáo trình
            if (s instanceof SachGiaoTrinh && thongTinMoi instanceof SachGiaoTrinh) {
                SachGiaoTrinh cu = (SachGiaoTrinh) s;
                SachGiaoTrinh moi = (SachGiaoTrinh) thongTinMoi;
                if (moi.getMonHoc() != null) {
                    cu.setMonHoc(moi.getMonHoc());
                }
                if (moi.getCapDo() != null) {
                    cu.setCapDo(moi.getCapDo());
                }
            }
            // MỞ RỘNG: cập nhật thêm thông tin riêng cho sách tiểu thuyết
            else if (s instanceof SachTieuThuyet && thongTinMoi instanceof SachTieuThuyet) {
                SachTieuThuyet cu = (SachTieuThuyet) s;
                SachTieuThuyet moi = (SachTieuThuyet) thongTinMoi;
                if (moi.getTheLoai() != null) {
                    cu.setTheLoai(moi.getTheLoai());
                }
                cu.setLaSeries(moi.isLaSeries());
            }

            return true;

        } catch (Exception e) {
            System.out.println("Loi cap nhat sach: " + e.getMessage());
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
            // MỞ RỘNG: sắp xếp theo giá bán
            ds.sort((a, b) -> tangDan
                    ? Double.compare(a.tinhGiaBan(), b.tinhGiaBan())
                    : Double.compare(b.tinhGiaBan(), a.tinhGiaBan()));

            System.out.println("Da sap xep danh sach theo gia " + (tangDan ? "tang dan" : "giam dan"));

            // MỞ RỘNG: sau khi sắp xếp thì hiển thị luôn danh sách
            hienThiTatCa();
        } catch (Exception e) {
            System.out.println("Loi sap xep gia: " + e.getMessage());
        }
    }

    @Override
    public double tinhTongGiaTriKho() {
        try {
            // MỞ RỘNG: tổng giá trị = giá bán * số lượng
            return ds.stream()
                    .mapToDouble(s -> s.tinhGiaBan() * s.getSoLuong())
                    .sum();
        } catch (Exception e) {
            System.out.println("Loi tinh tong gia tri kho: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public void luuCSV(String path) {
        try (PrintWriter pw = new PrintWriter(new File(path))) {

            if (ds.isEmpty()) {
                System.out.println("Danh sach trong, khong co du lieu!");
                return;
            }

            // MỞ RỘNG: ghi theo định dạng CSV
            for (Sach s : ds) {
                pw.println(s.toCSV());
            }

            System.out.println("Da luu file: " + path);

        } catch (Exception e) {
            System.out.println("Loi luu file: " + e.getMessage());
        }
    }

    @Override
    public void napTuCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            ds.clear();
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 8) {
                    continue;
                }

                Sach s;

                if (p[0].equalsIgnoreCase("SachGiaoTrinh")) {
                    s = new SachGiaoTrinh(
                            p[1], p[2], p[3],
                            Integer.parseInt(p[4]),
                            Integer.parseInt(p[5]),
                            Double.parseDouble(p[6]),
                            "Mon tu file",
                            "Cap do");
                } else {
                    s = new SachTieuThuyet(
                            p[1], p[2], p[3],
                            Integer.parseInt(p[4]),
                            Integer.parseInt(p[5]),
                            Double.parseDouble(p[6]),
                            "The loai tu file",
                            false);
                }

                // MỞ RỘNG: nạp thêm vị trí lưu kho
                s.setViTri(p[7]);

                ds.add(s);
            }

            System.out.println("Da nap file: " + path);

        } catch (Exception e) {
            System.out.println("Loi nap file: " + e.getMessage());
        }
    }
}

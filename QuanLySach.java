package T9_hdt;

import java.util.*;
import java.io.*;

public class QuanLySach implements IQuanLySach {
    private List<Sach> ds = new ArrayList<>();

    @Override
    public void themSach(Sach s) {
        ds.add(s);
    }

    @Override
    public void hienThi() {
        for (Sach s : ds) {
            System.out.println(s);
        }
    }

    @Override
    public Sach timSachTheoMa(String ma) {
        for (Sach s : ds) {
            if (s.getMaSach().equalsIgnoreCase(ma)) return s;
        }
        return null;
    }

    // 🔸 MỞ RỘNG: tìm theo tác giả
    public void timSachTheoTacGia(String tacGia) {
        System.out.println("Kết quả tìm theo tác giả: " + tacGia);
        for (Sach s : ds) {
            if (s.getTacGia().equalsIgnoreCase(tacGia)) {
                System.out.println(s);
            }
        }
    }

    // 🔸 MỞ RỘNG: sắp xếp theo giá
    public void sapXepTheoGia(boolean tangDan) {
        ds.sort((a, b) -> tangDan ?
            Double.compare(a.tinhGiaBan(), b.tinhGiaBan()) :
            Double.compare(b.tinhGiaBan(), a.tinhGiaBan()));
        System.out.println("Đã sắp xếp theo giá " + (tangDan ? "tăng dần" : "giảm dần"));
    }

    // 🔸 MỞ RỘNG: tính tổng giá trị kho
    public double tongGiaTriKho() {
        double tong = 0;
        for (Sach s : ds) {
            tong += s.tinhGiaBan() * s.getSoLuong();
        }
        return tong;
    }

    // 🔸 MỞ RỘNG: lưu CSV
    public void luuCSV(String filePath) {
        try (PrintWriter pw = new PrintWriter(new File(filePath))) {
            for (Sach s : ds) {
                pw.println(s.toCSV());
            }
            System.out.println("Đã lưu dữ liệu ra file: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔸 MỞ RỘNG: nạp CSV
    public void napTuCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            ds.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equalsIgnoreCase("SACHGIAOTRINH"))
                    ds.add(SachGiaoTrinh.fromCSV(p));
                else if (p[0].equalsIgnoreCase("SACHTIEUTHUYET"))
                    ds.add(SachTieuThuyet.fromCSV(p));
            }
            System.out.println("Đã nạp dữ liệu từ file: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

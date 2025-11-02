import java.util.ArrayList;
import java.util.List;

public class QuanLySachImpl implements IQuanLySach {
    private List<Sach> danhSach = new ArrayList<>();

    @Override
    public void themSach(Sach s) {
        danhSach.add(s);
        System.out.println("Da them sach: " + s.getTieuDe());
    }

    @Override
    public Sach timKiemTheoMa(String maSach) {
        for (Sach s : danhSach) {
            if (s.getMaSach().equalsIgnoreCase(maSach)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Sach> timKiemTheoTieuDe(String tuKhoa) {
        List<Sach> ketQua = new ArrayList<>();
        for (Sach s : danhSach) {
            if (s.getTieuDe().toLowerCase().contains(tuKhoa.toLowerCase())) {
                ketQua.add(s);
            }
        }
        return ketQua;
    }

    @Override
    public List<Sach> timKiemTheoTacGia(String tenTacGia) {
        List<Sach> ketQua = new ArrayList<>();
        for (Sach s : danhSach) {
            if (s.getTacGia().toLowerCase().contains(tenTacGia.toLowerCase())) {
                ketQua.add(s);
            }
        }
        return ketQua;
    }

    @Override
    public boolean xoaSach(String maSach) {
        Sach s = timKiemTheoMa(maSach);
        if (s != null) {
            danhSach.remove(s);
            System.out.println("Da xoa sach co ma: " + maSach);
            return true;
        }
        System.out.println("Khong tim thay sach de xoa!");
        return false;
    }

    @Override
    public void hienThiTatCa() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach trong!");
        } else {
            System.out.println("===== DANH SACH SACH =====");
            for (Sach s : danhSach) {
                System.out.println(s);
            }
        }
    }

    @Override
    public List<Sach> getDanhSach() {
        return danhSach;
    }

    // Cập nhật thông tin sách
    @Override
    public boolean capNhatSach(String maSach, Sach thongTinMoi) {
        Sach s = timKiemTheoMa(maSach);
        if (s == null) {
            System.out.println("Khong tim thay sach can cap nhat!");
            return false;
        }

        if (thongTinMoi.getTieuDe() != null && !thongTinMoi.getTieuDe().isEmpty()) {
            s.setTieuDe(thongTinMoi.getTieuDe());
        }
        if (thongTinMoi.getTacGia() != null && !thongTinMoi.getTacGia().isEmpty()) {
            s.setTacGia(thongTinMoi.getTacGia());
        }
        if (thongTinMoi.getNamXuatBan() <= 2025 && thongTinMoi.getNamXuatBan() > 0) {
            s.setNamXuatBan(thongTinMoi.getNamXuatBan());
        }
        if (thongTinMoi.getSoLuong() >= 0) {
            s.setSoLuong(thongTinMoi.getSoLuong());
        }
        if (thongTinMoi.getGiaCoBan() > 0) {
            s.setGiaCoBan(thongTinMoi.getGiaCoBan());
        }
        if (thongTinMoi.getViTri() != null && !thongTinMoi.getViTri().isEmpty()) {
            s.setViTri(thongTinMoi.getViTri());
        }

        System.out.println("Da cap nhat sach thanh cong!");
        return true;
    }
}

import java.util.ArrayList;

public class QuanLySachlmlp implements IQuanLySach {
    private ArrayList<Sach> danhSachSach;

    public QuanLySachlmlp() {
        danhSachSach = new ArrayList<>();
    }

    @Override
    public void themSach(Sach s) {
        danhSachSach.add(s);
        System.out.println(" Da them sach thanh cong!");
    }

    @Override
    public Sach timKiemSach(String maSach) {
        for (Sach s : danhSachSach) {
            if (s.getMaSach().equalsIgnoreCase(maSach)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void xoaSachTheoMa(String maSach) {
        Sach sachCanXoa = timKiemSach(maSach);
        if (sachCanXoa != null) {
            danhSachSach.remove(sachCanXoa);
            System.out.println(" Da xoa sach co ma: " + maSach);
        } else {
            System.out.println(" Khong tim thay sach co ma: " + maSach);
        }
    }

    @Override
    public void capNhatSoLuong(String maSach, int soLuongMoi) {
        Sach sach = timKiemSach(maSach);
        if (sach != null) {
            sach.setSoLuong(soLuongMoi);
            System.out.println(" Da cap nhat so luong sach co ma " + maSach + " thanh: " + soLuongMoi);
        } else {
            System.out.println(" Khong tim thay sach co ma: " + maSach);
        }
    }

    @Override
    public void hienThiDanhSachSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println(" Danh sach sach hien dang trong!");
            return;
        }
        System.out.println("\n===== DANH SACH SACH =====");
        for (Sach s : danhSachSach) {
            System.out.println(s.toString());
            System.out.println("-----------------------------");
        }
    }
}
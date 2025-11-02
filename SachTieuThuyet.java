package T9_hdt;

public class SachTieuThuyet extends Sach {
    private String theLoai;

    public SachTieuThuyet(String maSach, String tenSach, String tacGia, int namXB, int soLuong, double donGia, String theLoai) {
        super(maSach, tenSach, tacGia, namXB, soLuong, donGia);
        this.theLoai = theLoai;
    }

    @Override
    public double tinhGiaBan() {
        return donGia * 1.1; // VAT 10%
    }

    @Override
    public String toString() {
        return super.toString() + " | Thể loại: " + theLoai;
    }

    // 🔸 MỞ RỘNG: tạo đối tượng từ dòng CSV
    public static SachTieuThuyet fromCSV(String[] p) {
        return new SachTieuThuyet(p[1], p[2], p[3], Integer.parseInt(p[4]),
                Integer.parseInt(p[5]), Double.parseDouble(p[6]), "Chưa rõ");
    }
}

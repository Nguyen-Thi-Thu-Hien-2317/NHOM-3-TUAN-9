package T9_hdt;

public class SachGiaoTrinh extends Sach {
    private String monHoc;

    public SachGiaoTrinh(String maSach, String tenSach, String tacGia, int namXB, int soLuong, double donGia, String monHoc) {
        super(maSach, tenSach, tacGia, namXB, soLuong, donGia);
        this.monHoc = monHoc;
    }

    @Override
    public double tinhGiaBan() {
        return donGia * 1.05; // VAT 5%
    }

    @Override
    public String toString() {
        return super.toString() + " | Môn: " + monHoc;
    }

    // 🔸 MỞ RỘNG: tạo đối tượng từ dòng CSV
    public static SachGiaoTrinh fromCSV(String[] p) {
        return new SachGiaoTrinh(p[1], p[2], p[3], Integer.parseInt(p[4]),
                Integer.parseInt(p[5]), Double.parseDouble(p[6]), "Chưa rõ");
    }
}

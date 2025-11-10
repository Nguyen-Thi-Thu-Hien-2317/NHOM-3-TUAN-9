// Lớp con kế thừa từ Sach → thể hiện **tính kế thừa**
// Ghi đè phương thức tinhGiaBan → thể hiện **đa hình**
public class SachGiaoTrinhTuan9 extends SachTuan9 {
    private String monHoc, capDo;

    public SachGiaoTrinhTuan9(String ma, String td, String tg, int nam, int sl, double gia, String mh, String cd) {
        super(ma, td, tg, nam, sl, gia); // Gọi constructor lớp cha
        this.monHoc = mh;
        this.capDo = cd;
    }

    public SachGiaoTrinhTuan9() {
    }

    @Override
    public double tinhGiaBan() {
        int soNam = Math.max(0, 2025 - namXuatBan);
        return giaCoBan + soNam * 5000;
    }

    @Override
    public String toString() {
        return "\n===== SACH GIAO TRINH =====" +
                super.toString() +
                "\nMon hoc: " + monHoc +
                "\nCap do: " + capDo;
    }
}

// Lớp con kế thừa từ Sach thể hiện ""tính kế thừa""
// Có cách tính giá bán riêng → thể hiện "đa hình"
public class SachTieuThuyetTuan9 extends SachTuan9 {
    private String theLoai;
    private boolean laSeries;

    public SachTieuThuyetTuan9(String ma, String td, String tg, int nam, int sl, double gia, String tl, boolean ls) {
        super(ma, td, tg, nam, sl, gia);
        this.theLoai = tl;
        this.laSeries = ls;
    }

    public SachTieuThuyetTuan9() {
    }

    @Override
    public double tinhGiaBan() {
        return giaCoBan + (laSeries ? 15000 : 0);
    }

    @Override
    public String toString() {
        return "\n===== SACH TIEU THUYET =====" +
                super.toString() +
                "\nThe loai: " + theLoai +
                "\nLa series: " + (laSeries ? "Co" : "Khong");
    }
}

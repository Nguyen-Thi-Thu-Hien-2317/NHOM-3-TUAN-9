public class SachTieuThuyet extends Sach {
    private String theLoai;
    private boolean laSachSeries;
    private final double PHU_PHI_SERIES = 15000;

    public SachTieuThuyet(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong,
                          double giaCoBan, String theLoai, boolean laSachSeries) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.theLoai = theLoai;
        this.laSachSeries = laSachSeries;
    }

    @Override
    public double tinhGiaBan() {
        double phuPhi = laSachSeries ? PHU_PHI_SERIES : 0;
        return getGiaCoBan() + phuPhi;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Loai: Tieu Thuyet\n" +
               "The loai: " + theLoai + "\n" +
               "Thuoc series: " + (laSachSeries ? "Co" : "Khong") + "\n" +
               "GIA BAN: " + String.format("%,.0f", tinhGiaBan()) + " VND\n" +
               "Ton kho: " + getSoLuong() + "\n" +
               "---------------------------";
    }
}

public class SachGiaoTrinh extends Sach {
    private String monHoc;
    private String capDo;
    private final int NAM_HIEN_TAI = 2025;

    public SachGiaoTrinh(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong,
                         double giaCoBan, String monHoc, String capDo) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.monHoc = monHoc;
        this.capDo = capDo;
    }

    @Override
    public double tinhGiaBan() {
        int soNamDaXuatBan = NAM_HIEN_TAI - getNamXuatBan();
        double giaThem = soNamDaXuatBan * 5000;
        return getGiaCoBan() + giaThem;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Loai: Giao Trinh\n" +
               "Mon hoc: " + monHoc + "\n" +
               "Cap do: " + capDo + "\n" +
               "GIA BAN: " + String.format("%,.0f", tinhGiaBan()) + " VND\n" +
               "Ton kho: " + getSoLuong() + "\n" +
               "---------------------------";
    }
}

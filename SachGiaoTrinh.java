public class SachGiaoTrinh extends Sach {
    private String monHoc;
    private String capDo;

    public SachGiaoTrinh(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong,
            double giaCoBan, String monHoc, String capDo) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.monHoc = monHoc;
        this.capDo = capDo;
    }

    @Override
    public double tinhGiaBan() {
        int soNam = 2025 - getNamXuatBan();
        if (soNam < 0)
            soNam = 0;
        return getGiaCoBan() + soNam * 5000;
    }

    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return getSoLuong() >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        setViTri(viTriMoi);
        System.out.println("Da cap nhat vi tri sach giao trinh thanh: " + viTriMoi);
    }

    @Override
    public String toString() {
        return "\n===== SACH GIAO TRINH =====" +
                super.toString() +
                "\nMon hoc: " + monHoc +
                "\nCap do: " + capDo;
    }
}

public abstract class Sach implements IGiaBan {
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private int namXuatBan;
    private int soLuong;
    private double giaCoBan;
    private String viTri; // Thêm thuộc tính mới (mở rộng)

    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.giaCoBan = giaCoBan;
        this.viTri = "Chua xac dinh"; // Mặc định
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGiaCoBan() {
        return giaCoBan;
    }

    public String getViTri() {
        return viTri;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaCoBan(double giaCoBan) {
        this.giaCoBan = giaCoBan;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public abstract boolean kiemTraTonKho(int soLuongToiThieu);

    public abstract void capNhatViTri(String viTriMoi);

    @Override
    public String toString() {
        return "\nMa sach: " + maSach +
                "\nTua de: " + tieuDe +
                "\nTac gia: " + tacGia +
                "\nNam xuat ban: " + namXuatBan +
                "\nSo luong: " + soLuong +
                "\nGia co ban: " + giaCoBan +
                "\nVi tri: " + viTri +
                "\nGia ban: " + tinhGiaBan();
    }
}

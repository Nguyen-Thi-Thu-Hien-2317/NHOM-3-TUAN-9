
public interface IQuanLySach {

    // Chức năng cơ bản
    void themSach(Sach s);

    Sach timKiemTheoMa(String maSach);

    boolean xoaSach(String maSach);

    boolean capNhatSach(String maSach, Sach thongTinMoi);

    void hienThiTatCa();

    // MỞ RỘNG: Tìm kiếm theo tiêu đề (keyword)
    java.util.List<Sach> timKiemTheoTieuDe(String tuKhoa);

    // MỞ RỘNG: Tìm kiếm theo tác giả
    java.util.List<Sach> timKiemTheoTacGia(String tenTacGia);

    // MỞ RỘNG: Sắp xếp theo giá bán (tăng/giảm)
    void sapXepTheoGia(boolean tangDan);

    // MỞ RỘNG: Tính tổng giá trị kho (giaBan * soLuong)
    double tinhTongGiaTriKho();

    // MỞ RỘNG: Xuất dữ liệu ra file CSV
    void luuCSV(String filePath);

    // MỞ RỘNG: Nạp dữ liệu từ file CSV
    void napTuCSV(String filePath);
}

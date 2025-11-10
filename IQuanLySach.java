
// Interface thể hiện tính "trừu tượng""
// Xác định các hành vi cơ bản của một hệ thống quản lý sách
import java.util.List;

public interface IQuanLySach {
    void themSach(SachTuan9 s);

    SachTuan9 timKiemTheoMa(String maSach);

    List<SachTuan9> timKiemTheoTieuDe(String tuKhoa);

    List<SachTuan9> timKiemTheoTacGia(String tenTacGia);

    boolean xoaSach(String maSach);

    boolean capNhatSach(String maSach, SachTuan9 thongTinMoi);

    void hienThiTatCa();

    // Thêm mới các chức năng được yêu cầu trong nhận xét:
    void sapXepTheoGia(boolean tangDan);

    double tinhTongGiaTriKho();

    void luuCSV(String filePath);

    void napTuCSV(String filePath);
}

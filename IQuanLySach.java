import java.util.List;

public interface IQuanLySach {
    void themSach(Sach s);

    Sach timKiemTheoMa(String maSach);

    List<Sach> timKiemTheoTieuDe(String tuKhoa);

    List<Sach> timKiemTheoTacGia(String tenTacGia);

    boolean xoaSach(String maSach);

    void hienThiTatCa();

    List<Sach> getDanhSach();

    boolean capNhatSach(String maSach, Sach thongTinMoi);
}

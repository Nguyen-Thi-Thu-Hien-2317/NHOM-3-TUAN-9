import java.util.ArrayList;

public interface IQuanLySach {
    void themSach(Sach s);
    Sach timKiemSach(String maSach);
    boolean xoaSach(String maSach);
    void hienThiDanhSach();
    void baoCaoTonKho(int soLuongToiThieu);
    Sach timSachGiaCaoNhat(); // ✅ Tính năng mới
}

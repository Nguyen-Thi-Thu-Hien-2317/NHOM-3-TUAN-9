// Interface thể hiện tính ""trừu tượng"
// Định nghĩa hành vi kiểm kê và cập nhật vị trí cho mọi loại sách
public interface IKiemKe {
    boolean kiemTraTonKho(int soLuongToiThieu);

    void capNhatViTri(String viTriMoi);
}

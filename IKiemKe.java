public interface IKiemKe {
    // Kiểm tra tồn kho
    boolean kiemTraTonKho(int soLuongToiThieu);
    // Cập nhật vị trí sách trong kho
    void capNhatViTri(String viTriMoi);
}
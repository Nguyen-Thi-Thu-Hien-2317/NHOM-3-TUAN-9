// MỞ RỘNG: Lớp kế thừa từ Sach, thêm môn học và cấp độ
// Có validate riêng cho từng thuộc tính đặc thù của sách giáo trình

import java.util.Scanner;

public class SachGiaoTrinh extends Sach {

    private String monHoc;
    private String capDo;

    public SachGiaoTrinh() {
        super();
    }

    public SachGiaoTrinh(String ma, String td, String tg, int nam, int sl, double gia,
            String mh, String cd) {
        super(ma, td, tg, nam, sl, gia);
        this.monHoc = mh;
        this.capDo = cd;
    }

    // MỞ RỘNG: Hàm nhập có validate cho môn học và cấp độ
    public void nhap(Scanner sc) {
        // nhập thông tin chung từ lớp cha (đã có validate)
        super.nhapThongTinChung(sc);

        // môn học: chữ + số
        do {
            System.out.print("Mon hoc: ");
            monHoc = sc.nextLine().trim();
        } while (!monHoc.matches("[a-zA-Z0-9 ]+"));

        // cấp độ: chỉ cho phép dai hoc / pho thong
        do {
            System.out.print("Cap do (dai hoc/pho thong): ");
            capDo = sc.nextLine().trim().toLowerCase();
        } while (!(capDo.equals("dai hoc") || capDo.equals("pho thong")));
    }

    @Override
    public double tinhGiaBan() {
        // MỞ RỘNG: sách giáo trình tăng giá theo số năm cũ
        int soNam = Math.max(0, 2025 - namXuatBan);
        return giaCoBan + soNam * 5000;
    }

    // MỞ RỘNG: getter / setter cho monHoc, capDo (phục vụ cập nhật)
    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getCapDo() {
        return capDo;
    }

    public void setCapDo(String capDo) {
        this.capDo = capDo;
    }

    @Override
    public String toString() {
        return "\n===== SACH GIAO TRINH =====" +
                super.toString() +
                "\nMon hoc: " + monHoc +
                "\nCap do: " + capDo;
    }
}

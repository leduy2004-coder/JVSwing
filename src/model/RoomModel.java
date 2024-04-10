package model;

public class RoomModel {
    private String maPhong;
    private int tongGhe;
    private String loaiMayChieu;
    private String loaiAmThanh;
    private float dienTich;
    private Boolean tinhTrang;

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getTongGhe() {
        return tongGhe;
    }

    public void setTongGhe(int tongGhe) {
        this.tongGhe = tongGhe;
    }

    public String getLoaiMayChieu() {
        return loaiMayChieu;
    }

    public void setLoaiMayChieu(String loaiMayChieu) {
        this.loaiMayChieu = loaiMayChieu;
    }

    public String getLoaiAmThanh() {
        return loaiAmThanh;
    }

    public void setLoaiAmThanh(String loaiAmThanh) {
        this.loaiAmThanh = loaiAmThanh;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}

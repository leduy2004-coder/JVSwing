package model;

public class TicketModel {
    private String maVe;
    private String maNV;
    private MovieModel movieModel;
    private int soLuongToiDa;
    private int soLuongDaDat;
    private boolean tinhTrang;
    private float tien;

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public int getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    public int getSoLuongDaDat() {
        return soLuongDaDat;
    }

    public void setSoLuongDaDat(int soLuongDaDat) {
        this.soLuongDaDat = soLuongDaDat;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public float getTien() {
        return tien;
    }

    public void setTien(float tien) {
        this.tien = tien;
    }

    public String getTenPhim() {
        return getMovieModel().getTenPhim();
    }
}

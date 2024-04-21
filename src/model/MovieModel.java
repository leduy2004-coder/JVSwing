package model;

import java.sql.Blob;
import java.util.Date;

public class MovieModel {
    private String maPhim;
    private TypeMovieModel typeMovieModel;
    private String tenPhim;
    private String daoDien;
    private int doTuoi;
    private Date ngayKhoiChieu;
    private int thoiLuong;
    private boolean tinhTrang;
    private Blob hinhDaiDien;
    private String video;
    private String moTa;
    private String s;
    private byte[] img;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public TypeMovieModel getTypeMovieModel() {
        return typeMovieModel;
    }

    public void setTypeMovieModel(TypeMovieModel typeMovieModel) {
        this.typeMovieModel = typeMovieModel;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }

    public Date getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(Date ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Blob getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(Blob hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public MovieModel() {
    }

    @Override
    public String toString() {
        return tenPhim;
    }

    public String getTenLoaiPhim() {
        return getTypeMovieModel().getTenLPhim();
    }
}

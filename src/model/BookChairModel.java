package model;

public class BookChairModel {
    private String maGhe;
    private String maBook;
    private boolean tinhTrang;

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public String getMaBook() {
        return maBook;
    }

    public void setMaBook(String maBook) {
        this.maBook = maBook;
    }
}

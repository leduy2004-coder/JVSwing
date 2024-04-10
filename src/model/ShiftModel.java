package model;

import java.sql.Time;

public class ShiftModel {
    private String maCa;
    private String tenCa;
    private Time tgBD;

    public ShiftModel() {
    }

    public ShiftModel(String maCa, String tenCa) {
        this.maCa = maCa;
        this.tenCa = tenCa;
    }

    public Time getTgBD() {
        return tgBD;
    }

    public void setTgBD(Time tgBD) {
        this.tgBD = tgBD;
    }

    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    @Override
    public String toString() {
        return tenCa;
    }


}

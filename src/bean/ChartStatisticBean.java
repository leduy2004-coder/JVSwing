package bean;

import java.util.Date;

public class ChartStatisticBean {
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private int MonthOfYear;
    private int totalTicket;

    public int getMonthOfYear() {
        return MonthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        MonthOfYear = monthOfYear;
    }

    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }
}

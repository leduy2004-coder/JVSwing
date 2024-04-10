package Dao.impl;

import Dao.SQLSEVERDataAccess;
import bean.ChartStatisticBean;
import bean.DataStatisticBean;
import bean.TableStatisticBean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAO {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();

    public static StatisticDAO getInstance() {
        return new StatisticDAO();
    }
    public DataStatisticBean getData(){
        try {
            String sql = "\n" +
                    "SELECT \n" +
                    "    (SELECT COUNT(maPhim) FROM Phim) AS soPhim,\n" +
                    "    (SELECT COUNT(maKH) FROM KhachHang) AS soKhachHang,\n" +
                    "    (SELECT COUNT(maNV) FROM NhanVien WHERE tinhTrang = 1) AS soNhanVien;";
            ResultSet rs = con.getResultSet(sql);
            if (rs.next()) {
                DataStatisticBean data = new DataStatisticBean();
                data.setTotalCustomer(rs.getInt("soKhachHang"));
                data.setTotalEmployee(rs.getInt("soNhanVien"));
                data.setTotalMovie(rs.getInt("soPhim"));
                return data;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ChartStatisticBean> getChart(int year) {
        List<ChartStatisticBean> result = new ArrayList<ChartStatisticBean>();
        try {
            String sql = "select * from dbo.fThongKeTungThangTrongNam(?)";
            ResultSet rs = con.getResultSet(sql,year);
            ChartStatisticBean chart = null;
            while (rs.next()) {
                chart = new ChartStatisticBean();
                chart.setMonthOfYear(rs.getInt("Thang"));
                chart.setTotalTicket(rs.getInt("TongSoVe"));
                result.add(chart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<TableStatisticBean> getTable(Date from, Date to) {
        List<TableStatisticBean> result = new ArrayList<TableStatisticBean>();
        try {
            String sql = "SELECT * FROM TongHopThongKe(?,?)";
            ResultSet rs = con.getResultSet(sql,from,to);
            TableStatisticBean table = null;
            while (rs.next()) {
                table = new TableStatisticBean();
                table.setMovieName(rs.getString("tenPhim"));
                table.setTotalDate(rs.getInt("tongSoNgayChieu"));
                table.setTotalTicket(rs.getInt("tongSoVeDaBan"));
                table.setTotalTurnover(rs.getDouble("doanhThu"));
                result.add(table);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<ChartStatisticBean> getAllYear() {
        List<ChartStatisticBean> result = new ArrayList<ChartStatisticBean>();
        try {
            String sql = "select distinct year(ngayChieu) as nam from SuatChieu";
            ResultSet rs = con.getResultSet(sql);
            ChartStatisticBean chart = null;
            while (rs.next()) {
                chart = new ChartStatisticBean();
                chart.setYear(rs.getInt("nam"));
                result.add(chart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

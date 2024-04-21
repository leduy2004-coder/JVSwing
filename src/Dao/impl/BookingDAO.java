package Dao.impl;

import Dao.SQLSEVERDataAccess;
import bean.FilterBean;
import model.BookChairModel;
import model.BookTicketModel;
import model.ScheduleModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();

    public static BookingDAO getInstance() {
        return new BookingDAO();
    }

    public int insertBookChair(BookChairModel b) {
        String sql = "INSERT INTO BookGhe (maGhe,maBook) VALUES (?,?)";
        int k = con.ExecuteUpdateSQL(sql, b.getMaGhe(), b.getMaBook());
        return k;
    }

    public BookTicketModel insertBookTicket(BookTicketModel b) {
        BookTicketModel result = new BookTicketModel();
        try {
            String sql = "INSERT INTO BookVe (maKH,maNV,maSuat,maVe,tongTien,ngayMua) VALUES (?,?,?,?,?,?)";
            ResultSet rs = con.getResultSet(sql, b.getMaKH(), b.getMaNV(), b.getMaSuat(), b.getMaVe(), b.getTongTien(), (Date) b.getNgayMua());
            BookTicketModel bookTicketModel = null;
            while (rs.next()) {
                bookTicketModel = new BookTicketModel();
                bookTicketModel.setMaBook(rs.getString("maBook"));
                result = bookTicketModel;
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<BookChairModel> selectChair(String maSC) {
        List<BookChairModel> result = new ArrayList<BookChairModel>();
        try {
            String sql = "select * from dbo.fSoGheDaDat(?)";
            ResultSet rs = con.getResultSet(sql, maSC);
            BookChairModel bookChairModel = null;
            while (rs.next()) {
                bookChairModel = new BookChairModel();
                bookChairModel.setMaGhe(rs.getString("maGhe"));
                bookChairModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                result.add(bookChairModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<FilterBean> selectByPhone(String phone){
        List<FilterBean> result = new ArrayList<FilterBean>();
        try {
            String sql = "select * from dbo.fXuatTT(?)";
            ResultSet rs = con.getResultSet(sql, phone);
            FilterBean filterBean = null;
            while (rs.next()) {
                filterBean = new FilterBean();
                ScheduleModel scheduleModel = new ScheduleModel();
                scheduleModel.setMaCa(rs.getString("maCa"));
                scheduleModel.setMaPhim(rs.getString("maPhim"));
                filterBean.setTenCa(ScheduleDAO.getInstance().selectByMCa(scheduleModel).getTenCa());
                filterBean.setHoTen(rs.getString("hoTen"));
                filterBean.setTenPhim(ScheduleDAO.getInstance().selectByMPhim(scheduleModel).getTenPhim());
                filterBean.setMaPhong(rs.getString("maPhong"));
                filterBean.setNgayMua(rs.getDate("ngayMua"));
                filterBean.setMaGhe(rs.getString("maGhe"));
                result.add(filterBean);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

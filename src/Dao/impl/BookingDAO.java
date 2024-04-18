package Dao.impl;

import Dao.SQLSEVERDataAccess;
import model.BookChairModel;
import model.BookTicketModel;
import model.MovieModel;
import model.TypeMovieModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO{
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    public static BookingDAO getInstance() {
        return new BookingDAO();
    }
    public int insertBookChair(BookChairModel b) {
        String sql = "INSERT INTO BookGhe (maGhe,maBook) VALUES (?,?)";
        int k = con.ExecuteUpdateSQL(sql, b.getMaGhe(),b.getMaBook());
        return k;
    }
    public String insertBookTicket(BookTicketModel b) {
        String sql = "INSERT INTO BookVe (maKH,maNV,maSuat,maVe,tongTien,ngayMua) VALUES (?,?,?,?,?,?)";
        String k = con.insertAndGetId(sql,b.getMaKH(),b.getMaNV(),b.getMaSuat(),b.getMaVe(),b.getTongTien(),(Date) b.getNgayMua());
        System.out.println(k);
        return k;
    }

    public List<BookChairModel> selectChair(String maSC){
        List<BookChairModel> result = new ArrayList<BookChairModel>();
        try {
        String sql = "select * from dbo.fSoGheDaDat(?)";
        ResultSet rs = con.getResultSet(sql,maSC);
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
}

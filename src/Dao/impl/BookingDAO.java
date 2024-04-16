package Dao.impl;

import Dao.SQLSEVERDataAccess;
import model.BookChairModel;
import model.BookTicketModel;

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
//    public String insertBookTicket(BookTicketModel b) {
//        String sql = "INSERT INTO BookVe (maKH,maNV,maSuat,maVe,tongTien,ngayMua) VALUES (?,?,?,?,?,?)";
//        String k = con.insertAndGetId(sql,  );
//        return k;
//    }
}

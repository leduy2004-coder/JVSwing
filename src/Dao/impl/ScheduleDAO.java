
package Dao.impl;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.DAOInterface;
import Dao.SQLSEVERDataAccess;
import model.MovieModel;
import model.RoomModel;
import model.ScheduleModel;
import model.ShiftModel;

public class ScheduleDAO implements DAOInterface<ScheduleModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    public static ScheduleDAO getInstance() {
        return new ScheduleDAO();
    }

    @Override
    public int insert(ScheduleModel s) {
        String sql = "INSERT INTO SuatChieu (maPhim, maPhong, maCa, ngayChieu) VALUES (?,?,?,?)";
        int k = con.ExecuteUpdateSQL(sql,s.getMaPhim(),s.getMaPhong(),s.getMaCa(), (Date) s.getNgayChieu());
        return k;
    }

    @Override
    public int update(ScheduleModel s) {
        String sql = "UPDATE SuatChieu SET maPhim = ?, maPhong = ?, maCa = ?, ngayChieu = ? WHERE maSuat = ?";
        int k = con.ExecuteUpdateSQL(sql,s.getMaPhim(),s.getMaPhong(),s.getMaCa(), (Date) s.getNgayChieu(), s.getMaSC());
        return k;
    }

    @Override
    public int delete(ScheduleModel s) {
        String sql = "DELETE from SuatChieu Where maSuat = ?";
        int k = con.ExecuteUpdateSQL(sql, s.getMaSC());
        return k;
    }

    @Override
    public List<ScheduleModel> selectAll() {
        return null;
    }

    public List<ScheduleModel> selectAllByDate(Date date) {
        List<ScheduleModel> result = new ArrayList<ScheduleModel>();
        try {
            String sql = "SELECT * FROM SuatChieu WHERE ngayChieu = ?";
            ResultSet rs = con.getResultSet(sql,date);
            ScheduleModel scheduleModel = null;
            while (rs.next()) {
                scheduleModel = new ScheduleModel();
                scheduleModel.setMaPhong(rs.getString("maPhong"));
                scheduleModel.setMaSC(rs.getString("maSuat"));
                scheduleModel.setMaPhim(rs.getString("maPhim"));
                scheduleModel.setMaCa(rs.getString("maCa"));
                scheduleModel.setNgayChieu(rs.getDate("ngayChieu"));
                result.add(scheduleModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<ScheduleModel> selectAllDate(Date from, Date to) {
        List<ScheduleModel> result = new ArrayList<ScheduleModel>();
        try {
            String sql = "SELECT * FROM SuatChieu WHERE ngayChieu BETWEEN ? AND ?";
            ResultSet rs = con.getResultSet(sql,from,to);
            ScheduleModel scheduleModel = null;
            while (rs.next()) {
                scheduleModel = new ScheduleModel();
                scheduleModel.setMaPhong(rs.getString("maPhong"));
                scheduleModel.setMaPhim(rs.getString("maPhim"));
                scheduleModel.setMaCa(rs.getString("maCa"));
                scheduleModel.setNgayChieu(rs.getDate("ngayChieu"));
                result.add(scheduleModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<ScheduleModel> selectAllToTal(Date from, Date to, ScheduleModel t) {
        List<ScheduleModel> result = new ArrayList<ScheduleModel>();
        try {
            String sql = "SELECT * FROM SuatChieu WHERE ngayChieu > ? and ngayChieu < ? and maPhim = ? and maPhong= ? and maCa = ?";
            ResultSet rs = con.getResultSet(sql,from,to,t.getMaPhim(),t.getMaPhong(),t.getMaCa());
            ScheduleModel scheduleModel = null;
            while (rs.next()) {
                scheduleModel = new ScheduleModel();
                scheduleModel.setMaSC(rs.getString("maSuat"));
                scheduleModel.setMaPhong(rs.getString("maPhong"));
                scheduleModel.setMaPhim(rs.getString("maPhim"));
                scheduleModel.setMaCa(rs.getString("maCa"));
                scheduleModel.setNgayChieu(rs.getDate("ngayChieu"));
                result.add(scheduleModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public MovieModel selectByMPhim(ScheduleModel t) {
        try {
            String sql = "SELECT * FROM Phim where maPhim= ?";
            ResultSet rs = con.getResultSet(sql,t.getMaPhim());
            if (rs.next()) {
                MovieModel movieModel = new MovieModel();
                movieModel.setMaPhim(rs.getString("maPhim"));
                movieModel.setTenPhim(rs.getString("tenPhim"));
                return movieModel;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ShiftModel selectByMCa(ScheduleModel t) {
        try {
            String sql = "SELECT * FROM CaChieu WHERE maCa = ?";
            ResultSet rs = con.getResultSet(sql,t.getMaCa());
            if (rs.next()) {
                ShiftModel shiftModel = new ShiftModel();
                shiftModel.setMaCa(rs.getString("maCa"));
                shiftModel.setTenCa(rs.getString("tenCa"));
                shiftModel.setTgBD(rs.getTime("thoiGianBatDau"));
                return shiftModel;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ShiftModel> selectAllShift(Date date) {
        List<ShiftModel> ketQua = new ArrayList<ShiftModel>();
        try {
            String sql = "select * from fKTTheoCa(?)";
            ResultSet rs = con.getResultSet(sql,date);
            ShiftModel shift = null;
            while (rs.next()) {
                shift = new ShiftModel();
                shift.setMaCa(rs.getString("maCa"));
                shift.setTenCa(rs.getString("tenCa"));
                shift.setTgBD(rs.getTime("thoiGianBatDau"));
                ketQua.add(shift);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ketQua;
    }

    public List<RoomModel> selectAllRoom(Date date,ShiftModel shiftModel) {
        List<RoomModel> ketQua = new ArrayList<RoomModel>();
        try {
            String sql = "select * from fPhongChuaChieu(?,?)";
//            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.setDate(1, date);
//            pstmt.setString(2, shiftModel.getMaCa());
            ResultSet rs = con.getResultSet(sql,date,shiftModel.getMaCa());
            RoomModel room = null;
            while (rs.next()) {
                room = new RoomModel();
                room.setMaPhong(rs.getString("maPhong"));
                ketQua.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ketQua;
    }

//    @Override
//    public String insert(ScheduleModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            String sql = "INSERT INTO SuatChieu (maPhim, maPhong, maCa, ngayChieu) VALUES (?,?,?,?)";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, t.getMaPhim());
//            pst.setString(2, t.getMaPhong());
//            pst.setString(3, t.getMaCa());
//            pst.setDate(4, (Date) t.getNgayChieu());
//            pst.execute();
//            ResultSet rs = pst.getGeneratedKeys();
//            while (rs.next()) {
//                ketQua = rs.getString(1);
//            }
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return ketQua;
//    }
//
//    @Override
//    public String update(ScheduleModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            String sql = "UPDATE SuatChieu SET maPhim = ?, maPhong = ?, maCa = ?, ngayChieu = ? WHERE maSuat = ?";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, t.getMaPhim());
//            pst.setString(2, t.getMaPhong());
//            pst.setString(3, t.getMaCa());
//            pst.setDate(4, (Date) t.getNgayChieu());
//            pst.setString(5,t.getMaSC());
//
//            int affectedRows = pst.executeUpdate();
//            if (affectedRows > 0) {
//                ResultSet rs = pst.getGeneratedKeys();
//                while (rs.next()) {
//                    ketQua = rs.getString(1);
//                }
//            }
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return ketQua;
//    }
//
//    @Override
//    public String delete(ScheduleModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from SuatChieu Where maSuat = ?";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1,t.getMaSC());
//            int affectedRows = pst.executeUpdate();
//            if (affectedRows > 0) {
//                ResultSet rs = pst.getGeneratedKeys();
//                while (rs.next()) {
//                    ketQua = rs.getString(1);
//                }
//            }
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return ketQua;
//    }


}

package Dao.impl;

import Dao.DAOInterface;
import Dao.SQLSEVERDataAccess;
import Service.impl.TypeMovieService;
import model.MovieModel;
import model.TypeMovieModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDAO implements DAOInterface<MovieModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    TypeMovieService typeMovieService = new TypeMovieService();
    public static MovieDAO getInstance() {
        return new MovieDAO();
    }


    @Override
    public int insert(MovieModel m) {
        String sql = "INSERT INTO Phim (maLPhim, tenPhim, daoDien, doTuoiYeuCau, ngayKhoiChieu, thoiLuong, tinhTrang, hinhDaiDien, video,moTa) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int k = con.ExecuteUpdateSQL(sql,m.getTypeMovieModel().getMaLPhim(),m.getTenPhim(),m.getDaoDien(),m.getDoTuoi(),
                                    (Date) m.getNgayKhoiChieu(),m.getThoiLuong(),m.isTinhTrang(),m.getHinhDaiDien(),m.getVideo(),m.getMoTa());
        return k;
    }

    @Override
    public int update(MovieModel m) {
        StringBuilder sql = new StringBuilder("UPDATE Phim SET ");
        sql.append("maLPhim = ?, tenPhim = ?, daoDien = ?, doTuoiYeuCau = ?, ngayKhoiChieu = ?, thoiLuong = ?, tinhTrang = ?, hinhDaiDien = ?, video = ?,moTa = ?");
        sql.append(" Where maPhim = ?");
        int k = con.ExecuteUpdateSQL(sql.toString(),m.getTypeMovieModel().getMaLPhim(),m.getTenPhim(),m.getDaoDien(),m.getDoTuoi(),
                                    (Date) m.getNgayKhoiChieu(),m.getThoiLuong(),m.isTinhTrang(),m.getHinhDaiDien(),m.getVideo(),m.getMoTa(),m.getMaPhim());
        return k;
    }

    @Override
    public int delete(MovieModel m) {
        String sql = "DELETE from Phim Where maPhim = ?";
        int k = con.ExecuteUpdateSQL(sql, m.getMaPhim());
        return k;
    }

    @Override
    public List<MovieModel> selectAll() {
        List<MovieModel> result = new ArrayList<MovieModel>();
        try {
            String sql = "SELECT * FROM Phim";
            ResultSet rs = con.getResultSet(sql);
            MovieModel movieModel = null;
            while (rs.next()) {
                movieModel = new MovieModel();
                movieModel.setMaPhim(rs.getString("maPhim"));
                movieModel.setTenPhim(rs.getString("tenPhim"));
                movieModel.setDaoDien(rs.getString("daoDien"));
                movieModel.setDoTuoi(rs.getInt("doTuoiYeuCau"));
                movieModel.setNgayKhoiChieu(rs.getDate("ngayKhoiChieu"));
                movieModel.setThoiLuong(rs.getInt("thoiLuong"));
                movieModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                movieModel.setHinhDaiDien(rs.getString("hinhDaiDien"));
                movieModel.setVideo(rs.getString("video"));
                movieModel.setMoTa(rs.getString("moTa"));
                try {
                    TypeMovieModel type = new TypeMovieModel();
                    type.setMaLPhim(rs.getString("maLPhim"));
                    movieModel.setTypeMovieModel(typeMovieService.selectById(type));
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                result.add(movieModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public MovieModel selectById(MovieModel m) {
        List<MovieModel> result = new ArrayList<MovieModel>();
        try {
            String sql = "SELECT * FROM Phim where maPhim= ?";
            ResultSet rs = con.getResultSet(sql, m.getMaPhim());
            MovieModel movieModel = null;
            while (rs.next()) {
                movieModel = new MovieModel();
                movieModel.setMaPhim(rs.getString("maPhim"));
                movieModel.setTenPhim(rs.getString("tenPhim"));
                movieModel.setDaoDien(rs.getString("daoDien"));
                movieModel.setDoTuoi(rs.getInt("doTuoiYeuCau"));
                movieModel.setNgayKhoiChieu(rs.getDate("ngayKhoiChieu"));
                movieModel.setThoiLuong(rs.getInt("thoiLuong"));
                movieModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                movieModel.setHinhDaiDien(rs.getString("hinhDaiDien"));
                movieModel.setVideo(rs.getString("video"));
                movieModel.setMoTa(rs.getString("moTa"));
                try {
                    TypeMovieModel type = new TypeMovieModel();
                    type.setMaLPhim(rs.getString("maLPhim"));
                    movieModel.setTypeMovieModel(typeMovieService.selectById(type));
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                result.add(movieModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result.get(0);
    }

    public List<MovieModel> selectStatus() {
        List<MovieModel> ketQua = new ArrayList<MovieModel>();
        try {
            String sql = "SELECT * FROM Phim Where tinhTrang = 1";
            ResultSet rs = con.getResultSet(sql);
            MovieModel movieModel = null;
            while (rs.next()) {
                movieModel = new MovieModel();
                movieModel.setMaPhim(rs.getString("maPhim"));
                movieModel.setTenPhim(rs.getString("tenPhim"));
                movieModel.setDaoDien(rs.getString("daoDien"));
                movieModel.setDoTuoi(rs.getInt("doTuoiYeuCau"));
                movieModel.setNgayKhoiChieu(rs.getDate("ngayKhoiChieu"));
                movieModel.setThoiLuong(rs.getInt("thoiLuong"));
                movieModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                movieModel.setHinhDaiDien(rs.getString("hinhDaiDien"));
                movieModel.setVideo(rs.getString("video"));
                movieModel.setMoTa(rs.getString("moTa"));
                try {
                    TypeMovieModel type = new TypeMovieModel();
                    type.setMaLPhim(rs.getString("maLPhim"));
                    movieModel.setTypeMovieModel(typeMovieService.selectById(type));
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                ketQua.add(movieModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ketQua;
    }
    //    @Override
//    public String insert(MovieModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            String sql = "INSERT INTO Phim (maLPhim, tenPhim, daoDien, doTuoiYeuCau, ngayKhoiChieu, thoiLuong, tinhTrang, hinhDaiDien, video,moTa) VALUES (?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, t.getTypeMovieModel().getMaLPhim());
//            pst.setString(2, t.getTenPhim());
//            pst.setString(3, t.getDaoDien());
//            pst.setInt(4, t.getDoTuoi());
//            pst.setDate(5,(Date) t.getNgayKhoiChieu());
//            pst.setInt(6, t.getThoiLuong());
//            pst.setBoolean(7, t.isTinhTrang());
//            pst.setString(8, t.getHinhDaiDien());
//            pst.setString(9, t.getVideo());
//            pst.setString(10, t.getMoTa());
//
//            pst.execute();
//            ResultSet rs = pst.getGeneratedKeys();
//            while (rs.next()) {
//                    ketQua = rs.getString(1);
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
//    public String update(MovieModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            StringBuilder sql = new StringBuilder("UPDATE Phim SET ");
//            sql.append("maLPhim = ?, tenPhim = ?, daoDien = ?, doTuoiYeuCau = ?, ngayKhoiChieu = ?, thoiLuong = ?, tinhTrang = ?, hinhDaiDien = ?, video = ?,moTa = ?");
//            sql.append(" Where maPhim = ?");
//            PreparedStatement pst = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, t.getTypeMovieModel().getMaLPhim());
//            pst.setString(2, t.getTenPhim());
//            pst.setString(3, t.getDaoDien());
//            pst.setInt(4, t.getDoTuoi());
//            pst.setDate(5,(Date) t.getNgayKhoiChieu());
//            pst.setInt(6, t.getThoiLuong());
//            pst.setBoolean(7, t.isTinhTrang());
//            pst.setString(8, t.getHinhDaiDien());
//            pst.setString(9, t.getVideo());
//            pst.setString(10, t.getMoTa());
//            pst.setString(11, t.getMaPhim());
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
//    public String delete(MovieModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from Phim Where maPhim = ?";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1,t.getMaPhim());
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

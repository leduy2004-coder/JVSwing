package Dao.impl;

import Dao.DAOInterface;
import Dao.SQLSEVERDataAccess;
import model.MovieModel;
import model.TypeMovieModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDAO implements DAOInterface<MovieModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();

    public static MovieDAO getInstance() {
        return new MovieDAO();
    }


    @Override
    public int insert(MovieModel m) {
        try {
            InputStream is = new FileInputStream(new File(m.getS()));
            String sql = "INSERT INTO Phim (maLPhim, tenPhim, daoDien, doTuoiYeuCau, ngayKhoiChieu, thoiLuong, tinhTrang, hinhDaiDien, video,moTa) VALUES (?,?,?,?,?,?,?,?,?,?)";
            int k = con.ExecuteUpdateSQL(sql,m.getTypeMovieModel().getMaLPhim(),m.getTenPhim(),m.getDaoDien(),m.getDoTuoi(),
                    (Date) m.getNgayKhoiChieu(),m.getThoiLuong(),m.isTinhTrang(),is,m.getVideo(),m.getMoTa());
            return k;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(MovieModel m) {
        try {
            int k;
            StringBuilder sql = new StringBuilder("UPDATE Phim SET ");
            sql.append("maLPhim = ?, tenPhim = ?, daoDien = ?, doTuoiYeuCau = ?, ngayKhoiChieu = ?, thoiLuong = ?, tinhTrang = ?, hinhDaiDien = ?, video = ?,moTa = ?");
            sql.append(" Where maPhim = ?");
            if(m.getS()!=null){
                InputStream is = new FileInputStream(new File(m.getS()));
                k = con.ExecuteUpdateSQL(sql.toString(),m.getTypeMovieModel().getMaLPhim(),m.getTenPhim(),m.getDaoDien(),m.getDoTuoi(),
                        (Date) m.getNgayKhoiChieu(),m.getThoiLuong(),m.isTinhTrang(),is,m.getVideo(),m.getMoTa(),m.getMaPhim());
            }else
                k = con.ExecuteUpdateSQL(sql.toString(),m.getTypeMovieModel().getMaLPhim(),m.getTenPhim(),m.getDaoDien(),m.getDoTuoi(),
                        (Date) m.getNgayKhoiChieu(),m.getThoiLuong(),m.isTinhTrang(),m.getImg(),m.getVideo(),m.getMoTa(),m.getMaPhim());
        return k;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                movieModel.setImg(rs.getBytes("hinhDaiDien"));
                movieModel.setVideo(rs.getString("video"));
                movieModel.setMoTa(rs.getString("moTa"));
                try {
                    TypeMovieModel type = new TypeMovieModel();
                    type.setMaLPhim(rs.getString("maLPhim"));
                    movieModel.setTypeMovieModel(TypeMovieDAO.getInstance().selectById(type));
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

    public List<MovieModel> selectMovieUnShow() {
        List<MovieModel> result = new ArrayList<MovieModel>();
        try {
            String sql = "SELECT maPhim,tenPhim FROM Phim where maPhim not in (select maPhim from Ve)";
            ResultSet rs = con.getResultSet(sql);
            MovieModel movieModel = null;
            while (rs.next()) {
                movieModel = new MovieModel();
                movieModel.setMaPhim(rs.getString("maPhim"));
                movieModel.setTenPhim(rs.getString("tenPhim"));
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
                movieModel.setImg(rs.getBytes("hinhDaiDien"));
                movieModel.setVideo(rs.getString("video"));
                movieModel.setMoTa(rs.getString("moTa"));
                try {
                    TypeMovieModel type = new TypeMovieModel();
                    type.setMaLPhim(rs.getString("maLPhim"));
                    movieModel.setTypeMovieModel(TypeMovieDAO.getInstance().selectById(type));
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
                movieModel.setImg(rs.getBytes("hinhDaiDien"));
                movieModel.setVideo(rs.getString("video"));
                movieModel.setMoTa(rs.getString("moTa"));
                try {
                    TypeMovieModel type = new TypeMovieModel();
                    type.setMaLPhim(rs.getString("maLPhim"));
                    movieModel.setTypeMovieModel(TypeMovieDAO.getInstance().selectById(type));
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
}

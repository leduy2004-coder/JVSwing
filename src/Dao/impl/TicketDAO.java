package Dao.impl;

import Dao.DAOInterface;
import Dao.SQLSEVERDataAccess;
import Service.impl.MovieService;
import model.MovieModel;
import model.TicketModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements DAOInterface<TicketModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    MovieService movieService = new MovieService();
    public static TicketDAO getInstance() {
        return new TicketDAO();
    }


    @Override
    public int insert(TicketModel t) {
        String sql = "INSERT INTO Ve (maPhim, maNV, tinhTrang, soLuongToiDa, soLuongDaDat, tien) VALUES (?,?,?,?,?,?)";
        int k = con.ExecuteUpdateSQL(sql,t.getMovieModel().getMaPhim(), t.getMaNV(), t.isTinhTrang(), t.getSoLuongToiDa(),t.getSoLuongDaDat(), t.getTien());
        return k;
    }

    @Override
    public int update(TicketModel t) {
        StringBuilder sql = new StringBuilder("UPDATE Ve SET ");
        sql.append("maPhim = ?, maNV = ?, tinhTrang = ?, soLuongToiDa = ?, soLuongDaDat = ?, tien = ?");
        sql.append(" Where maVe = ?");
        int k = con.ExecuteUpdateSQL(sql.toString(),t.getMovieModel().getMaPhim(), t.getMaNV(), t.isTinhTrang(),
                                    t.getSoLuongToiDa(),t.getSoLuongDaDat(), t.getTien(), t.getMaVe());
        return k;
    }

    @Override
    public int delete(TicketModel t) {
        String sql = "DELETE from Ve Where maVe = ?";
        int k = con.ExecuteUpdateSQL(sql, t.getMaVe());
        return k;
    }

    @Override
    public List<TicketModel> selectAll() {
        List<TicketModel> result = new ArrayList<TicketModel>();
        try {
            String sql = "SELECT * FROM Ve";
            ResultSet rs = con.getResultSet(sql);
            TicketModel ticketModel = null;
            while (rs.next()) {
                ticketModel = new TicketModel();
                ticketModel.setMaNV(rs.getString("maNV"));
                ticketModel.setTien(rs.getFloat("tien"));
                ticketModel.setSoLuongDaDat(rs.getInt("soLuongDaDat"));
                ticketModel.setSoLuongToiDa(rs.getInt("soLuongToiDa"));
                ticketModel.setTinhTrang(rs.getBoolean("tinhTrang"));

                try {
                    MovieModel movie = new MovieModel();
                    movie.setMaPhim(rs.getString("maPhim"));
                    ticketModel.setMovieModel(movieService.selectById(movie));
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                result.add(ticketModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}

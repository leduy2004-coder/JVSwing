package Dao.impl;

import Dao.DAOInterface;
import Dao.JDBCUtil;
import Dao.SQLSEVERDataAccess;
import model.TypeMovieModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeMovieDAO implements DAOInterface<TypeMovieModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();

    public static TypeMovieDAO getInstance() {
        return new TypeMovieDAO();
    }


    @Override
    public int insert(TypeMovieModel typeMovieModel) {
        return 0;
    }

    @Override
    public int update(TypeMovieModel typeMovieModel) {
        return 0;
    }

    @Override
    public int delete(TypeMovieModel typeMovieModel) {
        return 0;
    }

    @Override
    public List<TypeMovieModel> selectAll() {
        List<TypeMovieModel> result = new ArrayList<TypeMovieModel>();
        try {
            String sql = "SELECT * FROM TheLoaiPhim";
            ResultSet rs = con.getResultSet(sql);
            TypeMovieModel type = null;
            while (rs.next()) {
                type = new TypeMovieModel();
                type.setMaLPhim(rs.getString("maLPhim"));
                type.setTenLPhim(rs.getString("tenLPhim"));
                type.setMoTaLP(rs.getString("moTaLP"));
                result.add(type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public TypeMovieModel selectById(TypeMovieModel t) {
        List<TypeMovieModel> result = new ArrayList<TypeMovieModel>();
        try {
            String sql = "SELECT * FROM TheLoaiPhim where maLPhim = N'"+t.getMaLPhim()+"'";
            ResultSet rs = con.getResultSet(sql);
            TypeMovieModel typeMovieModel = null;
            while (rs.next()) {
                typeMovieModel = new TypeMovieModel();
                typeMovieModel.setMaLPhim(rs.getString("maLPhim"));
                typeMovieModel.setTenLPhim(rs.getString("tenLPhim"));
                typeMovieModel.setMoTaLP(rs.getString("moTaLP"));
                result.add(typeMovieModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result.get(0);
    }

    public TypeMovieModel selectByName(TypeMovieModel t) {
        List<TypeMovieModel> result = new ArrayList<TypeMovieModel>();
        try {
            String sql = "SELECT * FROM TheLoaiPhim where tenLPhim = N'"+t.getTenLPhim()+"'";
            ResultSet rs = con.getResultSet(sql);
            TypeMovieModel typeMovieModel = null;
            while (rs.next()) {
                typeMovieModel = new TypeMovieModel();
                typeMovieModel.setMaLPhim(rs.getString("maLPhim"));
                typeMovieModel.setTenLPhim(rs.getString("tenLPhim"));
                typeMovieModel.setMoTaLP(rs.getString("moTaLP"));
                result.add(typeMovieModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result.get(0);
    }

}

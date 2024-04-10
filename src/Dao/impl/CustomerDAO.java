
package Dao.impl;
import Dao.DAOInterface;
import Dao.JDBCUtil;
import Dao.SQLSEVERDataAccess;
import model.CustomerModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDAO implements DAOInterface<CustomerModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    @Override
    public int insert(CustomerModel c) {
        String sql = "INSERT INTO KhachHang (hoTen, sdt, ngaySinh, email,tinhTrang, tenTK, matKhau) VALUES (?,?,?,?,?,?,?)";
        int k = con.ExecuteUpdateSQL(sql, c.getHoTen(),c.getSdt(),(Date)c.getNgaySinh(),c.getEmail(),c.isTinhTrang(),c.getTentk(),c.getMatKhau());
        return k;
    }

    @Override
    public int update(CustomerModel c) {
        StringBuilder sql = new StringBuilder("UPDATE KhachHang SET ");
        sql.append("hoten = ?, sdt = ?, ngaySinh = ?, email = ?,tinhTrang=?, tenTK = ?, matKhau = ?");
        sql.append(" Where maKH = ?");
        int k = con.ExecuteUpdateSQL(sql.toString(), c.getHoTen(),c.getSdt(),(Date)c.getNgaySinh(),c.getEmail(),c.isTinhTrang(),c.getTentk(),c.getMatKhau(),c.getMaKH());
        return k;
    }

    @Override
    public int delete(CustomerModel c) {
        String sql = "DELETE from KhachHang Where maKH = ?";
        int k = con.ExecuteUpdateSQL(sql, c.getMaKH());
        return k;
    }

    @Override
    public List<CustomerModel> selectAll() {
        List<CustomerModel> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KhachHang";
            ResultSet rs = con.getResultSet(sql);
            CustomerModel customerModel = null;
            while (rs.next()) {
                customerModel = new CustomerModel();
                customerModel.setMaKH(rs.getString("maKH"));
                customerModel.setHoTen(rs.getString("hoTen"));
                customerModel.setSdt(rs.getString("sdt"));
                customerModel.setEmail(rs.getString("email"));
                customerModel.setNgaySinh(rs.getDate("ngaySinh"));
                customerModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                customerModel.setTentk(rs.getString("tenTK"));
                customerModel.setMatKhau(rs.getString("matKhau"));
                result.add(customerModel);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    @Override
//    public String insert(CustomerModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            String sql = "INSERT INTO KhachHang (hoTen, sdt, ngaySinh, email,tinhTrang, tenTK, matKhau) VALUES (?,?,?,?,?,?,?)";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, t.getHoTen());
//            pst.setString(2, t.getSdt());
//            pst.setDate(3, (Date) t.getNgaySinh());
//            pst.setString(4, t.getEmail());
//            pst.setBoolean(5,t.isTinhTrang());
//            pst.setString(6, t.getTentk());
//            pst.setString(7, t.getMatKhau());
//
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
//    public String update(CustomerModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            StringBuilder sql = new StringBuilder("UPDATE KhachHang SET ");
//            sql.append("hoten = ?, sdt = ?, ngaySinh = ?, email = ?,tinhTrang=?, tenTK = ?, matKhau = ?");
//            sql.append(" Where maKH = ?");
//            PreparedStatement pst = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, t.getHoTen());
//            pst.setString(2, t.getSdt());
//            pst.setDate(3, (Date) t.getNgaySinh());
//            pst.setString(4,t.getEmail());
//            pst.setBoolean(5, t.isTinhTrang());
//            pst.setString(6, t.getTentk());
//            pst.setString(7, t.getMatKhau());
//            pst.setString(8, t.getMaKH());
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
//    public String delete(CustomerModel t) {
//        String ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "DELETE from KhachHang Where maKH = ?";
//            PreparedStatement pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1,t.getMaKH());
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
//
//    @Override
//    public List<CustomerModel> selectAll() {
//        List<CustomerModel> ketQua = new ArrayList<CustomerModel>();
//        try {
//            Connection con = JDBCUtil.getConnection();
//            Statement st = con.createStatement();
//            String sql = "SELECT * FROM KhachHang";
//            ResultSet rs = st.executeQuery(sql);
//            CustomerModel customerModel = null;
//            while (rs.next()) {
//                customerModel = new CustomerModel();
//                customerModel.setMaKH(rs.getString("maKH"));
//                customerModel.setHoTen(rs.getString("hoTen"));
//                customerModel.setSdt(rs.getString("sdt"));
//                customerModel.setEmail(rs.getString("email"));
//                customerModel.setNgaySinh(rs.getDate("ngaySinh"));
//                customerModel.setTinhTrang(rs.getBoolean("tinhTrang"));
//                customerModel.setTentk(rs.getString("tenTK"));
//                customerModel.setMatKhau(rs.getString("matKhau"));
//                ketQua.add(customerModel);
//            }
//            //b5
//            JDBCUtil.closeConnection(con);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return ketQua;
//    }


}

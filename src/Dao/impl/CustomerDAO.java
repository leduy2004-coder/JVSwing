
package Dao.impl;
import Dao.DAOInterface;
import Dao.SQLSEVERDataAccess;
import model.BookTicketModel;
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

    public CustomerModel insertGetId(CustomerModel c) {
        CustomerModel result = new CustomerModel();
        try {
            String sql = "INSERT INTO KhachHang (hoTen, sdt, ngaySinh, email,tinhTrang, tenTK, matKhau) VALUES (?,?,?,?,?,?,?)";
            ResultSet rs = con.getResultSet(sql, c.getHoTen(),c.getSdt(),(Date)c.getNgaySinh(),c.getEmail(),c.isTinhTrang(),c.getTentk(),c.getMatKhau());
            CustomerModel customerModel = null;
            while (rs.next()) {
                customerModel = new CustomerModel();
                customerModel.setMaKH(rs.getString("maBook"));
                result = customerModel;
                break;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int insert(CustomerModel customerModel) {
        return 0;
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


}

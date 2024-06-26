package Dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.DAOInterface;
import Dao.SQLSEVERDataAccess;
import model.EmployeeModel;

public class EmployeeDAO implements DAOInterface<EmployeeModel> {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    public static EmployeeDAO getInstance() {
        return new EmployeeDAO();
    }

    @Override
    public int insert(EmployeeModel e) {
        String sql = "INSERT INTO NhanVien (hoTen, sdt, gioiTinh, ngaySinh, diaChi, cccd, tinhTrang, tenTK, matKhau, maQL) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int k = con.ExecuteUpdateSQL(sql, e.getHoTen(),e.getSdt(),e.isGioiTinh(),(Date)e.getNgaySinh(),e.getDiaChi(),
                                    e.getCCCD(),e.isTinhTrang(),e.getTentk(),e.getMatKhau(),e.getMaQL());
        return k;
    }

    @Override
    public int update(EmployeeModel e) {
        StringBuilder sql = new StringBuilder("UPDATE NhanVien SET ");
        sql.append("hoten = ?, sdt = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, cccd = ?, tinhTrang = ?, tenTK = ?, matKhau = ?");
        sql.append(" Where maNV = ?");
        int k = con.ExecuteUpdateSQL(sql.toString(), e.getHoTen(),e.getSdt(),e.isGioiTinh(),(Date)e.getNgaySinh(),
                                    e.getDiaChi(),e.getCCCD(),e.isTinhTrang(),e.getTentk(),e.getMatKhau(),e.getMaNV());
        return k;
    }

    @Override
    public int delete(EmployeeModel e) {
        String sql = "DELETE from NhanVien Where maNV = ?";
        int k = con.ExecuteUpdateSQL(sql, e.getMaNV());
        return k;
    }

    @Override
    public List<EmployeeModel> selectAll() {
        List<EmployeeModel> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM NhanVien";
            ResultSet rs = con.getResultSet(sql);
            EmployeeModel employeeModel = null;
            while (rs.next()) {
                employeeModel = new EmployeeModel();
                employeeModel.setMaNV(rs.getString("maNV"));
                employeeModel.setHoTen(rs.getString("hoTen"));
                employeeModel.setSdt(rs.getString("sdt"));
                employeeModel.setGioiTinh(rs.getBoolean("gioiTinh"));
                employeeModel.setNgaySinh(rs.getDate("ngaySinh"));
                employeeModel.setDiaChi(rs.getString("diaChi"));
                employeeModel.setCCCD(rs.getLong("cccd"));
                employeeModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                employeeModel.setTentk(rs.getString("tenTK"));
                employeeModel.setMatKhau(rs.getString("matKhau"));
                result.add(employeeModel);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public EmployeeModel selectByUserNameAndPassword(String userName, String password) {
        EmployeeModel result = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE tenTK = ? and matKhau = ?";
            ResultSet rs = con.getResultSet(sql,userName,password);
            while (rs.next()) {
                EmployeeModel e = new EmployeeModel();
                e.setHoTen(rs.getString("hoTen"));
                e.setMaNV(rs.getString("maNV"));
                e.setTinhTrang(rs.getBoolean("tinhTrang"));
                result = e;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}

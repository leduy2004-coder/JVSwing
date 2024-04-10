package Dao.impl;

import Dao.JDBCUtil;
import Dao.SQLSEVERDataAccess;
import model.ManageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManageDAO {
    SQLSEVERDataAccess con = new SQLSEVERDataAccess();
    public ManageModel selectByUserNameAndPassword(String userName, String password) {
        ManageModel result = null;
        try {
            String sql = "SELECT * FROM QuanLi WHERE tenTK = ? and matKhau = ?";
            ResultSet rs = con.getResultSet(sql,userName,password);
            while (rs.next()) {
                ManageModel manageModel = new ManageModel();
                manageModel.setHoTen(rs.getString("hoTen"));
                manageModel.setMaNV(rs.getString("maQL"));
                manageModel.setTinhTrang(rs.getBoolean("tinhTrang"));
                result = manageModel;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

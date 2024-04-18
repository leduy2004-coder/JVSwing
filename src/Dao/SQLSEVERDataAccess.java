package Dao;
import java.sql.*;

public class SQLSEVERDataAccess {
    Connection cnn;
    Statement stm;
    PreparedStatement ps;

    public SQLSEVERDataAccess() {
        try {
            String DriverClass, DriverURL;
            String UserName = "sa";
            String PassWord = "ad123456";
            String DataBaseName = "Cinema";
            String ServerName="DESKTOP-2NFEM03";

            String IntegratedSecurity = "IntegratedSecurity=false";
            DriverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            DriverURL = "jdbc:sqlserver://"+ServerName+":1433;databaseName="+DataBaseName+";user="+UserName+" ;password="+PassWord+";encrypt=true;trustServerCertificate=true";
            Class.forName(DriverClass);
            this.cnn=DriverManager.getConnection(DriverURL,UserName,PassWord);
            this.stm = this.cnn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(String SQL) {
        try {
            ResultSet rs;
            rs = this.stm.executeQuery(SQL);
            return rs;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    //SELECT * FROM TBLAOINHANVIEN WHERE  IDLOAINHANVIEN=?
    public ResultSet getResultSet(String SQL, Object... param) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement(SQL);
            int i = 1;
            for (Object value : param) {
                ps.setObject(i, value);
                i++;
            }
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String insertAndGetId(String SQL, Object... param) {
        PreparedStatement ps = null;
        String generatedMaQL = null;
        try {
            cnn.setAutoCommit(false);
            ps = cnn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Object value : param) {
                ps.setObject(i, value);
                i++;
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    generatedMaQL = rs.getString(1);
                    System.out.println("Generated maQL: " + generatedMaQL);
                }
                cnn.commit();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return generatedMaQL;
    }


    //SQL (DELETE, UPDATE, INSERT)
    //INSERT INTO TBLOAINHANVIEN(IDLOAINHANVIEN,TENLOAINHANVIEN) VALUES(1,'NHAN VIEN BIEN CHE')
    public int ExecuteUpdateSQL(String SQL) {
        try {
            int k = 0;
            k = this.stm.executeUpdate(SQL);
            return k;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //INSERT INTO TBLOAINHANVIEN(IDLOAINHANVIEN,TENLOAINHANVIEN) VALUES(?,?)
    public int ExecuteUpdateSQL(String SQL, Object... param) {
        try {
            int k = 0;
            PreparedStatement ps = this.cnn.prepareStatement(SQL);
            int i = 1;
            for (Object value : param) {
                ps.setObject(i, value);
                i++;
            }
            k = ps.executeUpdate();
            ps.close();
            return k;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int Execute_StoredProcedures(String NameStoredProcedures, Object... param) {
        try {
            int k = 0;
            CallableStatement  ps = this.cnn.prepareCall("{call "+NameStoredProcedures+"}");
            int i = 1;
            for (Object value : param) {

                ps.setObject(i, value);
                i++;
            }
            k = ps.executeUpdate();
            return k;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ResultSet getResultSet_StoredProcedures(String NameStoredProcedures, Object... param) {
        ResultSet rs = null;
        CallableStatement ps = null;

        try {
            ps = cnn.prepareCall("{call "+NameStoredProcedures+"}");
            if(param!=null)
            {
                int i = 1;
                for (Object value : param) {
                    ps.setObject(i, value);
                    i++;
                }
            }
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

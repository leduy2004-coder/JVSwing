import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-2NFEM03:1433;databaseName=LD2006;encrypt=true;trustServerCertificate=true";
            String userName = "sa";
            String password = "ad123456";

            Connection connection = DriverManager.getConnection(url,userName,password);
            String sql = "select * from dbo.BacSi";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
            System.out.println("ok");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
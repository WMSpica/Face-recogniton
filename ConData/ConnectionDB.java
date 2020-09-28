package ConData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
//////////////////////////////////////////////////////    
    public Statement stm;
    public ResultSet rs;
    public Connection conn;
///////////////////////////////////////////////////////
    private final String driver = "org.mysql.Driver";
    private final String path   = "jdbc:mysql://127.0.0.1/face_recognition";
    private final String user   = "root";
    private final String pass   = "";
    
    public void conn() {
        try {
            System.setProperty("jdbc.Driver", driver);
            conn = DriverManager.getConnection(path, user, pass);  
           //// System.out.println("Sve je ok! ");
        } catch (SQLException e) {
            System.out.println("Greska! " + e);
        }
    }
     public void cloconn() throws SQLException {
        conn.close();
    }
      public void executSQL(String SQL) {
      
          try {
              stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
              rs = stm.executeQuery(SQL);
          }catch (Exception e){
            System.out.println("Greska!!! " + e);
          }
      }
}
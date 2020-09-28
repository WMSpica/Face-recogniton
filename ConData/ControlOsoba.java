package ConData;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;

public class ControlOsoba {
    
     ConnectionDB conect = new ConnectionDB();
    
    public void insert(ModelOsoba mod) {
        
        try{
            conect.conn();
            PreparedStatement ps = (PreparedStatement) conect.conn.prepareStatement("INSERT INTO osoba "
               + "(id, ime, prezime, godina_rodjenja, mail) values (?, ?, ?, ?, ?)");

            ps.setInt(1, mod.getId());
            ps.setString(2, mod.getIme());
            ps.setString(3, mod.getPrezime());
            ps.setString(4, mod.getGodina_rodjenja());
            ps.setString(5, mod.getMail());
           
            ps.executeUpdate();
            conect.cloconn();
            
         } catch (SQLException ex) {
             System.out.println("Greska! " + ex);
        }
    }    
}
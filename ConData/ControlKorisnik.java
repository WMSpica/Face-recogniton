package ConData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlKorisnik {
    
     ConnectionDB conect = new ConnectionDB();
    
    public void insert(ModelKorisnik mod) {
        
        try{
            conect.conn();
            PreparedStatement psk = (PreparedStatement) conect.conn.prepareStatement("INSERT INTO korisnik "
               + "(id, korisnicko_ime, lozinka) values (?, ?, ?)");

            psk.setInt(1, mod.getId());
            psk.setString(2, mod.getkorisnicko_ime());
            psk.setString(3, mod.getlozinka());
           
            psk.executeUpdate();
            conect.cloconn();
            
         } catch (SQLException ex) {
             System.out.println("Greska! " + ex);
        }
    }      
}
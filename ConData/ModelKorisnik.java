package ConData;

public class ModelKorisnik {
        
    private int id;
    private String korisnicko_ime;
    private String lozinka;
  
    public ModelKorisnik(int id, String korisnicko_ime, String lozinka) {
        this.id = id;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
      
    }
       public ModelKorisnik() {
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setkorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }
    public void setlozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    public int getId() {
        return id;
    }
    public String getkorisnicko_ime() {
        return korisnicko_ime;
    }
    public String getlozinka() {
        return lozinka;
    }
}
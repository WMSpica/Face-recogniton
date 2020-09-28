package ConData;

public class ModelOsoba {
    
    private int id;
    private String ime;
    private String prezime;
    private String godina_rodjenja;
    private String mail;
  
    public ModelOsoba(int id, String ime, String prezime, String godina_rodjenja, String mail) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.godina_rodjenja = godina_rodjenja;
        this.mail = mail;       
    }
       public ModelOsoba() {
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public void setGodina_rodjenja(String godina_rodjenja) {
        this.godina_rodjenja = godina_rodjenja;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getId() {
        return id;
    }
    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public String getGodina_rodjenja() {
        return godina_rodjenja;
    }
    public String getMail() {
        return mail;
    }
}
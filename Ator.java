import java.time.LocalDateTime;
/**
 * Write a description of class Atores here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ator{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDateTime data;
    
    public Ator(){
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.data = LocalDateTime.now();
    }
    
    public Ator(String email, String nome, String password, String morada, LocalDateTime data){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.data = data;
    }
    
    public Ator(Ator c){
        this.email = c.getEmail();
        this.nome = c.getNome();
        this.password = c.getPassword();
        this.morada = c.getMorada();
        this.data = c.getData();
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getMorada(){
        return this.morada;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
        
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setMorada(String morada){
        this.morada = morada;
    }
    
    public void setData(LocalDateTime data){
        this.data = data;
    }
        
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Ator c = (Ator) o;
        return this.email.equals(c.getEmail()) 
            && this.nome.equals(c.getNome()) 
            && this.password.equals(c.getPassword()) 
            && this.morada.equals(c.getMorada())
            && this.data.equals(c.getData());
    }
    
    public Ator clone(){
        return new Ator(this);
    }

    public String toString(){
        StringBuilder s= new StringBuilder("Proprietario");
        s.append(super.toString());
        s.append("\n Email: " + this.email);
        s.append("\n Nome: " + this.nome);
        s.append("\n Password: " + this.password);
        s.append("\n Morada: " + this.morada);
        s.append("\n Data: " + this.data.toString());
        return s.toString();
    }
}

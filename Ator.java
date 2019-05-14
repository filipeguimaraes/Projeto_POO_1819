import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Write a description of class Atores here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ator{
    private String nome;
    private int nif;
    private String email;
    private String password;
    private String morada;
    private LocalDateTime data;
    private Classificacao classificacao;
    
    public Ator(){
        this.email = "";
        this.nif = 0;
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.data = LocalDateTime.now();
        this.classificacao= new Classificacao();
    }
    
    public Ator(String email,int nif, String nome, String password, String morada, LocalDateTime data,Classificacao classificacao){
        this.email = email;
        this.nome = nome;
        this.nif=nif;
        this.password = password;
        this.morada = morada;
        this.data = data;
        this.classificacao=classificacao;
    }
    
    public Ator(Ator c){
        this.email = c.getEmail();
        this.nome = c.getNome();
        this.nif=c.getNif();
        this.password = c.getPassword();
        this.morada = c.getMorada();
        this.data = c.getData();
        this.classificacao = c.getClassificacao();
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

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public void adicionaClassificacao(int classificacao){
        this.classificacao.adicionaClassificacao(classificacao);
    }

    public double classificacaoMedia(){
        return this.classificacao.classificacaoMedia();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return nif == ator.nif &&
                Objects.equals(nome, ator.nome) &&
                Objects.equals(email, ator.email) &&
                Objects.equals(password, ator.password) &&
                Objects.equals(morada, ator.morada) &&
                Objects.equals(data, ator.data) &&
                Objects.equals(classificacao, ator.classificacao);
    }


    public Ator clone(){
        return new Ator(this);
    }

    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("Geral{");
        s.append(" Email: " + this.email);
        s.append(", Nome: " + this.nome);
        s.append(", Password: " + this.password);
        s.append(", Morada: " + this.morada);
        s.append(", Data: " + this.data.toString());
        s.append(", Classificação: " + this.classificacao.toString() +'}');
        return s.toString();
    }
}

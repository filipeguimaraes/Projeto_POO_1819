import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Write a description of class Proprietario here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Proprietario extends Ator{
    private int classificacao;
    private List<Aluguer> historico;
    
    public Proprietario(){
        super();
        this.classificacao = 0;
        this.historico = new ArrayList<>();
    }
    
    public Proprietario(String email, String nome, String password, String morada, Date data, int classificacao, List historico){
        super(email, nome, password, morada, data);
        this.classificacao = classificacao;
        this.setHistorico(historico);
    }
    
    public Proprietario(Proprietario c){
        super(c);
        this.classificacao = c.getClassificacao();
        this.historico = c.getHistorico();
    }
        
    public int getClassificacao(){
        return this.classificacao;
    }
    
    public List<Aluguer> getHistorico(){
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }
        
    public void setClassificacao(int classificacao){
        this.classificacao = classificacao;
    }
    
    public void setHistorico(List<Aluguer> historico){
        this.historico = new ArrayList<>(historico.size());
        for(Aluguer a : historico)
            this.historico.add(a.clone());
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Proprietario c = (Proprietario) o;
        return this.getEmail().equals(c.getEmail()) 
            && this.getNome().equals(c.getNome()) 
            && this.getPassword().equals(c.getPassword()) 
            && this.getMorada().equals(c.getMorada())
            && this.getData().equals(c.getData())
            && this.classificacao == c.getClassificacao()
            && this.historico.equals(c.getHistorico());
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Proprietario\n");
        s.append(super.toString());
        s.append(" Classificacao: " + this.classificacao);
        s.append(" Historico: " + this.historico.toString());
        return s.toString();
    }
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
}
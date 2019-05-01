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
    private List<Carro> listaCarros;


    public Proprietario(){
        super();
        this.classificacao = 0;
        this.historico = new ArrayList<>();
        this.listaCarros = new ArrayList<>();
    }

    public Proprietario(String email, String nome, String password, String morada, Date data, int classificacao, List<Aluguer> historico, List<Carro> listaCarros) {
        super(email, nome, password, morada, data);
        this.classificacao = classificacao;
        this.historico = historico;
        this.listaCarros = listaCarros;
    }

    
    public Proprietario(Proprietario umProprietario){
        super(umProprietario);
        this.classificacao = umProprietario.getClassificacao();
        this.historico = umProprietario.getHistorico();
        this.listaCarros = umProprietario.getListaCarros();
    }

    public List<Carro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carro> listaCarros) {
        this.listaCarros = listaCarros;
    }

    public int getClassificacao(){
        return this.classificacao;
    }

    public void setClassificacao(int classificacao){
        this.classificacao = classificacao;
    }

    public List<Aluguer> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Aluguer> historico) {
        this.historico = historico;
    }

    /*
        public List<Aluguer> getHistorico(){
            return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
        }

        public void setHistorico(List<Aluguer> historico){
            this.historico = new ArrayList<>(historico.size());
            for(Aluguer a : historico)
                this.historico.add(a.clone());
        }
        */
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
        StringBuilder s= new StringBuilder("Proprietario");
        s.append(super.toString());
        s.append("\n Classificacao: " + this.classificacao);
        s.append("\n Historico: " + this.historico.toString());
        s.append("\n Carros: " + this.listaCarros.toString());
        return s.toString();
    }
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
}
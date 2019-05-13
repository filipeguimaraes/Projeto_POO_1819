import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Casse mais especifica que o ator que contem as variaveis para um proprietario e os metodos correspendentes
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Proprietario extends Ator {
    private List<Aluguer> historico;
    private List<Carro> listaCarros;


    public Proprietario() {
        super();
        this.historico = new ArrayList<>();
        this.listaCarros = new ArrayList<>();
    }

    public Proprietario(String email, int nib, String nome, String password, String morada, LocalDateTime data, Classificacao classificacao, List<Aluguer> historico, List<Carro> listaCarros) {
        super(email, nib, nome, password, morada, data,classificacao);
        this.historico = historico;
        this.listaCarros = listaCarros;
    }

    public Proprietario(Proprietario umProprietario) {
        super(umProprietario);
        this.historico = umProprietario.getHistorico();
        this.listaCarros = umProprietario.getListaCarros();
    }



    public List<Carro> getListaCarros() {
        return this.listaCarros.stream().map(Carro::clone).collect(Collectors.toList());
    }

    public void setListaCarros(List<Carro> listaCarros) {
        this.listaCarros = new ArrayList<>(listaCarros.size());
        for (Carro a : listaCarros)
            this.listaCarros.add(a.clone());
    }

    public List<Aluguer> getHistorico() {
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setHistorico(List<Aluguer> historico) {
        this.historico = new ArrayList<>(historico.size());
        for (Aluguer a : historico)
            this.historico.add(a.clone());
    }

    public void adicionaCarro(Carro car){
        this.listaCarros.add(car.clone());
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
            && this.historico.equals(c.getHistorico());
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Proprietario ->");
        s.append(super.toString());
        s.append("Específico{");
        s.append(", Historico: " + this.historico.toString());
        s.append(", Carros: " + this.listaCarros.toString()+'}');
        return s.toString();
    }
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
}
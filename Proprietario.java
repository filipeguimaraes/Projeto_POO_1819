import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Casse mais especifica que o ator que contem as variaveis para um proprietario e os metodos correspendentes
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Proprietario extends Ator {
    private Map<String,Carro> listaCarros;


    public Proprietario() {
        super();
        this.listaCarros = new HashMap<String,Carro>();
    }

    public Proprietario(String email, int nif, String nome, String password, String morada, LocalDateTime data, Classificacao classificacao, List<Aluguer> historico, Map<String,Carro> listaCarros) {
        super(email, nif, nome, password, morada, data,classificacao,historico);
        this.listaCarros = new HashMap<String,Carro>();
        setListaCarros(listaCarros);
    }

    public Proprietario(Proprietario umProprietario) {
        super(umProprietario);
        this.listaCarros = umProprietario.getListaCarros();
    }



    public Map<String,Carro> getListaCarros() {
        return this.listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaCarros(Map<String,Carro> listaCarros) {
        this.listaCarros = listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }


    public void adicionaCarro(Carro car){
        this.listaCarros.put(car.getMatricula(),car);
    }

    public void alteraPreco(double preco, String matricula) throws CarroException{
        if(listaCarros.containsKey(matricula)){
            this.listaCarros.get(matricula).setPreco(preco);
        } else throw new CarroException("O Carro "+matricula+" não lhe pertence ou não está registado no sistema!");
    }

    public void abasteceCarro(String tipoCombustivel, String matricula) throws CarroException{
        if (this.listaCarros.containsKey(matricula)){
            this.listaCarros.get(matricula).abasteceCarro(tipoCombustivel);
        }else throw new CarroException("O carro "+matricula+" não existe");
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Proprietario c = (Proprietario) o;
        return this.getEmail().equals(c.getEmail()) 
            && this.getNome().equals(c.getNome()) 
            && this.getPassword().equals(c.getPassword()) 
            && this.getMorada().equals(c.getMorada())
            && this.getData().equals(c.getData());
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Proprietario ->");
        s.append(super.toString());
        s.append("Específico{");
        s.append(" Carros: " + this.listaCarros.toString()+'}');
        return s.toString();
    }
    
    public Proprietario clone(){
        return new Proprietario(this);
    }

    public List<String> listaAlugueresPendentes() throws AluguerException{

        List<String> ls = this.getHistorico().stream()
                .filter(l->l.getEstado()==Aluguer.PENDENTE)
                .map(Aluguer::toString)
                .collect(Collectors.toList());

        if(ls.isEmpty()) throw new AluguerException("Não há alugueres pendentes!");
        else return ls;
    }


}
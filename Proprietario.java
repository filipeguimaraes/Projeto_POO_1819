import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Casse mais especifica que o ator que contem as variaveis para um proprietario e os metodos correspendentes
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Proprietario extends Ator {
    private Map<String, Veiculo> listaCarros;


    public Proprietario() {
        super();
        this.listaCarros = new HashMap<String, Veiculo>();
    }

    public Proprietario(String email, int nif, String nome, String password, String morada, LocalDateTime data, Classificacao classificacao, List<Aluguer> historico, Map<String, Veiculo> listaCarros) {
        super(email, nif, nome, password, morada, data,classificacao,historico);
        this.listaCarros = new HashMap<String, Veiculo>();
        setListaCarros(listaCarros);
    }

    public Proprietario(Proprietario umProprietario) {
        super(umProprietario);
        this.listaCarros = umProprietario.getListaCarros();
    }



    public Map<String, Veiculo> getListaCarros() {
        return this.listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaCarros(Map<String, Veiculo> listaCarros) {
        this.listaCarros = listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }


    public void adicionaCarro(Veiculo car){
        this.listaCarros.put(car.getMatricula(),car);
    }

    public void alteraPreco(double preco, String matricula) throws CarroException{
        if(listaCarros.containsKey(matricula)){
            this.listaCarros.get(matricula).setPreco(preco);
        } else throw new CarroException("O Veiculo "+matricula+" não lhe pertence ou não está registado no sistema!");
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

    public String showProprietario(){
        return "Nome: "+getNome()+" | NIF: "+getNif()+
                " | Data de nascimento: "+getData().toString()+
                " | Classificação: "+getClassificacao().classificacaoMedia()+
                " | Número de Alugueres: "+getHistorico().size()+
                " | Numero de carros registados: "+getListaCarros().size();

    }
}
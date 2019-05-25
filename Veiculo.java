import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe base Veículo
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public abstract class Veiculo implements Serializable {
    private String marca;
    private String matricula;
    private Proprietario proprietario;
    private int velocidade;
    private double preco;
    private Classificacao classificacao;
    private Point2D coordenada;
    private List<Aluguer> historico;

    public Veiculo(){
        this.marca= "";
        this.matricula= "00-00-00";
        this.proprietario=new Proprietario();
        this.velocidade=0;
        this.preco=0;
        this.classificacao=new Classificacao();
        this.coordenada=new Point2D.Double();
        this.historico=new ArrayList<>();
    }

    public Veiculo(String marca, String matricula, Proprietario proprietario, int velocidade, double preco,
                   Classificacao classificacao, Point2D coordenada, List<Aluguer> historico) {
        this.marca = marca;
        this.matricula = matricula;
        this.proprietario = proprietario;
        this.velocidade = velocidade;
        this.preco = preco;
        this.classificacao = classificacao;
        this.coordenada = coordenada;
        this.historico = historico;
    }

    
    public Veiculo(Veiculo umCarro){
        this.marca=umCarro.getMarca();
        this.matricula=umCarro.getMatricula();
        this.proprietario=umCarro.getProprietario();
        this.velocidade=umCarro.getVelocidade();
        this.preco=umCarro.getPreco();
        this.classificacao=umCarro.getClassificacao();
        this.coordenada=umCarro.getCoordenada();
        this.historico=umCarro.getHistorico();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Point2D getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Point2D coordenada) {
        this.coordenada = coordenada;
    }

    public abstract double getAutonomia();

    public abstract double getConsumo();

    public List<Aluguer> getHistorico() {
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setHistorico(List<Aluguer> hst){
        this.historico=new  ArrayList<>(hst.size());
        for (Aluguer l: hst)
            this.historico.add(l.clone());
    }

    /**
     * Adiciona classidicação ao veículo
     * @param classificacao Classificação atribuída
     */
    public void adicionaClassificacao(int classificacao){
        this.classificacao.adicionaClassificacao(classificacao);
    }

    /**
     * Adiciona aluguer á lista
     * @param aluguer Aluguer
     */
    public void adicionaAluguer(Aluguer aluguer){
        this.historico.add(aluguer);
    }

    /**
     * Determina a duração da viagem tendo em conta apenas o veículo
     * @param fim Ponto do fim da viagem
     * @return Tempo da viagem
     */
    public double tempoViagem(Point2D fim){
        return (this.coordenada.distance(fim)/this.velocidade);
    }

    /**
     * Metodo que calcula a percentagem da autonomia
     * Este método é abstracto na classe Veiculo e implementado em cada uma das subclasses
     */
    public abstract double getPercentagemAutonomia();

    /**
     * Metodo que retorna o numero de kilometros da autonomia
     * Este método é abstracto na classe Veiculo e implementado em cada uma das subclasses
     */
    public abstract double getKilometrosAutonomia();


    /**
     * Metodo que faz com que o carro percorra a distancia fornecida
     * Este método é abstracto na classe Veiculo e implementado em cada uma das subclasses
     */
    public abstract void percorreDistancia(double kilometros);
    //metodo que abastece o veiculo segundo a autonomia de input e retorna a nova Autonomia do Veiculo

    /**
     * Metodo que acrescenta um determinado valor de combustivel
     * Este método é abstracto na classe Veiculo e implementado em cada uma das subclasses
     */
    public abstract double abasteceCarro(double acrescenta);

    /**
     * Metodo que abastece a totaldidade do Veículo com um dado combustível
     * Este método é abstracto na classe Veiculo e implementado em cada uma das subclasses
     */
    public abstract double abasteceCarro(String tipo) throws CarroException;

    /**
     * Determina a lista de alugueres que já foram aceitados
     * @return Lista de alugueres que foram aceites
     * @throws AluguerException Caso não haja alugueres aceites
     */
    public List<Aluguer> listaAlugueresAceites() throws AluguerException{

        List<Aluguer> ls = this.getHistorico().stream()
                .filter(l->l.getEstado()==Aluguer.ACEITE)
                .map(Aluguer::clone)
                .collect(Collectors.toList());

        if(ls.isEmpty()) throw new AluguerException("Não há alugueres aceites!");
        else return ls;
    }

    /**
     * Metodo que cria string com informações relevantes de cada Veículo
     * Este método é abstracto na classe Veiculo e implementado em cada uma das subclasses
     */
    public abstract String showCarro();

    public abstract Veiculo clone();

    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Veiculo c = (Veiculo) o;
        return c.getVelocidade()==this.velocidade
                && c.getPreco()==this.preco
                && c.getClassificacao()==this.classificacao
                && c.getCoordenada().equals(this.coordenada)
                && c.getHistorico().equals(this.historico)
                && c.getProprietario().equals(this.proprietario);
    }

    public String toString(){
        StringBuilder s= new StringBuilder("Geral{");
        s.append(" Marca: " + this.marca);
        s.append(" Matrícula: " + this.matricula);
        s.append(" Velocidade: " + this.velocidade);
        s.append(", Preço: " + this.preco);
        s.append(", Classificaçao: " + this.classificacao);
        s.append(", Coordenada: " + this.coordenada);
        s.append(", Historico: " + this.historico.toString());
        s.append(", Proprietario: "+ this.proprietario.getNome()+'}');
        return s.toString();
    }

}



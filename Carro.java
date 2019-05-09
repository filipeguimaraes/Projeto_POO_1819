import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Escreva a descrição da classe Carro aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Carro{
    private String marca;
    private String matricula;
    private Proprietario proprietario;
    private int velocidade;
    private double preco;
    private int classificacao;
    private Point2D coordenada;
    private List<Aluguer> historico;

    public Carro(){
        this.marca= "";
        this.matricula= "00-00-00";
        this.proprietario=new Proprietario();
        this.velocidade=0;
        this.preco=0;
        this.classificacao=0;
        this.coordenada=new Point2D.Double();
        this.historico=new ArrayList<>();
    }

    public Carro(String marca, String matricula, Proprietario proprietario, int velocidade, double preco, int classificacao, Point2D coordenada, List<Aluguer> historico) {
        this.marca = marca;
        this.matricula = matricula;
        this.proprietario = proprietario;
        this.velocidade = velocidade;
        this.preco = preco;
        this.classificacao = classificacao;
        this.coordenada = coordenada;
        this.historico = historico;
    }

    
    public Carro(Carro umCarro){
        this.marca=umCarro.getMarca();
        this.matricula=umCarro.getMarca();
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

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public Point2D getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Point2D coordenada) {
        this.coordenada = coordenada;
    }

    public List<Aluguer> getHistorico() {
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setHistorico(List<Aluguer> hst){
        this.historico=new  ArrayList<>(hst.size());
        for (Aluguer l: hst)
            this.historico.add(l.clone());
    }

    public boolean carroValido(){
        return this.getPreco()>0 && this.getVelocidade()>0;
    }

    public Carro clone(){
       return new Carro(this);
    }

    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Carro c = (Carro) o;
        return c.getVelocidade()==this.velocidade
            && c.getPreco()==this.preco
            && c.getClassificacao()==this.classificacao
            && c.getCoordenada().equals(this.coordenada)
            && c.getHistorico().equals(this.historico)
            && c.getProprietario().equals(this.proprietario);
    }

    public String toString(){
        StringBuilder s= new StringBuilder("Geral{");
        s.append(" Velocidade: " + this.velocidade);
        s.append(", Preço: " + this.preco);
        s.append(", Classificaçao: " + this.classificacao);
        s.append(", Coordenada: " + this.coordenada);
        s.append(", Historico: " + this.historico.toString());
        s.append(", Proprietario: "+ this.proprietario.getNome()+'}');
        return s.toString();
    }

}



import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
/**
 * Escreva a descrição da classe Carro aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Carro{
    private int velocidade;
    private double preco;
    private double consumo;
    private int classificacao;
    private Point coordenada;
    private double autonomia;
    private List<Aluguer> historico;
    
    public Carro(){
        this.velocidade=0;
        this.preco=0;
        this.consumo=0;
        this.classificacao=0;
        this.coordenada=new Point();
        this.autonomia=0;
        this.historico=new ArrayList<>();
    }

    public Carro(int velocidade, double preco, double consumo, int classificacao, Point coordenada, double autonomia, List<Aluguer> historico){
        this.velocidade=velocidade;
        this.preco=preco;
        this.consumo=consumo;
        this.classificacao=classificacao;
        this.coordenada=coordenada;
        this.autonomia=autonomia;
        this.setHistorico(historico);
    }
    
    public Carro(Carro umCarro){
        this.velocidade=umCarro.getVelocidade();
        this.preco=umCarro.getPreco();
        this.consumo=umCarro.getConsumo();
        this.classificacao=umCarro.getClassificacao();
        this.coordenada=umCarro.getCoordenada();
        this.autonomia=umCarro.getAutonomia();
        this.historico=umCarro.getHistorico();
    }
        
    public int getVelocidade() {
        return this.velocidade;
    }

    public double getPreco() {
        return this.preco;
    }
    
    public double getConsumo() {
        return this.consumo;
    }

    public int getClassificacao() {
        return this.classificacao;
    }
    
    public Point getCoordenada() {
        return this.coordenada;
    }
    
    public double getAutonomia() {
        return this.autonomia;
    }
    
    public List<Aluguer> getHistorico(){
        List<Aluguer> copia= new ArrayList<>(historico.size());
        for (Aluguer l: historico){
            copia.add(l.clone());
        }
        return copia; 
    }
        
    public void setVelocidade(int v) {
        this.velocidade=v;
    }

    public void setPreco(double p) {
        this.preco=p;
    }
    
    public void setConsumo(double c) {
        this.consumo=c;
    }

    public void setClassificacao(int cl) {
        this.classificacao=cl;
    }
    
    public void setCoordenada(Point xy) {
        this.coordenada=xy;
    }
    
    public void setAutonomia(double a) {
        this.autonomia=a;
    }

    public void setHistorico(List<Aluguer> hst){
        this.historico=new  ArrayList<>(hst.size());
        for (Aluguer l: hst)
            this.historico.add(l.clone());
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
            && c.getConsumo()==this.consumo
            && c.getClassificacao()==this.classificacao
            && c.getCoordenada().equals(this.coordenada)
            && c.getAutonomia()==this.autonomia
            && c.getHistorico().equals(this.historico);
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Carro\n");
        s.append(" Velocidade: " + this.velocidade);
        s.append(" Preço: " + this.preco);
        s.append(" Consumo: " + this.consumo);
        s.append(" Coordenada: " + this.coordenada);
        s.append(" Classificaçao: " + this.classificacao);
        s.append(" Autonomia: " + this.autonomia);
        s.append(" Historico: " + this.historico.toString());
        return s.toString();
    }
    
}



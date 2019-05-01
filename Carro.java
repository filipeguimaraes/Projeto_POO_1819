import java.awt.*;
import java.awt.geom.Point2D;
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
    private int classificacao;
    private Point2D coordenada;
    private List<Aluguer> historico;
    private Proprietario p;

    public Carro(){
        this.velocidade=0;
        this.preco=0;
        this.classificacao=0;
        this.coordenada=new Point2D.Double();
        this.historico=new ArrayList<>();
        this.p=new Proprietario();
    }

    public Carro(int velocidade, double preco, int classificacao, Point2D coordenada, List<Aluguer> historico, Proprietario p){
        this.velocidade=velocidade;
        this.preco=preco;
        this.classificacao=classificacao;
        this.coordenada=coordenada;
        this.setHistorico(historico);
        this.p=p;
    }
    
    public Carro(Carro umCarro){
        this.velocidade=umCarro.getVelocidade();
        this.preco=umCarro.getPreco();;
        this.classificacao=umCarro.getClassificacao();
        this.coordenada=umCarro.getCoordenada();
        this.historico=umCarro.getHistorico();
        this.p=umCarro.getProprietario();
    }
        
    public int getVelocidade() {
        return this.velocidade;
    }

    public double getPreco() {
        return this.preco;
    }
    
    public int getClassificacao() {
        return this.classificacao;
    }
    
    public Point2D getCoordenada() {
        return this.coordenada;
    }
    
    public List<Aluguer> getHistorico(){
        List<Aluguer> copia= new ArrayList<>(historico.size());
        for (Aluguer l: historico){
            copia.add(l.clone());
        }
        return copia; 
    }
    
    public Proprietario getProprietario(){
        return this.p;
    }
        
    public void setVelocidade(int v) {
        this.velocidade=v;
    }

    public void setPreco(double p) {
        this.preco=p;
    }
    
    public void setClassificacao(int cl) {
        this.classificacao=cl;
    }
    
    public void setCoordenada(Point xy) {
        this.coordenada=xy;
    }
    
    public void setHistorico(List<Aluguer> hst){
        this.historico=new  ArrayList<>(hst.size());
        for (Aluguer l: hst)
            this.historico.add(l.clone());
    }
    
    public void setProprietario(Proprietario prop){
        this.p=prop;
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
            && c.getProprietario().equals(this.p);
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Carro\n");
        s.append(" Velocidade: " + this.velocidade);
        s.append(" Preço: " + this.preco);
        s.append(" Classificaçao: " + this.classificacao);
        s.append(" Coordenada: " + this.coordenada);
        s.append(" Historico: " + this.historico.toString());
        s.append(" Proprietario "+ this.p);
        return s.toString();
    }
    
}



import java.awt.Point;
import java.util.Date;
/**
 * Write a description of class Aluguer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Aluguer{
    private Point inicio; //carro
    private Point fim; //carro
    private Carro car;
    private Cliente cli;
    private Point i; //cliente
    private Point f; //cliente
    private Date inicial;
    private Date f;
    private int classificacao;
    private int estado;
    
    public static final int REJEITADO = 0;
    public static final int PENDENTE = 1;
    public static final int ACEITE = 2;
    
    

    
    public Aluguer(){
        this.inicio=new Point();
        this.fim=new Point();
        this.car=new Carro();
        this.estado=PENDENTE;
    }
    
    public Aluguer(Point inicio, Point fim, Carro car, Cliente cli, Point i, Point f, int classificacao, int estado){ 
        this.inicio=inicio;
        this.fim=fim;
        this.car=car;
        this.cli=cli;
        this.
        this.estado=estado;
    }
    
    public Aluguer(Aluguer umAluguer){
        this.inicio=umAluguer.getInicio();
        this.fim=umAluguer.getFim();
        this.car=umAluguer.getCar();
        this.estado=umAluguer.getEstado();
    }
    
    public Point getInicio(){
        return this.inicio;
    }
    
    public Point getFim(){
        return this.fim;
    }

    public Carro getCar(){
        return this.car;
    }
    
    public int getEstado() {
        return this.estado;
    }

    public void setInicio(Point i){
        this.inicio=i;
    }
    
    public void setFim(Point f){
        this.fim=f;
    }

    public void setCar(Carro umCarro){
        this.car=umCarro;
    }
    
    public void setEstado(int s) {
        this.estado=s;
    }

    public Aluguer clone(){
        return new Aluguer(this);
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Aluguer c = (Aluguer) o;
        return c.getInicio().equals(this.inicio) && c.getFim().equals(this.fim) && c.getCar().equals(this.car) && c.getEstado()==this.estado;
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Aluguer\n");
        s.append("Ponto inicial" + this.inicio);
        s.append("Ponto final" + this.fim);
        s.append("Carro" + this.car);
        s.append(" Estado: " + this.estado);
        return s.toString();
    }
   
}

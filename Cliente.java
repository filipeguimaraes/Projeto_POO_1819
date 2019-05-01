import java.util.Date;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * Write a description of class Cliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cliente extends Ator{
    private Point coordenada;
    private List<Aluguer> historico;
    
    public Cliente(){
        super();
        this.coordenada = new Point();
        this.historico = new ArrayList<>();
    }
    
    public Cliente(String email, String nome, String password, String morada, Date data, Point coordenada, List historico){
        super(email, nome, password, morada, data);
        this.coordenada = coordenada;
        this.setHistorico(historico);
    }
    
    public Cliente(Cliente c){
        super(c);
        this.coordenada = c.getCoordenada();
        this.historico = c.getHistorico();
    }
        
    public Point getCoordenada(){
        return this.coordenada;
    }
    
    public List<Aluguer> getHistorico(){
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }
        
    public void setCoordenada(Point coordenada){
        this.coordenada = coordenada;
    }
    
    public void setHistorico(List<Aluguer> historico){
        this.historico = new ArrayList<>(historico.size());
        for(Aluguer a : historico)
            this.historico.add(a.clone());
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Cliente c = (Cliente) o;
        return super.equals(c) 
            && this.coordenada.equals(c.getCoordenada())
            && this.historico.equals(c.getHistorico());
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Cliente");
        s.append(super.toString());
        s.append("\n Coordenada: " + this.coordenada);
        s.append("\n Historico: " + this.historico.toString());
        return s.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

}

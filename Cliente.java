import java.awt.geom.Point2D;
import java.time.LocalDateTime;
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
    private Point2D coordenada;
    
    public Cliente(){
        super();
        this.coordenada = new Point2D.Double();
    }

    public Cliente(String email, int nif, String nome, String password, String morada, LocalDateTime data, Point2D coordenada, Classificacao classificacao, List<Aluguer> historico) {
        super(email, nif, nome, password, morada, data,classificacao,historico);
        this.coordenada = coordenada;
    }

    public Cliente(Cliente c){
        super(c);
        this.coordenada = c.getCoordenada();
    }

    public Point2D getCoordenada(){
        return this.coordenada;
    }

    public void setCoordenada(Point2D coordenada){
        this.coordenada = coordenada;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Cliente c = (Cliente) o;
        return super.equals(c) 
            && this.coordenada.equals(c.getCoordenada());
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Cliente");
        s.append(super.toString());
        s.append("\n Coordenada: " + this.coordenada.toString());
        return s.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

}

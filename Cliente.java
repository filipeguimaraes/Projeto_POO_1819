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
    private List<Aluguer> historico;
    
    public Cliente(){
        super();
        this.coordenada = new Point2D.Double();
        this.historico = new ArrayList<>();
    }

    public Cliente(String email, int nib, String nome, String password, String morada, LocalDateTime data, Point2D coordenada, Classificacao classificacao, List<Aluguer> historico) {
        super(email, nib, nome, password, morada, data,classificacao);
        this.coordenada = coordenada;
        this.historico = historico;
    }

    public Cliente(Cliente c){
        super(c);
        this.coordenada = c.getCoordenada();
        this.historico = c.getHistorico();
    }

    public Point2D getCoordenada(){
        return this.coordenada;
    }

    public List<Aluguer> getHistorico(){
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setCoordenada(Point2D coordenada){
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
        s.append("\n Historico: " + this.historico.toString());
        return s.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

}

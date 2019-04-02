import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Aluguer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Aluguer{
    private Point inicio;
    private Point fim;
    private List<Carro> carros;
    
    public Aluguer(){
        this.inicio=new Point();
        this.fim=new Point();
        this.carros=new ArrayList<>();
    }
    
    public Aluguer(Point inicio, Point fim, List carros){
        this.inicio=inicio;
        this.fim=fim;
        this.carros=carros;
    }
    
    public Aluguer(Aluguer umAluguer){
        this.inicio=umAluguer.getInicio();
        this.fim=umAluguer.getFim();
        this.carros=umAluguer.getCarros();
    }
    
    public Point getInicio(){
        return this.inicio;
    }
    
    public Point getFim(){
        return this.fim;
    }

    public List<Carro> getCarros(){
        List<Carro> copia= new ArrayList<>(carros.size());
        for(Carro c: carros){
            copia.add(c.clone());
        }
        return copia;
    }

    public void setInicio(Point i){
        this.inicio=i;
    }
    
    public void setFim(Point f){
        this.fim=f;
    }

    public void setCarros(List<Carro> car){
        this.carros=new ArrayList<>(car.size());
        for(Carro c: car)
            this.carros.add(c.clone());
    }
    
    public Aluguer clone(){
        return new Aluguer(this);
    }
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Aluguer c = (Aluguer) o;
        return c.getInicio().equals(this.inicio) && c.getFim().equals(this.fim) && c.getCarros().equals(this.carros);
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Aluguer\n");
        s.append("Ponto inicial" + this.inicio);
        s.append("Ponto final" + this.fim);
        s.append("Lista de carros" + this.carros.toString());
        return s.toString();
    }
}

import java.awt.geom.Point2D;
import java.util.List;
/**
 * Write a description of class CarroGasolina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarroHibrido extends Carro{
    private double bateria;
    private double gasolina;
    private double consumoBateria;
    private double consumoGasolina;

    public CarroHibrido(){
        super();
        this.bateria=0.0;
        this.gasolina=0.0;
        this.consumoBateria=0.0;
        this.consumoGasolina=0.0;
    }
    
    public CarroHibrido(int velocidade, double preco, int classificacao, Point2D coordenada, List<Aluguer> historico, Proprietario p, double bateria, double gasolina, double consumoBateria, double consumoGasolina){
        super(velocidade,preco,classificacao,coordenada,historico,p);
        this.bateria=bateria;
        this.gasolina=gasolina;
        this.consumoBateria=consumoBateria;
        this.consumoGasolina=consumoGasolina;
    }
    
    public CarroHibrido(CarroHibrido umCarroHibrido){
        super(umCarroHibrido);
        this.bateria=umCarroHibrido.getBateria();
        this.gasolina=umCarroHibrido.getGasolina();
        this.consumoBateria=umCarroHibrido.getConsumoBateria();
        this.consumoGasolina=umCarroHibrido.getConsumoGasolina();

    }
    
        public double getBateria(){
        return this.bateria;
    }
    
    public void setBateria(double b){
        this.bateria=b;
    }
    
    public double getGasolina (){
        return this.gasolina;
    }

    public void setGasolina(double g){
        this.gasolina=g;
    }

    public double getConsumoBateria(){
        return this.consumoBateria;
    }
    
    public void setConsumoBateria(double cb){
        this.consumoBateria=cb;
    }
    
    public double getConsumoGasolina(){
        return this.consumoGasolina;
    }
    
    public void setConsumoGasolina(double cg){
        this.consumoGasolina=cg;
    }
    
    public double AutonomiaHibrido(){
        return (bateria*consumoBateria)+(gasolina*consumoGasolina);
    }

    public String toString(){
        StringBuilder s= new StringBuilder("Carro ->");
        s.append(super.toString());
        s.append("Específico{ Consumo: Bateria - " + this.consumoBateria+"Gasolina - "+this.consumoGasolina);
        s.append(", Bateria: "+ this.bateria);
        s.append(", Gasolina: "+ this.gasolina+'}');
        return s.toString();
    }
}


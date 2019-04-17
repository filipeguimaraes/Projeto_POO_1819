import java.awt.Point;
import java.util.List;
/**
 * Write a description of class CarroGasolina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarroGasolina extends Carro{
    private double gasolina;
    private double consumo;

    public CarroGasolina(){
        super();
        this.gasolina=0.0;
        this.consumo=0.0;
    }
    
    public CarroGasolina(int velocidade, double preco, int classificacao, Point coordenada, List<Aluguer> historico, Proprietario p, double consumo, double gasolina){
        super(velocidade,preco,classificacao,coordenada,historico,p);
        this.consumo=consumo;
        this.gasolina=gasolina;
    }
    public CarroGasolina(CarroGasolina umCarroGasolina){
        super(umCarroGasolina);
        this.gasolina=umCarroGasolina.getGasolina();
        this.consumo=umCarroGasolina.getConsumo();
    }
    
    public double getGasolina (){
        return this.gasolina;
    }
    
    public double getConsumo(){
        return this.consumo;
    }
    
    public void setGasolina(double g){
        this.gasolina=g;
    }
    
    public void setConsumo(double c){
        this.consumo=c;
    }
    
    public double AutonomiaGasolina(){
        return gasolina*consumo;
    }
}
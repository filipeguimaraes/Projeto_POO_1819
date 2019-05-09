import java.awt.geom.Point2D;
import java.util.List;
/**
 * Write a description of class CarroGasolina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarroGasolina extends Carro{
    private double consumo;
    private double autonomia;

    public CarroGasolina(){
        super();
        this.consumo=0.0;
        this.autonomia=0.0;
    }

    public CarroGasolina(String marca, String matricula, Proprietario proprietario, int velocidade, double preco, int classificacao, Point2D coordenada, List<Aluguer> historico, double consumo, double autonomia) {
        super(marca, matricula, proprietario, velocidade, preco, classificacao, coordenada, historico);
        this.consumo = consumo;
        this.autonomia = autonomia;
    }

    public CarroGasolina(CarroGasolina umCarroGasolina){
        super(umCarroGasolina);
        this.consumo=umCarroGasolina.getConsumo();
        this.autonomia=umCarroGasolina.getAutonomia();
    }


    public double getAutonomia() {

        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public double getConsumo(){
        return this.consumo;
    }

    public void setConsumo(double c){
        this.consumo=c;
    }
    /*
    public double AutonomiaGasolina(){
        return gasolina*consumo;
    }
*/
    public String toString(){
        StringBuilder s= new StringBuilder("Carro ->");
        s.append(super.toString());
        s.append("Específico{ Consumo: " + this.consumo);
        s.append(", Autonomia: "+ this.autonomia+'}');
        return s.toString();
    }

    public CarroGasolina clone(){
        return new CarroGasolina(this);
    }
}
import java.awt.geom.Point2D;
import java.util.List;
/**
 * Write a description of class CarroGasolina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarroHibrido extends Carro{
    private double consumo;
    private double autonomia;

    public CarroHibrido() {
        super();
        this.consumo = 0.0;
        this.autonomia = 0.0;
    }

    public CarroHibrido(String marca, String matricula, Proprietario proprietario, int velocidade, double preco, Classificacao classificacao, Point2D coordenada, List<Aluguer> historico, double consumo, double autonomia) {
        super(marca, matricula, proprietario, velocidade, preco, classificacao, coordenada, historico);
        this.consumo = consumo;
        this.autonomia = autonomia;
    }

    public CarroHibrido(CarroHibrido umCarro){
        super(umCarro);
        this.consumo=umCarro.getConsumo();
        this.autonomia=umCarro.getAutonomia();
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }


    public String toString(){
        StringBuilder s= new StringBuilder("Carro ->");
        s.append(super.toString());
        s.append("Específico{ ");
        s.append(" Tipo: Hibrido, Consumo: "+ this.consumo);
        s.append(", Autonomia: "+ this.autonomia+" }");
        return s.toString();
    }

    public CarroHibrido clone(){
        return new CarroHibrido(this);
    }
}


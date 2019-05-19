import java.awt.geom.Point2D;
import java.util.List;

/**
 * Escreva a descrição da classe CarroEletrico aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CarroEletrico extends Carro{
    private double consumo;
    private double autonomia;

    private final static int autonomiaTotal=1000;

    public CarroEletrico(){
        super();
        this.consumo=0.0;
        this.autonomia=0.0;
    }

    public CarroEletrico(String marca, String matricula, Proprietario proprietario, int velocidade, double preco, Classificacao classificacao, Point2D coordenada, List<Aluguer> historico, double consumo, double autonomia) {
        super(marca, matricula, proprietario, velocidade, preco, classificacao, coordenada, historico);
        this.consumo = consumo;
        this.autonomia = autonomia;
    }

    public CarroEletrico(CarroEletrico umCarroEletrico){
        super(umCarroEletrico);
        this.consumo=umCarroEletrico.getConsumo();
        this.autonomia=umCarroEletrico.getAutonomia();

    }
    
    public double getConsumo(){
        return this.consumo;
    }

    public void setConsumo(double c){
        this.consumo=c;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    /*
    public double AutonomiaEletrico(){
        return consumo*bateria;
    }
    */

    public String toString(){
        StringBuilder s= new StringBuilder("Carro ->");
        s.append(super.toString());
        s.append("Específico{ Tipo: Eletrico, Consumo: " + this.consumo);
        s.append(", Autonomia: "+ this.autonomia+'}');
        return s.toString();
    }

    public CarroEletrico clone(){
        return new CarroEletrico(this);
    }
}




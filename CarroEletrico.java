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
    private double bateria;

    public CarroEletrico(){
        super();
        this.consumo=0.0;
        this.bateria=0.0;
    }
    
    public CarroEletrico(int velocidade, double preco, int classificacao, Point2D coordenada, List<Aluguer> historico, Proprietario p, double consumo, double bateria){
        super(velocidade,preco,classificacao,coordenada,historico,p);
        this.consumo=consumo;
        this.bateria=bateria;
    }
    
    public CarroEletrico(CarroEletrico umCarroEletrico){
        super(umCarroEletrico);
        this.consumo=umCarroEletrico.getConsumo();
        this.bateria=umCarroEletrico.getBateria();

    }
    
    public double getConsumo(){
        return this.consumo;
    }

    public void setConsumo(double c){
        this.consumo=c;
    }

    public double getBateria(){
        return this.bateria;
    }

    public void setBateria(double b){
        this.bateria=b;
    }

    
    public double AutonomiaEletrico(){
        return consumo*bateria;
    }
}




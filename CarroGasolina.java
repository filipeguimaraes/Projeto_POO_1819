import java.awt.Point;
import java.util.List;
/**
 * Write a description of class CarroGasolina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarroGasolina extends Carro{

    public CarroGasolina(){
        super();
    }
    
    public CarroGasolina(int velocidade, double preco, double consumo, int classificacao, Point coordenada, double autonomia, List<Aluguer> historico){
        super(velocidade,preco,consumo,classificacao,coordenada,autonomia,historico);
    }
    
    public CarroGasolina(CarroGasolina umCarroGasolina){
        super(umCarroGasolina);
    }
}
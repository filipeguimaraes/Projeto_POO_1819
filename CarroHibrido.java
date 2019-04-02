import java.awt.Point;
import java.util.List;
/**
 * Write a description of class CarroGasolina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarroHibrido extends Carro{

    public CarroHibrido(){
        super();
    }
    
    public CarroHibrido(int velocidade, double preco, double consumo, int classificacao, Point coordenada, double autonomia, List<Aluguer> historico){
        super(velocidade,preco,consumo,classificacao,coordenada,autonomia,historico);
    }
    
    public CarroHibrido(CarroHibrido umCarroHibrido){
        super(umCarroHibrido);
    }
}


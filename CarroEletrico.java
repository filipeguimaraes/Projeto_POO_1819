import java.awt.Point;
import java.util.List;

/**
 * Escreva a descrição da classe CarroEletrico aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CarroEletrico extends Carro{

    public CarroEletrico(){
        super();
    }
    
    public CarroEletrico(int velocidade, double preco, double consumo, int classificacao, Point coordenada, double autonomia, List<Aluguer> historico){
        super(velocidade,preco,consumo,classificacao,coordenada,autonomia,historico);
    }
    
    public CarroEletrico(CarroEletrico umCarroEletrico){
        super(umCarroEletrico);
    }
}




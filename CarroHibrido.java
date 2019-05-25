import java.awt.geom.Point2D;
import java.util.List;
import java.util.Objects;

/**
 * Carro Híbrido, com o seu consumo e autonomia
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class CarroHibrido extends Veiculo {
    private double consumo;
    private double autonomia;

    private final static int autonomiaTotal=1500;

    public CarroHibrido() {
        super();
        this.consumo = 0.0;
        this.autonomia = 0.0;
    }

    public CarroHibrido(String marca, String matricula, Proprietario proprietario, int velocidade,
                        double preco, Classificacao classificacao, Point2D coordenada,
                        List<Aluguer> historico, double consumo, double autonomia) {
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

    public double getPercentagemAutonomia(){
        return autonomia/(double)autonomiaTotal;
    }

    public double getKilometrosAutonomia(){
        return autonomia/consumo;
    }

    /**
     * Faz o carro percorrer a distância fornecida
     * @param kilometros Distância
     */
    public void percorreDistancia(double kilometros){
        this.autonomia -= kilometros*this.consumo;
    }

    /**
     * Abastece a quantidade fornecida
     * @param acrescenta Quantidade a acrescentar
     * @return Autonomia resultante
     */
    public double abasteceCarro(double acrescenta){
        this.autonomia += acrescenta;
        return this.getAutonomia();
    }

    /**
     * Abastece na totalidade o carro com o combustivel fornecido
     * @param tipo Tipo de combustivel
     * @return Autonomia resultante
     * @throws CarroException Caso o carro não possa ser abastecido com esse tipo de combustivel
     */
    public double abasteceCarro(String tipo) throws CarroException{
        if(tipo.contains("Eletricidade")){
            this.autonomia =autonomiaTotal/3;
        }else if(tipo.contains("Gasolina")){
            this.autonomia =(2*autonomiaTotal)/3;
        }else throw new CarroException("O carro não pode ser abastecido com "+tipo);
        return this.getAutonomia();
    }

    /**
     * Cria string com informações relevantes do carro hibrido
     * @return String com informações do carro hibrido
     */
    public String showCarro(){
        return "Hibrido | "+getMatricula()+" | "+getMarca()+" | Velocidade media: "+getVelocidade()+"km | Preço: "
                +getPreco()+"€ | Localização: ("+getCoordenada().getX()+","+getCoordenada().getY()+") | Consumo: "+
                getConsumo()+" | Autonomia: "+getAutonomia();
    }

    public String toString(){
        StringBuilder s= new StringBuilder("CarroHibrido ->");
        s.append(super.toString());
        s.append("Específico{ ");
        s.append(" Tipo: Hibrido, Consumo: "+ this.consumo);
        s.append(", Autonomia: "+ this.autonomia+" }");
        return s.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarroHibrido that = (CarroHibrido) o;
        return that.consumo==consumo &&
                that.autonomia==autonomia;
    }


    public CarroHibrido clone(){
        return new CarroHibrido(this);
    }
}


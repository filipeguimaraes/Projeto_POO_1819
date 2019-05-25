import java.awt.geom.Point2D;
import java.util.List;

/**
 * Carro Gasolina, com o seu consumo e autonomia
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class CarroGasolina extends Veiculo {
    private double consumo;
    private double autonomia;

    private final static int autonomiaTotal=1000; //km

    public CarroGasolina(){
        super();
        this.consumo=0.0;
        this.autonomia=0.0;
    }

    public CarroGasolina(String marca, String matricula, Proprietario proprietario, int velocidade,
                         double preco, Classificacao classificacao, Point2D coordenada,
                         List<Aluguer> historico, double consumo, double autonomia) {
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
        if(tipo.contains("Gasolina")){
            this.autonomia =autonomiaTotal;
        }else throw new CarroException("O carro não pode ser abastecido com "+tipo);
        return this.getAutonomia();
    }

    /**
     * Cria string com informações relevantes do carro a gasolina
     * @return String com informações do carro a gasolina
     */
    public String showCarro(){
        return "Gasolina | "+getMatricula()+" | "+getMarca()+" | Velocidade media: "+getVelocidade()+
                "km | Preço: "+getPreco()+ "€ | Localização: ("+getCoordenada().getX()+","+getCoordenada().getY()+
                ") | Consumo: "+ getConsumo()+" | Autonomia: "+getAutonomia();
    }

    public CarroGasolina clone(){
        return new CarroGasolina(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarroGasolina that = (CarroGasolina) o;
        return that.consumo==consumo &&
                that.autonomia==autonomia;
    }

    public String toString(){
        StringBuilder s= new StringBuilder("CarroGasolina ->");
        s.append(super.toString());
        s.append("Específico{ Tipo: Gasolina, Consumo: " + this.consumo);
        s.append(", Autonomia: "+ this.autonomia+'}');
        return s.toString();
    }
}
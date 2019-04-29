import java.awt.Point;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Write a description of class Servico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Servico{
    private List<CarroEletrico> carroseletricos;
    private List<CarroHibrido> carroshibridos;
    private List<CarroGasolina> carrosgasolina;
        
    public Servico(){
        this.carroseletricos= new ArrayList<>();
        this.carroshibridos= new ArrayList<>();
        this.carrosgasolina= new ArrayList<>();
    }

    public Servico(List<CarroEletrico> carroseletricos,List<CarroHibrido> carroshibridos, List<CarroGasolina> carrosgasolina){
        this.setCarroseletricos(carroseletricos);
        this.setCarroshibridos(carroshibridos);
        this.setCarrosgasolina(carrosgasolina);
    }

    public Servico(Servico umServico){
        this.carroseletricos=umServico.getCarroseletricos();
        this.carroshibridos=umServico.getCarroshibridos();
        this.carrosgasolina=umServico.getCarrosgasolina();
    }

    public List<CarroEletrico> getCarroseletricos() {
        return carroseletricos;
    }

    public void setCarroseletricos(List<CarroEletrico> carroseletricos) {
        this.carroseletricos = carroseletricos;
    }

    public List<CarroHibrido> getCarroshibridos() {
        return carroshibridos;
    }

    public void setCarroshibridos(List<CarroHibrido> carroshibridos) {
        this.carroshibridos = carroshibridos;
    }

    public List<CarroGasolina> getCarrosgasolina() {
        return carrosgasolina;
    }

    public void setCarrosgasolina(List<CarroGasolina> carrosgasolina) {
        this.carrosgasolina = carrosgasolina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(carroseletricos, servico.carroseletricos) &&
                Objects.equals(carroshibridos, servico.carroshibridos) &&
                Objects.equals(carrosgasolina, servico.carrosgasolina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carroseletricos, carroshibridos, carrosgasolina);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "carroseletricos=" + carroseletricos +
                ", carroshibridos=" + carroshibridos +
                ", carrosgasolina=" + carrosgasolina +
                '}';
    }

    public Servico clone(){
        return new Servico(this);
    }
    

    public void desloca(Aluguer a,Point fim){
        a.setFimCarro(fim);
    }
    
    
/*    public void arrenda(Carro car,Point inicioCarro, Point fimCarro,Cliente cli,Point i, Point f, Date dataInicio, Date dataFim){
        Aluguer novoAluguer = new Aluguer(inicio, fim, car, cli, inicial, f, classificacao,PENDENTE);
        car.getHistorico().add(novoAluguer);
        c.getHistorico().add(novoAluguer);
        p.getHistorico().add(novoAluguer);
    }
*/

    public boolean temAutonomia(Carro c, Point i,Point f){
        double autonomia;
        double distancia;
        double autonomiaFinal;
        if(c.getClass().equals(CarroEletrico.class)){
            autonomia=((CarroEletrico) c).AutonomiaEletrico();
            distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
            autonomiaFinal=distancia*((CarroEletrico) c).getConsumo();
            return(autonomiaFinal<10 && autonomiaFinal<=autonomia);
        }
        if(c.getClass().equals(CarroHibrido.class)){
            autonomia=((CarroHibrido) c).AutonomiaHibrido();
            distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
            autonomiaFinal=distancia*(((CarroHibrido) c).getConsumoGasolina()*((CarroHibrido) c).getConsumoBateria());
            return(autonomiaFinal<10 && autonomiaFinal<=autonomia);
        }
        if(c.getClass().equals(CarroGasolina.class)){
            autonomia=((CarroGasolina) c).AutonomiaGasolina();
            distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
            autonomiaFinal=distancia*((CarroGasolina) c).getConsumo();
            return(autonomiaFinal<10 && autonomiaFinal<=autonomia);
        }
        return false;
    }

    public double custo(Carro c, Point i, Point f){
        double distancia;
        distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
        return c.getPreco()*distancia;
    }

    public double tempo(Cliente cli, Carro car){
        double distancia= distancia=Math.sqrt(Math.pow(cli.getCoordenada().getX()-car.getCoordenada().getX(), 2) +Math.pow(cli.getCoordenada().getY()-car.getCoordenada().getY(), 2));
        return (distancia/4);
    }
        
    public void aceitaEstado(Aluguer a){
        a.setEstado(Aluguer.ACEITE);
    }
    
    public void rejeitaEstado(Aluguer a){
        a.setEstado(Aluguer.REJEITADO);
    }

    public void fimInicio(Aluguer a){
        a.setInicioCarro(a.getFimCarro());
    }
}

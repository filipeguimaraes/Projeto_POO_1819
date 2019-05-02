import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.Comparator;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(carroseletricos, servico.carroseletricos) &&
                Objects.equals(carroshibridos, servico.carroshibridos) &&
                Objects.equals(carrosgasolina, servico.carrosgasolina);
    }

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
    

    public void desloca(Aluguer a,Point2D fim){
        a.setFimCarro(fim);
    }

    public void arrenda(Point2D inicioCarro, Point2D fimCarro, Carro car, Cliente cli, Proprietario p, Point2D inicioCliente, Point2D fimCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int classificacao, Meteorologia meteorologia, int estado){
        Aluguer novoAluguer = new Aluguer(inicioCarro, fimCarro, car,cli, p, inicioCliente, fimCliente,dataInicio,dataFim,classificacao, meteorologia, estado);
        car.getHistorico().add(novoAluguer);
        cli.getHistorico().add(novoAluguer);
        p.getHistorico().add(novoAluguer);
    }

    public boolean temAutonomia(Carro c, Point2D i,Point2D f){
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

    public double  distanciaAoCarro(Cliente cli,Carro car){
        return Math.sqrt(Math.pow(cli.getCoordenada().getX()-car.getCoordenada().getX(), 2) +Math.pow(cli.getCoordenada().getY()-car.getCoordenada().getY(), 2));
    }

    public double custo(Carro c, Point2D i, Point2D f){
        double distancia;
        distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
        return c.getPreco()*distancia;
    }
    /*Tempo em horas*/
    public double tempoCliente(Cliente cli, Carro car){
        return (distanciaAoCarro(cli,car)/4);
    }

    public double  distanciaAoPonto(Cliente cli,Point2D ponto){
        return Math.sqrt(Math.pow(cli.getCoordenada().getX()-ponto.getX(), 2) +Math.pow(cli.getCoordenada().getY()-ponto.getY(), 2));
    }

    public Carro carroMaisProximo(Cliente cli){
        Point2D localizacaoCliente = cli.getCoordenada();
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(distanciaAoPonto(cli,c1.getCoordenada())<distanciaAoPonto(cli,c2.getCoordenada())) return -1;
            if(distanciaAoPonto(cli,c1.getCoordenada())>distanciaAoPonto(cli,c2.getCoordenada())) return 1;
            else return 0;
        };
        Carro cG = this.carrosgasolina.stream()
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();
        Carro cE = this.carroseletricos.stream()
                                       .sorted(c)
                                       .findFirst()
                                       .get()
                                       .clone();

        Carro cH = this.carroshibridos.stream()
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();
        if(distanciaAoCarro(cli,cG)<distanciaAoCarro(cli,cE) && distanciaAoCarro(cli,cG)<distanciaAoCarro(cli,cH)){
            return cG;
        }else if(distanciaAoCarro(cli,cE)<distanciaAoCarro(cli,cG) && distanciaAoCarro(cli,cE)<distanciaAoCarro(cli,cH)){
            return cE;
        }else return cH;
    }



    public Carro carroMaisBarato(){
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };

        Carro cG = null;
        Carro cE = null;
        Carro cH = null;

        if (!this.carrosgasolina.isEmpty()) {
            cG = this.carrosgasolina.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
        }

        if (!this.carroseletricos.isEmpty()) {
            cE = this.carroseletricos.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
        }

        if (!this.carroshibridos.isEmpty()) {
            cH = this.carroshibridos.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
        }

        if(cE==null || cG==null || cH==null){
            return null;
        }else if (cG.carroValido() && cE.carroValido() && cH.carroValido() && cG.getPreco() < cE.getPreco() && cG.getPreco() < cH.getPreco()) {
                return cG;
            } else if (cG.carroValido() && cE.carroValido() && cH.carroValido() && cE.getPreco() < cG.getPreco() && cE.getPreco() < cH.getPreco()) {
                return cE;
            } else if (cH.carroValido()){
            return cH;
        }else return null;

    }

    public Carro carroProximoMaisBarato(Cliente cli, Double distancia){
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };

        Carro cG = this.carrosgasolina.stream()
                                      .filter(car-> distanciaAoCarro(cli,car)<=distancia)
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();

        Carro cE = this.carroseletricos.stream()
                                       .filter(car-> distanciaAoCarro(cli,car)<=distancia)
                                       .sorted(c)
                                       .findFirst()
                                       .get()
                                       .clone();

        Carro cH = this.carroshibridos.stream()
                                      .filter(car-> distanciaAoCarro(cli,car)<=distancia)
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();
        if(cG.getPreco()<cE.getPreco() && cG.getPreco()<cH.getPreco()){
            return cG;
        } else if(cE.getPreco()<cG.getPreco() && cE.getPreco()<cH.getPreco()){
            return cE;
        } else return cH;
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

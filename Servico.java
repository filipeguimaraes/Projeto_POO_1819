import javax.swing.*;
import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private Map<Integer,Proprietario> listaProprietarios;
    private Map<Integer,Cliente> listaClientes;
        
    public Servico(){
        this.carroseletricos= new ArrayList<>();
        this.carroshibridos= new ArrayList<>();
        this.carrosgasolina= new ArrayList<>();
        this.listaProprietarios= new HashMap<>();
        this.listaClientes= new HashMap<>();
    }


    public Servico(List<CarroEletrico> carroseletricos,List<CarroHibrido> carroshibridos, List<CarroGasolina> carrosgasolina){
        this.setCarroseletricos(carroseletricos);
        this.setCarroshibridos(carroshibridos);
        this.setCarrosgasolina(carrosgasolina);
        this.setListaProprietarios(listaProprietarios);
        this.setListaClientes(listaClientes);
    }

    public Servico(Servico umServico){
        this.carroseletricos=umServico.getCarroseletricos();
        this.carroshibridos=umServico.getCarroshibridos();
        this.carrosgasolina=umServico.getCarrosgasolina();
        this.listaProprietarios=umServico.getListaProprietarios();
        this.listaClientes=umServico.getListaClientes();
    }

    public List<CarroEletrico> getCarroseletricos() {
        return this.carroseletricos.stream().map(CarroEletrico::clone).collect(Collectors.toList());
    }

    public void setCarroseletricos(List<CarroEletrico> carroseletricos) {
        this.carroseletricos = carroseletricos;
    }

    public List<CarroHibrido> getCarroshibridos() {
        return this.carroshibridos.stream().map(CarroHibrido::clone).collect(Collectors.toList());
    }

    public void setCarroshibridos(List<CarroHibrido> carroshibridos) {
        this.carroshibridos = carroshibridos;
    }

    public List<CarroGasolina> getCarrosgasolina() {
        return this.carrosgasolina.stream().map(CarroGasolina::clone).collect(Collectors.toList());
    }

    public void setCarrosgasolina(List<CarroGasolina> carrosgasolina) {
        this.carrosgasolina = carrosgasolina;
    }

    public Map<Integer, Proprietario> getListaProprietarios() {
        return listaProprietarios;
    }

    public void setListaProprietarios(Map<Integer, Proprietario> listaProprietarios) {
        this.listaProprietarios = listaProprietarios;
    }

    public Map<Integer, Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Map<Integer, Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }


    public void adicionaCarroEletrico(CarroEletrico e){
        this.carroseletricos.add(e);
    }

    public void adicionaCarroGasolina(CarroGasolina g){
        this.carrosgasolina.add(g);
    }

    public void adicionaCarroHibrido(CarroHibrido h){
        this.carroshibridos.add(h);
    }

    public void adicionaCliente(Cliente c){
        this.listaClientes.put(c.getNib(),c);
    }

    public void adicionaProprietario(Proprietario p){
        this.listaProprietarios.put(p.getNib(),p);
    }

    public Proprietario procuraProprietario(int nif){
        return this.listaProprietarios.get(nif);
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
                "\ncarroseletricos=" + carroseletricos +
                ",\ncarroshibridos=" + carroshibridos +
                ",\ncarrosgasolina=" + carrosgasolina +
                ",\nLista Proprietarios=" + listaProprietarios +
                ",\nLista Clientes=" + listaClientes +
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
/*
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
*/
    public double  distanciaAoCarro(Cliente cli,Carro car){
        return Math.sqrt(Math.pow(cli.getCoordenada().getX()-car.getCoordenada().getX(), 2) +Math.pow(cli.getCoordenada().getY()-car.getCoordenada().getY(), 2));
    }

    public double custo(Carro c, Point2D i, Point2D f){
        double distancia;
        distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
        return c.getPreco()*distancia;
    }


    /**
    *
    *Tempo em horas
    * */
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



    public Carro carroMaisBarato(String tipo){
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };


        if (tipo.contains("Gasolina") && !carrosgasolina.isEmpty()) {
            CarroGasolina cG = this.carrosgasolina.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cG;
        }

        if (tipo.contains("Electrico") && !carroseletricos.isEmpty()) {
            CarroEletrico cE = this.carroseletricos.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cE;
        }

        if (tipo.contains("Hibrido") && !carroshibridos.isEmpty()) {
            CarroHibrido cH = this.carroshibridos.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
        return cH;
        }
        //erro caso a lista esteja vazia
        return null;

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

import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Write a description of class Servico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Servico{
    private Map<String,CarroEletrico> carroseletricos;
    private Map<String,CarroHibrido> carroshibridos;
    private Map<String,CarroGasolina> carrosgasolina;
    private Map<Integer,Proprietario> listaProprietarios;
    private Map<Integer,Cliente> listaClientes;
    private Meteorologia meteorologia;
        
    public Servico(){
        this.carroseletricos= new HashMap<>();
        this.carroshibridos= new HashMap<>();
        this.carrosgasolina= new HashMap<>();
        this.listaProprietarios= new HashMap<>();
        this.listaClientes= new HashMap<>();
        this.meteorologia=new Meteorologia();
    }


    public Servico(Map<String, CarroEletrico> carroseletricos, Map<String, CarroHibrido> carroshibridos, Map<String, CarroGasolina> carrosgasolina, Map<Integer, Proprietario> listaProprietarios, Map<Integer, Cliente> listaClientes) {
        this.carroseletricos = carroseletricos;
        this.carroshibridos = carroshibridos;
        this.carrosgasolina = carrosgasolina;
        this.listaProprietarios = listaProprietarios;
        this.listaClientes = listaClientes;
        this.meteorologia = new Meteorologia();
    }

    public Servico(Servico umServico){
        this.carroseletricos=umServico.getCarroseletricos();
        this.carroshibridos=umServico.getCarroshibridos();
        this.carrosgasolina=umServico.getCarrosgasolina();
        this.listaProprietarios=umServico.getListaProprietarios();
        this.listaClientes=umServico.getListaClientes();
        this.meteorologia=umServico.getMeteorologia();
    }

    public Map<String, CarroEletrico> getCarroseletricos() {
        return carroseletricos;
    }

    public void setCarroseletricos(Map<String, CarroEletrico> carroseletricos) {
        this.carroseletricos = carroseletricos;
    }

    public Map<String, CarroHibrido> getCarroshibridos() {
        return carroshibridos;
    }

    public void setCarroshibridos(Map<String, CarroHibrido> carroshibridos) {
        this.carroshibridos = carroshibridos;
    }

    public Map<String, CarroGasolina> getCarrosgasolina() {
        return carrosgasolina;
    }

    public void setCarrosgasolina(Map<String, CarroGasolina> carrosgasolina) {
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

    public Meteorologia getMeteorologia() {
        return meteorologia;
    }

    public void setMeteorologia(Meteorologia meteorologia) {
        this.meteorologia = meteorologia;
    }

    public void adicionaCarroEletrico(CarroEletrico e){
        this.carroseletricos.put(e.getMatricula(),e);
    }

    public void adicionaCarroGasolina(CarroGasolina g){
        this.carrosgasolina.put(g.getMatricula(),g);
    }

    public void adicionaCarroHibrido(CarroHibrido h){
        this.carroshibridos.put(h.getMatricula(),h);
    }

    public void adicionaCliente(Cliente c){
        this.listaClientes.put(c.getNif(),c);
    }

    public void adicionaProprietario(Proprietario p){
        this.listaProprietarios.put(p.getNif(),p);
    }

    public Proprietario procuraProprietario(int nif){
        return this.listaProprietarios.get(nif);
    }

    public void atualizaMetereologia(int velocidadeVento,int temperatura,int precepitacao){
        this.meteorologia = new Meteorologia(velocidadeVento,temperatura,precepitacao);
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

/*
    public void desloca(Aluguer a,Point2D fim) {
        a.setFimCarro(fim);
    }

    public void arrenda(Point2D inicioCarro, Point2D fimCarro, Carro car, Cliente cli, Proprietario p, Point2D inicioCliente, Point2D fimCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int classificacao, Meteorologia meteorologia, int estado){
            Aluguer novoAluguer = new Aluguer(inicioCarro, fimCarro, car,cli, p, inicioCliente, fimCliente,dataInicio,dataFim,classificacao, meteorologia, estado);
            car.getHistorico().add(novoAluguer);
            cli.getHistorico().add(novoAluguer);
            p.getHistorico().add(novoAluguer);
    }

 */


    public boolean temAutonomia(Carro c, Point2D i,Point2D f){
        double autonomia;
        double distancia;
        double autonomiaFinal;
        if(c.getClass().equals(CarroEletrico.class)){
            autonomia=((CarroEletrico) c).getAutonomia();
            distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
            autonomiaFinal=distancia*((CarroEletrico) c).getConsumo();
            return(autonomiaFinal<10 && autonomiaFinal<=autonomia);
        }
        if(c.getClass().equals(CarroHibrido.class)){
            autonomia=((CarroHibrido) c).getAutonomia();
            distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
            autonomiaFinal=distancia*(((CarroHibrido) c).getConsumo()*((CarroHibrido) c).getConsumo());
            return(autonomiaFinal<10 && autonomiaFinal<=autonomia);
        }
        if(c.getClass().equals(CarroGasolina.class)){
            autonomia=((CarroGasolina) c).getAutonomia();
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
        Carro cG = this.carrosgasolina.values().stream()
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();
        Carro cE = this.carroseletricos.values().stream()
                                       .sorted(c)
                                       .findFirst()
                                       .get()
                                       .clone();

        Carro cH = this.carroshibridos.values().stream()
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
            CarroGasolina cG = this.carrosgasolina.values().stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cG;
        }

        if (tipo.contains("Electrico") && !carroseletricos.isEmpty()) {
            CarroEletrico cE = this.carroseletricos.values().stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cE;
        }

        if (tipo.contains("Hibrido") && !carroshibridos.isEmpty()) {
            CarroHibrido cH = this.carroshibridos.values().stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
        return cH;
        }
        //erro caso a lista esteja vazia
        return null;

    }

    //distancia maxima
    public Carro carroProximoMaisBarato(Cliente cli, Double distancia){
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };

        Carro cG = this.carrosgasolina.values().stream()
                                      .filter(car-> distanciaAoCarro(cli,car)<=distancia)
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();

        Carro cE = this.carroseletricos.values().stream()
                                       .filter(car-> distanciaAoCarro(cli,car)<=distancia)
                                       .sorted(c)
                                       .findFirst()
                                       .get()
                                       .clone();

        Carro cH = this.carroshibridos.values().stream()
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

    public void classificarCarro(String matricula, int classificação){
        CarroEletrico cE = this.carroseletricos.values().stream()
                    .filter(x->matricula.equals(x.getMatricula()))
                    .findAny()
                    .orElse(null);

        CarroGasolina cG = this.carrosgasolina.values().stream()
                .filter(x->matricula.equals(x.getMatricula()))
                .findAny()
                .orElse(null);

        CarroHibrido cH = this.carroshibridos.values().stream()
                .filter(x->matricula.equals(x.getMatricula()))
                .findAny()
                .orElse(null);

        if(cE!=null && cG==null && cH==null){
            cE.adicionaClassificacao(classificação);
        }else if(cE==null && cG!=null && cH==null){
            cG.adicionaClassificacao(classificação);
        } else if(cE==null && cG==null && cH!=null){
            cH.adicionaClassificacao(classificação);
        }
        //adicionar erro
    }

    public void classificarAtores(int nif,int classificacao){
        if(!listaProprietarios.isEmpty() && listaProprietarios.containsKey(nif)){
            listaProprietarios.get(nif).adicionaClassificacao(classificacao);
        }else if(!listaClientes.isEmpty() && listaClientes.containsKey(nif)){
            listaClientes.get(nif).adicionaClassificacao(classificacao);
        }else System.out.println("error");
         //exception
    }


    /**
     *
     *Tempo em minutos
     * */
    public double tempoCliente(Cliente cli, Carro car){
        return (distanciaAoCarro(cli,car)/4)*60;
    }

/**
 * Tempo do cliente chegar ao carro+ atraso devido á metereologia+tempo da viagem
 **/
    public long duracaoAluguer(Cliente cli,Carro car,Point2D destino){
        return Math.round(this.meteorologia.medicaoMinutos()+tempoCliente(cli,car)+car.tempoViagem(destino));
    }

    public void pedidoAluguer(int nifCliente,Point2D destino, Carro car){
        //adicionar try
        Cliente c = this.listaClientes.get(nifCliente);
        Proprietario p = listaProprietarios.get(car.getProprietario().getNif());
        Point2D localizacaoCarro = car.getCoordenada();
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFimPrevista = dataInicio.plusMinutes(duracaoAluguer(c,car,destino));
        int estado=Aluguer.PENDENTE;


    }



    public void aceitaEstado(Aluguer a){
        a.setEstado(Aluguer.ACEITE);
    }
    
    public void rejeitaEstado(Aluguer a){
        a.setEstado(Aluguer.REJEITADO);
    }

}

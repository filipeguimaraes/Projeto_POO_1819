import java.awt.geom.Point2D;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a description of class Servico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Servico implements Serializable {
    private Map<String,Carro> listaCarros;
    private Map<Integer,Ator> listaAtores;
    private Map<LocalDateTime,Aluguer> listaAlugueres;
    private Meteorologia meteorologia;
        
    public Servico(){
        this.listaCarros = new HashMap<>();
        this.listaAtores = new HashMap<>();
        this.listaAlugueres = new HashMap<>();
        this.meteorologia=new Meteorologia();
    }


    public Servico(Map<String, Carro> listaCarros, Map<Integer, Ator> listaAtores, Map<LocalDateTime, Aluguer> listaAlugueres, Meteorologia meteorologia) {
        this.listaCarros = listaCarros;
        this.listaAtores = listaAtores;
        this.listaAlugueres = listaAlugueres;
        this.meteorologia = meteorologia;
    }

    public Servico(Servico umServico){
        this.listaCarros = umServico.getListaCarros();
        this.listaAtores = umServico.getListaAtores();
        this.listaAlugueres = umServico.getListaAlugueres();
        this.meteorologia = umServico.getMeteorologia();
    }

    public Map<String, Carro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(Map<String, Carro> listaCarros) {
        this.listaCarros = listaCarros;
    }

    public Map<Integer, Ator> getListaAtores() {
        return listaAtores;
    }

    public void setListaAtores(Map<Integer, Ator> listaAtores) {
        this.listaAtores = listaAtores;
    }

    public Map<LocalDateTime, Aluguer> getListaAlugueres() {
        return listaAlugueres;
    }

    public void setListaAlugueres(Map<LocalDateTime, Aluguer> listaAlugueres) {
        this.listaAlugueres = listaAlugueres;
    }

    public Meteorologia getMeteorologia() {
        return meteorologia;
    }

    public void setMeteorologia(Meteorologia meteorologia) {
        this.meteorologia = meteorologia;
    }

    public void adicionaCarroEletrico(CarroEletrico e){
        this.listaCarros.put(e.getMatricula(),e);
    }

    public void adicionaCarroGasolina(CarroGasolina g){
        this.listaCarros.put(g.getMatricula(),g);
    }

    public void adicionaCarroHibrido(CarroHibrido h){
        this.listaCarros.put(h.getMatricula(),h);
    }

    public void adicionaCliente(Cliente c){
        this.listaAtores.put(c.getNif(),c);
    }

    public void adicionaProprietario(Proprietario p){
        this.listaAtores.put(p.getNif(),p);
    }

    public Proprietario procuraProprietario(int nif){
        return (Proprietario)this.listaAtores.get(nif);
    }

    public Cliente procuraCliente(int nif){
        return (Cliente)this.listaAtores.get(nif);
    }

    public void adicionaAluguer(Aluguer a){
        this.listaAlugueres.put(a.getDataInicio(),a);
    }

    public void atualizaMetereologia(int velocidadeVento,int temperatura,int precepitacao){
        this.meteorologia = new Meteorologia(velocidadeVento,temperatura,precepitacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(listaCarros, servico.listaCarros) &&
                Objects.equals(listaAtores, servico.listaAtores) &&
                Objects.equals(listaAlugueres, servico.listaAlugueres) &&
                Objects.equals(meteorologia, servico.meteorologia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaCarros, listaAtores, listaAlugueres, meteorologia);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "listaCarros=" + listaCarros +
                ", listaAtores=" + listaAtores +
                ", listaAlugueres=" + listaAlugueres +
                ", meteorologia=" + meteorologia +
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
        Carro car = this.listaCarros.values().stream()
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();
        return car;
    }

    /**
     *
     * Classe que da lista de carros encontra o mais barato de um certo tipo
     *
     * @param tipo do carro
     * @return carro mais barato
     */
    public Carro carroMaisBarato(String tipo){
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };

        List<CarroEletrico> eletricos = this.listaCarros.values().stream().filter(car -> c.getClass().equals(CarroEletrico.class)).map(ele-> (CarroEletrico)ele).collect(Collectors.toList());
        List<CarroGasolina> gasolinas = this.listaCarros.values().stream().filter(car -> c.getClass().equals(CarroGasolina.class)).map(gas-> (CarroGasolina)gas).collect(Collectors.toList());
        List<CarroHibrido> hibridos = this.listaCarros.values().stream().filter(car -> c.getClass().equals(CarroHibrido.class)).map(hib-> (CarroHibrido)hib).collect(Collectors.toList());

        if (tipo.contains("Gasolina") && !eletricos.isEmpty()) {
            CarroGasolina cG = gasolinas.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cG;
        }

        if (tipo.contains("Electrico") && !eletricos.isEmpty()) {
            CarroEletrico cE = eletricos.stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cE;
        }

        if (tipo.contains("Hibrido") && !hibridos.isEmpty()) {
            CarroHibrido cH = hibridos.stream()
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
    //lista pode estar vazia
    public Carro carroProximoMaisBarato(Cliente cli, Double distancia){
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };

        Carro car = this.listaCarros.values().stream()
                                      .filter(carro-> distanciaAoCarro(cli,carro)<=distancia)
                                      .sorted(c)
                                      .findFirst()
                                      .get()
                                      .clone();
        return car;
    }

    public void classificarCarro(String matricula, int classificação){
        Carro car = this.listaCarros.values().stream()
                    .filter(x->matricula.equals(x.getMatricula()))
                    .findAny()
                    .orElse(null);

        car.adicionaClassificacao(classificação);
        //adicionar erro
    }

    public void classificarAtores(int nif,int classificacao){
        if(!listaAtores.isEmpty() && listaAtores.containsKey(nif)){
            listaAtores.get(nif).adicionaClassificacao(classificacao);
        } else System.out.println("error");
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
        Carro carro = listaCarros.get(car.getMatricula());

        //pode não existir carro
        Cliente c = procuraCliente(nifCliente);
        Proprietario p = procuraProprietario(car.getProprietario().getNif());
        Point2D localizacaoCarro = car.getCoordenada();
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFimPrevista = dataInicio.plusMinutes(duracaoAluguer(c,car,destino));
        int estado=Aluguer.PENDENTE;
        Aluguer aluguer= new Aluguer(carro,c,p,localizacaoCarro,destino,dataInicio,dataFimPrevista,estado);
        this.adicionaAluguer(aluguer);

        c.adicionaAluguer(listaAlugueres.get(aluguer.getDataInicio()));
        p.adicionaAluguer(listaAlugueres.get(aluguer.getDataInicio()));
        carro.adicionaAluguer(listaAlugueres.get(aluguer.getDataInicio()));
    }

    public void aceitaEstado(Aluguer a){
        a.setEstado(Aluguer.ACEITE);
    }
    
    public void rejeitaEstado(Aluguer a){
        a.setEstado(Aluguer.REJEITADO);
    }

}

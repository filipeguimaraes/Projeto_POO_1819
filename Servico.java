
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
    private List<Aluguer> listaAlugueres;
    private Meteorologia meteorologia;
        
    public Servico(){
        this.listaCarros = new HashMap<>();
        this.listaAtores = new HashMap<>();
        this.listaAlugueres = new ArrayList<>();
        this.meteorologia=new Meteorologia();
    }


    public Servico(Map<String, Carro> listaCarros, Map<Integer, Ator> listaAtores, List<Aluguer> listaAlugueres, Meteorologia meteorologia) {
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
        return listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaCarros(Map<String, Carro> listaCarros) {
        this.listaCarros = listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<Integer, Ator> getListaAtores() {
        return listaAtores.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaAtores(Map<Integer, Ator> listaAtores) {
        this.listaAtores = listaAtores.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public List<Aluguer> getListaAlugueres() {
        return listaAlugueres.stream()
                .map(Aluguer::clone)
                .collect(Collectors.toList());
    }

    public void setListaAlugueres(List<Aluguer> listaAlugueres) {
        this.listaAlugueres = listaAlugueres.stream()
                .map(Aluguer::clone)
                .collect(Collectors.toList());
    }

    public Meteorologia getMeteorologia() {
        return new Meteorologia(meteorologia);
    }

    public void setMeteorologia(Meteorologia meteorologia) {
        this.meteorologia = new Meteorologia(meteorologia);
    }


    /**
     * Método para adicionar um carro eletrico à lista de carros
     * @param e carro elétrico
     * @throws CarroException caso o carro já exista
     */
    public void adicionaCarroEletrico(CarroEletrico e) throws CarroException,AtorException{
            if (!this.listaCarros.containsKey(e.getMatricula())){
                Proprietario p = procuraProprietario(e.getProprietario().getNif());
                this.listaCarros.put(e.getMatricula(),e.clone());
                p.adicionaCarro(e);
            }else throw new CarroException("Já existe este carro registado! "+e.getMatricula());
    }

    public void adicionaCarroEletrico(String marca, String matricula, int nif, int velocidade, double preco, Point2D localizacao, double consumo, double autonomia) throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroEletrico car = new CarroEletrico(marca,matricula,p,velocidade,preco,new Classificacao(),localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(car);
        }else throw new CarroException("Erro ao adicionar o carro "+matricula);
    }

    public void adicionaCarroGasolina(CarroGasolina g) throws CarroException,AtorException{
            if (!this.listaCarros.containsKey(g.getMatricula())){
                Proprietario p = procuraProprietario(g.getProprietario().getNif());
                this.listaCarros.put(g.getMatricula(),g.clone());
                p.adicionaCarro(g);
            }else throw new CarroException("Já existe este carro registado! "+g.getMatricula());
    }

    public void adicionaCarroGasolina(String marca, String matricula, int nif, int velocidade, double preco, Point2D localizacao, double consumo, double autonomia) throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroGasolina car = new CarroGasolina(marca,matricula,p,velocidade,preco,new Classificacao(),localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(car);
        }else throw new CarroException("Erro ao adicionar o carro "+matricula);
    }

    public void adicionaCarroHibrido(CarroHibrido h) throws CarroException,AtorException{
        if (!this.listaCarros.containsKey(h.getMatricula())){
            Proprietario p = procuraProprietario(h.getProprietario().getNif());
            this.listaCarros.put(h.getMatricula(),h.clone());
            p.adicionaCarro(h);
        }else throw new CarroException("Já existe este carro registado! "+h.getMatricula());

    }

    public void adicionaCarroHibrido(String marca, String matricula, int nif, int velocidade, double preco, Point2D localizacao, double consumo, double autonomia) throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroHibrido car = new CarroHibrido(marca,matricula,p,velocidade,preco,new Classificacao(),localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(car);
        }else throw new CarroException("Erro ao adicionar o carro "+matricula);
    }

    public void adicionaCliente(Cliente c) throws AtorException{
        if(!listaAtores.containsKey(c.getNif())){
            this.listaAtores.put(c.getNif(),c.clone());
        } else {
            throw new AtorException("Utilizador "+c.getNif()+" já existe!");
        }
    }

    public void adicionaCliente(String email, String password, int nif, String nome, String morada, LocalDateTime data) throws AtorException{
        if(!listaAtores.containsKey(nif)){
            Cliente c = new Cliente(email, nif, nome, password, morada, data, new Point2D.Double(), new Classificacao(), new ArrayList<Aluguer>());
            listaAtores.put(nif,c);
        } else {
            throw new AtorException("Cliente "+nif+" já existe!");
        }
    }

    public void adicionaProprietario(Proprietario p) throws AtorException{
        if(!listaAtores.containsKey(p.getNif())){
            this.listaAtores.put(p.getNif(),p.clone());
        } else {
            throw new AtorException("Utilizador"+p.getNif()+" já existe!");
        }
    }

    public void adicionaProprietario(String email, String password, int nif, String nome, String morada, LocalDateTime data) throws AtorException{
        if(!listaAtores.containsKey(nif)){
            Proprietario p = new Proprietario(email,nif,nome,password,morada,data, new Classificacao(), new ArrayList<Aluguer>(),new HashMap<String, Carro>());
            this.listaAtores.put(nif,p);
        } else {
            throw new AtorException("Proprietário"+nif+" já existe!");
        }
    }

    public void adicionaAluguer(Aluguer a)throws AluguerException{
        if(listaAlugueres.contains(a)){
            throw new AluguerException("Esse aluguer já existe!");
        }else this.listaAlugueres.add(a.clone());
    }

    public Proprietario procuraProprietario(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return (Proprietario)this.listaAtores.get(nif);
        else throw new AtorException("Não existe o Proprietario "+nif);
    }

    public Carro procuraCarro(String matricula) throws CarroException{
        if (listaCarros.containsKey(matricula)) return listaCarros.get(matricula);
        else throw new CarroException("Não existe o carro "+matricula);
    }

    public Cliente procuraCliente(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return (Cliente)this.listaAtores.get(nif);
        else throw new AtorException("Não existe o Cliente "+nif);
    }

    public Aluguer procuraAluguer(Aluguer a) throws AluguerException{
        if(listaAlugueres.contains(a)){
            return this.listaAlugueres.stream().filter(l -> l.equals(a)).collect(Collectors.toList()).get(0);
        }else throw new AluguerException("Esse aluguer não existe!");
    }

    public void atualizaMetereologia(int velocidadeVento,int temperatura,int precepitacao){
        this.meteorologia = new Meteorologia(velocidadeVento,temperatura,precepitacao);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(listaCarros, servico.listaCarros) &&
                Objects.equals(listaAtores, servico.listaAtores) &&
                Objects.equals(listaAlugueres, servico.listaAlugueres) &&
                Objects.equals(meteorologia, servico.meteorologia);
    }

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


    public boolean temAutonomia(Carro c,Point2D f){
        double autonomia;
        double distancia;
        double autonomiaFinal;
        Point2D i = c.getCoordenada();
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

    public double custo(Carro c, Point2D f){
        double distancia;
        Point2D i = c.getCoordenada();
        distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
        return c.getPreco()*distancia;
    }


    public double  distanciaAoPonto(Cliente cli,Point2D ponto){
        return Math.sqrt(Math.pow(cli.getCoordenada().getX()-ponto.getX(), 2) +Math.pow(cli.getCoordenada().getY()-ponto.getY(), 2));
    }


    /**
     * Metodo que retorna o carro mais proximo do cliente que realiza o aluguer
     * @param nifcli nif do cliente
     * @param tipo tipo do carro
     * @return carro mais proximo do determinado cliente
     * @throws AtorException Caso não exista cliente
     */
    public Carro carroMaisProximo(int nifcli,String tipo) throws AtorException{
        Cliente cli= procuraCliente(nifcli);
        Point2D localizacaoCliente = cli.getCoordenada();
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(distanciaAoPonto(cli,c1.getCoordenada())<distanciaAoPonto(cli,c2.getCoordenada())) return -1;
            if(distanciaAoPonto(cli,c1.getCoordenada())>distanciaAoPonto(cli,c2.getCoordenada())) return 1;
            else return 0;
        };

        if (tipo.contains("Gasolina")) {
            CarroGasolina cG = (CarroGasolina) this.listaCarros.values().stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cG;
        }
        if (tipo.contains("Electrico")) {
            CarroEletrico cE = (CarroEletrico) this.listaCarros.values().stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cE;
        }
        if (tipo.contains("Hibrido")) {
            CarroHibrido cH = (CarroHibrido) this.listaCarros.values().stream()
                    .sorted(c)
                    .findFirst()
                    .get()
                    .clone();
            return cH;
        }

        return null;
    }

    /**
     *
     * Método que determina o carro mais barato de um certo tipo
     *
     * @param tipo do carro
     * @return carro mais barato
     */
    public Carro carroMaisBarato(String tipo) throws CarroException{
        Comparator<Carro> c = (Carro c1, Carro c2) -> {
            if(c1.getPreco()<c2.getPreco()) return -1;
            if(c1.getPreco()>c2.getPreco()) return 1;
            else return 0;
        };

        if (!listaCarros.isEmpty()) {

/*
            if (tipo.contains("Gasolina")) {
                List<CarroGasolina> gasolinas = this.listaCarros.values().stream().filter(car -> c.getClass().getSimpleName().equals(CarroGasolina)).map(gas -> (CarroGasolina) gas).collect(Collectors.toList());
                if (!gasolinas.isEmpty()) {
                    CarroGasolina cG = gasolinas.stream()
                            .sorted(c)
                            .findFirst()
                            .get()
                            .clone();
                    return cG;
                } else throw new CarroException("Não há carros a gasolina registados!");
            }

            if (tipo.contains("Electrico")) {
                List<CarroEletrico> eletricos = this.listaCarros.values().stream().filter(car -> c.getClass().equals(CarroEletrico.class)).map(ele -> (CarroEletrico) ele).collect(Collectors.toList());
                if (!eletricos.isEmpty()) {
                    CarroEletrico cE = eletricos.stream()
                            .sorted(c)
                            .findFirst()
                            .get()
                            .clone();
                    return cE;
                } else throw new CarroException("Não há carros eletricos registados!");
            }

            if (tipo.contains("Hibrido")) {
                List<CarroHibrido> hibridos = this.listaCarros.values().stream().filter(car -> c.getClass().equals(CarroHibrido.class)).map(hib -> (CarroHibrido) hib).collect(Collectors.toList());
                if (!hibridos.isEmpty()) {
                    CarroHibrido cH = hibridos.stream()
                            .sorted(c)
                            .findFirst()
                            .get()
                            .clone();
                    return cH;
                } else throw new CarroException("Não há carros hibridos registados!");
            }

 */
        } else throw new CarroException("Não há carros no sistema!");
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

    /**
     * Método que atribui classificações aos carros
     * @param matricula matricula do carro em questão
     * @param classificacao classificação a atribuir
     * @throws CarroException Quando não existe o carro no sistema
     */
    public void classificarCarro(String matricula, int classificacao) throws CarroException{
        if (listaCarros.containsKey(matricula)){
            this.listaCarros.get(matricula).adicionaClassificacao(classificacao);
        } else throw new CarroException("Carro "+matricula+" inválido!");
    }

    /**
     * Método que atribui classificações aos utilizadores
     * @param nif nif do utilizador em questão
     * @param classificacao classificação a atribuir
     * @throws AtorException Quando não existe o utilizador no sistema
     */
    public void classificarAtores(int nif,int classificacao) throws AtorException{
        if(!listaAtores.containsKey(nif)){
            throw new AtorException("Utilizador "+nif+" inválido");
        } else listaAtores.get(nif).adicionaClassificacao(classificacao);
    }


    /**
     * Método que calcura o tempo que o cliente demora a chegar ao carro
     * @param cli cliente
     * @param car carro
     * @return tempo em minutos
     */
    public double tempoCliente(Cliente cli, Carro car){
        return (distanciaAoCarro(cli,car)/4)*60;
    }

    /**
     * Método que calcula a duração estimada da viagem
     *
     * @param cli cliente
     * @param car carro
     * @param destino destino da viagem
     * @return duração da viagem em minutos
     */
    public long duracaoAluguer(Cliente cli,Carro car,Point2D destino){
        return Math.round(this.meteorologia.medicaoMinutos()+tempoCliente(cli,car)+car.tempoViagem(destino));
    }

    public double pedidoAluguer(int nifCliente,Point2D destino, Carro car) throws AtorException,CarroException,AluguerException{
        double custo=0;
        Carro carro = procuraCarro(car.getMatricula());
        Cliente c = procuraCliente(nifCliente);
        Proprietario p = procuraProprietario(car.getProprietario().getNif());
        Point2D localizacaoCarro = car.getCoordenada();
        if(temAutonomia(carro,destino)){
            custo = custo(carro,destino);
        } else throw new AluguerException("O carro não tem autonomia suficiente para efetuar a viagem.");

        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFimPrevista = dataInicio.plusMinutes(duracaoAluguer(c,car,destino));
        int estado=Aluguer.PENDENTE;

        Aluguer aluguer= new Aluguer(carro,c,p,localizacaoCarro,destino,dataInicio,dataFimPrevista,estado);

        this.adicionaAluguer(aluguer);
        atribuiAluguer(aluguer,c,p,carro);
        return custo;
    }


    public void atribuiAluguer(Aluguer aluguer, Cliente c, Proprietario p, Carro car) throws AluguerException{
        c.adicionaAluguer(procuraAluguer(aluguer));
        p.adicionaAluguer(procuraAluguer(aluguer));
        car.adicionaAluguer(procuraAluguer(aluguer));
    }



    /**
     * Método que trata do login dos atores do sistema devoldo o seu identificador caso estejam registados no sistema.
     * @param email inserido pelo utilizador
     * @param password inserido pelo utilizador
     * @return nif do ator dentro do sistema
     * @throws AutenticacaoException caso o utilizador não esteja registado ou a password não esteja correta mas o utilizador exista
     */
    public int login(String email,String password) throws AutenticacaoException{
        int nif;
        if(this.listaAtores.values().stream().anyMatch(ator-> ator.getEmail().equals(email))){
            Ator a = this.listaAtores.values().stream().filter(ator-> ator.getEmail().equals(email)).findFirst().get().clone();
            if(a.getPassword().equals(password)){
                nif= a.getNif();
            } else {
                throw new AutenticacaoException("Password inválida");
            }
        }else{
            throw new AutenticacaoException("Não existe email na base de dados");
        }
        return nif;
    }


    public List<String> listaCarrosProprietario(int nif) throws AtorException{
        return procuraProprietario(nif).getListaCarros()
                .values()
                .stream()
                .map(Carro::toString)
                .collect(Collectors.toList());
    }

}

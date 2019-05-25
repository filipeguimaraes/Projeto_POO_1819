
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
    private Map<String, Veiculo> listaCarros;
    private Map<Integer,Ator> listaAtores;
    private List<Aluguer> listaAlugueres;
    private Meteorologia meteorologia;
        
    public Servico(){
        this.listaCarros = new HashMap<>();
        this.listaAtores = new HashMap<>();
        this.listaAlugueres = new ArrayList<>();
        this.meteorologia=new Meteorologia();
    }


    public Servico(Map<String, Veiculo> lsCarros, Map<Integer, Ator> lsAtores, List<Aluguer> lsAlugueres,
                   Meteorologia meteorologia) {
        this.listaCarros = lsCarros;
        this.listaAtores = lsAtores;
        this.listaAlugueres = lsAlugueres;
        this.meteorologia = meteorologia;
    }

    public Servico(Servico umServico){
        this.listaCarros = umServico.getListaCarros();
        this.listaAtores = umServico.getListaAtores();
        this.listaAlugueres = umServico.getListaAlugueres();
        this.meteorologia = umServico.getMeteorologia();
    }



    public Map<String, Veiculo> getListaCarros() {
        return listaCarros.entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaCarros(Map<String, Veiculo> listaCarros) {
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
    @SuppressWarnings("Duplicates")
    public void adicionaCarroEletrico(CarroEletrico e) throws CarroException,AtorException{
            if (!this.listaCarros.containsKey(e.getMatricula())){
                Proprietario p = procuraProprietario(e.getProprietario().getNif());
                this.listaCarros.put(e.getMatricula(),e.clone());
                p.adicionaCarro(listaCarros.get(e.getMatricula()));
            }else throw new CarroException("O carro "+e.getMatricula()+" já existe!");
    }

    @SuppressWarnings("Duplicates")
    public void adicionaCarroEletrico(String marca, String matricula, int nif, int velocidade, double preco,
                                      Point2D loc, double consumo, double autonomia) throws CarroException,AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroEletrico car = new CarroEletrico(marca,matricula,p,velocidade,preco,new Classificacao(),
                    loc,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(listaCarros.get(matricula));
        }else throw new CarroException("O carro "+matricula+" já existe!");
    }

    @SuppressWarnings("Duplicates")
    public void adicionaCarroGasolina(CarroGasolina g) throws CarroException,AtorException{
            if (!this.listaCarros.containsKey(g.getMatricula())){
                Proprietario p = procuraProprietario(g.getProprietario().getNif());
                this.listaCarros.put(g.getMatricula(),g.clone());
                p.adicionaCarro(listaCarros.get(g.getMatricula()));
            }else throw new CarroException("O carro "+g.getMatricula()+" já existe!");
    }

    public void adicionaCarroGasolina(String marca, String matricula, int nif, int velocidade, double preco, Point2D localizacao, double consumo, double autonomia) throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroGasolina car = new CarroGasolina(marca,matricula,p,velocidade,preco,new Classificacao(),localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(listaCarros.get(matricula));
        }else throw new CarroException("O carro "+matricula+" já existe!");
    }

    @SuppressWarnings("Duplicates")
    public void adicionaCarroHibrido(CarroHibrido h) throws CarroException,AtorException{
        if (!this.listaCarros.containsKey(h.getMatricula())){
            Proprietario p = procuraProprietario(h.getProprietario().getNif());
            this.listaCarros.put(h.getMatricula(),h.clone());
            p.adicionaCarro(listaCarros.get(h.getMatricula()));
        }else throw new CarroException("O carro "+h.getMatricula()+" já existe!");

    }

    public void adicionaCarroHibrido(String marca, String matricula, int nif, int velocidade, double preco, Point2D localizacao, double consumo, double autonomia) throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroHibrido car = new CarroHibrido(marca,matricula,p,velocidade,preco,new Classificacao(),localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(listaCarros.get(matricula));
        }else throw new CarroException("O carro "+matricula+" já existe!");
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
            throw new AtorException("Utilizador "+p.getNif()+" já existe!");
        }
    }

    public void adicionaProprietario(String email, String password, int nif, String nome, String morada, LocalDateTime data) throws AtorException{
        if(!listaAtores.containsKey(nif)){
            Proprietario p = new Proprietario(email,nif,nome,password,morada,data, new Classificacao(), new ArrayList<Aluguer>(),new HashMap<String, Veiculo>());
            this.listaAtores.put(nif,p);
        } else {
            throw new AtorException("Proprietário "+nif+" já existe!");
        }
    }

    public void adicionaAluguer(Aluguer a)throws AluguerException{
        if(listaAlugueres.contains(a)){
            throw new AluguerException("Esse aluguer já existe!");
        }else this.listaAlugueres.add(a.clone());
    }

    public Ator procuraAtor(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return this.listaAtores.get(nif);
        else throw new AtorException("Não existe este Ator "+nif);
    }

    public Proprietario procuraProprietario(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return (Proprietario)this.listaAtores.get(nif);
        else throw new AtorException("Não existe o Proprietario "+nif);
    }

    public Veiculo procuraCarro(String matricula) throws CarroException{
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


    public boolean temAutonomia(Veiculo c, Point2D f){
        double autonomia;
        double distancia;
        double autonomiaFinal;
        Point2D i = c.getCoordenada();

        autonomia=c.getAutonomia();
        distancia=Point2D.distance(i.getX(),i.getY(),f.getX(),f.getY());
        autonomiaFinal=autonomia-(distancia*c.getConsumo());
        return(autonomiaFinal>10);
    }

    public double  distanciaAoCarro(Cliente cli, Veiculo car){
        return Math.sqrt(Math.pow(cli.getCoordenada().getX()-car.getCoordenada().getX(), 2) +Math.pow(cli.getCoordenada().getY()-car.getCoordenada().getY(), 2));
    }

    public double custo(Veiculo c, Point2D f){
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
    @SuppressWarnings("Duplicates")
    public String carroMaisProximo(int nifcli,String tipo) throws AtorException, CarroException{
        Cliente cli= procuraCliente(nifcli);
        Point2D localizacaoCliente = cli.getCoordenada();
        Comparator<Veiculo> c = (Veiculo c1, Veiculo c2) -> {
            if(distanciaAoPonto(cli,c1.getCoordenada())<distanciaAoPonto(cli,c2.getCoordenada())) return -1;
            if(distanciaAoPonto(cli,c1.getCoordenada())>distanciaAoPonto(cli,c2.getCoordenada())) return 1;
            else return 0;
        };

        if (!listaCarros.isEmpty()) {
            if (tipo.contains("Gasolina")) {
                String cG = listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroGasolina"))
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return cG;
            }

            if (tipo.contains("Electrico")) {
                String cE = listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroEletrico"))
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return cE;
            }

            if (tipo.contains("Hibrido")) {
                String cH = listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroHibrido"))
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return cH;
            }

        } else throw new CarroException("Não há carros no sistema!");


        return "N/A";
    }

    @SuppressWarnings("Duplicates")
    public String carroMaisProximo(int nifcli) throws AtorException, CarroException{
        Cliente cli= procuraCliente(nifcli);
        Point2D localizacaoCliente = cli.getCoordenada();
        Comparator<Veiculo> c = (Veiculo c1, Veiculo c2) -> {
            if(distanciaAoPonto(cli,c1.getCoordenada())<distanciaAoPonto(cli,c2.getCoordenada())) return -1;
            if(distanciaAoPonto(cli,c1.getCoordenada())>distanciaAoPonto(cli,c2.getCoordenada())) return 1;
            else return 0;
        };
        if (!listaCarros.isEmpty()) {
                String car = listaCarros.values().stream()
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return car;
        } else throw new CarroException("Não há carros no sistema!");
    }

    /**
     *
     * Método que determina o carro mais barato de um certo tipo
     *
     * @param tipo do carro
     * @return carro mais barato
     */
    @SuppressWarnings("Duplicates")
    public String carroMaisBarato(String tipo) throws CarroException{

        if (!listaCarros.isEmpty()) {
            if (tipo.contains("Gasolina")) {
                String cG = listaCarros.values().stream()
                            .filter(car -> car.getClass().getSimpleName().equals("CarroGasolina"))
                            .sorted(new ComparatorCarroPreco())
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                    return cG;
            }

            if (tipo.contains("Electrico")) {
                String cE = listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroEletrico"))
                        .sorted(new ComparatorCarroPreco())
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return cE;
            }

            if (tipo.contains("Hibrido")) {
                String cH = listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroHibrido"))
                        .sorted(new ComparatorCarroPreco())
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return cH;
            }

        } else throw new CarroException("Não há carros no sistema!");

        return "N/A";
    }

    public String carroMaisBarato() throws CarroException{

        if (!listaCarros.isEmpty()) {
                String car = listaCarros.values().stream()
                        .sorted(new ComparatorCarroPreco())
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
                return car;
        } else throw new CarroException("Não há carros no sistema!");
    }

    /**
     * Metodo que retorna a matricula do carro mais barato mais proximo do cliente
     * @param nifCli nif do cliente
     * @param distancia distancia que o cliente está disposto a percorrer a pé
     * @return matricula
     */
    public String carroProximoMaisBarato(int nifCli, double distancia) throws AtorException{

        Cliente cli = procuraCliente(nifCli);

        String car = this.listaCarros.values().stream()
                                              .filter(carro-> distanciaAoCarro(cli,carro)<=distancia)
                                              .sorted(new ComparatorCarroPreco())
                                              .map(Veiculo::getMatricula)
                                              .findFirst()
                                              .orElse("N/A");
        return car;
    }

    public List<String> carroAutonomiaDesejada(double autonomia){
        return this.listaCarros.values().stream()
                                        .filter(l -> l.getAutonomia()>=autonomia)
                                        .map(Veiculo:: showCarro) //getmatricula
                                        .collect(Collectors.toList());
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
        } else throw new CarroException("Veiculo "+matricula+" inválido!");
    }

    /**
     * Método que calcula o total faturado por uma viatura num determinado período
     * @param matricula matricula do carro
     * @param inicio Data inicial
     * @param fim Data final
     * @return Total faturado por uma viatura num determinado período
     * @throws CarroException Se o carro não existir
     */
    public double totalFaturadoPeriodo(String matricula, LocalDateTime inicio, LocalDateTime fim) throws CarroException{
        return procuraCarro(matricula).getHistorico().stream()
                                                     .filter(l -> l.getDataInicio().isAfter(inicio) && l.getDataFim().isBefore(fim))
                                                     .mapToDouble(Aluguer::precoAluguer)
                                                     .sum();
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
    public double tempoCliente(Cliente cli, Veiculo car){
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
    public long duracaoAluguer(Cliente cli, Veiculo car, Point2D destino){
        return Math.round(this.meteorologia.medicaoMinutos()+tempoCliente(cli,car)+car.tempoViagem(destino));
    }

    @SuppressWarnings("Duplicates")
    public double AluguerProf(int nifCliente,Point2D destino, Veiculo car) throws AtorException,CarroException,AluguerException{
        double custo=0;
        Veiculo carro = procuraCarro(car.getMatricula());
        Cliente c = procuraCliente(nifCliente);
        Proprietario p = procuraProprietario(carro.getProprietario().getNif());
        Point2D localizacaoCarro = carro.getCoordenada();
        if(temAutonomia(carro,destino)){
            custo = custo(carro,destino);
        } else throw new AluguerException("O carro "+carro.getMatricula()+" não tem autonomia suficiente para efetuar a viagem.");

        LocalDateTime dataInicio = dataAleatoria();
        LocalDateTime dataFimPrevista = dataInicio.plusMinutes(duracaoAluguer(c,carro,destino));
        int estado=Aluguer.ACEITE;
        Aluguer aluguer= new Aluguer(carro,c,p,localizacaoCarro,destino,dataInicio,dataFimPrevista,estado);
        carro.setCoordenada(destino);

        this.adicionaAluguer(aluguer);
        atribuiAluguer(aluguer,c,p,carro);
        return custo;
    }

    @SuppressWarnings("Duplicates")
    public double pedidoAluguer(int nifCliente,Point2D destino, String matricula) throws AtorException,CarroException,AluguerException{
        double custo=0;
        Veiculo carro = procuraCarro(matricula);
        Cliente c = procuraCliente(nifCliente);
        Proprietario p = procuraProprietario(carro.getProprietario().getNif());
        Point2D localizacaoCarro = carro.getCoordenada();
        if(temAutonomia(carro,destino)){
            custo = custo(carro,destino);
        } else throw new AluguerException("O carro "+carro.getMatricula()+" não tem autonomia suficiente para efetuar a viagem.");

        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFimPrevista = dataInicio.plusMinutes(duracaoAluguer(c,carro,destino));
        int estado=Aluguer.PENDENTE;

        Aluguer aluguer= new Aluguer(carro,c,p,localizacaoCarro,destino,dataInicio,dataFimPrevista,estado);

        this.adicionaAluguer(aluguer);
        atribuiAluguer(aluguer,c,p,carro);
        return custo;
    }


    public void terminaAluguer(String estado, int nifCliente) throws CarroException{
        List<Aluguer> la = new ArrayList<>();
        for (Aluguer a : this.listaAlugueres){
            if(a.getEstado()==Aluguer.PENDENTE && a.getCli().getNif()==nifCliente){
                la.add(a);
            }
        }

        Aluguer alug = la.get(0);

        if(estado.equals("Aceitar")){
            alug.aceitaEstado();
            this.procuraCarro(alug.getCar().getMatricula()).percorreDistancia(alug.kmsPercorridos());
        } else{
            alug.rejeitaEstado();
            alug.setDataFim(LocalDateTime.now());
        }
    }

    public List<Aluguer> alugueresPendentes(int nifProp){
        return this.listaAlugueres.stream()
                .filter(l -> l.getEstado()==Aluguer.PENDENTE && l.getP().getNif()==nifProp)
                .collect(Collectors.toList());
    }



    public void atribuiAluguer(Aluguer aluguer, Cliente c, Proprietario p, Veiculo car) throws AluguerException{
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
            throw new AutenticacaoException("Não existe email na base de dados "+email);
        }
        return nif;
    }


    public double precoViagem(String matricula, LocalDateTime inicio, LocalDateTime fim){
        return this.listaAlugueres.stream()
                                  .filter(l->l.getCar().getMatricula().equals(matricula) && l.getDataFim().isBefore(fim) && l.getDataInicio().isAfter(inicio))
                                  .mapToDouble(Aluguer :: precoAluguer)
                                  .sum();
    }

    public List<String> listaCarrosProprietario(int nif) throws AtorException{
        return procuraProprietario(nif).getListaCarros()
                .values()
                .stream()
                .map(Veiculo::showCarro)
                .collect(Collectors.toList());
    }



    public List<String> alugueresEntreDatasCliente(int nifCli, LocalDateTime inicio, LocalDateTime fim){
        return this.listaAlugueres.stream()
                .filter(l -> l.getCli().getNif()==nifCli && l.getDataFim().isBefore(fim) && l.getDataInicio().isAfter(inicio))
                .map(Aluguer::showAluguer)
                .collect(Collectors.toList());
    }

    public List<String> alugueresEntreDatasProprietario(int nifProp, LocalDateTime inicio, LocalDateTime fim){
        return this.listaAlugueres.stream()
                .filter(l -> l.getP().getNif()==nifProp && l.getDataFim().isBefore(fim) && l.getDataInicio().isAfter(inicio))
                .map(Aluguer::showAluguer)
                .collect(Collectors.toList());
    }

    public List<String> dezClientesNVezes(){
        Comparator<Cliente> c = (Cliente c1, Cliente c2) -> {
            if(c1.getHistorico().size()>c2.getHistorico().size()) return -1;
            if(c1.getHistorico().size()<c2.getHistorico().size()) return 1;
            return 0;
        };

        return listaAtores.values().stream()
                .filter(l->l instanceof Cliente)
                .map(l->(Cliente)l)
                .sorted(c)
                .map(Cliente::showCliente)
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<String> dezClientesKms(){
        Comparator<Cliente> c = (Cliente c1, Cliente c2) -> {
            if(c1.kmsPercorridosTotal()>c2.kmsPercorridosTotal()) return -1;
            if(c1.kmsPercorridosTotal()<c2.kmsPercorridosTotal()) return 1;
            return 0;
        };
        return listaAtores.values().stream()
                .filter(l->l instanceof Cliente)
                .map(l->(Cliente)l)
                .sorted(c)
                .map(Cliente::showCliente)
                .limit(10)
                .collect(Collectors.toList());
    }

    public void classificaCarro(int nifCliente, String matricula, int classificacao)
            throws AluguerException,CarroException{
        boolean flag = this.procuraCarro(matricula).listaAlugueresAceites().stream()
                .anyMatch(l -> l.getCli().getNif()==nifCliente && l.getEstado()==Aluguer.ACEITE);

        if (flag) {
            procuraCarro(matricula).adicionaClassificacao(classificacao);
        } else throw new CarroException("Não pode classificar o carro "+matricula);
    }

    public void classificaCliente(int nifProp, int nifCli, int classificacao) throws AtorException,AluguerException{
        boolean flag = this.procuraProprietario(nifProp).listaAlugueresAceites().stream()
                .anyMatch(l -> l.getCli().getNif()==nifCli && l.getEstado()==Aluguer.ACEITE);

        if(flag) {
            this.procuraCliente(nifCli).adicionaClassificacao(classificacao);
        } else throw new AtorException("Não pode classificar o cliente "+nifCli);
    }

    public void classificaProprietario(int nifCli, int nifProp, int classificacao) throws AtorException,AluguerException{
        boolean flag = this.procuraCliente(nifCli).listaAlugueresAceites().stream()
                .anyMatch(l -> l.getP().getNif()==nifProp && l.getEstado()==Aluguer.ACEITE);

        if(flag) {
            this.procuraProprietario(nifProp).adicionaClassificacao(classificacao);
        } else throw new AtorException("Não pode classificar o proprietario "+nifProp);
    }






    public String verNome(int nif)throws AtorException{
        return this.procuraAtor(nif).getNome();
    }

    public String verEmail(int nif) throws AtorException{
        return this.procuraAtor(nif).getEmail();
    }

    public int verNif(int nif) throws AtorException{
        return this.procuraAtor(nif).getNif();
    }

    public String verMorada(int nif) throws AtorException{
        return this.procuraAtor(nif).getMorada();
    }

    public LocalDateTime verDataNascimente(int nif) throws AtorException{
        return this.procuraAtor(nif).getData();
    }

    public double verClassificacao(int nif) throws AtorException{
        return this.procuraAtor(nif).getClassificacao().classificacaoMedia();
    }

    public int verNumeroAluguer(int nif) throws AtorException{
        return this.procuraAtor(nif).getHistorico().size();
    }

    public LocalDateTime nascimentoAleatorio(){
        int dia = 1 + (int)(Math.random() * (28 - 1));
        int mes =  1 + (int)(Math.random() * (12 - 1));
        int ano =  1950 + (int)(Math.random() * (2000 - 1950));
        return LocalDateTime.of(ano, mes, dia,0,0);
    }

    public LocalDateTime dataAleatoria(){
        int dia = 1 + (int)(Math.random() * (28 - 1));
        int mes =  1 + (int)(Math.random() * (5 - 1));
        int ano =  2019;
        return LocalDateTime.of(ano, mes, dia,0,0);
    }

    public void introduzMeteorologia(double precipitacao,double temperatura, double velocidadeVento){
        this.meteorologia.setPrecipitacao(precipitacao);
        this.meteorologia.setTemperatura(temperatura);
        this.meteorologia.setVelocidadeVento(velocidadeVento);
    }

}

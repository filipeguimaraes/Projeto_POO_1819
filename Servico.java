import java.awt.geom.Point2D;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Serviço, contém todos os dados do sistema
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class Servico implements Serializable {
    private Map<String, Veiculo> listaCarros;
    private Map<Integer,Ator> listaAtores;
    private List<Aluguer> listaAlugueres;
    private Meteorologia meteorologia;
        
    public Servico() {
        this.listaCarros = new HashMap<>();
        this.listaAtores = new HashMap<>();
        this.listaAlugueres = new ArrayList<>();
        this.meteorologia = new Meteorologia();
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
     * Adiciona um carro elétrico à lista de carros
     * @param e Carro elétrico
     * @throws CarroException Caso o carro já exista
     * @throws AtorException Caso o proprietário não exista
     */
    @SuppressWarnings("Duplicates")
    public void adicionaCarroEletrico(CarroEletrico e) throws CarroException,AtorException{
            if (!this.listaCarros.containsKey(e.getMatricula())){
                Proprietario p = procuraProprietario(e.getProprietario().getNif());
                this.listaCarros.put(e.getMatricula(),e.clone());
                p.adicionaCarro(listaCarros.get(e.getMatricula()));
            }else throw new CarroException("O carro "+e.getMatricula()+" já existe!");
    }

    /**
     * Adiciona um carro elétrico à lista de carros
     * @param marca Marca do carro
     * @param matricula Matrícula do carro
     * @param nif Nif do proprietário
     * @param velocidade Pelocidade do carro
     * @param preco Preco do carro
     * @param loc Localizacao do carro
     * @param consumo Consumo do carro
     * @param autonomia Autonomia do carro
     * @throws CarroException Caso o carro já exista
     * @throws AtorException Caso o proprietário não exista
     */
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

    /**
     * Adiciona um carro a gasolina à lista de carros
     * @param g Carro a gasolina
     * @throws CarroException Caso o carro já exista
     * @throws AtorException Caso o proprietário não exista
     */
    @SuppressWarnings("Duplicates")
    public void adicionaCarroGasolina(CarroGasolina g) throws CarroException,AtorException{
            if (!this.listaCarros.containsKey(g.getMatricula())){
                Proprietario p = procuraProprietario(g.getProprietario().getNif());
                this.listaCarros.put(g.getMatricula(),g.clone());
                p.adicionaCarro(listaCarros.get(g.getMatricula()));
            }else throw new CarroException("O carro "+g.getMatricula()+" já existe!");
    }

    /**
     * Adiciona um carro a gasolina à lista de carros
     * @param marca Marca do carro
     * @param matricula Matrícula do carro
     * @param nif Nif do proprietário
     * @param velocidade Pelocidade do carro
     * @param preco Preco do carro
     * @param localizacao Localizacao do carro
     * @param consumo Consumo do carro
     * @param autonomia Autonomia do carro
     * @throws CarroException Caso o carro já exista
     * @throws AtorException Caso o proprietário não exista
     */
    public void adicionaCarroGasolina(String marca, String matricula, int nif, int velocidade, double preco,
                                      Point2D localizacao, double consumo, double autonomia)
                                        throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroGasolina car = new CarroGasolina(marca,matricula,p,velocidade,preco,new Classificacao(),
                    localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(listaCarros.get(matricula));
        }else throw new CarroException("O carro "+matricula+" já existe!");
    }

    /**
     * Adiciona um carro hibrido à lista de carros
     * @param h Carro hibrido
     * @throws CarroException Caso o carro já exista
     * @throws AtorException Caso o proprietário não exista
     */
    @SuppressWarnings("Duplicates")
    public void adicionaCarroHibrido(CarroHibrido h) throws CarroException,AtorException{
        if (!this.listaCarros.containsKey(h.getMatricula())){
            Proprietario p = procuraProprietario(h.getProprietario().getNif());
            this.listaCarros.put(h.getMatricula(),h.clone());
            p.adicionaCarro(listaCarros.get(h.getMatricula()));
        }else throw new CarroException("O carro "+h.getMatricula()+" já existe!");

    }

    /**
     * Adiciona um carro  hibrido à lista de carros
     * @param marca Marca do carro
     * @param matricula Matrícula do carro
     * @param nif Nif do proprietário
     * @param velocidade Pelocidade do carro
     * @param preco Preco do carro
     * @param localizacao Localizacao do carro
     * @param consumo Consumo do carro
     * @param autonomia Autonomia do carro
     * @throws CarroException Caso o carro já exista
     * @throws AtorException Caso o proprietário não exista
     */
    public void adicionaCarroHibrido(String marca, String matricula, int nif, int velocidade, double preco,
                                     Point2D localizacao, double consumo, double autonomia)
                                        throws CarroException, AtorException{
        if(!listaCarros.containsKey(matricula)){
            Proprietario p = procuraProprietario(nif);
            CarroHibrido car = new CarroHibrido(marca,matricula,p,velocidade,preco,new Classificacao(),
                    localizacao,new ArrayList<Aluguer>(),consumo,autonomia);
            listaCarros.put(matricula,car);
            p.adicionaCarro(listaCarros.get(matricula));
        }else throw new CarroException("O carro "+matricula+" já existe!");
    }

    /**
     * Adicionar cliente ao sistema
     * @param c Cliente
     * @throws AtorException Caso o cliente já exista
     */
    public void adicionaCliente(Cliente c) throws AtorException{
        if(!listaAtores.containsKey(c.getNif())){
            this.listaAtores.put(c.getNif(),c.clone());
        } else {
            throw new AtorException("Utilizador "+c.getNif()+" já existe!");
        }
    }

    /**
     * Adicionar Cliente ao sistema
     * @param email Email do novo cliente
     * @param password Password do novo cliente
     * @param nif Nif do novo cliente
     * @param nome Nome do novo cliente
     * @param morada Morada do novo cliente
     * @param data Data de nascimento do novo cliente
     * @throws AtorException Caso o cliente já exista
     */
    public void adicionaCliente(String email, String password, int nif, String nome, String morada, LocalDateTime data)
            throws AtorException{
        if(!listaAtores.containsKey(nif)){
            Cliente c = new Cliente(email, nif, nome, password, morada, data, new Point2D.Double(),
                    new Classificacao(), new ArrayList<Aluguer>());
            listaAtores.put(nif,c);
        } else {
            throw new AtorException("Cliente "+nif+" já existe!");
        }
    }

    /**
     * Adicionar proprietário ao sistema
     * @param p Proprietário
     * @throws AtorException Caso o proprietário já exista
     */
    public void adicionaProprietario(Proprietario p) throws AtorException{
        if(!listaAtores.containsKey(p.getNif())){
            this.listaAtores.put(p.getNif(),p.clone());
        } else {
            throw new AtorException("Utilizador "+p.getNif()+" já existe!");
        }
    }

    /**
     * Adicionar Proprietário ao sistema
     * @param email Email do novo proprietário
     * @param password Password do novo proprietário
     * @param nif Nif do novo proprietário
     * @param nome Nome do novo proprietário
     * @param morada Morada do novo proprietário
     * @param data Data de nascimento do novo proprietário
     * @throws AtorException Caso o proprietário já exista
     */
    public void adicionaProprietario(String email, String password, int nif, String nome, String morada,
                                     LocalDateTime data) throws AtorException{
        if(!listaAtores.containsKey(nif)){
            Proprietario p = new Proprietario(email,nif,nome,password,morada,data, new Classificacao(),
                    new ArrayList<Aluguer>(),new HashMap<String, Veiculo>());
            this.listaAtores.put(nif,p);
        } else {
            throw new AtorException("Proprietário "+nif+" já existe!");
        }
    }

    /**
     * Adiciona um novo aluguer ao sistema
     * @param a Aluguer
     * @throws AluguerException Caso este aluguer já tenha sido adicionado
     */
    public void adicionaAluguer(Aluguer a)throws AluguerException{
        if(listaAlugueres.contains(a)){
            throw new AluguerException("Esse aluguer já existe!");
        }else this.listaAlugueres.add(a.clone());
    }

    /**
     * Procura um ator no sistema
     * @param nif Nif do utilizador
     * @return Utilizador
     * @throws AtorException Caso o utilizador em questão não exista
     */
    public Ator procuraAtor(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return this.listaAtores.get(nif);
        else throw new AtorException("Não existe este Ator "+nif);
    }

    /**
     * Procura um proprietário no sistema
     * @param nif Nif do proprietário
     * @return Utilizador
     * @throws AtorException Caso o proprietário em questão não exista
     */
    public Proprietario procuraProprietario(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return (Proprietario)this.listaAtores.get(nif);
        else throw new AtorException("Não existe o Proprietario "+nif);
    }

    /**
     * Procura um carro no sistema
     * @param matricula Matrícula do carro
     * @return Carro
     * @throws AtorException Caso o carro em questão não exista
     */
    public Veiculo procuraCarro(String matricula) throws CarroException{
        if (listaCarros.containsKey(matricula)) return listaCarros.get(matricula);
        else throw new CarroException("Não existe o carro "+matricula);
    }

    /**
     * Procura um cliente no sistema
     * @param nif Nif do cliente
     * @return Utilizador
     * @throws AtorException Caso o cliente em questão não exista
     */
    public Cliente procuraCliente(int nif) throws AtorException{
        if (listaAtores.containsKey(nif)) return (Cliente)this.listaAtores.get(nif);
        else throw new AtorException("Não existe o Cliente "+nif);
    }

    /**
     * Procura um aluguer no sistema
     * @param a Aluguer
     * @return Utilizador
     * @throws AtorException Caso o cliente em questão não exista
     */
    public Aluguer procuraAluguer(Aluguer a) throws AluguerException{
        if(listaAlugueres.contains(a)){
            return this.listaAlugueres.stream().filter(l -> l.equals(a)).collect(Collectors.toList()).get(0);
        }else throw new AluguerException("Esse aluguer não existe!");
    }

    /**
     * Atualiza o estado da metereologia na aplicação
     * @param velocidadeVento Velocidade atual do vento km/h
     * @param temperatura Temperatuara atual em ºC
     * @param precepitacao Precipitação atual em %
     */
    public void atualizaMetereologia(double velocidadeVento,double temperatura,double precepitacao){
        this.meteorologia = new Meteorologia(velocidadeVento,temperatura,precepitacao);
    }

    /**
     * Verifica se um dado veiculo tem autonomia para a viagem
     * @param c Veículo
     * @param f Destino da viagem
     * @return true se tiver autonomia, false se não tiver
     */
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

    /**
     * Calcula a distância do cliente ao veículo
     * @param cli Cliente
     * @param car Veiculo
     * @return
     */
    public double  distanciaAoCarro(Cliente cli, Veiculo car){
        return (Math.sqrt(Math.pow(cli.getCoordenada().getX()-car.getCoordenada().getX(), 2) +
                Math.pow(cli.getCoordenada().getY()-car.getCoordenada().getY(), 2)));
    }

    /**
     * Custo estimado da viagem
     * @param c Veículo
     * @param f Destino da viagem
     * @return
     */
    public double custo(Veiculo c, Point2D f){
        double distancia;
        Point2D i = c.getCoordenada();
        distancia=Math.sqrt(Math.pow(i.getX()-f.getX(), 2) +Math.pow(i.getY()-f.getY(), 2));
        return c.getPreco()*distancia;
    }

    /**
     * Calcula a distância a um determinado ponto
     * @param cli Cliente
     * @param ponto Ponto
     * @return Distância do cliente ao ponto
     */
    public double  distanciaAoPonto(Cliente cli,Point2D ponto){
        return (Math.sqrt(Math.pow(cli.getCoordenada().getX()-ponto.getX(), 2) +
                Math.pow(cli.getCoordenada().getY()-ponto.getY(), 2)));
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
                return listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroGasolina"))
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
            }

            if (tipo.contains("Electrico")) {
                return listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroEletrico"))
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
            }

            if (tipo.contains("Hibrido")) {
                return listaCarros.values().stream()
                        .filter(car -> car.getClass().getSimpleName().equals("CarroHibrido"))
                        .sorted(c)
                        .map(Veiculo::getMatricula)
                        .findFirst()
                        .orElse("N/A");
            }

        } else throw new CarroException("Não há carros no sistema!");


        return "N/A";
    }

    /**
     * Procura na lista de veículos o mais proximo de um determinado cliente
     * @param nifcli Nif do cliente
     * @return Matrícula do carro mais próximo
     * @throws AtorException Caso o cliente fornecido não exista
     * @throws CarroException Caso não haja veículos
     */
    @SuppressWarnings("Duplicates")
    public String carroMaisProximo(int nifcli) throws AtorException, CarroException{
        Cliente cli= procuraCliente(nifcli);
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
     * Determina o Veículo mais barato de um certo tipo
     * @param tipo Tipo de Carro
     * @return MAtricula do carro mais barato
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

    /**
     * Determina o Veículo mais barato de um certo tipo
     * @return Matricula do carro mais barato
     * @throws CarroException Caso o sistema não tenha veículos
     */
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
     * Determina qual o veículo mais barato e mais proximo do cliente
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

    /**
     * Determina a lista de carros com a autonomia desejada
     * @param autonomia Autonomia
     * @return Lista de carros com a autonomia desejada
     */
    public List<String> carroAutonomiaDesejada(double autonomia){
        return this.listaCarros.values().stream()
                                        .filter(l -> l.getAutonomia()>=autonomia)
                                        .map(Veiculo:: showCarro) //getmatricula
                                        .collect(Collectors.toList());
    }

    /**
     * Atribui classificações aos carros
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
     * Determina o total faturado por uma viatura num determinado período
     * @param matricula matricula do carro
     * @param inicio Data inicial
     * @param fim Data final
     * @return Total faturado por uma viatura num determinado período
     * @throws CarroException Se o carro não existir
     */
    public double totalFaturadoPeriodo(String matricula, LocalDateTime inicio, LocalDateTime fim) throws CarroException{
        return procuraCarro(matricula).getHistorico().stream()
                                                     .filter(l -> l.getDataInicio().isAfter(inicio)
                                                             && l.getDataFim().isBefore(fim))
                                                     .mapToDouble(Aluguer::precoAluguer)
                                                     .sum();
    }

    /**
     * Atribui classificações aos utilizadores
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
     * Calcula o tempo que o cliente demora a chegar ao carro
     * @param cli cliente
     * @param car carro
     * @return tempo em minutos
     */
    public double tempoCliente(Cliente cli, Veiculo car){
        return (distanciaAoCarro(cli,car)/4)*60;
    }

    /**
     * Calcula a duração estimada da viagem
     * @param cli cliente
     * @param car carro
     * @param destino destino da viagem
     * @return duração da viagem em minutos
     */
    public long duracaoAluguer(Cliente cli, Veiculo car, Point2D destino){
        return Math.round(this.meteorologia.medicaoMinutos()+tempoCliente(cli,car)+car.tempoViagem(destino));
    }

    /**
     * Metodo que realiza os alugueres do ficheiro de logs
     * @param nifCliente Nif do cliente
     * @param destino Destino da viagem
     * @param car Carro
     * @return Custo da viagem
     * @throws AtorException Caso o cliente não esteja registado no sistema
     * @throws CarroException Caso o veículo não exista
     * @throws AluguerException Caso o veículo não tenha autonomia suficiente
     */
    @SuppressWarnings("Duplicates")
    public double AluguerProf(int nifCliente,Point2D destino, Veiculo car)
            throws AtorException,CarroException,AluguerException{
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

    /**
     * Metodo que realiza os alugueres do ficheiro de logs
     * @param nifCliente Nif do cliente
     * @param destino Destino da viagem
     * @param matricula Matrícula do carro
     * @return Custo da viagem
     * @throws AtorException Caso o cliente não esteja registado no sistema
     * @throws CarroException Caso o veículo não exista
     * @throws AluguerException Caso o veículo não tenha autonomia suficiente
     */
    @SuppressWarnings("Duplicates")
    public double pedidoAluguer(int nifCliente,Point2D destino, String matricula)
            throws AtorException,CarroException,AluguerException{
        double custo=0;
        Veiculo carro = procuraCarro(matricula);
        Cliente c = procuraCliente(nifCliente);
        Proprietario p = procuraProprietario(carro.getProprietario().getNif());
        Point2D localizacaoCarro = carro.getCoordenada();
        if(temAutonomia(carro,destino)){
            custo = custo(carro,destino);
        } else throw new AluguerException("O carro "+carro.getMatricula()+
                " não tem autonomia suficiente para efetuar a viagem.");

        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFimPrevista = dataInicio.plusMinutes(duracaoAluguer(c,carro,destino));
        int estado=Aluguer.PENDENTE;

        Aluguer aluguer= new Aluguer(carro,c,p,localizacaoCarro,destino,dataInicio,dataFimPrevista,estado);

        this.adicionaAluguer(aluguer);
        atribuiAluguer(aluguer,c,p,carro);
        return custo;
    }

    /**
     * Aceita/Rejeita e termina uma aluguer
     * @param estado Aceitar/Rejeitar
     * @param nifCliente nif do cliente
     * @throws CarroException Caso o carro não exista
     */
    public void terminaAluguer(String estado, int nifCliente) throws CarroException,AluguerException{
        List<Aluguer> la = new ArrayList<>();
        for (Aluguer a : this.listaAlugueres){
            if(a.getEstado()==Aluguer.PENDENTE && a.getCli().getNif()==nifCliente){
                la.add(a);
            }
        }

        Aluguer alug = procuraAluguer(la.get(0));

        if(estado.equals("Aceitar")){
            alug.aceitaEstado();
            this.procuraCarro(alug.getCar().getMatricula()).percorreDistancia(alug.kmsPercorridos());
        } else{
            alug.rejeitaEstado();
            alug.setDataFim(LocalDateTime.now());
        }
    }

    /**
     * Determina da lista de alugueres aqueles que estão pendentes
     * @param nifProp Nif do proprietário
     * @return Lista de alugueres pendentes
     */
    public List<Aluguer> alugueresPendentes(int nifProp){
        return this.listaAlugueres.stream()
                .filter(l -> l.getEstado()==Aluguer.PENDENTE && l.getP().getNif()==nifProp)
                .collect(Collectors.toList());
    }

    /**
     * Metodo auxiliar que atribui os alugueres aos atores e carros do sistema
     * @param aluguer Aluguer
     * @param c Cliente
     * @param p Proprietario
     * @param car Carro
     * @throws AluguerException Caso o aluguer não exista
     */
    public void atribuiAluguer(Aluguer aluguer, Cliente c, Proprietario p, Veiculo car) throws AluguerException{
        c.adicionaAluguer(procuraAluguer(aluguer));
        p.adicionaAluguer(procuraAluguer(aluguer));
        car.adicionaAluguer(procuraAluguer(aluguer));
    }

    /**
     * Trata do login dos atores do sistema devoldo o seu identificador caso estejam registados no sistema.
     * @param email inserido pelo utilizador
     * @param password inserido pelo utilizador
     * @return Nif do ator dentro do sistema
     * @throws AutenticacaoException Caso o utilizador não esteja registado ou a password não esteja correta mas o utilizador exista
     */
    public int login(String email,String password) throws AutenticacaoException{
        int nif;
        if(this.listaAtores.values().stream().anyMatch(ator-> ator.getEmail().equals(email))){
            Ator a = this.listaAtores.values().stream().filter(ator->
                    ator.getEmail().equals(email)).findFirst().get().clone();
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

    /**
     * Cria uma lista de strings de carros de um dado proprietário
     * @param nif Nif do proprietário
     * @return Lista de carros
     * @throws AtorException Caso o proprietário não exista
     */
    public List<String> listaCarrosProprietario(int nif) throws AtorException{
        return procuraProprietario(nif).getListaCarros()
                .values()
                .stream()
                .map(Veiculo::showCarro)
                .collect(Collectors.toList());
    }

    /**
     * Lista de carros com autonomia abaixo de 10 de um dado proprietário
     * @param nif Nif do proprietário
     * @return Lista de carros
     * @throws AtorException Caso o proprietário não exista
     */
    public List<String> listaCarrosAtonomiaDez(int nif) throws AtorException{
        Proprietario prop=procuraProprietario(nif);
        return procuraProprietario(nif).getListaCarros()
                .values()
                .stream()
                .filter(v-> v.getAutonomia()<=10 && v.getProprietario().getNif()==prop.getNif())
                .map(Veiculo::showCarro)
                .collect(Collectors.toList());
    }

    /**
     * Cria uma lista de strings de Alugueres, de um dado cliente, num determinado período de tempo
     * @param nifCli Nif cliente
     * @param inicio Data inicial
     * @param fim Data final
     * @return Lista de alugueres
     */
    public List<String> alugueresEntreDatasCliente(int nifCli, LocalDateTime inicio, LocalDateTime fim){
        return this.listaAlugueres.stream()
                .filter(l -> l.getCli().getNif()==nifCli
                        && l.getDataFim().isBefore(fim) && l.getDataInicio().isAfter(inicio))
                .map(Aluguer::showAluguer)
                .collect(Collectors.toList());
    }

    /**
     * Cria uma lista de strings de Alugueres, de um dado proprietário, num determinado período de tempo
     * @param nifProp Nif proprietario
     * @param inicio Data inicial
     * @param fim Data final
     * @return Lista de alugueres
     */
    public List<String> alugueresEntreDatasProprietario(int nifProp, LocalDateTime inicio, LocalDateTime fim){
        return this.listaAlugueres.stream()
                .filter(l -> l.getP().getNif()==nifProp
                        && l.getDataFim().isBefore(fim) && l.getDataInicio().isAfter(inicio))
                .map(Aluguer::showAluguer)
                .collect(Collectors.toList());
    }

    /**
     * Determina o Top 10 de clientes por numero de vezes que utilizaram o sistema
     * @return Lista de clientes
     */
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

    /**
     * Determina o Top 10 de clientes por kms percorridos com o sistema
     * @return Lista de clientes
     */
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

    /**
     * Metodo para classificar um determinado carro
     * @param nifCliente Cliente que vai classificar
     * @param matricula Matricula do carro
     * @param classificacao Classificação do carro
     * @throws CarroException Caso não possa classificar o carro
     */
    public void classificaCarro(int nifCliente, String matricula, int classificacao)
            throws AluguerException,CarroException{
        boolean flag = this.procuraCarro(matricula).listaAlugueresAceites().stream()
                .anyMatch(l -> l.getCli().getNif()==nifCliente && l.getEstado()==Aluguer.ACEITE);

        if (flag) {
            procuraCarro(matricula).adicionaClassificacao(classificacao);
        } else throw new CarroException("Não pode classificar o carro "+matricula);
    }

    /**
     * Metodo para classificar um determinado cliente
     * @param nifProp Proprietario que vai classificar
     * @param nifCli Cliente
     * @param classificacao Classificação
     * @throws AtorException Caso o proprietario ou o cliente não existam
     * @throws AluguerException Caso o proprietario não possa classificar o cliente
     */
    public void classificaCliente(int nifProp, int nifCli, int classificacao) throws AtorException,AluguerException{
        boolean flag = this.procuraProprietario(nifProp).listaAlugueresAceites().stream()
                .anyMatch(l -> l.getCli().getNif()==nifCli && l.getEstado()==Aluguer.ACEITE);

        if(flag) {
            this.procuraCliente(nifCli).adicionaClassificacao(classificacao);
        } else throw new AtorException("Não pode classificar o cliente "+nifCli);
    }

    /**
     * Metodo para classificar um determinado proprietario
     * @param nifCli Cliente que vai classificar
     * @param nifProp Proprietario
     * @param classificacao Classificação
     * @throws AtorException Caso o proprietario ou o cliente não existam
     * @throws AluguerException Caso o cliente não possa classificar o proprietario
     */
    public void classificaProprietario(int nifCli, int nifProp, int classificacao) throws AtorException,AluguerException{
        boolean flag = this.procuraCliente(nifCli).listaAlugueresAceites().stream()
                .anyMatch(l -> l.getP().getNif()==nifProp && l.getEstado()==Aluguer.ACEITE);

        if(flag) {
            this.procuraProprietario(nifProp).adicionaClassificacao(classificacao);
        } else throw new AtorException("Não pode classificar o proprietario "+nifProp);
    }

    /**
     * Retira o nome do utilizador
     * @param nif Nif do ator
     * @return Nome
     * @throws AtorException Caso o utilizador não exista
     */
    public String verNome(int nif)throws AtorException{
        return this.procuraAtor(nif).getNome();
    }

    /**
     * Retira o email do utilizador
     * @param nif Nif do ator
     * @return Email
     * @throws AtorException Caso o utilizador não exista
     */
    public String verEmail(int nif) throws AtorException{
        return this.procuraAtor(nif).getEmail();
    }

    /**
     * Rretira o nif do utilizador
     * @param nif Nif do ator
     * @return Nif
     * @throws AtorException Caso o utilizador não exista
     */
    public int verNif(int nif) throws AtorException{
        return this.procuraAtor(nif).getNif();
    }

    /**
     * Retira a Morada do utilizador
     * @param nif Nif do ator
     * @return Morada
     * @throws AtorException Caso o utilizador não exista
     */
    public String verMorada(int nif) throws AtorException{
        return this.procuraAtor(nif).getMorada();
    }

    /**
     * Retira a data de nascimento do utilizador
     * @param nif Nif do ator
     * @return Data de nascimento
     * @throws AtorException Caso o utilizador não exista
     */
    public LocalDateTime verDataNascimente(int nif) throws AtorException{
        return this.procuraAtor(nif).getData();
    }

    /**
     * Retira a classificação média do utilizador
     * @param nif Nif do ator
     * @return Classificação média
     * @throws AtorException Caso o utilizador não exista
     */
    public double verClassificacao(int nif) throws AtorException{
        return this.procuraAtor(nif).getClassificacao().classificacaoMedia();
    }

    /**
     * Retira o numero de alugueres que o utilizador realizou
     * @param nif Nif do ator
     * @return Numero de alugueres
     * @throws AtorException Caso o utilizador não exista
     */
    public int verNumeroAluguer(int nif) throws AtorException{
        return this.procuraAtor(nif).getHistorico().size();
    }

    /**
     * Produz uma data de nascimento aleatória ente 1950 e 2000
     * @return Data de nascimento
     */
    public LocalDateTime nascimentoAleatorio(){
        int dia = 1 + (int)(Math.random() * (28 - 1));
        int mes =  1 + (int)(Math.random() * (12 - 1));
        int ano =  1950 + (int)(Math.random() * (2000 - 1950));
        return LocalDateTime.of(ano, mes, dia,0,0);
    }

    /**
     * Produz uma data aleatória no ano de 2019 até junho
     * @return Data
     */
    public LocalDateTime dataAleatoria(){
        int dia = 1 + (int)(Math.random() * (28 - 1));
        int mes =  1 + (int)(Math.random() * (5 - 1));
        int ano =  2019;
        return LocalDateTime.of(ano, mes, dia,0,0);
    }

    /**
     * Atualiza a localização atual do cliente
     * @param nifCliente Nif do cliente
     * @param ponto Ponto onde o cliente se encontra
     * @throws AtorException Caso o cliente não esteja registado no sistema
     */
    public void atualizaLocalizacaoCliente(int nifCliente, Point2D ponto) throws AtorException{
        this.procuraCliente(nifCliente).getCoordenada().setLocation(ponto);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return listaCarros.equals(servico.listaCarros) &&
                listaAtores.equals(servico.listaAtores) &&
                listaAlugueres.equals(servico.listaAlugueres) &&
                meteorologia.equals(servico.meteorologia);
    }

    public Servico clone(){
        return new Servico(this);
    }

    public String toString() {
        return "Servico{" +
                "listaCarros=" + listaCarros +
                ", listaAtores=" + listaAtores +
                ", listaAlugueres=" + listaAlugueres +
                ", meteorologia=" + meteorologia +
                '}';
    }
}

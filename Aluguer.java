import java.awt.geom.Point2D;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Aluguer implements Serializable {
    private Carro car;
    private Cliente cli;
    private Proprietario p;
    private Point2D pontoInicial;
    private Point2D destino;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private int estado;
    
    public static final int REJEITADO = 0;
    public static final int PENDENTE = 1;
    public static final int ACEITE = 2;
    
   
    public Aluguer(){
        this.car = new CarroGasolina();
        this.cli = new Cliente();
        this.p = new Proprietario();
        this.pontoInicial = new Point2D.Double();
        this.destino = new Point2D.Double();
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
        this.estado = PENDENTE;
    }

    public Aluguer(Carro car, Cliente cli, Proprietario p, Point2D pontoInicial, Point2D destino, LocalDateTime dataInicio, LocalDateTime dataFim, int estado) {
        this.car = car;
        this.cli = cli;
        this.p = p;
        this.pontoInicial = pontoInicial;
        this.destino = destino;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.estado = estado;
    }

    public Aluguer(Aluguer umAluguer){
        this.car= umAluguer.getCar();
        this.cli = umAluguer.getCli();
        this.p= umAluguer.getP();
        this.pontoInicial = umAluguer.getPontoInicial();
        this.destino = umAluguer.getDestino();
        this.dataInicio = umAluguer.getDataInicio();
        this.dataFim = umAluguer.getDataFim();
        this.estado= umAluguer.getEstado();
    }

    public Carro getCar() {
        return car;
    }

    public void setCar(Carro car) {
        this.car = car;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Proprietario getP() {
        return p;
    }

    public void setP(Proprietario p) {
        this.p = p;
    }

    public Point2D getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(Point2D pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public Point2D getDestino() {
        return destino;
    }

    public void setDestino(Point2D destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Aluguer clone(){
        return new Aluguer(this);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguer aluguer = (Aluguer) o;
        return estado == aluguer.estado &&
                Objects.equals(car, aluguer.car) &&
                Objects.equals(cli, aluguer.cli) &&
                Objects.equals(p, aluguer.p) &&
                Objects.equals(pontoInicial, aluguer.pontoInicial) &&
                Objects.equals(destino, aluguer.destino) &&
                Objects.equals(dataInicio, aluguer.dataInicio) &&
                Objects.equals(dataFim, aluguer.dataFim);
    }


    public String toString(){
        StringBuilder s= new StringBuilder("Aluguer\n");
        s.append(" Carro" + this.car.getMatricula());
        s.append(" Cliente" + this.cli.getNif());
        s.append(" Proprietario" + this.p.getNif());
        s.append(" Ponto Inicial" + this.pontoInicial);
        s.append(" Destino" + this.destino);
        s.append(" Data inicial" + this.dataInicio);
        s.append(" Data final" + this.dataFim);
        s.append(" Estado" + this.estado);
        return s.toString();
    }

    /**
     * Método que aceita um determinado aluguer
     */
    public void aceitaEstado(){
        this.estado = Aluguer.ACEITE;
    }

    /**
     * Método que regeita um determinado aluguer
     */
    public void rejeitaEstado(){
        this.estado = Aluguer.REJEITADO;
    }

    /**
     * Metodo que calcula o preço do aluguer
     * @return preço do aluguer
     */
    public double precoAluguer(){
        double distancia=Point2D.distance(pontoInicial.getX(),pontoInicial.getY(),destino.getX(),destino.getY());
        return this.car.getPreco()*distancia;
    }

    public double kmsPercorridos(){
       return Point2D.distance(pontoInicial.getX(),pontoInicial.getY(),destino.getX(),destino.getY());
    }
   
}

import java.awt.Point;
import java.util.Date;

public class Aluguer{
    private Point inicio; //carro
    private Point fim; //carro
    private Carro car;
    private Cliente cli;
    private Point i; //cliente
    private Point f; //cliente
    private Date dataInicio;
    private Date dataFim;
    private int classificacao;
    private int estado;
    
    public static final int REJEITADO = 0;
    public static final int PENDENTE = 1;
    public static final int ACEITE = 2;
    
   
    public Aluguer(){
        this.inicio=new Point();
        this.fim=new Point();
        this.car=new Carro();
        this.estado=PENDENTE;
    }

    public Aluguer(Point inicio, Point fim, Carro car, Cliente cli, Point i, Point f, Date dataInicio, Date dataFim, int classificacao, int estado) {
        this.inicio = inicio;
        this.fim = fim;
        this.car = car;
        this.cli = cli;
        this.i = i;
        this.f = f;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.classificacao = classificacao;
        this.estado = estado;
    }

    public Aluguer(Aluguer umAluguer){
        this.inicio= umAluguer.getInicio();
        this.fim= umAluguer.getFim();
        this.car= umAluguer.getCar();
        this.cli = umAluguer.getCli();
        this.i = umAluguer.getI();
        this.f = umAluguer.getF();
        this.dataInicio = umAluguer.getDataInicio();
        this.dataFim = umAluguer.getDataFim();
        this.classificacao = umAluguer.getClassificacao();
        this.estado= umAluguer.getEstado();
    }

    public Point getInicio() {
        return inicio;
    }

    public void setInicio(Point inicio) {
        this.inicio = inicio;
    }

    public Point getFim() {
        return fim;
    }

    public void setFim(Point fim) {
        this.fim = fim;
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

    public Point getI() {
        return i;
    }

    public void setI(Point i) {
        this.i = i;
    }

    public Point getF() {
        return f;
    }

    public void setF(Point f) {
        this.f = f;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
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
    
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
        Aluguer c = (Aluguer) o;
        return c.getInicio().equals(this.inicio) && c.getFim().equals(this.fim) && c.getCar().equals(this.car) && c.getEstado()==this.estado;
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Aluguer\n");
        s.append(" Ponto inicial" + this.inicio);
        s.append(" Ponto final" + this.fim);
        s.append(" Carro" + this.car);
        s.append(" Estado: " + this.estado);
        return s.toString();
    }
   
}

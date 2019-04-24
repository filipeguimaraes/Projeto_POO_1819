import java.awt.Point;
import java.util.Date;

public class Aluguer{
    private Point inicioCarro; //carro
    private Point fimCarro; //carro
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
        this.inicioCarro=new Point();
        this.fimCarro=new Point();
        this.car=new Carro();
        this.estado=PENDENTE;
    }

    public Aluguer(Point inicioCarro, Point Carro, Carro car, Cliente cli, Point i, Point f, Date dataInicio, Date dataFim, int classificacao, int estado) {
        this.inicioCarro = inicioCarro;
        this.fimCarro = fimCarro;
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
        this.inicioCarro= umAluguer.getInicioCarro();
        this.fimCarro= umAluguer.getFimCarro();
        this.car= umAluguer.getCar();
        this.cli = umAluguer.getCli();
        this.i = umAluguer.getI();
        this.f = umAluguer.getF();
        this.dataInicio = umAluguer.getDataInicio();
        this.dataFim = umAluguer.getDataFim();
        this.classificacao = umAluguer.getClassificacao();
        this.estado= umAluguer.getEstado();
    }

    public Point getInicioCarro() {
        return inicioCarro;
    }

    public void setInicioCarro(Point inicioCarro) {
        this.inicioCarro = inicioCarro;
    }

    public Point getFimCarro() {
        return fimCarro;
    }

    public void setFimCarro(Point fimCarro) {
        this.fimCarro = fimCarro;
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
        return c.getInicioCarro().equals(this.inicioCarro) && c.getFimCarro().equals(this.fimCarro) && c.getCar().equals(this.car) && c.getEstado()==this.estado;
    }
    
    public String toString(){
        StringBuilder s= new StringBuilder("Aluguer\n");
        s.append(" Ponto inicial" + this.inicioCarro);
        s.append(" Ponto final" + this.fimCarro);
        s.append(" Carro" + this.car);
        s.append(" Estado: " + this.estado);
        return s.toString();
    }
   
}

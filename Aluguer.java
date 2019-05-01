import java.awt.geom.Point2D;
import java.time.LocalDateTime;


public class Aluguer{
    private Point2D inicioCarro; //carro
    private Point2D fimCarro; //carro
    private Carro car;
    private Cliente cli;
    private Proprietario p;
    private Point2D i; //cliente
    private Point2D f; //cliente
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private int classificacao;
    private Meteorologia meteorologia;
    private int estado;
    
    public static final int REJEITADO = 0;
    public static final int PENDENTE = 1;
    public static final int ACEITE = 2;
    
   
    public Aluguer(){
        this.inicioCarro = new Point2D.Double();
        this.fimCarro = new Point2D.Double();
        this.car = new Carro();
        this.cli = new Cliente();
        this.p = new Proprietario();
        this.i = new Point2D.Double();
        this.f = new Point2D.Double();
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
        this.classificacao = 1;
        this.meteorologia= new Meteorologia();
        this.estado = PENDENTE;
    }

    public Aluguer(Point2D inicioCarro, Point2D fimCarro, Carro car, Cliente cli, Proprietario p, Point2D i, Point2D f, LocalDateTime dataInicio, LocalDateTime dataFim, int classificacao, Meteorologia meteorologia, int estado) {
        this.inicioCarro = inicioCarro;
        this.fimCarro = fimCarro;
        this.car = car;
        this.cli = cli;
        this.p = p;
        this.i = i;
        this.f = f;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.classificacao = classificacao;
        this.meteorologia= meteorologia;
        this.estado = estado;
    }

    public Aluguer(Aluguer umAluguer){
        this.inicioCarro= umAluguer.getInicioCarro();
        this.fimCarro= umAluguer.getFimCarro();
        this.car= umAluguer.getCar();
        this.cli = umAluguer.getCli();
        this.p= umAluguer.getP();
        this.i = umAluguer.getI();
        this.f = umAluguer.getF();
        this.dataInicio = umAluguer.getDataInicio();
        this.dataFim = umAluguer.getDataFim();
        this.classificacao = umAluguer.getClassificacao();
        this.meteorologia = umAluguer.getMeteorologia();
        this.estado= umAluguer.getEstado();
    }

    public Meteorologia getMeteorologia() {
        return meteorologia;
    }

    public void setMeteorologia(Meteorologia meteorologia) {
        this.meteorologia = meteorologia;
    }

    public Point2D getInicioCarro() {
        return inicioCarro;
    }

    public void setInicioCarro(Point2D inicioCarro) {
        this.inicioCarro = inicioCarro;
    }

    public Point2D getFimCarro() {
        return fimCarro;
    }

    public void setFimCarro(Point2D fimCarro) {
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
    
    public Proprietario getP(){
        return p;
    }
    
    public void setP(Proprietario p){
        this.p=p;
    }

    public Point2D getI() {
        return i;
    }

    public void setI(Point2D i) {
        this.i = i;
    }

    public Point2D getF() {
        return f;
    }

    public void setF(Point2D f) {
        this.f = f;
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
        return c.getInicioCarro().equals(this.inicioCarro) && c.getFimCarro().equals(this.fimCarro) && c.getCar().equals(this.car)
        && c.getCli().equals(this.cli) && c.getP().equals(this.p) && c.getI().equals(this.i) && c.getF().equals(this.f)
        && c.getDataInicio().equals(this.dataInicio) && c.getDataFim().equals(this.dataFim) && c.getClassificacao()==this.classificacao
        && c.getMeteorologia()==this.meteorologia && c.getEstado()==this.estado;
    }

    public String toString(){
        StringBuilder s= new StringBuilder("Aluguer\n");
        s.append(" Ponto inicial carro" + this.inicioCarro);
        s.append(" Ponto final carro" + this.fimCarro);
        s.append(" Carro" + this.car);
        s.append(" Cliente" + this.cli);
        s.append(" Proprietario" + this.p);
        s.append(" Ponto inicial cliente" + this.i);
        s.append(" Ponto final cliente" + this.f);
        s.append(" Data inicial" + this.dataInicio);
        s.append(" Data final" + this.dataFim);
        s.append(" Classificacao" + this.classificacao);
        s.append(" Meteorologia" + this.meteorologia);
        s.append(" Estado" + this.estado);
        return s.toString();
    }
   
}

import java.io.Serializable;

/**
 * Meteorologia, com o as condições metereologicas e o tempo de atraso que as mesmas provocam
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class Meteorologia implements Serializable {
    private double velocidadeVento; //km/h
    private double temperatura; //graus
    private double precipitacao; //%


    public Meteorologia(){
        this.velocidadeVento=0;
        this.temperatura=0;
        this.precipitacao=0;
    }

    public Meteorologia(double velocidadeVento, double temperatura, double precepitacao) {
        this.velocidadeVento = velocidadeVento;
        this.temperatura = temperatura;
        this.precipitacao = precepitacao;
    }

    public Meteorologia(Meteorologia umaMeteorologia){
        this.velocidadeVento = umaMeteorologia.getVelocidadeVento();
        this.temperatura = umaMeteorologia.getTemperatura();
        this.precipitacao = umaMeteorologia.getPrecipitacao();
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    /**
     * Calcula o atraso (segundo o IPMA)
     * @return Atraso em minutos
     */
    public int medicaoMinutos(){
        int m=0;

        if (this.velocidadeVento>=15 && this.velocidadeVento<=35) m+=1;
        if (this.velocidadeVento>=36 && this.velocidadeVento<=55) m+=2;
        if (this.velocidadeVento>=56) m+=3;

        if (this.temperatura<=0) m+=2;
        if (this.temperatura>=0 && this.temperatura<=10) m+=1;
        if (this.temperatura>=25 && this.temperatura<=30) m+=1;
        if (this.temperatura<=31)m+=2;

        if (this.precipitacao>=25 && this.precipitacao<=50) m+=1;
        if (this.precipitacao>=50 && this.precipitacao<=75) m+=2;
        if (this.precipitacao>=75 && this.precipitacao<=100) m+=3;

        return m;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meteorologia that = (Meteorologia) o;
        return velocidadeVento == that.velocidadeVento &&
                temperatura == that.temperatura &&
                precipitacao == that.precipitacao;
    }

    public Meteorologia clone(){
        return new Meteorologia(this);
    }

    public String toString() {
        return "Meteorologia{" +
                "velocidadeVento=" + velocidadeVento +
                ", temperatura=" + temperatura +
                ", precepitacao=" + precipitacao +
                '}';
    }

}

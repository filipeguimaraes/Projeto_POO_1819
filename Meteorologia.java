import java.io.Serializable;

/**
 * Classe que contem as condições metereologicas e o tempo de atraso que as mesmas provocam
 *
 *
 * @version (a version number or a date)
 */
public class Meteorologia implements Serializable {
    private int velocidadeVento; //km/h
    private int temperatura; //graus
    private int precipitacao; //0-100


    public Meteorologia(){
        this.velocidadeVento=0;
        this.temperatura=0;
        this.precipitacao=0;
    }

    public Meteorologia(int velocidadeVento, int temperatura, int precepitacao) {
        this.velocidadeVento = velocidadeVento;
        this.temperatura = temperatura;
        this.precipitacao = precepitacao;
    }

    public Meteorologia(Meteorologia umaMeteorologia){
        this.velocidadeVento = umaMeteorologia.getVelocidadeVento();
        this.temperatura = umaMeteorologia.getTemperatura();
        this.precipitacao = umaMeteorologia.getPrecipitacao();
    }

    public int getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(int velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecepitacao(int precepitacao) {
        this.precipitacao = precepitacao;
    }

    /* Vento fraco  < 15 km/h ->+1, Vento moderado  15 a 35 km/h->+2, Vento forte 36 a 55 km/h->+3, vento muito forte >56
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

    public String toString() {
        return "Meteorologia{" +
                "velocidadeVento=" + velocidadeVento +
                ", temperatura=" + temperatura +
                ", precepitacao=" + precipitacao +
                '}';
    }

}

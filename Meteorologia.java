import java.util.Objects;

/**
 * Write a description of class Meteorologia here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Meteorologia
{
    private int velocidadeVento; //km/h
    private int temperatura; //graus
    private int precepitacao; //0-100

    public Meteorologia(){
        this.velocidadeVento=0;
        this.temperatura=0;
        this.precepitacao=0;
    }

    public Meteorologia(int velocidadeVento, int temperatura, int precepitacao) {
        this.velocidadeVento = velocidadeVento;
        this.temperatura = temperatura;
        this.precepitacao = precepitacao;
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

    public int getPrecepitacao() {
        return precepitacao;
    }

    public void setPrecepitacao(int precepitacao) {
        this.precepitacao = precepitacao;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meteorologia that = (Meteorologia) o;
        return velocidadeVento == that.velocidadeVento &&
                temperatura == that.temperatura &&
                precepitacao == that.precepitacao;
    }

    public String toString() {
        return "Meteorologia{" +
                "velocidadeVento=" + velocidadeVento +
                ", temperatura=" + temperatura +
                ", precepitacao=" + precepitacao +
                '}';
    }
}

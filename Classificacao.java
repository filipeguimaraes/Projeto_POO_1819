import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Write a description of class Classificacao here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Classificacao {

    private List<Integer> classificacoes;

    public Classificacao(){
        this.classificacoes = new ArrayList<>();
    }

    public Classificacao(List<Integer> classificacoes) {
        this.classificacoes = classificacoes;
    }

    public List<Integer> getClassificacoes() {
        return classificacoes;
    }

    public void setClassificacoes(List<Integer> classificacoes) {
        this.classificacoes = classificacoes;
    }

    public void adicionaClassificacao(int classificacao){
        this.classificacoes.add(classificacao);
    }

    public double classificacaoMedia(){
       return this.classificacoes.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classificacao that = (Classificacao) o;
        return Objects.equals(classificacoes, that.classificacoes);
    }

    public String toString() {
        return '{' +
                " Classificações=" + this.classificacoes +
                " Classificação média=" + this.classificacaoMedia() +
                '}';
    }
}

import java.util.ArrayList;
import java.util.List;

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

    public void classificacaoMedia(){
        this.classificacoes.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

    }
}

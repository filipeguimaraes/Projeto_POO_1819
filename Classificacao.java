import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Classificação, com lista de classificações
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class Classificacao implements Serializable {

    private List<Integer> classificacoes;

    public Classificacao(){
        this.classificacoes = new ArrayList<>();
    }

    public Classificacao(List<Integer> classificacoes) {
        this.classificacoes = classificacoes;
    }

    public List<Integer> getClassificacoes() {
        return new ArrayList<>(classificacoes);
    }

    public void setClassificacoes(List<Integer> classificacoes) {
        this.classificacoes = new ArrayList<>(classificacoes);
    }

    /**
     * Adicionar classificação à lista
     * @param classificacao Classificação
     */
    public void adicionaClassificacao(int classificacao){
        this.classificacoes.add(classificacao);
    }

    /**
     * Determina a classificação média da lista de classificações
     * @return Classificação média
     */
    public double classificacaoMedia(){
       return this.classificacoes.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classificacao that = (Classificacao) o;
        return classificacoes.equals(that.classificacoes);
    }

    public String toString() {
        return '{' +
                " Classificações=" + this.classificacoes +
                " Classificação média=" + this.classificacaoMedia() +
                '}';
    }
}

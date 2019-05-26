import java.util.Comparator;

/**
 * Class comparator para a ordenação dos carros por preço
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class ComparatorCarroPreco implements Comparator<Veiculo> {

    public int compare(Veiculo c1, Veiculo c2){
        if(c1.getPreco()<c2.getPreco()) return -1;
        if(c1.getPreco()>c2.getPreco()) return 1;
        return 0;
    }
}

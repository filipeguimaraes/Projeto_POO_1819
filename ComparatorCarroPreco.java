import java.util.Comparator;

/**
 * Write a description of class ComparatorCarroPreco here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ComparatorCarroPreco implements Comparator<Veiculo> {

    public int compare(Veiculo c1, Veiculo c2){
        if(c1.getPreco()<c2.getPreco()) return -1;
        if(c1.getPreco()>c2.getPreco()) return 1;
        return 0;
    }
}

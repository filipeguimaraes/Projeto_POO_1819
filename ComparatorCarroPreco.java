import java.util.Comparator;

/**
 * Write a description of class ComparatorCarroPreco here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ComparatorCarroPreco implements Comparator<Carro> {

    public int compare(Carro c1, Carro c2){
        if(c1.getPreco()<c2.getPreco()) return -1;
        if(c1.getPreco()>c2.getPreco()) return 1;
        return 0;
    }
}

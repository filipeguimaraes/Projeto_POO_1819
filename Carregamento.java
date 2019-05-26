import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Carregamento, contém metodos para guardar e ler ficheiro de objetos
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class Carregamento {

    /**
     * Metodo para escrever o estado atual do serviço num ficheiro objeto
     * @param servico Serviço atual
     * @param path Caminho do ficheiro
     */
    public static void escreverFicheiroOjeto(Servico servico, String path) throws IOException {
        File arq = new File(path);
        arq.delete();
        arq.createNewFile();
        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
        objOutput.writeObject(servico);
        objOutput.close();
    }

    /**
     * Metodo para escrever o estado atual do serviço num ficheiro objeto
     * @param path Caminho do ficheiro
     */
    public static Servico lerFicheiroObjeto(String path) throws IOException, ClassNotFoundException {
        Servico servico = new Servico();
        File arq = new File(path);
        if (arq.exists()) {
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
            servico = (Servico)objInput.readObject();
            objInput.close();
        }
        return (servico);
    }
}


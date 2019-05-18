import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe responsável por guardar o estado do "serviço" em ficheiro objeto bem como os carregar no início da proxima sessão
 *
 * @authors Beatriz Rocha, Filipe Guimarães, Gonçalo Ferreira
 */
public class Carregamento {

    public static void escreverFicheiroOjeto(Servico servico, String path) throws IOException {
        File arq = new File(path);
        arq.delete();
        arq.createNewFile();
        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
        objOutput.writeObject(servico);
        objOutput.close();
    }

    // desserialização: recuperando os objetos gravados no arquivo binário "nomeArq"
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


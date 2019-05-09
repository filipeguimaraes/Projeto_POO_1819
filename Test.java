import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Test {
    public static void main(String args[]) throws IOException {

        Servico servico = new Servico();
        BufferedReader inicio = new BufferedReader(new FileReader("logsPOO_carregamentoInicial.bak"));
        while(inicio.ready()){
            String linha = inicio.readLine();

            if(!linha.contains("--")) {
                String[] aux = linha.split(":");

                if (linha.contains("NovoProp:")) {
                    String[] campos = aux[1].split(",");
                    String nome = campos[0];
                    int nif = Integer.valueOf(campos[1]);
                    String password = campos[1];
                    String email = campos[2];
                    String rua = campos[3];
                    LocalDateTime data = LocalDateTime.now();
                    Proprietario novoProprietario = new Proprietario(email, nif, nome, password, rua, data, 0, new ArrayList<>(), new ArrayList<>());
                    servico.adicionaProprietario(novoProprietario.clone());
                }

                if (linha.contains("NovoCliente:")) {
                    String[] camposCli = aux[1].split(",");
                    String nome = camposCli[0];
                    int nif = Integer.valueOf(camposCli[1]);
                    String password = camposCli[1];
                    String email = camposCli[2];
                    String rua = camposCli[3];
                    LocalDateTime data = LocalDateTime.now();
                    Point2D ponto= new Point2D.Double(Double.parseDouble(camposCli[4]),Double.parseDouble(camposCli[5]));
                    Cliente novoCliente = new Cliente(email, nif, nome, password, rua, data, ponto,0, new ArrayList<>());
                    servico.adicionaCliente(novoCliente.clone());
                }
            }
        }
        inicio.close();

        System.out.println(servico.toString());

    }
}

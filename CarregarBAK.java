import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Write a description of class CarregarBAK here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarregarBAK {

    private String path;

    public CarregarBAK(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void carregaAtoresCarros(Servico servico) throws IOException,AtorException,CarroException,AluguerException {
        BufferedReader inicio = new BufferedReader(new FileReader(this.path));

        while (inicio.ready()) {
            String linha = inicio.readLine();
            if (!linha.contains("--")) {
                String[] aux = linha.split(":");

                if (linha.contains("NovoProp:")) {
                    String[] campos = aux[1].split(",");
                    String nome = campos[0];
                    int nif = Integer.valueOf(campos[1]);
                    String password = campos[1];
                    String email = campos[2];
                    String rua = campos[3];
                    LocalDateTime data = LocalDateTime.now();
                    servico.adicionaProprietario(email,password,nif,nome,rua,data);
                }

                if (linha.contains("NovoCliente:")) {
                    String[] camposCli = aux[1].split(",");
                    String nome = camposCli[0];
                    int nif = Integer.valueOf(camposCli[1]);
                    String password = camposCli[1];
                    String email = camposCli[2];
                    String rua = camposCli[3];
                    LocalDateTime data = LocalDateTime.now();
                    Point2D ponto = new Point2D.Double(Double.parseDouble(camposCli[4]), Double.parseDouble(camposCli[5]));
                    servico.adicionaCliente(email,password,nif,nome,rua,data);
                }

                if (linha.contains("NovoCarro:")){
                    String[] camposCar = aux[1].split(",");
                    String marca = camposCar[1];
                    String matricula = camposCar[2];
                    int nif = Integer.valueOf(camposCar[3]);
                    int velocidadeMedia = Integer.valueOf(camposCar[4]);
                    double preco = Double.parseDouble(camposCar[5]);
                    double consumo = Double.parseDouble(camposCar[6]);
                    double autonomia = Double.parseDouble(camposCar[7]);
                    Point2D ponto = new Point2D.Double(Double.parseDouble(camposCar[8]),Double.parseDouble(camposCar[9]));

                    if (camposCar[0].contains("Electrico")){
                        servico.adicionaCarroEletrico(marca,matricula,nif,velocidadeMedia,preco,ponto,consumo,autonomia);
                    } else if (camposCar[0].contains("Gasolina")){
                        servico.adicionaCarroGasolina(marca,matricula,nif,velocidadeMedia,preco,ponto,consumo,autonomia);
                    } else if (camposCar[0].contains("Hibrido")) {
                        servico.adicionaCarroHibrido(marca,matricula,nif,velocidadeMedia,preco,ponto,consumo,autonomia);
                    }
                }

                if (linha.contains("Classificar:")) {
                    String[] campos = aux[1].split(",");
                    if (campos[0].length()==8){
                        String matricula = campos[0];
                        int classificacao = Integer.valueOf(campos[1]);
                        servico.classificarCarro(matricula,classificacao);
                    }
                    if (campos[0].length()==9){
                        int nif = Integer.valueOf(campos[0]);
                        int classificacao = Integer.valueOf(campos[1]);
                        servico.classificarAtores(nif,classificacao);
                    }
                }

                if(linha.contains("Aluguer:")){
                    String[] campos = aux[1].split(",");
                    int nifCliente = Integer.valueOf(campos[0]);
                    Point2D ponto = new Point2D.Double(Double.parseDouble(campos[1]), Double.parseDouble(campos[2]));
                    String tipoCombustivel = campos[3];
                    String preferencia = campos[4];
                    Carro carro = null;
                    if(preferencia.contains("MaisBarato")){
                        System.out.println("OLA darlling");
                        carro = servico.carroMaisBarato(tipoCombustivel);
                    }else if(preferencia.contains("MaisPerto")){
                        carro = servico.carroMaisProximo(nifCliente, tipoCombustivel);
                    }
                    servico.pedidoAluguer(nifCliente,ponto,carro);
                }
            }
        }
        inicio.close();
    }

}

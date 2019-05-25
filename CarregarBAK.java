import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class CarregarBAK here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarregarBAK {

    private String path;
    private List<String> excecoes;

    public CarregarBAK(String path) {
        this.path = path;
        this.excecoes = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getExcecoes() {
        return excecoes;
    }

    public void setExcecoes(List<String> excecoes) {
        this.excecoes = excecoes;
    }

    public void carregaFicheiro(Servico servico) throws IOException {
        BufferedReader inicio = new BufferedReader(new FileReader(this.path));

        while (inicio.ready()) {
            String linha = inicio.readLine();
            if (!linha.contains("--")) {
                String[] aux = linha.split(":");

                if (linha.contains("NovoProp:")) {
                    try {
                        prop(aux,servico);
                    }catch (AtorException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if (linha.contains("NovoCliente:")) {
                    try {
                        cli(aux,servico);
                    }catch (AtorException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if (linha.contains("NovoCarro:")){
                    try {
                        car(aux,servico);
                    }catch (AtorException|CarroException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if (linha.contains("Classificar:")) {
                    try {
                        classificar(aux,servico);
                    }catch (AtorException|CarroException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if (linha.contains("Aluguer:")) {
                    try {
                        aluguer(aux,servico);
                    }catch (AtorException|CarroException|AluguerException a){
                        this.excecoes.add(a.getMessage());
                    }
                }
            }
        }
        inicio.close();
    }


    @SuppressWarnings("Duplicates")
    public void prop(String[] aux, Servico servico) throws AtorException{
        String[] campos = aux[1].split(",");
        String nome = campos[0];
        int nif = Integer.valueOf(campos[1]);
        String password = campos[1];
        String email = campos[2];
        String rua = campos[3];
        LocalDateTime data = servico.nascimentoAleatorio();
        servico.adicionaProprietario(email,password,nif,nome,rua,data);
    }

    @SuppressWarnings("Duplicates")
    public void cli(String[] aux, Servico servico) throws AtorException{
        String[] camposCli = aux[1].split(",");
        String nome = camposCli[0];
        int nif = Integer.valueOf(camposCli[1]);
        String password = camposCli[1];
        String email = camposCli[2];
        String rua = camposCli[3];
        LocalDateTime data = servico.nascimentoAleatorio();
        Point2D ponto = new Point2D.Double(Double.parseDouble(camposCli[4]), Double.parseDouble(camposCli[5]));
        servico.adicionaCliente(email,password,nif,nome,rua,data);
    }

    public void car(String[] aux, Servico servico) throws CarroException,AtorException{
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

    public void classificar(String[] aux, Servico servico) throws AtorException,CarroException{
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

    public void aluguer(String[] aux, Servico servico) throws CarroException,AluguerException,AtorException{
        String[] campos = aux[1].split(",");
        int nifCliente = Integer.valueOf(campos[0]);
        Point2D ponto = new Point2D.Double(Double.parseDouble(campos[1]), Double.parseDouble(campos[2]));
        String tipoCombustivel = campos[3];
        String preferencia = campos[4];
        if (preferencia.contains("MaisBarato")) {
            servico.AluguerProf(nifCliente, ponto, servico.procuraCarro(servico.carroMaisBarato(tipoCombustivel)));
        }else{
            servico.AluguerProf(nifCliente, ponto, servico.procuraCarro(servico.carroMaisProximo(nifCliente, tipoCombustivel)));
        }
    }

}

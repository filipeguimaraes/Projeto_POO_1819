import java.io.BufferedReader;
import java.io.IOException;

public class Controller {

    private Servico servico;

    public static void main(String args[]) throws IOException {
        new Controller().run();
    }

    private Controller(){
        this.servico= new Servico();
    }

    public void run()throws IOException{
        String path="logsPOO_carregamentoInicial.bak";
        CarregarBAK backup=new CarregarBAK(path);
        View view = new View();
        String[] opcoes={"ola","mundo"};
        view.mainMenu(opcoes);
        //backup.carregaAtoresCarros(this.servico);
        //System.out.println(this.servico.toString());

    }






    /*                if(linha.contains("Aluguer:")){
                    String[] campos = aux[1].split(",");
                    int nifCliente = Integer.valueOf(campos[0]);
                    Point2D pontoDestino = new Point2D.Double(Double.parseDouble(campos[1]),Double.parseDouble(campos[2]));
                    String tipoCombustivel= campos[3];
                    String preferencia = campos[4];
                    if (preferencia.contains("Electrico")){
                        CarroEletrico car = ((CarroEletrico) servico.carroMaisBarato("Electrico"));
                        System.out.println(car.toString());
                    }else if (preferencia.contains("Gasolina")){
                        CarroGasolina car = ((CarroGasolina) servico.carroMaisBarato("Gasolina"));
                    }else if (preferencia.contains("Hibrido")){
                        CarroHibrido car = ((CarroHibrido) servico.carroMaisBarato("Hibrido"));
                    } else{
                        Carro car = null; //dar algum erro a ler as opcoes!
                    }

     */


}

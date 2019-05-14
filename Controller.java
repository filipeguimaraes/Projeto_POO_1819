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
        String[] opcoes={"ola","mundo","Gonçalo"};
        view.mainMenu(opcoes);
        //backup.carregaAtoresCarros(this.servico);
        //System.out.println(this.servico.toString());

    }

}

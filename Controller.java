import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {

    private Servico servico;

    public static void main(String args[]) throws IOException {
        new Controller().runMainMenu();
    }

    private Controller(){
        this.servico= new Servico();
    }


    private void run(){
    }


    private void runMainMenu()throws IOException{
        View view = new View();
        String[] opcoes={"Carregar ficheiro de inicio","Carregar estado anterior"};
        view.mainMenu(opcoes);

        int escolha=0;
        while (escolha!=-1) {
            escolha = lerInt();
            switch (escolha) {
                case 1:
                    try {
                        String path = "logsPOO_carregamentoInicial.bak";
                        CarregarBAK backup = new CarregarBAK(path);
                        backup.carregaAtoresCarros(this.servico);
                        System.out.println(this.servico.toString());
                        String[] opcoes2={"ola","mundo"};
                        view.mainMenu(opcoes2);
                    }catch (IOException e){
                        view.erroFicheiro();
                        escolha=lerInt();
                        continue;
                    }
                    break;
                case 2:
                    System.out.println("Ficheiro de obj aqui");
                    break;
                case -1:
                    view.fim();
                    break;
                default:
                    System.out.println("calmaaaa");
                    break;
            }
        }


    }

    public static String lerString() {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        String txt = "";
        while(!flag) {
            try {
                txt = input.nextLine();
                flag = true;
            } catch(InputMismatchException e) {
                System.out.println("Formato inválido");
                System.out.print("Nova opção: ");
                input.nextLine();
            }
        }
        return txt;
    }


    public static int lerInt() {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        int i = 0;
        while(!flag) {
            try {
                i = input.nextInt();
                flag = true;
            } catch(InputMismatchException e) {
                System.out.println("Inteiro Invalido");
                System.out.print("Novo valor: ");
                input.nextLine();
            }
        }
        return i;
    }

}

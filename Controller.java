import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {

    private Servico servico;

    private static String BAK_PATH = "logsPOO_carregamentoInicial.bak";
    private static String OBJ_PATH = "estado.obj";

    public static void main(String args[]) throws IOException {
        new Controller().run();
    }

    private Controller(){
        this.servico= new Servico();
    }




    private void run()throws IOException{
        View view = new View();
        String[] opcoes={"Carregar ficheiro de inicio","Carregar estado anterior","Sair e guardar estado"};
        String[] opcoes2={"ola","mundo"};
        view.mainMenu(opcoes);

        int escolha=0;
        while (escolha!=-1) {
            escolha = lerInt();
            switch (escolha) {
                //Carregar ficheiro de inicio
                case 1:
                    try {
                        CarregarBAK backup = new CarregarBAK(BAK_PATH);
                        backup.carregaAtoresCarros(this.servico);
                        System.out.println(this.servico.toString());
                        view.mainMenu(opcoes);
                    } catch (IOException e){
                        view.erroFicheiro();
                        escolha=lerInt();
                        continue;
                    }
                    break;
                    //carregar estado anterior
                case 2:
                    try {
                        this.servico = Carregamento.lerFicheiroObjeto(OBJ_PATH);
                        System.out.println(this.servico.toString());
                        view.mainMenu(opcoes);
                    } catch (IOException e){
                        view.erroFicheiro();
                        continue;
                    } catch (ClassNotFoundException e){
                        System.out.println("Erro: "+e);
                        continue;
                    }
                    break;
                    //gravar estado1
                case 3:
                    try {
                        Carregamento.escreverFicheiroOjeto(this.servico,OBJ_PATH);
                        System.out.println("Guardado com sucesso");
                    } catch (IOException e){
                        System.out.println(View.RED+"Erro ao escrever ficheiro: "+View.RESET+e);
                    }
                    break;
                case -1:
                    view.fim();
                    break;
                default:
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

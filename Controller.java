import java.awt.geom.Point2D;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {

    private Servico servico;
    private View view;

    private static String BAK_PATH = "logsPOO_carregamentoInicial.bak";
    private static String OBJ_PATH = "estado.obj";

    public static void main(String[] args) throws IOException {
        new Controller().run();
    }

    private Controller(){
        this.servico = new Servico();
        this.view = new View();
    }

    private void run()throws IOException{
        String[] opcoes={"Carregar ficheiro de inicio","Carregar estado anterior","Guardar estado","Entrar no programa","Sair"};
        int escolha=0;
        do {
            switch (escolha) {
                case 0:
                    view.mainMenu(opcoes);
                    escolha=lerInt();
                    break;
                //Carregar ficheiro de inicio
                case 1:
                    try {
                        CarregarBAK backup = new CarregarBAK(BAK_PATH);
                        try {
                            backup.carregaAtoresCarros(this.servico);
                        } catch (AtorException|CarroException a){
                            System.out.println(a);
                            view.enterContinuar();
                        }
                    } catch (IOException e){
                        view.erroFicheiro();
                        view.enterContinuar();
                        continue;
                    }
                    escolha=0;
                    break;
                //carregar estado anterior
                case 2:
                    try {
                        this.servico = Carregamento.lerFicheiroObjeto(OBJ_PATH);
                        System.out.println(this.servico.toString());
                        view.mainMenu(opcoes);
                        escolha=0;
                    } catch (IOException e){
                        view.erroFicheiro();
                        escolha=0;
                        continue;
                    } catch (ClassNotFoundException e){
                        System.out.println("Erro: "+e);
                        view.enterContinuar();
                        escolha = 0;
                        continue;
                    }
                    break;
                // Gravar estado
                case 3:
                    try {
                        Carregamento.escreverFicheiroOjeto(this.servico,OBJ_PATH);
                        System.out.println("Guardado com sucesso");
                    } catch (IOException e){
                        System.out.println(View.RED+"Erro ao escrever ficheiro: "+View.RESET+e);
                    }
                    view.enterContinuar();
                    escolha=0;
                    break;
                case 4:
                    escolha=runEscolherAtor();
                    break;
                default:
                    System.out.println("Ocorreu um erro.");
                    view.enterContinuar();
                    escolha=0;
                    break;
            }
        } while (escolha!=5);
        view.fim();

    }

    private int runEscolherAtor(){
        String[] opcoes = {"Cliente","Proprietario","Menu Inicial"};
        int escolha=0;
        do{
            view.mainMenu(opcoes);
            escolha = lerInt();
            switch (escolha){
                case 1:
                    view.souCliente();
                    escolha = runCliente();
                case 2 :
                    view.souProprietario();
                    escolha = runProprietario();
                default:
                    break;
            }
        } while (escolha!=3);
        return 0;
    }

    private int runLogin(){
        String[] opcoes = {"Login","Registar","Menu Principal"};
        int escolha=0;
        int nif=0;
        do{
            view.mainMenu(opcoes);
            escolha = lerInt();
            switch (escolha){
                case 1:
                    String[] credenciais =view.loginMenu();
                    String email1= credenciais[View.EMAIL];
                    String pass1 = credenciais[View.PASS];
                    try {
                        nif = servico.login(email1,pass1);
                        escolha = 0;
                    }catch (AutenticacaoException e){
                        System.out.println(e);
                        view.enterContinuar();
                        continue;
                    }
                    break;
                case 2:
                    String[] registo = view.registarAtorMenu();
                    String email = registo[View.EMAIL];
                    String pass = registo[View.PASS];
                    int nifi = Integer.valueOf(registo[View.NIF]);
                    String nome = registo[View.NOME];
                    String morada = registo[View.MORADA];

                    String[] dataf = registo[View.DATA].split("/");
                    int dia = Integer.valueOf(dataf[0]);
                    int mes = Integer.valueOf(dataf[1]);
                    int ano = Integer.valueOf(dataf[2]);
                    LocalDateTime data = LocalDateTime.of(ano,mes,dia,0,0);
                    if(view.getAtor()==View.CLIENTE){
                        try{
                            servico.adicionaCliente(email,pass,nif,nome,morada,data);
                        } catch (AtorException a){
                            System.out.println(a);
                            view.enterContinuar();
                        }
                    }
                    if (view.getAtor()==View.PROPRIETARIO){
                        try {
                            servico.adicionaProprietario(email,pass,nif,nome,morada,data);
                        }catch (AtorException a){
                            System.out.println(a);
                            view.enterContinuar();
                        }
                    }


                case 3:
                    escolha=0;
                   break;
            }
        }while (escolha!=0);
        return nif;
    }

    private int runCliente(){
        String[] opcoes = {"Realizar aluguer","Terminar sessão"};
        int nif=runLogin();
        int escolha=0;
        if(nif!=0){
            do{
                view.mainMenu(opcoes);
                escolha = lerInt();
            }while (escolha!=0);

        }else System.out.println("Cliente Invalido");
        return -1;
    }

    private int runProprietario(){
        String[] opcoes = {"Registar carro","Terminar sessão"};
        int nif=runLogin();
        int escolha=0;
        if(nif!=0){
            do{
                view.mainMenu(opcoes);
                escolha = lerInt();
                switch (escolha){
                    case 1:
                        String[] registo = view.registarCarroMenu();
                        String marca = registo[View.MARCA];
                        String Matricula = registo[View.MATRICULA];
                        int velocidade = Integer.valueOf(registo[View.VELOCIDADE]);
                        double preco = Double.parseDouble(registo[View.PRECO]);
                        String[] locs = registo[View.LOCALIZACAO].split(",");
                        double x = Double.parseDouble(locs[0]);
                        double y = Double.parseDouble(locs[1]);
                        Point2D localizacao = new Point2D.Double(x,y);
                        double consumo = Double.parseDouble(registo[View.CONSUMO]);
                        double autonomia = Double.parseDouble(registo[View.AUTONOMIA]);
                        break;

                    default:
                        break;
                }
            }while (escolha!=0);

        }else System.out.println("Cliente Invalido");
        return -1;
    }



    public int lerInt() {
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

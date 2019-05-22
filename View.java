import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Write a description of class View here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View {
    private int ator;

    public static int ECRA=42; //Tamanho do Ecra

    public static String LINE = "#################################################################################################";

    public static String RESET = "\033[0m";  // Text Reset
    public static String RED = "\033[0;31m";     // RED
    public static String CYAN_BOLD = "\033[1;36m";   // Cyan Bold
    public static String CYAN = "\033[0;36m";
    public static int CLIENTE = 1;
    public static int PROPRIETARIO = 2;

    public final static int EMAIL=0;
    public final static int PASS=1;
    public final static int NIF=2;
    public final static int NOME=3;
    public final static int MORADA=4;
    public final static int DATA=5;

    public final static int MARCA=0;
    public final static int MATRICULA=1;
    public final static int VELOCIDADE=2;
    public final static int PRECO=3;
    public final static int LOCALIZACAO=4;
    public final static int CONSUMO=5;
    public final static int AUTONOMIA=6;
    public final static int TIPOCARRO=7;



    public View(){
        this.ator=0;
    }

    public int getAtor() {
        return ator;
    }

    public void setAtor(int ator) {
        this.ator = ator;
    }

    public void souCliente(){
        this.ator=CLIENTE;
    }

    public void souProprietario(){
        this.ator=PROPRIETARIO;
    }

    public void clear(int number){
        for (int i = 0; i < number; ++i) System.out.println();
    }

    public void line(int numeroDeLinhas){
        for (int i=0;i<numeroDeLinhas;i++) System.out.println(LINE);
    }

    public void mudarLinha(){
        System.out.println();
    }

    public void cyan(){
        System.out.println("\033[0;36m");
    }

    public void red(){
        System.out.println("\033[0;31m");
    }

    public void resetColor(){
        System.out.println("\033[0m");
    }

    public void printOpcao(int i,String s){
        System.out.println("     "+CYAN_BOLD+i+")"+RESET+"  "+s);
    }

    public void ban() {
        cyan();
        line(2);
        System.out.println("$$\\   $$\\ $$\\      $$\\  $$$$$$\\                                             $$$$$\\           $$\\ ");
        System.out.println("$$ |  $$ |$$$\\    $$$ |$$  __$$\\                                            \\__$$ |          $$ | ");
        System.out.println("$$ |  $$ |$$$$\\  $$$$ |$$ /  \\__| $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\        $$ | $$$$$$\\  $$ | ");
        System.out.println("$$ |  $$ |$$\\$$\\$$ $$ |$$ |       \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\       $$ | \\____$$\\ $$ | ");
        System.out.println("$$ |  $$ |$$ \\$$$  $$ |$$ |       $$$$$$$ |$$ |  \\__|$$ |  \\__|$$ /  $$ |$$\\   $$ | $$$$$$$ |\\__| ");
        System.out.println("$$ |  $$ |$$ |\\$  /$$ |$$ |  $$\\ $$  __$$ |$$ |      $$ |      $$ |  $$ |$$ |  $$ |$$  __$$ |    ");
        System.out.println("\\$$$$$$  |$$ | \\_/ $$ |\\$$$$$$  |\\$$$$$$$ |$$ |      $$ |      \\$$$$$$  |\\$$$$$$  |\\$$$$$$$ |$$\\ ");
        System.out.println(" \\______/ \\__|     \\__| \\______/  \\_______|\\__|      \\__|       \\______/  \\______/  \\_______|\\__| ");
        line(2);
        resetColor();
    }

    public void fim(){
        cyan();
        int number=(ECRA-10)/2;
        clear(number);
        line(1);
        System.out.println("                      $$$$$$\\        $$\\                               $$\\ ");
        System.out.println("                     $$  __$$\\       $$ |                              $$ |");
        System.out.println("                     $$ /  $$ | $$$$$$$ | $$$$$$\\  $$\\   $$\\  $$$$$$$\\ $$ |");
        System.out.println("                     $$$$$$$$ |$$  __$$ |$$  __$$\\ $$ |  $$ |$$  _____|$$ |");
        System.out.println("                     $$  __$$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |\\$$$$$$\\  \\__|");
        System.out.println("                     $$ |  $$ |$$ |  $$ |$$   ____|$$ |  $$ | \\____$$\\     ");
        System.out.println("                     $$ |  $$ |\\$$$$$$$ |\\$$$$$$$\\ \\$$$$$$  |$$$$$$$  |$$\\ ");
        System.out.println("                     \\__|  \\__| \\_______| \\_______| \\______/ \\_______/ \\__|");
        line(1);
        clear(number);
        resetColor();
    }

    public void mainMenu(String[] opcoes){
        ban();
        int number= ((ECRA - 18) - opcoes.length) / 2;
        int i=1;
        clear(number);
        for(String s: opcoes){
            printOpcao(i,s);
            i++;
        }
        cyan();
        clear(number);
        line(1);
        resetColor();
        System.out.println("     Opção pretendida: ");
    }



    public void continuarSair(){
        System.out.print("Continuar(0) ou sair(5): ");

    }



    public String[] loginMenu(){
        String[] emailPassword = new String[2];
        clear(ECRA);
        ban();
        System.out.print("Insira o email: ");
        emailPassword[EMAIL] = lerString();
        mudarLinha();
        System.out.print("Insira a password"+RED+"(Campo visível, cuidado!)"+RESET+": ");
        emailPassword[PASS] = lerString();
        mudarLinha();
        return emailPassword;
    }

    public String[] registarAtorMenu(){
        String[] registos = new String[6];
        clear(ECRA);
        ban();
        System.out.print("Insira o seu nome: ");
        registos[NOME] = lerString();
        mudarLinha();
        System.out.print("Insira o email: ");
        registos[EMAIL] = lerString();
        mudarLinha();
        System.out.print("Insira a password"+RED+"(Campo visível, cuidado!)"+RESET+": ");
        registos[PASS] = lerString();
        mudarLinha();
        System.out.print("Insira o NIF: ");
        registos[NIF] = lerString();
        mudarLinha();System.out.print("Insira a sua Morada: ");
        registos[MORADA] = lerString();
        mudarLinha();
        System.out.print("Insira a data de nascimento(DD/MM/AAAA): ");
        registos[DATA] = lerString();
        mudarLinha();
        return registos;
    }

    public String[] registarCarroMenu(){
        String[] registos = new String[8];
        clear(ECRA);
        ban();
        System.out.print("Tipo"+CYAN +"(Electrico|Gasolina|Hibrido)"+RESET+": ");
        registos[TIPOCARRO] = lerString();
        mudarLinha();
        System.out.print("Marca: ");
        registos[MARCA] = lerString();
        mudarLinha();
        System.out.print("Matricula"+RED+" AA/AA/AA "+RESET+": ");
        registos[MATRICULA] = lerString();
        mudarLinha();
        System.out.print("Velocidade Média: ");
        registos[VELOCIDADE] = lerString();
        mudarLinha();
        System.out.print("Preco por km: ");
        registos[PRECO] = lerString();
        mudarLinha();
        System.out.print("Localização do carro"+RED+" x,y "+RESET+": ");
        registos[LOCALIZACAO] = lerString();
        mudarLinha();
        System.out.print("Consumo: ");
        registos[CONSUMO] = lerString();
        System.out.print("Autonomia no momento"+RED+"(em km)"+RESET+": ");
        registos[AUTONOMIA] = lerString();
        return registos;
    }


    public void lista(String[] elementos){
        clear(ECRA);
        ban();
        mudarLinha();
        int i=0;
        for (String s : elementos){
            printOpcao(i,elementos[i]);
            i++;
        }
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        System.out.println("     Escolha: ");

    }


    public void erroFicheiro(){
        red();
        System.out.println("Erro ao carregar/escrever ficheiro");
        resetColor();
        enterContinuar();
    }

    void enterContinuar() {
        Scanner s=new Scanner(System.in);
        System.out.print("Precione enter para continuar . . . ");
        s.nextLine();
    }

    public String lerString() {
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
}

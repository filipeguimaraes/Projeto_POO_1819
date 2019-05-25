import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
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

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String CYAN_BOLD = "\033[1;36m";   // Cyan Bold
    public static final String CYAN = "\033[0;36m";
    public static final int CLIENTE = 1;
    public static final int PROPRIETARIO = 2;

    public static final int EMAIL=0;
    public static final int PASS=1;
    public static final int NIF=2;
    public static final int NOME=3;
    public static final int MORADA=4;
    public static final int DATA=5;

    public static final int MARCA=0;
    public static final int MATRICULA=1;
    public static final int VELOCIDADE=2;
    public static final int PRECO=3;
    public static final int LOCALIZACAO=4;
    public static final int CONSUMO=5;
    public static final int AUTONOMIA=6;
    public static final int TIPOCARRO=7;

    public static final int DESTINO=0;
    public static final int CARRO=1;
    public static final int DISTANCIA=2;
    public static final int AUTONOMIADESEJADA=3;

    public static final int DATAINICIO=0;
    public static final int DATAFIM=2;

    public static final int COMBUSTIVEL=0;

    public static final int ACEITARREJEITAR=0;

    public static final int CLASSIFICACAO=0;




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

    public void top10() {
        cyan();
        line(2);
        System.out.println("             $$$$$$$$\\  $$$$$$\\  $$$$$$$\\          $$\\   $$$$$$\\  ");
        System.out.println("             \\__$$  __|$$  __$$\\ $$  __$$\\       $$$$ | $$$ __$$\\ ");
        System.out.println("                $$ |   $$ /  $$ |$$ |  $$ |      \\_$$ | $$$$\\ $$ |");
        System.out.println("                $$ |   $$ |  $$ |$$$$$$$  |        $$ | $$\\$$\\$$ |");
        System.out.println("                $$ |   $$ |  $$ |$$  ____/         $$ | $$ \\$$$$ |");
        System.out.println("                $$ |   $$ |  $$ |$$ |              $$ | $$ |\\$$$ |");
        System.out.println("                $$ |    $$$$$$  |$$ |            $$$$$$\\\\$$$$$$  /");
        System.out.println("                \\__|    \\______/ \\__|            \\______|\\______/");
        line(2);
        resetColor();
    }

    public void mainMenu(String[] opcoes){
        clear(ECRA);
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
        String[] registos= new String[8];
        clear(ECRA);
        ban();
        System.out.print("Tipo"+CYAN +"(Electrico|Gasolina|Hibrido)"+RESET+": ");
        registos[TIPOCARRO] = lerString();
        mudarLinha();
        System.out.print("Marca: ");
        registos[MARCA] = lerString();
        mudarLinha();
        System.out.print("Matricula"+RED+" AA-AA-AA "+RESET+": ");
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
        mudarLinha();
        System.out.print("Autonomia no momento"+RED+"(em km)"+RESET+": ");
        registos[AUTONOMIA] = lerString();
        mudarLinha();
        return registos;
    }

    public String[] aluguerCarroEspecifico(){
        String[] aluguer = new String[2];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza o destino da viagem x,y :");
        aluguer[DESTINO] = lerString();
        mudarLinha();
        System.out.print("Introduza o Veiculo que deseja (AA-AA-AA):");
        aluguer[CARRO] = lerString();
        return aluguer;
    }

    public String[] aluguerDestino(){
        String[] aluguer = new String[1];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza o destino da viagem x,y :");
        aluguer[DESTINO] = lerString();
        mudarLinha();
        return aluguer;
    }

    public String[] aluguerDistancia(){
        String[] aluguer = new String[3];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza o destino da viagem x,y :");
        aluguer[DESTINO] = lerString();
        mudarLinha();
        System.out.print("Introduza a distância que está disposto a percorrer a pé (em km):");
        aluguer[DISTANCIA] = lerString();
        return aluguer;
    }

    public String[] aluguerAutonomia(){
        String[] aluguer = new String[4];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza o destino da viagem x,y :");
        aluguer[DESTINO] = lerString();
        mudarLinha();
        System.out.print("Introduza a autonomia desejada (em km):");
        aluguer[AUTONOMIADESEJADA] = lerString();
        return aluguer;
    }

    public String listaCarroAutonomia(List<String> carros){
        clear(ECRA);
        cyan();
        line(1);
        System.out.println("                                  Carros com a autonomia desejada");
        line(1);
        resetColor();
        mudarLinha();
        int i=1;
        for (String c : carros){
            printOpcao(i,c);
            i++;
        }
        cyan();
        line(1);
        resetColor();
        System.out.print("Introduza a matrícula: ");
        return lerString();
    }

    public void imprimeCusto(double custo){
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Este aluguer vai ter um custo de "+df.format(custo)+"€");
        System.out.println(RED+"Espere pela confimação do proprietario."+RESET);
        System.out.println(CYAN+"Obrigado por preferir a UMCarroJá!"+RESET);
    }


    public void imprimeCarro(String matricula){
        System.out.println("O Veiculo que satisfaz o seu pedido é "+CYAN_BOLD+matricula+RESET);
    }

    @SuppressWarnings("Duplicates")
    public void printExcecoes(List<String> ex){
        clear(ECRA);
        cyan();
        line(1);
        System.out.println("                        Logs do carregamento do ficheiro");
        line(1);
        resetColor();
        mudarLinha();
        int i=1;
        for (String s : ex) {
            printOpcao(i, s);
            i++;
        }
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        enterContinuar();
    }

    @SuppressWarnings("Duplicates")
    public void listaCarros(List<String> elementos){
        clear(ECRA);
        ban();
        int number= ((ECRA - 25) - elementos.size()) / 2;
        clear(number);
        mudarLinha();
        int i=1;
        for (String s : elementos) {
            printOpcao(i, s);
            i++;
        }
        clear(number);
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        enterContinuar();
    }

    @SuppressWarnings("Duplicates")
    public String[] datasAlugueres(){
        String[] aluguer = new String[3];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a data inicial (DD/MM/AAAA) :");
        aluguer[DATAINICIO] = lerString();
        mudarLinha();
        System.out.print("Introduza a data final (DD/MM/AAAA) :");
        aluguer[DATAFIM] = lerString();
        mudarLinha();
        return aluguer;
    }

    @SuppressWarnings("Duplicates")
    public void listaAlugueres(List<String> ls){
        clear(ECRA);
        cyan();
        line(1);
        System.out.println("                             Lista dos alugueres efetuados entre datas");
        line(1);
        resetColor();
        mudarLinha();
        int i=1;
        for (String s : ls) {
            printOpcao(i, s);
            i++;
        }
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        enterContinuar();
    }

    public String[] totalFaturado(){
        String[] fatura = new String[3];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a matrícula:");
        fatura[MATRICULA] = lerString();
        mudarLinha();
        System.out.print("Introduza a data inicial (DD/MM/AAAA) :");
        fatura[DATAINICIO] = lerString();
        mudarLinha();
        System.out.print("Introduza a data final (DD/MM/AAAA) :");
        fatura[DATAFIM] = lerString();
        mudarLinha();
        return fatura;
    }

    public void imprimeTotalFaturado(String matricula, double faturado){
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("O Veiculo "+matricula+" faturou, no período de tempo fornecido :"+df.format(faturado)+"€");
    }


    public void erroFicheiro(){
        red();
        System.out.println("Erro ao carregar/escrever ficheiro");
        resetColor();
        enterContinuar();
    }

    void enterContinuar() {
        Scanner s=new Scanner(System.in);
        System.out.print("Pressione enter para continuar . . . ");
        s.nextLine();
    }

    public void imprimeTop10(List<Integer> nifs){
        clear(ECRA);
        top10();
        int number= ((ECRA - 18) - nifs.size()) / 2;
        clear(number);
        int i = 1;
        for(Integer nif : nifs){
            printOpcao(i,String.valueOf(nif));
            i++;
        }
        clear(number);
        cyan();
        line(1);
        resetColor();
        enterContinuar();
    }

    @SuppressWarnings("Duplicates")
    public String[] alteraPreco(){
        String[] preco = new String[4];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a matrícula:");
        preco[MATRICULA] = lerString();
        mudarLinha();
        System.out.print("Introduza o novo preço (em €/km) :");
        preco[PRECO] = lerString();
        mudarLinha();
        return preco;
    }

    public void precoAlterado(){
        System.out.println(RED+"Preço alterado!"+RESET);
        enterContinuar();
    }

    @SuppressWarnings("Duplicates")
    public String[] abasteceCarro(){
        String[] abastece = new String[4];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a matrícula:");
        abastece[MATRICULA] = lerString();
        mudarLinha();
        System.out.print("Introduza o tipo de combustível "+RED+"(Gasolina/Eletricidade)"+RESET+":");
        abastece[COMBUSTIVEL] = lerString();
        mudarLinha();
        return abastece;
    }

    public void carroAbastecido(double autonomia){
        line(1);
        System.out.println("Veiculo abastecido!");
        System.out.println("Autonomia atual "+autonomia);
        line(1);
        enterContinuar();
    }

    @SuppressWarnings("Duplicates")
    public String[] tratarAlugueres(List<String> ls){
        String[] campos = new String[3];
        clear(ECRA);
        cyan();
        line(1);
        System.out.println("                                Lista dos alugueres pendentes");
        line(1);
        resetColor();
        mudarLinha();
        int i=1;
        for (String s : ls) {
            printOpcao(i, s);
            i++;
        }
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        System.out.print("Introduza o nif do cliente referente ao aluguer a tratar:");
        campos[NIF] = lerString();
        mudarLinha();
        System.out.print("Introduza (Aceitar/Rejeitar):");
        campos[ACEITARREJEITAR] = lerString();
        mudarLinha();
        return campos;
    }

    public void verInformacoes(String nome, String email, int nif, String morada, LocalDateTime dataNascimento, double classificacao, int numeroAlugueres){
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print(CYAN_BOLD+"Nome :    "+RESET+nome);
        mudarLinha();
        System.out.print(CYAN_BOLD+"Email :    "+RESET+email);
        mudarLinha();
        System.out.print(CYAN_BOLD+"Nif :    "+RESET+nif);
        mudarLinha();
        System.out.print(CYAN_BOLD+"Morada :    "+RESET+morada);
        mudarLinha();
        System.out.print(CYAN_BOLD+"Data de nascimento :    "+RESET+dataNascimento.toString());
        mudarLinha();
        System.out.print(CYAN_BOLD+"Classificação :    "+RESET+classificacao);
        mudarLinha();
        System.out.print(CYAN_BOLD+"Numero de alugueres efetuados :    "+RESET+numeroAlugueres);
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        enterContinuar();
    }

    @SuppressWarnings("Duplicates")
    public String[] classificarCarro(){
        String[] campos = new String[2];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a matrícula:");
        campos[MATRICULA] = lerString();
        mudarLinha();
        System.out.print("Introduza a classificação (0/100):");
        campos[CLASSIFICACAO] = lerString();
        mudarLinha();
        return campos;
    }

    public String[] classificarAtor(){
        String[] campos = new String[3];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza o nif:");
        campos[NIF] = lerString();
        mudarLinha();
        System.out.print("Introduza a classificação (0/100):");
        campos[CLASSIFICACAO] = lerString();
        mudarLinha();
        return campos;
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

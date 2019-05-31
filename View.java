import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * View, imprime os menus do sistema
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public class View {
    private int ator;

    public static int ECRA=42; //Tamanho do Ecra

    public static String LINE =
            "#################################################################################################";


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

    public static final int PRECIPITACAO=0;
    public static final int TEMPERATURA=1;
    public static final int VELOCIDADEVENTO=2;



    public View(){
        this.ator=0;
    }

    public int getAtor() {
        return ator;
    }

    public void setAtor(int ator) {
        this.ator = ator;
    }

    /**
     * Define o utilizador como cliente
     */
    public void souCliente(){
        this.ator=CLIENTE;
    }

    /**
     * Define o utilizador como cliente
     */
    public void souProprietario(){
        this.ator=PROPRIETARIO;
    }

    /**
     * Avança o numero de linhas introduzidas
     * @param number Numero de linhas
     */
    public void clear(int number){
        for (int i = 0; i < number; ++i) System.out.println();
    }

    /**
     * Impre um determinado número de linhas de separação
     * @param numeroDeLinhas Número de linhas
     */
    public void line(int numeroDeLinhas){
        for (int i=0;i<numeroDeLinhas;i++) System.out.println(LINE);
    }

    /**
     * Avança uma linha
     */
    public void mudarLinha(){
        System.out.println();
    }

    /**
     * Texto a azul
     */
    public void cyan(){
        System.out.println("\033[0;36m");
    }

    /**
     * Texto a vermelho
     */
    public void red(){
        System.out.println("\033[0;31m");
    }

    /**
     * Volta a colocar a cor "normal"
     */
    public void resetColor(){
        System.out.println("\033[0m");
    }

    /**
     * Formata uma opção
     * @param i Numero da opção
     * @param s Opção
     */
    public void printOpcao(int i,String s){
        System.out.println("     "+CYAN_BOLD+i+")"+RESET+"  "+s);
    }

    /**
     * Banner do projeto
     */
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

    /**
     * Banner do fim do projeto
     */
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

    /**
     * Banner para o top 10
     */
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

    /**
     * Imprime as opções fornecidas
     * @param opcoes Opções
     */
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

    /**
     * Imprime o menu de login
     * @return Strings dos campos do login
     */
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

    /**
     * Imprime o menu de registo para um utilizador
     * @return Strings dos campos do registo
     */
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

    /**
     * Imprime o menu de registo para um carro
     * @return Strings dos campos do registo
     */
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

    /**
     * Menu para escolher uma carro especifico
     * @return Destino da vigem e matricula
     */
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

    /**
     * Menu para escolher o destino da viagem
     * @return Destino da vigem
     */
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

    /**
     * Imprime menu da distancia que está diposto a percorrer a pé
     * @return Destino e distancia
     */
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

    /**
     * Menu para escolher uma carro  com uma autonomia especifica
     * @return Destino da vigem e autonomia desejada
     */
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

    /**
     * Imprime uma lista de carro com autonomia desejada
     * @param carros Lista de carros
     * @return MAtricula
     */
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

    /**
     * Imprime o cust de um aluguer
     * @param custo Custo de um aluguer
     */
    public void imprimeCusto(double custo){
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Este aluguer vai ter um custo de "+df.format(custo)+"€");
        System.out.println(RED+"Espere pela confimação do proprietario."+RESET);
        System.out.println(CYAN+"Obrigado por preferir a UMCarroJá!"+RESET);
    }

    /**
     * Imprime o carro que satisfaz o pedido
     * @param matricula MAtricula do carro
     */
    public void imprimeCarro(String matricula){
        System.out.println("O Veiculo que satisfaz o seu pedido é "+CYAN_BOLD+matricula+RESET);
    }

    /**
     * Imprime a liste de exceções do carregamento inicial
     * @param ex Lista de exceções
     */
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

    /**
     * Imprime uma lista de carros
     * @param elementos Lista de carros
     */
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

    /**
     * Imprime o menu para escolher o período de alugueres
     * @return Data inicial e data final
     */
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

    /**
     * Imprime uma liste de alugueres
     * @param ls Lista de alugueres
     */
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

    /**
     * Imprime o menu para introdução de dados do total faturado
     * @return Matricula do carro, Data de início e Data de fim
     */
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

    /**
     * Imprime o total faturado por um veículo
     * @param matricula Matricula
     * @param faturado Total faturado
     */
    public void imprimeTotalFaturado(String matricula, double faturado){
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("O Veiculo "+matricula+" faturou, no período de tempo fornecido :"+df.format(faturado)+"€");
    }

    /**
     * Imprime erro de carregar/escrever ficheiro
     */
    public void erroFicheiro(){
        red();
        System.out.println("Erro ao carregar/escrever ficheiro");
        resetColor();
        enterContinuar();
    }

    /**
     * Imprime o "enter para continuar"
     */
    public void enterContinuar() {
        Scanner s=new Scanner(System.in);
        System.out.print("Pressione enter para continuar . . . ");
        s.nextLine();
    }

    /**
     * Imprime o top 10
     * @param nifs Lista de clientes
     */
    @SuppressWarnings("Duplicates")
    public void imprimeTop10(List<String> nifs){
        clear(ECRA);
        top10();
        int number= ((ECRA - 18) - nifs.size()) / 2;
        clear(number);
        int i = 1;
        for(String nif : nifs){
            printOpcao(i,nif);
            i++;
        }
        clear(number);
        cyan();
        line(1);
        resetColor();
        enterContinuar();
    }

    /**
     * Imprime o menu para introduzir dados para alterar o preço de um veículo
     * @return Matrícula e preço
     */
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

    /**
     * Imprime "Preço alterado"
     */
    public void precoAlterado(){
        System.out.println(RED+"Preço alterado!"+RESET);
        enterContinuar();
    }

    /**
     * Imprime menu para receber dados do carro a abastecer
     * @return Matricula e tipo de combustivel
     */
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

    /**
     * Imprime se o carro foi abastecido e a autonomia
     * @param autonomia Autonomia
     */
    public void carroAbastecido(double autonomia){
        line(1);
        System.out.println("Veiculo abastecido!");
        System.out.println("Autonomia atual "+autonomia);
        line(1);
        enterContinuar();
    }

    /**
     * Imprime os alugueres pendentes e recebe os dados de qual quer tratar
     * @param ls Lista de alugueres pendentes
     * @return Nif do cliente e a escolha
     */
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

    /**
     * Imprime o perfil
     * @param nome Nome do utilizador
     * @param email Email do utilizador
     * @param nif Nif do utilizador
     * @param morada Moradado utilizador
     * @param dataNascimento Data de nascimento do utilizador
     * @param classificacao Classificação média do utilizador
     * @param numeroAlugueres Número de alugueres realizados pelo utilizador
     */
    public void verInformacoes(String nome, String email, int nif, String morada, LocalDateTime dataNascimento,
                               double classificacao, int numeroAlugueres){
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

    /**
     * Imprime o menu que recebe a classificação do carro
     * @return Matricula e classificação
     */
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

    /**
     * Imprime o menu que recebe a classificação de um utilizador
     * @return Nif e classificação
     */
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

    /**
     * Imprime menu que recebe as informações metereologicas
     * @return Precipitacao, Velocidade e Temperatura
     */
    public String[] introduzirMeteo(){
        String[] campos = new String[3];
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a precipitação atual (em %):");
        campos[PRECIPITACAO] = lerString();
        mudarLinha();
        System.out.print("Introduza a velocidade do vento (em km/h):");
        campos[VELOCIDADE] = lerString();
        mudarLinha();
        System.out.print("Introduza a temperatura (em ºC):");
        campos[TEMPERATURA] = lerString();
        mudarLinha();
        return campos;
    }

    /**
     * Imprime menu que recebe a localização atual do cliente
     * @return Localização
     */
    public String localizacaoAtual(){
        clear(ECRA);
        ban();
        mudarLinha();
        System.out.print("Introduza a sua localização atual "+RED+"x,y"+RESET+":");
        String loc=lerString();
        mudarLinha();
        cyan();
        line(1);
        resetColor();
        return loc;
   }

    /**
     * Método para ler strings
     * @return String lida
     */
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

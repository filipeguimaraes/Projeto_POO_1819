import java.awt.geom.Point2D;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
        String[] opcoes={"Carregar ficheiro de inicio",
                "Carregar estado anterior",
                "Introduzir informações meteorológicas",
                "Guardar estado",
                "Entrar no programa",
                "Extras",
                "Sair"};
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
                        backup.carregaFicheiro(this.servico);
                        view.printExcecoes(backup.getExcecoes());
                    } catch (IOException e){
                        view.erroFicheiro();
                        view.enterContinuar();
                        escolha=0;
                        continue;
                    }
                    escolha=0;
                    break;
                //carregar estado anterior
                case 2:
                    try {
                        this.servico = Carregamento.lerFicheiroObjeto(OBJ_PATH);
                        view.mainMenu(opcoes);
                        escolha=0;
                    } catch (IOException e){
                        view.erroFicheiro();
                        escolha=0;
                        continue;
                    } catch (ClassNotFoundException e){
                        System.out.println("Erro: "+e.getMessage());
                        view.enterContinuar();
                        escolha = 0;
                        continue;
                    }
                    break;
                case 3:
                    String[] campos3 = view.introduzirMeteo();
                    double temperatura = Double.valueOf(campos3[View.TEMPERATURA]);
                    double precipitacao = Double.valueOf(campos3[View.PRECIPITACAO]);
                    double velocidadevento = Double.valueOf(campos3[View.VELOCIDADEVENTO]);
                    servico.introduzMeteorologia(precipitacao,temperatura,velocidadevento);
                    escolha = 0;
                    break;
                // Gravar estado
                case 4:
                    try {
                        Carregamento.escreverFicheiroOjeto(this.servico,OBJ_PATH);
                        System.out.println("Guardado com sucesso");
                    } catch (IOException e){
                        System.out.println(View.RED+"Erro ao escrever ficheiro: "+View.RESET+e.getMessage());
                    }
                    view.enterContinuar();
                    escolha=0;
                    break;
                case 5:
                    escolha=runEscolherAtor();
                    break;
                case 6:
                    escolha=runExtras();
                    break;
                default:
                    System.out.println("Ocorreu um erro.");
                    view.enterContinuar();
                    escolha=0;
                    break;
            }
        } while (escolha!=7);
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
                    break;
                case 2 :
                    view.souProprietario();
                    escolha = runProprietario();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ocorreu um erro.");
                    view.enterContinuar();
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
                        System.out.println(e.getMessage());
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
                    LocalDateTime data = stringParaData(registo[View.DATA]);

                    if(view.getAtor()==View.CLIENTE){
                        try{
                            servico.adicionaCliente(email,pass,nifi,nome,morada,data);
                            nif=nifi;
                            escolha=0;
                        } catch (AtorException a) {
                            System.out.println(a.getMessage());
                            view.enterContinuar();
                        }
                    }
                    if (view.getAtor()==View.PROPRIETARIO){
                        try {
                            servico.adicionaProprietario(email,pass,nifi,nome,morada,data);
                            nif=nifi;
                            escolha=0;
                        }catch (AtorException a){
                            System.out.println(a.getMessage());
                            view.enterContinuar();
                        }
                    }
                    break;
                case 3:
                    nif=-1;
                   break;
                default:
                    System.out.println("Ocorreu um erro.");
                    view.enterContinuar();
                    break;
            }
        }while (escolha!=0 && escolha!=3);
        return nif;
    }

    @SuppressWarnings("Duplicates")
    private int runCliente(){
        String[] opcoes = {"Realizar aluguer",
                "Lista dos Alugueres efetuados entre datas",
                "Ver Perfil",
                "Classificar Proprietario",
                "Classificar Veiculo",
                "Terminar sessão"};
        int nif=runLogin();
        int escolha=0;
        if(nif==-1) return -1;
        if(nif!=0){
            do{
                view.mainMenu(opcoes);
                escolha = lerInt();
                switch (escolha){
                    case 1:
                        escolha=runAluguer(nif);
                        break;
                    case 2:
                        String[] datas = view.datasAlugueres();
                        LocalDateTime inicio = stringParaData(datas[View.DATAINICIO]);
                        LocalDateTime fim = stringParaData(datas[View.DATAFIM]);
                        view.listaAlugueres(servico.alugueresEntreDatasCliente(nif,inicio,fim));
                        break;
                    case 3:
                        try {
                            view.verInformacoes(servico.verNome(nif),servico.verEmail(nif),servico.verNif(nif),
                                    servico.verMorada(nif),servico.verDataNascimente(nif),servico.verClassificacao(nif),
                                    servico.verNumeroAluguer(nif));
                        } catch (AtorException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 4:
                        String[] campos4 = view.classificarAtor();
                        int nif4 = Integer.valueOf(campos4[View.NIF]);
                        int classificacao4 = Integer.valueOf(campos4[View.CLASSIFICACAO]);
                        try {
                            servico.classificaProprietario(nif,nif4,classificacao4);
                        } catch (AtorException|AluguerException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 5:
                        String[] campos5 = view.classificarCarro();
                        String matricula5 = campos5[View.MATRICULA];
                        int classificacao5 = Integer.valueOf(campos5[View.CLASSIFICACAO]);
                        try {
                            servico.classificaCarro(nif,matricula5,classificacao5);
                        } catch (CarroException|AluguerException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Ocorreu um erro.");
                        view.enterContinuar();
                        break;
                }
            }while (escolha!=6);

        }else System.out.println("Cliente Invalido");
        return -1;
    }

    @SuppressWarnings("Duplicates")
    private int runAluguer(int nif){
        String[] opcoes = {"Veiculo Específico",
                "Veiculo mais barato",
                "Veiculo mais proximo",
                "Veiculo mais barato dentro de uma determinada distância",
                "Veiculo com uma autonomia desejada",
                "Terminar sessão"};
        int escolha=0;
            do{
                view.mainMenu(opcoes);
                escolha = lerInt();
                switch (escolha){
                    case 1:
                        String[] campos1 = view.aluguerCarroEspecifico();
                        try {
                            Point2D destino1 = stringParaPonto(campos1[View.DESTINO]);
                            String matricula = campos1[View.CARRO];
                            try {
                                view.imprimeCusto(servico.pedidoAluguer(nif, destino1, matricula));
                                view.enterContinuar();
                                escolha = 0;
                            } catch (AtorException | AluguerException | CarroException e) {
                                System.out.println(e.getMessage());
                                view.enterContinuar();
                                escolha = 1;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            System.out.println("Formato errado:"+e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 2:
                        String[] campos2 = view.aluguerDestino();
                        try {
                            Point2D destino2 = stringParaPonto(campos2[View.DESTINO]);
                            try {
                                view.imprimeCarro(servico.carroMaisBarato());
                                view.imprimeCusto(servico.pedidoAluguer(nif, destino2, servico.carroMaisBarato()));
                                view.enterContinuar();
                                escolha = 0;
                            } catch (CarroException | AluguerException | AtorException e) {
                                System.out.println(e.getMessage());
                                view.enterContinuar();
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                                System.out.println("Formato errado:"+e.getMessage());
                                view.enterContinuar();
                                continue;
                        }
                        break;
                    case 3:
                        String[] campos3 = view.aluguerDestino();

                        try {
                            Point2D destino3 = stringParaPonto(campos3[View.DESTINO]);
                            try {
                                view.imprimeCarro(servico.carroMaisProximo(nif));
                                view.imprimeCusto(servico.pedidoAluguer(nif, destino3, servico.carroMaisProximo(nif)));
                                view.enterContinuar();
                                escolha = 0;
                            } catch (CarroException | AluguerException | AtorException e) {
                                System.out.println(e.getMessage());
                                view.enterContinuar();
                            }
                        }catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Formato errado:" + e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 4:
                        String[] campos4 = view.aluguerDistancia();
                        try {
                            Point2D destino4 = stringParaPonto(campos4[View.DESTINO]);
                            double distancia = Double.parseDouble(campos4[View.DISTANCIA]);
                            try {
                                view.imprimeCarro(servico.carroProximoMaisBarato(nif, distancia));
                                view.imprimeCusto(servico.pedidoAluguer(nif, destino4, servico.carroProximoMaisBarato(nif, distancia)));
                                view.enterContinuar();
                                escolha = 0;
                            } catch (CarroException | AluguerException | AtorException e) {
                                System.out.println(e.getMessage());
                                view.enterContinuar();
                            }
                        }catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Formato errado:" + e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 5:
                        String[] campos5 = view.aluguerAutonomia();
                        try {
                            Point2D destino5 = stringParaPonto(campos5[View.DESTINO]);
                            double autonomia = Double.parseDouble(campos5[View.AUTONOMIADESEJADA]);
                            String matricula5 = view.listaCarroAutonomia(servico.carroAutonomiaDesejada(autonomia));
                            try {
                                view.imprimeCusto(servico.pedidoAluguer(nif, destino5, matricula5));
                                view.enterContinuar();
                                escolha = 0;
                            } catch (AtorException | AluguerException | CarroException e) {
                                System.out.println(e.getMessage());
                                view.enterContinuar();
                                escolha = 1;
                            }
                        }catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Formato errado:" + e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Ocorreu um erro.");
                        view.enterContinuar();
                        break;
                }
            }while (escolha!=6);
        return -1;
    }

    private int runProprietario(){
        String[] opcoes = {"Registar carro",
                "Lista de carros registados",
                "Lista de alugueres efetuados entre datas",
                "Alterar o preço por Km de um carro registado",
                "Abastecer veículo",
                "Aceitar/Rejeitar alugueres",
                "Ver Perfil",
                "Classificar Cliente",
                "Veículos com autonomia inferior a 10",
                "Terminar sessão"};
        int nif=runLogin();
        int escolha=0;
        if (nif==-1) return -1;
        if(nif!=0){
            do{
                view.mainMenu(opcoes);
                escolha = lerInt();
                switch (escolha){
                    case 1:
                        String[] registo = view.registarCarroMenu();
                        String tipo = registo[View.TIPOCARRO];
                        String marca = registo[View.MARCA];
                        String matricula = registo[View.MATRICULA];
                        int velocidade = Integer.valueOf(registo[View.VELOCIDADE]);
                        double preco = Double.parseDouble(registo[View.PRECO]);
                        try {
                            Point2D localizacao = stringParaPonto(registo[View.LOCALIZACAO]);
                            double consumo = Double.parseDouble(registo[View.CONSUMO]);
                            double autonomia = Double.parseDouble(registo[View.AUTONOMIA]);
                            if (tipo.contains("Electrico")) {
                                try {
                                    servico.adicionaCarroEletrico(marca, matricula, nif, velocidade, preco, localizacao, consumo, autonomia);
                                } catch (CarroException | AtorException e) {
                                    System.out.println(e.getMessage());
                                    view.enterContinuar();
                                }
                            } else if (tipo.contains("Gasolina")) {
                                try {
                                    servico.adicionaCarroGasolina(marca, matricula, nif, velocidade, preco, localizacao, consumo, autonomia);
                                } catch (CarroException | AtorException e) {
                                    System.out.println(e.getMessage());
                                    view.enterContinuar();
                                }
                            } else if (tipo.contains("Hibrido")) {
                                try {
                                    servico.adicionaCarroHibrido(marca, matricula, nif, velocidade, preco, localizacao, consumo, autonomia);
                                } catch (CarroException | AtorException e) {
                                    System.out.println(e.getMessage());
                                    view.enterContinuar();
                                }
                            }
                        }catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Formato errado:" + e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 2:
                        try {
                            List<String> lista=servico.listaCarrosProprietario(nif);
                            System.out.println(lista);
                            view.listaCarros(lista);
                        }catch (AtorException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                        }
                        break;
                    case 3:
                            String[] datas = view.datasAlugueres();
                            LocalDateTime inicio = stringParaData(datas[View.DATAINICIO]);
                            LocalDateTime fim = stringParaData(datas[View.DATAFIM]);
                            view.listaAlugueres(servico.alugueresEntreDatasProprietario(nif,inicio,fim));
                            break;
                    case 4:
                        String[] campos4 = view.alteraPreco();
                        String matricula4 = campos4[View.MATRICULA];
                        double preco4  = Double.parseDouble(campos4[View.PRECO]);
                        try {
                            servico.procuraProprietario(nif).alteraPreco(preco4,matricula4);
                            view.precoAlterado();
                        } catch (AtorException|CarroException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 5:
                        String[] campos5 = view.abasteceCarro();
                        String matricula5 = campos5[View.MATRICULA];
                        String tipoCombustivel = campos5[View.COMBUSTIVEL];
                        try{
                            view.carroAbastecido(servico.procuraCarro(matricula5).abasteceCarro(tipoCombustivel));
                        }catch (CarroException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 6:
                        try {
                            List<String> la = servico.procuraProprietario(nif).listaAlugueresPendentes();
                            String[] campos6 = view.tratarAlugueres(la);
                            int nifCliente = Integer.valueOf(campos6[View.NIF]);
                            String estado = campos6[View.ACEITARREJEITAR];
                            try {
                                servico.terminaAluguer(estado,nifCliente);
                            }catch(CarroException e){
                                System.out.println(e.getMessage());
                                view.enterContinuar();
                                continue;
                            }
                        }catch (AtorException | AluguerException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 7:
                        try {
                            view.verInformacoes(servico.verNome(nif),servico.verEmail(nif),servico.verNif(nif),
                                    servico.verMorada(nif),servico.verDataNascimente(nif),servico.verClassificacao(nif),
                                    servico.verNumeroAluguer(nif));
                        } catch (AtorException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 8:
                        String[] campos8 = view.classificarAtor();
                        int nif8 = Integer.valueOf(campos8[View.NIF]);
                        int classificacao8 = Integer.valueOf(campos8[View.CLASSIFICACAO]);
                        try {
                            servico.classificaCliente(nif,nif8,classificacao8);
                        } catch (AtorException|AluguerException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;
                    case 9:
                        try{
                            view.listaCarros(servico.listaCarrosAtonomiaDez(nif));
                        }catch(AtorException e){
                            System.out.println(e.getMessage());
                            view.enterContinuar();
                            continue;
                        }
                        break;

                    case 10:
                        break;
                    default:
                        System.out.println("Ocorreu um erro.");
                        view.enterContinuar();
                        break;
                }
            }while (escolha!=10);

        }else System.out.println("Cliente Invalido");
        return -1;
    }

    public int runExtras(){
        String[] opcoes = {"Total faturado por uma viatura num determinado período",
                "Top 10 clientes que mais utilizam o sistema em número de vezes",
                "Top 10 clientes que mais utilizam o sistema em Kms percorridos",
                "Menu principal"};
        int escolha=0;
        do{

            view.mainMenu(opcoes);
            escolha = lerInt();
            switch (escolha){
                case 1:
                    String[] campos3 = view.totalFaturado();
                    String matricula3 = campos3[View.MATRICULA];
                    LocalDateTime datainicio = stringParaData(campos3[View.DATAINICIO]);
                    LocalDateTime datafim = stringParaData(campos3[View.DATAFIM]);
                    try {
                        view.imprimeTotalFaturado(matricula3,servico.totalFaturadoPeriodo(matricula3,datainicio,datafim));
                        view.enterContinuar();
                    }catch (CarroException e){
                        System.out.println(e.getMessage());
                        view.enterContinuar();
                        escolha=1;
                        view.enterContinuar();
                        continue;
                    }
                    break;
                case 2:
                    view.imprimeTop10(servico.dezClientesNVezes());
                    break;
                case 3:
                    view.imprimeTop10(servico.dezClientesKms());
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Ocorreu um erro.");
                    view.enterContinuar();
                    break;
            }
        }while(escolha!=4);
        return 0;
    }

    public LocalDateTime stringParaData(String dma){
        String[] dataf = dma.split("/");
        int dia = Integer.valueOf(dataf[0]);
        int mes = Integer.valueOf(dataf[1]);
        int ano = Integer.valueOf(dataf[2]);
        return LocalDateTime.of(ano,mes,dia,0,0);
    }

    public Point2D stringParaPonto(String xy)throws ArrayIndexOutOfBoundsException{
        String[] locs = xy.split(",");
        double x = Double.parseDouble(locs[0]);
        double y = Double.parseDouble(locs[1]);
        return new Point2D.Double(x,y);
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

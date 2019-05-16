import java.util.ResourceBundle;

/**
 * Write a description of class View here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View
{
    // Line
    public static String LINE = "#################################################################################################";
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public void clear(int number){
        for (int i = 0; i < number; ++i) System.out.println();
    }

    public void line(){
        System.out.println(LINE);
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
        line();
        line();
        System.out.println("$$\\   $$\\ $$\\      $$\\  $$$$$$\\                                             $$$$$\\           $$\\ ");
        System.out.println("$$ |  $$ |$$$\\    $$$ |$$  __$$\\                                            \\__$$ |          $$ | ");
        System.out.println("$$ |  $$ |$$$$\\  $$$$ |$$ /  \\__| $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\        $$ | $$$$$$\\  $$ | ");
        System.out.println("$$ |  $$ |$$\\$$\\$$ $$ |$$ |       \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\       $$ | \\____$$\\ $$ | ");
        System.out.println("$$ |  $$ |$$ \\$$$  $$ |$$ |       $$$$$$$ |$$ |  \\__|$$ |  \\__|$$ /  $$ |$$\\   $$ | $$$$$$$ |\\__| ");
        System.out.println("$$ |  $$ |$$ |\\$  /$$ |$$ |  $$\\ $$  __$$ |$$ |      $$ |      $$ |  $$ |$$ |  $$ |$$  __$$ |    ");
        System.out.println("\\$$$$$$  |$$ | \\_/ $$ |\\$$$$$$  |\\$$$$$$$ |$$ |      $$ |      \\$$$$$$  |\\$$$$$$  |\\$$$$$$$ |$$\\ ");
        System.out.println(" \\______/ \\__|     \\__| \\______/  \\_______|\\__|      \\__|       \\______/  \\______/  \\_______|\\__| ");
        line();
        line();
        resetColor();
    }

    public void mainMenu(String[] opcoes){
        ban();
        int i=1;
        for(String s: opcoes){
            printOpcao(i,s);
            i++;
        }
        cyan();
        line();
        resetColor();
        System.out.print("     Opção pretendida: ");
    }

    public void fim(){
        cyan();
        line();
        System.out.println(" $$$$$$\\        $$\\                               $$\\ ");
        System.out.println("$$  __$$\\       $$ |                              $$ |");
        System.out.println("$$ /  $$ | $$$$$$$ | $$$$$$\\  $$\\   $$\\  $$$$$$$\\ $$ |");
        System.out.println("$$$$$$$$ |$$  __$$ |$$  __$$\\ $$ |  $$ |$$  _____|$$ |");
        System.out.println("$$  __$$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |\\$$$$$$\\  \\__|");
        System.out.println("$$ |  $$ |$$ |  $$ |$$   ____|$$ |  $$ | \\____$$\\     ");
        System.out.println("$$ |  $$ |\\$$$$$$$ |\\$$$$$$$\\ \\$$$$$$  |$$$$$$$  |$$\\ ");
        System.out.println("\\__|  \\__| \\_______| \\_______| \\______/ \\_______/ \\__|");
        line();
        clear(17);
        resetColor();
    }

    public void erroFicheiro(){
        red();
        System.out.println("Erro ao carregar/escrever ficheiro");
        resetColor();
        System.out.print("Continuar(1) Sair(-1)");

    }




}

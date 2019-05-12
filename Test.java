import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Test {
    public static void main(String args[]) throws IOException {

        CarregarBAK backup=new CarregarBAK("logsPOO_carregamentoInicial.bak");
        Servico servico = new Servico();
        backup.carregaAtoresCarros(servico);

        System.out.println(servico.toString());
    }

}

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test
{
    public static void main(String args[]) {

        Date date = new Date();

        Ator umAtor = new Ator("fmtfg99@gmail.com", "filipe guimarães", "password", "Rua qualquer", date);
        System.out.println("Ator"+umAtor.toString()+'\n');

        List<Aluguer> historico=new ArrayList<>();
        Proprietario umProprietario= new Proprietario("proprietario@email.com", "prop", "password", "Rua qualquer", date,5,historico);
        System.out.println(umProprietario.toString()+'\n');

        Point coordenadasCliente = new Point(2,4);
        List<Aluguer> historicoCliente=new ArrayList<>();
        Cliente umCliente= new Cliente("cliente@email.com", "cli", "password", "Rua qualquer", date, coordenadasCliente,historicoCliente);
        System.out.println(umCliente.toString());

        Meteorologia hoje= new Meteorologia(30,15,50);
        int minutos= hoje.medicaoMinutos();

        System.out.println("Minutos: "+minutos);


    }
}

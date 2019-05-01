import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test
{
    public static void main(String args[]) {

        Date date = new Date();
        List<Aluguer> aluguerC1= new ArrayList<>();


        Ator umAtor = new Ator("fmtfg99@gmail.com", "filipe guimarães", "password", "Rua qualquer", date);
        //System.out.println("Ator"+umAtor.toString()+'\n');


        List<Aluguer> historico=new ArrayList<>();
        List<Carro> carros =new ArrayList<>(1);
        Point p= new Point(3,5);
        Proprietario umProprietario= new Proprietario("proprietario@email.com", "prop", "password", "Rua qualquer", date,5,historico,carros);
        Carro c1 = new CarroEletrico(20,100,5,p,historico,umProprietario,0.5,200);
        carros.add(c1);
        System.out.println(carros.toString());
        //System.out.println(umProprietario.toString()+'\n');


        Point coordenadasCliente = new Point(2,4);
        List<Aluguer> historicoCliente=new ArrayList<>();
        Cliente umCliente= new Cliente("cliente@email.com", "cli", "password", "Rua qualquer", date, coordenadasCliente,historicoCliente);
        //System.out.println(umCliente.toString());


        Meteorologia hoje= new Meteorologia(30,15,50);
        int minutos= hoje.medicaoMinutos();

        //System.out.println("Minutos: "+minutos);


    }
}

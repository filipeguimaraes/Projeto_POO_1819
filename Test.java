import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class Test
{
    public static void main(String args[]) {


        LocalDateTime date = LocalDateTime.now();
        List<Aluguer> aluguerC1= new ArrayList<>();
        System.out.println(date.toString());

        Ator umAtor = new Ator("fmtfg99@gmail.com", "filipe guimarães", "password", "Rua qualquer", date);
        //System.out.println("Ator"+umAtor.toString()+'\n');


        List<Aluguer> historico=new ArrayList<>();
        List<Carro> carros =new ArrayList<>();

        Point2D p= new Point2D.Double(3.4,5.5);
        Proprietario umProprietario= new Proprietario();
        Carro c1 = new CarroEletrico();
        c1.setVelocidade(20);
        c1.setHistorico(historico);
        umProprietario.setClassificacao(5);
        //c1.setProprietario(umProprietario);
        carros.add(c1.clone());
        umProprietario.setListaCarros(carros);
        System.out.println(carros.toString());
        System.out.println(umProprietario.toString()+'\n');


        Point coordenadasCliente = new Point();
        List<Aluguer> historicoCliente=new ArrayList<>();
        Cliente umCliente= new Cliente();
        //System.out.println(umCliente.toString());


        Meteorologia hoje= new Meteorologia(30,15,50);
        int minutos= hoje.medicaoMinutos();

        //System.out.println("Minutos: "+minutos);


    }
}

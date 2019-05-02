import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class Test
{
    public static void main(String args[]) {
        LocalDateTime date = LocalDateTime.now();
        //System.out.println(date.toString());

        Ator umAtor = new Ator("fmtfg99@gmail.com", "filipe guimarães", "password", "Rua qualquer", date);
        //System.out.println('\n'+umAtor.toString()+'\n');

        /*
        Proprietario umProprietario= new Proprietario();
        umProprietario.setEmail("ola@gmail.com");
        umProprietario.setNome("António");
        umProprietario.setPassword("bolodearroz");
        umProprietario.setMorada("Rua");
        umProprietario.setData(date);
        umProprietario.setClassificacao(5);
        //System.out.println(umProprietario.toString()+'\n');
*/
        Proprietario umProprietario= new Proprietario("asdfg@asdfgh.com","Antonio","password","Rua",date,5,new ArrayList<>(),new ArrayList<>());

        //Carro c1
        Point2D p1= new Point2D.Double(3.4,5.5);
        CarroEletrico c1 = new CarroEletrico(20,100,0,p1,new ArrayList<>(),umProprietario,0.5,50);
        umProprietario.getListaCarros().add(c1.clone());
        //System.out.println(c1.toString());
        System.out.println(umProprietario.toString());


        //Carro c2
        CarroEletrico c2 = new CarroEletrico();
        c2.setVelocidade(50);
        c2.setPreco(180);
        c2.setClassificacao(0);
        Point2D p2= new Point2D.Double(4,10.6);
        c2.setCoordenada(p2);
        c2.setProprietario(umProprietario);
        //System.out.println(c2.toString());


        umProprietario.getListaCarros().add(c1);
        umProprietario.getListaCarros().add(c2);
        //System.out.println(umProprietario.toString()+'\n');


        Servico umServico= new Servico();
        umServico.getCarroseletricos().add(c1);
        umServico.getCarroseletricos().add(c2);

        //System.out.println(umServico.toString());

        //Carro car= umServico.carroMaisBarato();
        //System.out.println(car.toString());

        Point coordenadasCliente = new Point();
        Cliente umCliente= new Cliente();
        //System.out.println(umCliente.toString());

        //System.out.println(c1.getProprietario().getListaCarros().toString());


        Meteorologia hoje= new Meteorologia(30,15,50);
        int minutos= hoje.medicaoMinutos();

        //System.out.println("Minutos: "+minutos);





    }
}

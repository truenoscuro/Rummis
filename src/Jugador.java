import java.util.Scanner;

public class Jugador extends Ma {
    Scanner jugar;

    public Jugador(){
       super();
       jugar = new Scanner(System.in);
    }

    //ACCiONS

    public Carta mostrar(){
        System.out.println("Selecciona una carta");
        super.imprimir();
        return super.seleccionar( jugar.nextInt() );
    }




}

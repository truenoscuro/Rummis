import java.util.Scanner;

public class Jugador extends Ma {
    private final Scanner jugar;
    private int puntuacio;
    public Jugador(){
       super();
       puntuacio = 0;
       jugar = new Scanner(System.in);
    }

    // puntuació
    public void resetearPuntuacio(){ puntuacio = 0; }
    public void agregarPuntuacio(int punts){ puntuacio += punts; }
    public int puntuacio() { return puntuacio; }
    
    //ACCiONS
    public Carta mostrar(){
        int i ;
        System.out.println("Selecciona una carta");
        super.imprimir();
        do{ i = jugar.nextInt();
        }while( !super.estaEnRang( i ) );
        return super.seleccionar( i );
    }




}

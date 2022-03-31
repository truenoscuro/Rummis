import java.util.ArrayList;

public class Mazo {

    ArrayList<Carta> mazo;

    public Mazo(){
        mazo = new ArrayList<>();
    }

    public void agregarCarta( Carta carta ){ mazo.add(carta); }


    public void imprimir(){
        for( Carta carta: mazo ){
            carta.imprimir();
            System.out.println();
        }
    }

}

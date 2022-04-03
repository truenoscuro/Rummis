import java.util.ArrayList;

public class tests {
    public static void main(String[] args) {
        Ma ma = new Jugador();
        ma.robar(new Carta(1,1));
        ma.robar(new Carta(5,2));
        ma.robar( new Carta (7,2));
        ma.robar(new Carta(2,3));
        ma.mostrar();
        ma.imprimir();


    }
}

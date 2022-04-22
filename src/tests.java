import Cartes.*;
import Jugador.*;
import Normes.*;

public class tests {
    public static void main(String[] args) throws CloneNotSupportedException {
        Ma grup = new Jugador();

        grup.robar(new CartaFrancesa(1, 1));
        grup.robar(new CartaFrancesa(2,3));
        grup.robar(new CartaFrancesa( 13,2));
        grup.robar(new CartaFrancesa(10,4));

        GCartes grup2 = (GCartes) grup.clone();
        grup2.imprimir();
        grup2.robar(new CartaFrancesa(2,2));
        grup2.imprimir();
        grup.imprimir();
    }
}

import Cartes.*;
import Normes.*;

public class tests {
   public static void main(String[] args) {
        Rummy normes = new Rummy();
        GCartes g = new GCartes();
        g.robar(new CartaFrancesa(13,1));
        g.robar(new CartaFrancesa(12,1));
        g.robar(new CartaFrancesa(11,1));
        g.robar(new CartaFrancesa(10,1));
        g.imprimir();
        if(normes.esJugadaValida(g)) {System.out.println("es valid");}
        g.imprimir();
    }
}

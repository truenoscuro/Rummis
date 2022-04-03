import java.util.ArrayList;

public class tests {
    public static void main(String[] args) {
        GCartes ma = new GCartes() {
            @Override
            public Carta mostrar() {
                return null;
            }
        };
        ma.robar(new Carta(1,1));
        ma.robar(new Carta(5,2));
        ma.robar( new Carta (7,2));
        ma.robar(new Carta(2,3));

        ma.imprimir();


    }
}

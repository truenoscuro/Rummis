import java.util.ArrayList;

public class Ma {

    private final ArrayList<Carta> ma;

    public Ma(){ ma = new ArrayList<>(); }

    //boleans
    public boolean esBuida(){ return ma.isEmpty(); }

    //Acions
    public void robar(Carta carta){ ma.add( carta ); }

    public Carta mostrar(int numCarta){ return ma.get(numCarta); }

    public Carta jugar(int numCarta){
        Carta carta = ma.get(numCarta);
        ma.remove(numCarta);
        return carta;
    }
    // Imprimir ma

    public void imprimir(){
        for(Carta carta: ma) {
            carta.imprimir();
            System.out.print(" ");
        }
    }
}

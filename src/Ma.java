import java.util.ArrayList;

public abstract class Ma {

    private final ArrayList<Carta> ma;

    public Ma(){ ma = new ArrayList<>(); }

    //boleans
    public boolean esBuida(){ return ma.isEmpty(); }
    protected boolean estaEnRang( int i ){ return i>=0 && i<ma.size(); }

    //Acions
    public void robar(Carta carta){ ma.add( carta ); }

    protected Carta seleccionar(int i){ return ma.get(i); };

    public abstract Carta mostrar();

    public void jugar(Carta carta){ ma.remove(carta); }
    // Imprimir ma

    public void imprimir(){
        for(Carta carta: ma) {
            carta.imprimir();
            System.out.print(" ");
        }
        System.out.println();
    }
}

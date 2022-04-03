package Jugador;
import Cartes.Carta;
import Cartes.GCartes;

public abstract class Ma extends GCartes {
    public Ma(){ super(); }

    //Accio
    public abstract void aJugadat();
    public abstract boolean esJugadaInicial();
    public abstract Carta mostrar();
    //puntuacio
    public abstract void resetearPuntuacio();
    public abstract void agregarPuntuacio(int punts);
    public abstract int puntuacio();

}

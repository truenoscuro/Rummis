package Jugador;
import Cartes.Carta;
import Cartes.GCartes;

public abstract class Ma extends GCartes {
    public Ma(){ super(); }
    // pot jugar


    @Override
    public void robar(Carta carta) {
        carta.canviarEstat( true );
        super.robar(carta);
    }

    public abstract void aJugat();
    public abstract boolean esJugadaInicial();
    //Accio
    public abstract Carta mostrar();
    public abstract boolean volJugar(String text);
    //puntuacio
    public abstract void resetearPuntuacio();
    public abstract void agregarPuntuacio(int punts);
    public abstract int puntuacio();

}
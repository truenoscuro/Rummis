package Jugador;
import Cartes.Carta;
import Cartes.GCartes;
import Taula.ZonaJoc;

public abstract class Ma extends GCartes {
    public Ma(){ super(); }
    // pot jugar


    public abstract void aJugat();
    public abstract boolean esJugadaInicial();
    //Accio
    public abstract Carta mostrar();
    public abstract boolean volJugar(String text);
    //puntuacio
    public abstract void resetearPuntuacio();
    public abstract void agregarPuntuacio(int punts);
    public abstract int puntuacio();

    // Zona de Joc
    public abstract ZonaJoc extreure();
    public abstract void afegir(ZonaJoc zona);
}

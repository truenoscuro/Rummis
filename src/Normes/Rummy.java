package Normes;

import Cartes.GCartes;

import java.util.ArrayList;

public class Rummy {


    public int cartesInit(){return 14;} // es el repartir de cartes en quantes començes
    public boolean esGuanyador() {
        return false;
    }

    public boolean esJugadaInicial(ArrayList<GCartes> grups) { return false;}

    public boolean esJugadaValida(ArrayList<GCartes> grups) {
        return false;
    }
    public boolean esEscala(ArrayList<GCartes> grups){ return false;}
    public boolean esMateixNum(ArrayList<GCartes> grups){ return false;}
    /*
    Punts de cartes
    1-7  -> 5 punts
    8-13 -> 10 punts
    Joker -> 0 punts ? no diu res
     */
    public void sumarPuntuacio(GCartes[] jugadors) {

    }
}

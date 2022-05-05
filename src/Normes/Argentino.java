package Normes;

import Jugador.Ma;

public class Argentino extends Rummikub{



    private int punts = 1001;

    protected int puntuacio(){ return punts ;}

    @Override
    public void imprimir() {

    }
    @Override
    public boolean hihaGuanyador( Ma...jugadors ) {
        for(int i = 0; i <jugadors.length ;i++) {
            if (jugadors[i].puntuacio() >= puntuacio()) {
                System.out.println("El guanyador es el jugador " + i);
                return true;
            }
        }
        return false;
    }
}

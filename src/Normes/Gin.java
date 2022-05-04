package Normes;

import Cartes.Carta;
import Jugador.Ma;

public class Gin extends Rummikub{
    private int numCartes = 10;
    private int puntuacio = 101;

    @Override
    public int cartesInit() { return numCartes; }

    @Override
    public boolean hihaGuanyador( Ma...jugadors ) {
        for(int i = 0; i <jugadors.length ;i++) {
            if (jugadors[i].puntuacio() >= puntuacio) {
                System.out.println("El guanyador es el jugador " + i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean esGuanyadorRonda(Ma jugador) throws CloneNotSupportedException {
        // tots els grups ell i despres mirar si es kong o gin si es guanyador li lleves totes les cartes seleccionades
        Ma jugadorAux = (Ma) jugador.clone();



        return  false;
    }

    protected int puntCartes(Carta carta){
        /*
        Punts de cartes
        J,Q,K --> 10
        */
        return switch ( carta.num( ) ){
            case 10,11,13  -> 10;
            default -> carta.num();
        };
    }

    @Override
    public void sumarPuntuacio(Ma... jugadors) { // será el jugador que tengui menos cartes en Ma.

    }

    @Override
    public void imprimir() {

    }
}

package Normes;

import Cartes.Carta;
import Cartes.GCartes;
import Jugador.Ma;
import Taula.ZonaJoc;

public class Argentino extends Rummikub{


    private int numCartes = 9;
    private int punts = 1001;

    protected int puntuacio(){ return punts ;}

    @Override
    public int cartesInit() {return numCartes; }

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

    @Override
    protected int puntCartes(Carta carta) {
        int punt = 5;
        int num = carta.num();
        if ( num > 7 ) punt = 10;
        if ( num == 1 ) punt = 15;
        if ( num == 0 ) punt = 50;
        if ( num == 2 && carta.pes() > 7 ) punt = 20;
        return punt;
    }

    @Override
    public void esComodin(Carta carta) {
        if(carta.num()!=2 && carta.num()!= 0) return;
        carta.canviarPes( super.selectNum() );
    }

    @Override
    public void sumarPuntuacio(Ma... jugadors) throws CloneNotSupportedException {
        // me falta el cas 1 escala
        ZonaJoc zona;
        GCartes grup;
        int total = 0;
        for( Ma jugador:jugadors ){
           zona = jugador.extreure();
           for(int g = 0; g <zona.tamany();g++){
               grup = zona.selectGrup(g);
               for(int c = 0; c <grup.tamanyGrup();c++) {
                   jugador.agregarPuntuacio(puntCartes(grup.seleccionar(c)));
                   total +=1;
               }
           }
           if (total == 10) jugador.agregarPuntuacio(100);
           else if (total > 0 ) jugador.agregarPuntuacio(50);
           total = -100;
        }
        for ( Ma jugador:jugadors)
            for (int c = 0 ;c < jugador.tamanyGrup() ; c++)
                jugador.agregarPuntuacio(-puntCartes( jugador.seleccionar( c ) ));

    }

    @Override
    public void imprimir() {
    }
}

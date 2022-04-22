package Normes;

import Cartes.Carta;
import Cartes.GCartes;
import Jugador.Ma;

public class Rummikub  extends Rummy{
    private int torns = 0;
    private static final int TORNSMAX = 3;

    @Override
    public boolean hihaGuanyador(Ma... jugadors) {
        int index = 0;
        for(int i = 1; i <jugadors.length ;i++)
            if (jugadors[i].puntuacio() > jugadors[index].puntuacio()) index = i;
        if(++torns<TORNSMAX) return false;
        System.out.println("EL guanyador es el jugador "+ index);
        return true;
    }

    @Override
    protected boolean tamanyEscala(int tamany) { return tamany>=4; }


    @Override
    protected void canviarPes(GCartes grup) { }

    @Override
    protected void desferPes(GCartes grup) { }

    @Override
    protected void imprimirSeleccionar() { System.out.println(" Selecciona un numero entre el 1 i el 13 "); }

    @Override
    protected int puntCartes(Carta carta) { return carta.palo() == 0? 30: carta.num(); }

    public void sumarPuntuacio( Ma...jugadors ) {
        Ma jugadorGuanyador = jugadors[0];
        int totalPunts=0;
        int cont  ;
        for(Ma jugador : jugadors) {
            cont =0;
            if (jugador.esBuida()) jugadorGuanyador = jugador;
            for (int c = 0; c < jugador.tamanyGrup(); c++)
                cont+=puntCartes(jugador.seleccionar(c));
            totalPunts+=cont;
            jugador.agregarPuntuacio(-cont);
        }
        jugadorGuanyador.agregarPuntuacio(totalPunts);
    }

    @Override
    public void imprimir() {

    }
}

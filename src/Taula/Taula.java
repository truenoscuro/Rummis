package Taula;

import Cartes.*;
import Jugador.* ;
import Normes.* ;

import java.util.ArrayList;
import java.util.Scanner;

// Canviar els prints perque sigui mes lletguible
public class Taula {
    private final Ma[] jugadors;
    private final Mazo mazo;
    private final Rummy normes;
    private final ZonaJoc zonaJoc;
    public Taula( int numJugadors ){
        jugadors = new Jugador[numJugadors];
        for (int i = 0; i<numJugadors;i++) jugadors[i] =  new Jugador();
        zonaJoc = new ZonaJoc();

        //Mazo i ses normes; s'ha de posar un selector
        normes = new Rummy();
        mazo = new Mazo();

        GMazos.cFaJ(mazo);
    }
    private void recollirGrup(GCartes grup){
        Carta carta;
        while(!grup.esBuida()){
            carta = grup.seleccionar(0);
            grup.jugar(carta);
            carta.agregarGrup(new GCartes());
            mazo.agregar(carta);
        }
    }
    //s'ha de programar
    private void recollir(){
        GCartes grup;
        while(!zonaJoc.esBuida()) {
            grup = zonaJoc.selectGrup(0);
            recollirGrup(grup);
            zonaJoc.extreuGrup(grup);
        }
        for(GCartes jugador: jugadors) recollirGrup(jugador);
        mazo.barallar();
    }
    private void repartir() {
        int numCartes = normes.cartesInit();
        Carta carta;
        for (Ma jugador : jugadors){
            for (int i = 0; i < numCartes; i++) {
                carta = mazo.robar();
                carta.agregarGrup(jugador);
                jugador.robar(carta);
            }
        }
    }


    private void arreglarGrups(Ma jugador, GCartes grupArreglar ){
        Carta carta;
        boolean torn1 = true;
        while (torn1 || !normes.esJugadaValida( grupArreglar )) { // modific puc fer que sigui en general
            carta = jugador.mostrar();
            jugador.jugar( carta );
            grupArreglar.robar( carta );
            jugador.imprimir();
            grupArreglar.imprimir();
            torn1 = false;
            if( !jugador.volJugar("seguir jugant" ) ) {
                for (int i = 0; i < grupArreglar.tamanyGrup(); i++)
                    grupArreglar.seleccionar(i).retornar();
                jugador.mostrar();
                return;
            }
        }
        for( int i = 0 ; i < grupArreglar.tamanyGrup() ; i++ )
            grupArreglar.seleccionar(i).agregarGrup(grupArreglar);
    }
    private void jugarZona(Ma jugador,ZonaJoc zonaAux){
        GCartes grup;
        do {
            imprimirTaula( jugador );
            grup =  zonaJoc.mostrar();
            Carta carta = ((Jugador) grup).mostrar();
            zonaAux.selectGrup(0).robar( carta ); // afegeix al grup 0
            grup.jugar( carta );  // elimin carta al grup aqui es on hi ha el problema
            zonaAux.agregarGrup((GCartes) grup);
        } while ( jugador.volJugar(" seleccionar un altre grup" ) );

    }
    private void jugar( Ma jugador ){
        //Inits
        ZonaJoc zonaAux = new ZonaJoc();
        zonaAux.agregarGrup( new GCartes() );
        GCartes grup;
        if( !jugador.esJugadaInicial() && ! jugador.volJugar(" jugar de la ma ") ) jugarZona(jugador,zonaAux);
        for( int i = 0;  i < zonaAux.tamany(); i++ ) {
            grup = zonaAux.selectGrup(i);
            arreglarGrups( jugador , grup );
            if(jugador.volJugar(" vols sortir?")) break;
            if( !jugador.esJugadaInicial() || !normes.arribaAlMin( zonaAux ) ) zonaAux.agregarGrup(new GCartes());
            else jugador.aJugat();
            if( !grup.esBuida() && ! zonaJoc.extreuGrup( grup ) ) zonaJoc.agregarGrup( grup );

        }
    }
    private int pasarTorn(int torn){ return ++torn%jugadors.length; }

    public void jugarJoc(){
        // repartir cartes
        int torn;
        Ma jugador;
        Carta carta;
        normes.imprimir();
        while( !normes.hihaGuanyador( jugadors ) ){
            repartir();
            torn = 0;
            //ronda
            do {
                jugador = jugadors[ torn ];
                jugador.imprimir();
                System.out.println("Torn del jugador "+ torn);
                do {
                    if( !jugador.volJugar("si pots jugar" ) ){
                        carta = mazo.robar();
                        carta.agregarGrup(jugador);
                        jugador.robar( carta );
                        break;
                    }
                   jugar( jugador );
                } while( jugador.volJugar("vols seguir jugant" ) ); //<--- pasar a normes o deixarlo així.
                torn = pasarTorn( torn );
            } while( !normes.esGuanyadorRonda( jugador ) );
            normes.sumarPuntuacio( jugadors );
            imprimirPuntuacio();
            recollir();
        }
    }

    //imprimibles
    private void imprimirTaula(Ma jugador){
        System.out.print("Ma: ");
        jugador.imprimir();
        System.out.println("Taula");
        zonaJoc.imprimir();
    }
    private void imprimirPuntuacio(){
        System.out.print("Puntuacio jugadors: ");
        for (Ma jugador: jugadors)
            System.out.print(jugador.puntuacio() +" ");
        System.out.println();
    }




}

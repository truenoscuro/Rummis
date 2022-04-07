package Taula;

import Cartes.*;
import Jugador.* ;
import Normes.* ;

import java.util.ArrayList;
import java.util.Scanner;

public class Taula {
    private final Ma[] jugadors;
    private final Mazo mazo;
    private final Rummy normes;
    private final ZonaJoc zonaJoc;
    public Taula( int numJugadors ){
        jugadors = new Jugador[numJugadors];
        zonaJoc = new ZonaJoc();

        //Mazo i ses normes; s'ha de posar un selector
        normes = new Rummy();
        mazo = new Mazo();

        GMazos.cFaJ(mazo);
    }
    public void recollirGrup(GCartes grup){
        Carta carta;
        while(!grup.esBuida()){
            carta = grup.seleccionar(0);
            grup.jugar(carta);
            carta.agregarGrup(new GCartes());
            mazo.agregar(carta);
        }

    }
    //s'ha de programar
    public void recollir(){
        GCartes grup;
        while(!zonaJoc.esBuida()) {
            grup = zonaJoc.selectGrup(0);
            recollirGrup(grup);
            zonaJoc.extreuGrup(grup);
        }
        for(GCartes jugador: jugadors) recollirGrup(jugador);
        mazo.barallar();
    }
    public void repartir() {
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


    public void jugarZona(Ma jugador, GCartes grupArreglar ){
        Carta carta;
        if(jugador.volJugar("afegir una carta") ){
            carta = jugador.mostrar();
            jugador.jugar(carta);
            grupArreglar.robar( carta );
        }
        while (!normes.esJugadaValida( grupArreglar )) { // modific puc fer que sigui en general
            if( jugador.volJugar(" afegir sino llevar ") ){
                carta = jugador.mostrar();
                jugador.jugar( carta );
                grupArreglar.robar( carta );
            } else {
                carta = ( (Ma)grupArreglar ).mostrar( );
                grupArreglar.jugar( carta );
                jugador.robar( carta );
            }
            if(!jugador.volJugar("seguir jugant" ))
                for(int i = 0; i < grupArreglar.tamanyGrup();i++)
                    grupArreglar.seleccionar(i).retornar();

        }
        for(int i = 0; i < grupArreglar.tamanyGrup();i++)
            grupArreglar.seleccionar(i).agregarGrup(grupArreglar);
    }
    public void jugar(Ma jugador){
        //Inits
        ZonaJoc zonaAux = new ZonaJoc();
        zonaAux.agregarGrup(new GCartes());
        GCartes grup;
        if( ! jugador.volJugar(" jugar de la ma ") ) {    // <-- Vol jugar del grup
            do {
                System.out.println( "Selecciona un grup" );
                zonaJoc.imprimir();
                grup =  zonaJoc.mostrar();
                System.out.println( "Selecciona una carta" );
                Carta carta = ((Jugador) grup).mostrar();
                zonaAux.selectGrup(0).robar( carta ); // afegeix al grup 0

                grup.jugar(carta);  // elimin carta al grup aqui es on hi ha el problema
                zonaAux.agregarGrup((GCartes) grup);
            } while (jugador.volJugar(" seleccionar un altre grup"));
        }
        for(int i = 0;  i < zonaAux.tamany(); i++ ) {
            grup = zonaAux.selectGrup(i);
            jugarZona( jugador , grup );
            if( !jugador.esJugadaInicial() || !normes.arribaAlMin( zonaAux ) ) zonaAux.agregarGrup(new GCartes());
            else jugador.aJugat();
            if( zonaJoc.extreuGrup( grup ) ) zonaJoc.agregarGrup( grup );
        }
    }
    public int pasarTorn(int torn){ return ++torn%jugadors.length; }

    public void jugarJoc(){
        // repartir cartes
        int torn;
        Ma jugador;
        normes.imprimir(); // <--- Es podem imprimir quans elecciones . Es un poc tonteria
        while( !normes.hihaGuanyador( jugadors ) ){
            repartir();
            torn = 0;
            do { jugador = jugadors[ torn ];
                do {
                    if( !jugador.volJugar("si pots jugar" ) ){
                        jugador.robar( mazo.robar() );
                        break;
                    }
                   jugar( jugador );
                } while( jugador.volJugar("vols seguir jugant" ) ); //<--- pasar a normes o deixarlo així.
                torn = pasarTorn( torn );
            }while( !normes.esGuanyadorRonda( jugador ) );
            normes.sumarPuntuacio( jugadors );
            recollir();
        };
        //imprimir Guanyador
    }

    //imprimibles





}

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
        mazo.barallar();

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

    private void restaurar(ZonaJoc zonaAux){
        GCartes grup;
        while (!zonaAux.esBuida()) {
            grup = zonaAux.selectGrup(0);
            zonaAux.extreuGrup(grup);
            for (int i = 0; i < grup.tamanyGrup(); i++)
                grup.seleccionar(i).retornar();
        }
    }

    private boolean arreglarGrups(Ma jugador, GCartes grupArreglar ){
        Carta carta;
        boolean torn1 = true;
        while (torn1 || !jugador.esBuida() && !normes.esJugadaValida( grupArreglar )) { // modific puc fer que sigui en general
            carta = jugador.mostrar(); // si tria un grup  está clar que voldrá modificarlo
            jugador.jugar( carta );
            grupArreglar.robar( carta );
            grupArreglar.imprimir();
            torn1 = false;
            if(grupArreglar.tamanyGrup()< 3) continue; // Primer selecciona fins un grup de tres cartes
            if( jugador.volJugar("T'HAS EQUIVOCAT" ) ) return false;

        }
        return !jugador.esBuida() || normes.esJugadaValida(grupArreglar);
    }
    private void jugarZona(Ma jugador,ZonaJoc zonaAux){
        GCartes grup;
        do {
            zonaJoc.imprimir();
            grup =  zonaJoc.mostrar();
            // vol agafar una carta mes del grup
            do {
                Carta carta = ((Jugador) grup).mostrar();
                zonaAux.selectGrup(0).robar(carta); // afegeix al grup 0
                grup.jugar(carta);  // elimin carta al grup aqui es on hi ha el problema
            }while(jugador.volJugar(" CONTINUAR AGAFANT CARTES DEL MATEIX GRUP"));
            zonaAux.agregarGrup( grup );
            zonaAux.imprimir();
        } while ( jugador.volJugar(" SELECCIONAR UN ALTRE GRUP" ) );

    }
    private void jugar( Ma jugador ){
        //Inits
        ZonaJoc zonaAux = new ZonaJoc();
        zonaAux.agregarGrup( new GCartes() );
        GCartes grup;
        if( !jugador.esJugadaInicial() && jugador.volJugar(" AGAFAR CARTES EN JOC ") ) jugarZona(jugador,zonaAux);
        for( int i = 0;  i < zonaAux.tamany(); i++ ) {
            grup = zonaAux.selectGrup( i );
            if(!arreglarGrups( jugador , grup )){
                restaurar(zonaAux);
                break;
            }
            if( !jugador.esJugadaInicial() || !normes.arribaAlMin( zonaAux ) ) zonaAux.agregarGrup(new GCartes());
            else jugador.aJugat();
        }
        // es  per guardar els grups que no estan a zonaJoc
        while(!zonaAux.esBuida()){
            grup = zonaAux.selectGrup(0);
            if(!zonaAux.extreuGrup(grup)) zonaJoc.agregarGrup(grup);
            for( int i = 0 ; i < grup.tamanyGrup() ; i++ )
                grup.seleccionar(i).agregarGrup(grup);
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
                System.out.println("Torn del jugador "+ torn);
                do {
                    if( !jugador.volJugar("PASAR TORN" ) ){
                        carta = mazo.robar();
                        carta.agregarGrup( jugador );
                        jugador.robar( carta );
                        break;
                    }
                   jugar( jugador );
                } while( !normes.esGuanyadorRonda( jugador ) && jugador.volJugar("ALTRE JUGADA" ) ); //<--- pasar a normes o deixarlo així.
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

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
            carta.canviarEstat(false);
            grup.jugar(carta);
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
    }
    public void repartir(){
        int numCartes = normes.cartesInit();
        for(Ma jugador: jugadors)
            for(int i = 0 ; i < numCartes; i++) jugador.robar( mazo.robar() ) ;
    }


    public void jugarZona(Ma jugador, ArrayList<GCartes> grups , int i ,boolean jugarMa){
        GCartes grupArreglar = grups.get( i );
        Carta carta = jugador.mostrar();
        grupArreglar.robar( carta ); //
        while( !normes.esJugadaValida( grups ) ){ // modific puc fer que sigui en general
            if( !jugarMa ) grupArreglar.jugar( carta );
            if( !jugador.volJugar("si hi ha jugada valida" ) ) return;
            carta = jugador.mostrar();
            grupArreglar.robar( carta );
        }
        jugador.aJugat();
        for(int c = 0;  c < grupArreglar.tamanyGrup() ; c++ )
            if(carta.estaMa()) jugador.jugar( grupArreglar.seleccionar( c ) );
    }
    public void jugar(Ma jugador){
        //Inits
        ArrayList<GCartes> grups = new ArrayList<>();
        int i = 0;
        boolean jugarMa = true;
        Ma jugadorAux;
        if(!jugador.esJugadaInicial()) jugarMa = jugador.volJugar(" jugar de la ma ");
        if( !jugarMa ) {    // <-- Vol jugar del grup
            System.out.println(" Selecciona grup ");
            zonaJoc.imprimir();
            grups.add(zonaJoc.extreureGrup()); // afegeix un grup buid puc fer el de la ma! ueh!
        } else grups.add(new GCartes());
        //bucle de jugar
        while( i < grups.size() ) {
            if ( jugador.volJugar(" jugar de la Ma ") ) jugadorAux = jugador; // <--- Extreus a la zona
            else {
                grups.add( zonaJoc.extreureGrup() );
                jugadorAux = new Jugador( grups.get(i + 1) );
            }
            jugarZona( jugadorAux, grups, i, jugarMa );
            i++;
        }
        //Agregar dins la zona
        for(GCartes grup: grups) zonaJoc.agregarGrup(grup);
    }
    public int pasarTorn(int torn){ return ++torn%jugadors.length; }



    public void jugarJoc(){
        // repartir cartes
        int torn;
        Ma jugador;
        normes.imprimir(); // <--- Es podem imprimir quans elecciones . Es un poc tonteria
        while( !normes.hihaGuanyador( jugadors ) ){
            repartir();
            torn = 0; // un random
            do {
                jugador = jugadors[ torn ];
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

package Taula;

import Cartes.*;
import Jugador.* ;
import Normes.* ;

// Canviar els prints perque sigui mes lletguible
public class Taula {
    private  Ma[] jugadors;
    private  Mazo mazo;
    private  Rummy normes;
    private  ZonaJoc zonaJoc;
    private final int JOC ;
    public Taula( int numJugadors , int joc){
        jugadors = new Jugador[numJugadors];
        for (int i = 0; i<numJugadors;i++) jugadors[i] =  new Jugador();
        JOC =joc;
        construcJoc();
    }
    private void construcJoc(){
        mazo = new Mazo();
        zonaJoc = new ZonaJoc();
        switch (JOC){
            case 0 -> {
                normes = new Rummy();
                GMazos.cFaJ(mazo);
                GMazos.cFaJ(mazo);
            }
            case 1 -> {
                normes = new Rummikub();
                GMazos.RummiKub(mazo);
            }
            case 2 -> {
                normes = new Gin();
                GMazos.cFa(mazo);
            }
            default ->{
                normes = new Argentino();
                GMazos.cFaJ(mazo);
                GMazos.cFaJ(mazo);
            }
        }
        mazo.barallar();
    }

    //s'ha de programar
    private void recollir(){
        for(Ma jugador:jugadors)
            while(!jugador.esBuida())
                jugador.jugar(jugador.seleccionar(0));
        construcJoc();
    }
    private void repartir() {
        int numCartes = normes.cartesInit();
        Carta carta;
        for (Ma jugador : jugadors){
            for (int i = 0; i < numCartes; i++) {
                carta = mazo.robar();
                jugador.robar(carta);
            }
        }
    }


    // PEr RUMMY I RUMMIKUB
    private boolean arreglarGrups(Ma jugador, GCartes grupArreglar ){
        Carta carta;
        do{
            carta = jugador.mostrar(); // si tria un grup  está clar que voldrá modificarlo
            jugador.jugar( carta );
            normes.esComodin( carta );
            grupArreglar.robar( carta );
            grupArreglar.imprimir();
            if( jugador.volJugar("T'HAS EQUIVOCAT" ) ) return false;
        } while (grupArreglar.tamanyGrup()>= 3 &&
                (!jugador.esBuida() ||
                 !normes.esJugadaValida( grupArreglar )) );
        return true;
    }
    private void jugarZona(Ma jugador,ZonaJoc zonaAux){
        GCartes grup;
        do {
            zonaAux.imprimir();
            grup =  zonaAux.mostrar();
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
    private void jugar( int torn ) throws CloneNotSupportedException {
        //Clones
        Ma jugador = (Jugador) jugadors[ torn ].clone();
        ZonaJoc zonaAux = (JOC>2)?(ZonaJoc) zonaJoc.clone() : jugador.extreure(); //Argentino es igual pero amb la zona de JOc del jugador
        zonaAux.agregarGrup( new GCartes() );
        GCartes grup;
        if( !jugador.esJugadaInicial() && jugador.volJugar(" AGAFAR CARTES EN JOC ") ) jugarZona(jugador,zonaAux);
        for( int i = 0;  i < zonaAux.tamany(); i++ ) {
            grup = zonaAux.selectGrup( i );
            if(!arreglarGrups( jugador , grup )) return;
            if( !jugador.esJugadaInicial() || !normes.arribaAlMin( zonaAux ) ) zonaAux.agregarGrup(new GCartes());
            else jugador.aJugat();
        }
        // es  per guardar els grups que no estan a zonaJoc
        while(!zonaAux.esBuida()){
            grup = zonaAux.selectGrup(0);
            if(!zonaAux.extreuGrup(grup)) zonaJoc.agregarGrup(grup);
        }
        if(JOC>2) zonaJoc = zonaAux;
        else jugador.afegir( zonaAux );
        jugadors[ torn ] = jugador;
    }
    private int pasarTorn(int torn){ return ++torn%jugadors.length; }

    public void jugarJoc() throws CloneNotSupportedException {
        int torn;
        Ma jugador;
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
                        jugador.robar(  mazo.robar() );
                        break;
                    }
                   jugar( torn );
                } while( !normes.esGuanyadorRonda( jugador ) && jugador.volJugar("ALTRE JUGADA" ) ); //<--- pasar a normes o deixarlo així.
                torn = pasarTorn( torn );
            } while( !mazo.esBuid() && !normes.esGuanyadorRonda( jugador ) );
            if(mazo.esBuid()) continue;
            normes.sumarPuntuacio( jugadors );
            imprimirPuntuacio();
            recollir();
        }
    }


    // PER JUGAR A GIN I ARGENTINO
    private void jugarGin(int torn) throws CloneNotSupportedException {
        Ma jugadorAux = (Ma) jugadors[ torn ].clone();
        if( !jugadorAux.volJugar(" TENS PER TANCAR?")) return;
        ZonaJoc zonaAux = jugadorAux.extreure();
        final int MINCARTES = 3;
        GCartes grup ;
        Carta carta ;
        do {
            grup = new GCartes();
            do{
                carta = jugadorAux.mostrar();
                jugadorAux.jugar(carta);
                grup.robar(carta);
            } while( grup.tamanyGrup() >= MINCARTES && jugadorAux.volJugar(" VOL CONTINUAR AMB EL MATEIX GRUP"));
            if(!normes.esJugadaValida(grup)) break; // UN ERROR I A lA CALLE
            zonaAux.agregarGrup(grup);
        } while( jugadorAux.volJugar(" VOL FER UN GRUP "));
        if( !normes.esJugadaValida(grup) ) jugarGin( torn );
        else jugadors[ torn ].afegir( zonaAux );
    }

    public void moureJugador( int torn ){
        torn = pasarTorn(torn + jugadors.length-2);
        Ma jugador1 = jugadors[ torn ];
        jugadors[ torn ] = jugadors [ 0 ];
        jugadors[ 0 ] = jugador1;
    }
    public void jugarJocGin() throws CloneNotSupportedException {
        int torn;
        Ma jugador;
        Carta carta;
        Mazo cementeri = new Mazo();
        cementeri.agregar( mazo.robar() );
        normes.imprimir();

        while( !normes.hihaGuanyador( jugadors ) ){
            repartir();
            torn = 0;
            do {
                jugador = jugadors[ torn ];
                System.out.println("Torn del jugador "+ torn);
                imprimirTaula(jugador,cementeri);
                if( !jugador.volJugar("ROBAR BIBLIOTECA" ) ) jugador.robar(  mazo.robar() );
                else jugador.robar( cementeri.robar() );

                if ( JOC > 2 ) jugar( torn ); // Argentino
                // posar carta cementeri
                carta =jugador.mostrar();
                jugador.jugar(carta);
                cementeri.agregar( carta );
                torn = pasarTorn( torn );
                if( JOC == 2 ) jugarGin(torn);
            } while( !normes.esGuanyadorRonda( jugador ) );
            moureJugador( torn );
            normes.sumarPuntuacio( jugadors );
            moureJugador( torn );
            imprimirPuntuacio();
            recollir();
        }
    }
    //imprimibles
    private void imprimirTaula(Ma jugador, Mazo cementeri){
        System.out.print("Ma: ");
        jugador.imprimir();
        System.out.print("Cementeri: ");
        cementeri.imprimirSuperior();

    }
    private void imprimirPuntuacio(){
        System.out.print("Puntuacio jugadors: ");
        for (Ma jugador: jugadors)
            System.out.print(jugador.puntuacio() +" ");
        System.out.println();
    }




}

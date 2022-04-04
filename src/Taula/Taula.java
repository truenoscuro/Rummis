package Taula;

import Cartes.GCartes;
import Jugador.* ;
import Normes.* ;

import java.util.ArrayList;

public class Taula {
    private Ma[] jugadors;
    private Mazo mazo;
    private Rummy normes;
    public Taula( int numJugadors ){
        jugadors = new Jugador[numJugadors];
        //Mazo i ses normes;
        normes = new Rummy();
    }

    //s'ha de programar
    public void recollir(){}
    public void repartir(){}
    public void agregarZonaJoc(ArrayList<GCartes> grups){}
    public boolean volJugar(){return false;}

    public void selectMa( Ma jugador, ArrayList<GCartes>  grups ){}
    public void selectZona( Ma jugador, ArrayList<GCartes> grups){}
    public void jugar(Ma jugador,ArrayList<GCartes>  grups){/*Em de saber quines cartes son del juagador*/}
    public int pasarTorn(int torn){ return ++torn%jugadors.length; }
    public void robar(Ma jugador){}
    public void jugar(){
        // repartir cartes
        int torn;
        Ma jugador;
        ArrayList<GCartes> grups;
        do{
            repartir();
            torn = 0;
            do{
                jugador = jugadors[ torn ];
                grups = new ArrayList<>();
                jugador.canviarJugar(true);
                //es on hi ha tot el follon
                do {
                    //imprimir tablero i ma
                    if( !volJugar() ){
                        jugador.canviarJugar(false);
                        robar( jugador );
                        break;
                    }
                    if(jugador.esJugadaInicial()) {
                        selectMa(jugador,grups);
                    } else {
                        // ma i Zona de joc
                        /*
                        if(es zona de joc) selecZona(jugador,grups);
                        else selectMa(jugador,grups);
                         */
                    }
                }while( jugador.esJugadaInicial()?  normes.esJugadaInicial(grups):
                                                    normes.esJugadaValida(grups) );
                if( jugador.potJugar() ) {
                    jugador.aJugadat();
                    agregarZonaJoc( grups );
                    jugar( jugador , grups ); // <-- EM de saber quines cartes sen del jugador
                }
                torn = pasarTorn( torn );
            }while( !normes.esGuanyadorRonda( jugador ) );
            normes.sumarPuntuacio( jugadors );
            recollir();
        }while( !normes.hihaGuanyador( jugadors ) );
        //imprimir Guanyador
    }

    //imprimibles
    //imprimir regles normes!





}

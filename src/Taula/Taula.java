package Taula;

import Cartes.GCartes;
import Jugador.* ;
import Normes.* ;

import java.util.ArrayList;
import java.util.Scanner;

public class Taula {
    private Ma[] jugadors;
    private Mazo mazo;
    private Rummy normes;
    public Taula( int numJugadors ){
        jugadors = new Jugador[numJugadors];
        //Mazo i ses normes; s'ha de posar un selector
        normes = new Rummy();
        mazo = new Mazo();
        GMazos.cFaJ(mazo);
    }

    //s'ha de programar
    public void recollir(){}
    public void repartir(){}
    public void agregarZonaJoc(ArrayList<GCartes> grups){}


    public void selectMa( Ma jugador, ArrayList<GCartes>  grups ){}
    public void selectZona( Ma jugador, ArrayList<GCartes> grups){}
    public void jugar(Ma jugador,ArrayList<GCartes>  grups){/*Em de saber quines cartes son del juagador*/}
    public int pasarTorn(int torn){ return ++torn%jugadors.length; }
    public void robar(Ma jugador){}

    public boolean volJugar(){
        Scanner jugar = new Scanner(System.in);
        return jugar.nextInt() > 0;
    }
    public void jugar(){
        // repartir cartes
        int torn;
        Ma jugador;
        ArrayList<GCartes> grups;


        normes.imprimir();
        do{
            repartir();
            torn = 0;
            do{
                jugador = jugadors[ torn ];
                grups = new ArrayList<>();
                jugador.canviarJugar(true);
                do {
                    // Seleccionar si vol jugar
                    System.out.println("Si pots jugar selecciona un numero major que 0");
                    if( !volJugar() ){
                        jugador.canviarJugar(false);
                        robar( jugador );
                        break;
                    }
                    if(jugador.esJugadaInicial()) {
                        selectMa(jugador,grups);
                    } else {
                        System.out.println( "Si vols canviar qualque carte de la  " +
                                            "zona de joc tria numero major que o");
                        if(volJugar()) selectZona(jugador,grups);
                        else selectMa(jugador,grups);
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





}

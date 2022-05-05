package Normes;

import Cartes.Carta;
import Cartes.GCartes;
import Jugador.Ma;
import Taula.ZonaJoc;

import java.util.Arrays;

public class Gin extends Argentino{
    private int numCartes = 10;
    private int punts = 101;

    @Override
    public int cartesInit() { return numCartes; }

    @Override
    protected int puntuacio() { return punts; }

    @Override
    public boolean esGuanyadorRonda(Ma jugador) throws CloneNotSupportedException { //jugadors tendran una zonaJoc
        if( !jugador.volJugar(" TENS PER TANCAR?")) return false;
        Ma jugadorAux = (Ma) jugador.clone();
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
            if(!esJugadaValida(grup)) break; // UN ERROR I A lA CALLE
        } while( jugadorAux.volJugar(" VOL FER UN GRUP "));
        if(!esJugadaValida(grup) ) return esGuanyadorRonda( jugador );
        int punt = 0 ;
        for( int i = 0; i < jugadorAux.tamanyGrup() ; i++ ) punt += puntCartes( jugadorAux.seleccionar( i ) );
        if( punt >= 10 ){
            return false;
        }
        jugador = jugadorAux;
        return true;
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
    public void sumarPuntuacio(Ma... jugadors) throws CloneNotSupportedException {
        int iTanca = 0 ;
        int [] punts = new int[jugadors.length];
        int puntsTotal = 0;
        for( int j = 0; j < jugadors.length ; j++ ){
            if(jugadors[j].tamanyGrup() < cartesInit() ) {
                iTanca = j;
                continue;
            }
            else {
                System.out.println("Jugador "+j);
                esGuanyadorRonda(jugadors[ j ]);
            }
            for( int i = 0; i < jugadors[j].tamanyGrup() ; i++ )
                punts[ j ] += puntCartes( jugadors[j].seleccionar( i ) );
            puntsTotal+=punts[ j ];
        }
        //GIN
        if( punts[iTanca] == 0 ) {
            for (int punt : punts) jugadors[iTanca].agregarPuntuacio(punt);
            jugadors[iTanca].agregarPuntuacio(20);
            return;
        }
        // RON
        int iMin = iTanca;
        for (int j = 0 ; j<punts.length ; j++)
            if(punts[j] <= punts[iMin]) iMin = j;

        if( iTanca == iMin ) jugadors[iTanca].agregarPuntuacio(puntsTotal - punts[ iTanca ]);
        else jugadors[iMin].agregarPuntuacio(10 + punts[iTanca] - punts[iMin] ) ;

    }

    @Override
    public void imprimir() {

    }
}

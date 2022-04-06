package Normes;

import Cartes.*;
import Jugador.*;



import java.util.ArrayList;

public class Rummy {
    private  final static int numCartes = 14;
    private final static int puntuacio = 101;
    private final static int numIncial = 30;
    public int cartesInit(){ return  numCartes; } // es el repartir de cartes en quantes començes
    public boolean hihaGuanyador( Ma[] jugadors ) {
        for (Ma jugador : jugadors)
            if(jugador.puntuacio()>=puntuacio) return true;
        return false;
    }
    public boolean esGuanyadorRonda(Ma jugador){ return jugador.esBuida() ; }
    public boolean esJugadaValida( Ma jugador , ArrayList<GCartes> grups ) {
        int cont = 0;
        for (GCartes grup: grups){
            for (int i = 0; i < grup.tamanyGrup();i++)
                cont+= grup.seleccionar(i).num();
        }
       for(GCartes grup: grups){
           if(esEscala(grup)||esMateixNum(grup)) continue;
           return false;
       }
       return !jugador.esJugadaInicial() || cont >= numIncial;
    }
    private boolean compararEscala(Carta carta1,Carta carta2){
        int num1 = carta1.num();
        int num2 = carta2.num();
        int palo1 = carta1.palo();
        int palo2 = carta2.palo();
        if(palo1 != palo2) return false;
        if(num1 == 0 || num2 == 0) return true;
        if(num1 == 13 && num2 == 1) return true;
        if(num1 == 1 && num2 == 13) return true;
        return num1<=num2;
    }
    private boolean esEscala( GCartes grup ){
        int tamany = grup.tamanyGrup();
        if(tamany!=4) return false;
        for(int c = 1 ; c< grup.tamanyGrup();c++){
            if( compararEscala(grup.seleccionar(c-1) , grup.seleccionar(c) ) ) continue;
            return false;
        }
        return true;
    }
    private boolean compararGrup(Carta carta1,Carta carta2){
        int num1 = carta1.num();
        int num2 = carta2.num();
        if(num1 == 0 || num2 == 0) return true;
        return num1 == num2;
    }

    private boolean esMateixNum( GCartes grup ){
        int tamany = grup.tamanyGrup();
        if(!(tamany==3 || tamany == 4)) return false;
        for(int c = 1 ; c< grup.tamanyGrup();c++){
            if( compararGrup(grup.seleccionar(c-1) , grup.seleccionar(c) ) ) continue;
            return false;
        }
        return true;
    }

    private int puntCartes(Carta carta){
        /*
        Punts de cartes
        1-7  -> 5 punts
        8-13 -> 10 punts
        Joker -> 0 punts ? no diu res
         */
        int num = carta.num();
        int valor = 10;
        if(num == 0) valor = 0;
        if(num>=1 && num<= 7) valor = 5;
        return valor;
    }
    public void sumarPuntuacio( Ma[] jugadors ) {
        Ma jugadorGuanyador = jugadors[0];
        int totalPunts=0;
        for(Ma jugador : jugadors) {
            if (jugador.esBuida()) jugadorGuanyador = jugador;
            for (int c = 0; c < jugador.tamanyGrup(); c++)
                totalPunts+=puntCartes(jugador.seleccionar(c));
        }
        jugadorGuanyador.agregarPuntuacio(totalPunts);
    }



    public void imprimir(){
        // IMprimir ses regles

    }
}

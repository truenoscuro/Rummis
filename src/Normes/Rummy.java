package Normes;

import Cartes.*;
import Jugador.*;
import Taula.ZonaJoc;


import java.util.ArrayList;

public class Rummy {
    private  final static int numCartes = 14;
    private final static int puntuacio = 101;
    private final static int numIncial = 30;
    public int cartesInit(){ return  numCartes; } // es el repartir de cartes en quantes començes
    public boolean hihaGuanyador( Ma...jugadors ) {
        for(int i = 0; i <jugadors.length ;i++) {
            if (jugadors[i].puntuacio() >= puntuacio) {
                System.out.println("El guanyador es el jugador " + i);
                return true;
            }
        }
        return false;
    }
    public boolean esGuanyadorRonda(Ma jugador){ return jugador.esBuida() ; }
    public boolean arribaAlMin(ZonaJoc grups){
        int cont = 0;
        for (int i = 0 ; i < grups.tamany() ; i++ ){
            GCartes grup = grups.selectGrup(i);
            for (int j = 0; j < grup.tamanyGrup();j++)
                cont+= grup.seleccionar(i).num();
        }
        return  cont < numIncial;
    }
    public boolean esJugadaValida( GCartes grup ){ return esEscala( grup ) || esMateixNum( grup ); }
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
        int palo1 = carta1.palo();
        int palo2 = carta2.palo();
        if(palo1 >= palo2) return false;
        if(num1 == 0 || num2 == 0) return true;
        return num1 == num2;
    }
    private boolean esMateixNum( GCartes grup ){
        int tamany = grup.tamanyGrup();
        if(!(tamany==3  ||  tamany == 4)) return false;
        for(int c = 1 ; c< grup.tamanyGrup();c++){
            if( compararGrup(grup.seleccionar(c-1) , grup.seleccionar(c) ) ) continue;
            return false;
        }
        return true;
    }
    // Puntuacio
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
    public void sumarPuntuacio( Ma...jugadors ) {
        Ma jugadorGuanyador = jugadors[0];
        int totalPunts=0;
        for(Ma jugador : jugadors) {
            if (jugador.esBuida()) jugadorGuanyador = jugador;
            for (int c = 0; c < jugador.tamanyGrup(); c++)
                totalPunts+=puntCartes(jugador.seleccionar(c));
        }
        jugadorGuanyador.agregarPuntuacio(totalPunts);
    }
    //imprimir regles
    public void imprimir(){
        System.out.println("""
                 Normes: \s 1- Nomes es permeten grups de 3 o 4 de cartes amb mateix numero palo diferent\s
                º2- Les escales nomes son de 4 cartes i de palo igual , el 1 13 i 13 1 es valid\s
                 3- El comodi compte per qualsevol carta\s
                 4- La jugada inicial el grup de cartes a de sumar 30 o més\s
                 5- Despres d'haver obrit la primera vegada es pot jugar en les cartes del tauler\s
                 6- Si no es pot jugar es roba una carta i se pasa el torn\s
                  7- Es juga fins que un jugador tengui més de 100 punts""");
    }
}

package Normes;

import Cartes.*;
import Jugador.*;
import Taula.ZonaJoc;


import java.util.Scanner;

public class Rummy {
    private  int numCartes = 14;
    private  int punts = 101;
    private int numIncial = 30;
    protected int puntuacio(){ return punts ;}
    public int cartesInit(){ return  numCartes; } // es el repartir de cartes en quantes començes
    public boolean hihaGuanyador( Ma...jugadors ) {
        for( int i = 0; i <jugadors.length ;i++ ) {
            if (jugadors[i].puntuacio() >= puntuacio()) {
                System.out.println("El guanyador es el jugador " + i);
                return true;
            }
        }
        return false;
    }
    public boolean esGuanyadorRonda(Ma jugador) throws CloneNotSupportedException { return jugador.esBuida() ; }
    //Normes de jugar
    public boolean arribaAlMin(ZonaJoc grups){
        int cont = 0;
        for (int i = 0 ; i < grups.tamany() ; i++ ){
            GCartes grup = grups.selectGrup(i);
            for (int j = 0; j < grup.tamanyGrup();j++)
                cont += grup.seleccionar(i).pes();

        }
        return  cont < numIncial;
    }
    public boolean esJugadaValida( GCartes grup ){ return esEscala( grup ) || esMateixNum( grup ); }
    protected boolean compararEscala(Carta carta1,Carta carta2){
        int num1 = carta1.pes();
        int num2 = carta2.pes();
        int palo1 = carta1.palo();
        int palo2 = carta2.palo();
        if(palo1 == 0 || palo2 == 0) return num1<num2;
        if(palo1 != palo2 ) return false;
        if(num1 == 13 && num2 == 1) return true;
        if(num1 == 1 && num2 == 13) return true;
        return num1<num2;
    }


    protected void canviarPes( GCartes grup ){
        Carta carta1 = grup.seleccionar(0);
        Carta carta2 = grup.seleccionar(3);
        if( carta1.num() != 1 || carta2.num() != 13 ) return;
        if( carta1.num()+1 == grup.seleccionar(1).num() ) carta2.canviarPes(0);
        else if( carta2.num()-1 == grup.seleccionar(2).num() ) carta1.canviarPes(14);
    }
    protected void desferPes(GCartes grup){
        Carta carta1 = grup.seleccionar(0);
        Carta carta2 = grup.seleccionar(3);
        if(carta1.num() !=carta1.pes() )carta1.canviarPes(carta1.num());
        if(carta2.num() != carta2.pes())  carta2.canviarPes(carta2.num());
    }

    protected boolean tamanyEscala(int tamany){ return tamany == 4 ; }
    protected boolean esEscala( GCartes grup ){
        int tamany = grup.tamanyGrup();
        if(!tamanyEscala(tamany)) return false;
        canviarPes(grup);
        grup.sort();
        for(int c = 1 ; c < tamany ;c++){
            if( !compararEscala(grup.seleccionar(c-1) , grup.seleccionar(c) ) ) {
                desferPes(grup);
                return false;
            }
        }
        return true;
    }
    protected boolean compararGrup(Carta carta1,Carta carta2){
        int num1 = carta1.pes();
        int num2 = carta2.pes();
        int palo1 = carta1.palo();
        int palo2 = carta2.palo();
        if( palo1 == 0 || palo2 == 0 ) return num1 == num2;
        if( palo1 == palo2 ) return false;
        return num1 == num2;
    }
    protected boolean esMateixNum( GCartes grup ){
        int tamany = grup.tamanyGrup();
        if(tamany<=2  ||  tamany >= 5) return false;
        for(int c = 1 ; c< tamany ;c++){
            if( !compararGrup(grup.seleccionar(c-1 ) , grup.seleccionar( c ) ) ) return false;

        }
        return true;
    }
    // Comodin
    protected int selectNum (){
        Scanner num = new Scanner(System.in);
        int n;
        imprimirSeleccionar();
        do{ n = num.nextInt(); }while(n>=14 || n<=0);
        return n;
    }
    public void esComodin(Carta carta){
        if (carta.num() != 0) return ;
        carta.canviarPes(selectNum());
    }
    protected  void imprimirSeleccionar(){
        System.out.println("""
                Selecciona un numero entre el 1 i el 13\s
                el 1 --> A\s
                el 11 --> J\s
                el 12 --> Q\s
                el 13 --> K\s""");
    }



    // Puntuacio
    protected int puntCartes(Carta carta){
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
    public void sumarPuntuacio( Ma...jugadors ) throws CloneNotSupportedException {
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
                 Normes: \s
                 1- Nomes es permeten grups de 3 o 4 de cartes amb mateix numero palo diferent\s
                º2- Les escales nomes son de 4 cartes i de palo igual , el 1 13 i 13 1 es valid\s
                 3- El comodi compte per qualsevol carta\s
                 4- La jugada inicial el grup de cartes a de sumar 30 o més\s
                 5- Despres d'haver obrit la primera vegada es pot jugar en les cartes del tauler\s
                 6- Si no es pot jugar es roba una carta i se pasa el torn\s
                 7- Es juga fins que un jugador tengui més de 100 punts""");
    }
}

package Taula;

import Cartes.Carta;

import java.util.*;

public class Mazo {

    private final ArrayList <Carta> mazo;

    public Mazo(){ mazo = new ArrayList<>(); }

    //boleans
    public boolean esBuid(){ return mazo.isEmpty(); }

    //Accions
    public void agregar( Carta carta ){ mazo.add(carta); }
    public Carta robar(){
        int ultima = mazo.size() -1;
        Carta carta = mazo.get( ultima);
        mazo.remove( carta );
        return carta;
    }

    public void barallar(){ Collections.shuffle(mazo); }

    public void imprimirSuperior(){
        int ultima = mazo.size() -1;
        mazo.get(ultima).imprimir();
        System.out.println();
    }



}

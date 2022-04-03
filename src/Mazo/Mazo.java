package Mazo;

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
        Carta carta = mazo.get(0);
        mazo.remove(0);
        return carta;
    }

    public void barallar(){ Collections.shuffle(mazo); }



}

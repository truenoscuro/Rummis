package Cartes;

import Cartes.Carta;

import java.util.ArrayList;

public class GCartes {

    private final ArrayList<Carta> grup;

    public GCartes(){ grup = new ArrayList<>(); }

    //boleans
    public boolean esBuida(){ return grup.isEmpty(); }
    protected boolean estaEnRang( int i ){ return i>=0 && i<grup.size(); }

    //Acions
    public void robar(Carta carta){
       grup.add( carta );
       grup.sort(Carta::compareTo);
    };


    public Carta seleccionar(int i){ return grup.get(i); };


    public void jugar(Carta carta){ grup.remove(carta); }
    // Imprimir grup

    public void imprimir(){
        for(Carta carta: grup) {
            carta.imprimir();
            System.out.print(" ");
        }
        System.out.println();
    }
}

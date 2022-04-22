package Cartes;

import Cartes.Carta;

import java.util.ArrayList;
import java.util.Arrays;

public class GCartes {

    private final ArrayList<Carta> grup;

    public GCartes(){ grup = new ArrayList<>(); }
    public GCartes(Carta...cartes){
        this();
        grup.addAll(Arrays.asList(cartes));
    }
    //boleans
    public boolean esBuida(){ return grup.isEmpty(); }
    protected boolean estaEnRang( int i ){ return i>=0 && i<grup.size(); }

    //Acions
    public void robar(Carta carta){
       grup.add( carta );
       grup.sort(Carta::compareTo);
    };
    public void sort(){ grup.sort(Carta::compareTo); }


    public Carta seleccionar(int i){ return grup.get(i); };
    public int tamanyGrup(){ return grup.size(); }
    public void jugar(Carta carta){ grup.remove(carta); }
    // Imprimir grup

    public void imprimir(){
        for(Carta carta: grup) {
            carta.imprimir();
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GCartes grupNou = new GCartes();
        for (Carta carta: grup){ grupNou.robar( (Carta) carta.clone() ); }
        return grupNou;
    }
}

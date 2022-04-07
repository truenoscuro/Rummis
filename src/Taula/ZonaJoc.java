package Taula;

import Cartes.Carta;
import Cartes.GCartes;

import java.util.ArrayList;
import java.util.Scanner;

public class ZonaJoc {
    ArrayList<GCartes> grupCarta;
    public ZonaJoc(){ grupCarta = new ArrayList<>();}

    public Carta seleccionarCarta(int g , int c){ return grupCarta.get(g).seleccionar(c); }
    //public void extreure(int g , Carta carta){ grupCarta.get(g).jugar(carta); }
    public GCartes mostrar(){
        Scanner select = new Scanner(System.in);
        int g;
        System.out.println( "Selecciona un grup ");
        do { g = select.nextInt();
        } while( !(g >= 0 && g < grupCarta.size() ) );
        return grupCarta.get(g);
    }


    public void agregarGrup(GCartes grup){
        int total = grup.tamanyGrup();
        grupCarta.add(grup);
    }
    public void agregarCarta(int g, Carta carta){
        grupCarta.get(g).robar(carta); }

    public boolean esBuida(){ return grupCarta.isEmpty(); }

    public GCartes selectGrup(int i){ return grupCarta.get(i);};
    public boolean extreuGrup(GCartes grup){ return grupCarta.remove(grup); }
    public int tamany(){ return grupCarta.size(); }



    public void imprimir(){
        for(int i = 0; i < grupCarta.size();i++){
            System.out.print(i+": ");
            grupCarta.get(i).imprimir();
        }
    }


}

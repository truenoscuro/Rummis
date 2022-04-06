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
    public GCartes extreureGrup(){
        Scanner select = new Scanner(System.in);
        int g;

        System.out.println( "Selecciona un grup ");
        do{ g = select.nextInt();
        }while(!(g >= 0 && g < grupCarta.size() ));
        GCartes grup = grupCarta.get(g);
        grupCarta.remove(g);
        return grup;
    }


    public void agregarGrup(GCartes grup){
        int total = grup.tamanyGrup();
        for(int i = 0 ; i < total ;i++) grup.seleccionar(i).canviarEstat(false);
        grupCarta.add(grup);
    }
    public void agregarCarta(int g, Carta carta){
        carta.canviarEstat(false);
        grupCarta.get(g).robar(carta); }

    public boolean esBuida(){ return grupCarta.isEmpty(); }

    public GCartes selectGrup(int i){ return grupCarta.get(i);};
    public void extreuGrup(GCartes grup){ grupCarta.remove(grup); }



    public void imprimir(){
        for(int i = 0; i < grupCarta.size();i++){
            System.out.print(i+": ");
            grupCarta.get(i).imprimir();
        }
    }


}

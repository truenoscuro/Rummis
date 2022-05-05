package Jugador;

import Cartes.*;
import Taula.ZonaJoc;


import java.util.Scanner;

public class Jugador extends Ma {
    private final Scanner jugar;
    private int puntuacio;
    private boolean jugadaInicial;

    private ZonaJoc zona;

    public Jugador(GCartes grup){
        this();
        crearMa(grup);
    }

    public Jugador(){
       super();
       puntuacio = 0;
       jugadaInicial = true;
       jugar = new Scanner(System.in);
       zona = new ZonaJoc();

    }

    //Inits
    public void crearMa(GCartes grup){
        while(!grup.esBuida()){
            Carta carta = grup.seleccionar(0);
            grup.jugar(carta);
            robar(carta);
        }
    }



    @Override
    // puntuació
    public void resetearPuntuacio(){ puntuacio = 0; }
    public void agregarPuntuacio( int punts ){ puntuacio += punts; }
    public int puntuacio() { return puntuacio; }


    //ACCiONS
    public Carta mostrar(){
        int i ;
        System.out.println("Selecciona una carta ");
        super.imprimir();
        do{ i = jugar.nextInt(); } while ( !super.estaEnRang( i ) ) ;
        return super.seleccionar( i );
    }
    public boolean volJugar(String text) {

        System.out.println("Tria > 0 per "+text);
        return jugar.nextInt() > 0;
    }

    //booleans
    public void aJugat() { jugadaInicial = false; }
    public boolean esJugadaInicial() { return jugadaInicial; }

    public Object clone() throws CloneNotSupportedException {
        Ma maNova = new Jugador();
        for ( int i = 0 ; i < super.tamanyGrup() ; i++ )
            maNova.robar( ( Carta ) super.seleccionar(i).clone() );
        maNova.afegir( ( ZonaJoc ) this.zona.clone( ) );
        return maNova;
    }

    @Override
    public ZonaJoc extreure() { return zona; }

    @Override
    public void afegir(ZonaJoc zona) { this.zona = zona; }


}

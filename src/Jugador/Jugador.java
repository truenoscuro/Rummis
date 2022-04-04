package Jugador;

import Cartes.Carta;

import java.util.Scanner;

public class Jugador extends Ma {
    private final Scanner jugar;
    private int puntuacio;
    private boolean jugadaInicial;
    private boolean potJugar ;
    public Jugador(){
       super();
       puntuacio = 0;
       jugadaInicial = true;
       potJugar = true;
       jugar = new Scanner(System.in);

    }




    @Override
    // puntuació
    public void resetearPuntuacio(){ puntuacio = 0; }
    public void agregarPuntuacio( int punts ){ puntuacio += punts; }
    public int puntuacio() { return puntuacio; }

    //ACCiONS

    public Carta mostrar(){
        int i ;
        System.out.println("Selecciona una carta");
        super.imprimir();
        do{ i = jugar.nextInt();
        }while( !super.estaEnRang( i ) );
        return super.seleccionar( i );
    }

    //booleans
    public boolean potJugar() { return potJugar; }
    public void canviarJugar(boolean estat) { potJugar = estat;}
    public void aJugadat() { jugadaInicial = false; }
    public boolean esJugadaInicial() { return jugadaInicial; }

}

package Cartes;

import java.util.Objects;

public class Carta implements Comparable{
    private final int num ;
    private final int palo;

    private boolean cartaMa;

    public Carta( int num , int palo ){
        this.num = num;
        this.palo = palo;
        cartaMa = false;
    }
    // Gets
    public int num(){ return num; }
    public int palo(){ return palo; }

    public void canviarEstat(boolean estat){ cartaMa = estat; }
    public boolean estaMa(){ return cartaMa ;}
    //Imprimir
    public void imprimir(){System.out.print(num+""+palo+"");}

    // Compara dues cartes
    public boolean esMateixPalo( Carta carta ){ return palo == carta.palo(); }
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Carta carta = ( Carta ) o;
        return num == carta.num && palo == carta.palo;
    }
    public int hashCode() { return Objects.hash(num, palo); }
    public int compareTo(Object o) {
        Carta carta = (Carta) o;
        int comp = 0;
        if(num < carta.num) comp = -1;
        else if(num > carta.num) comp = 1;
        return comp;
    }
}
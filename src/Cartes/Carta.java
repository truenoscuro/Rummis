package Cartes;

import java.util.Objects;

public class Carta implements Comparable{
    private final int num ;
    private final int palo;
    private GCartes grupRetorna; // buumerang

    public Carta( int num , int palo ){
        this.num = num;
        this.palo = palo;
        grupRetorna = new GCartes(); // <--- eliminar
    }
    // Gets
    public int num(){ return num; }
    public int palo(){ return palo; }

    //Imprimir
    public void imprimir(){System.out.print(num+""+palo+"");}

    //efecto bumerang de ses cartes;
    public void agregarGrup(GCartes grup){ grupRetorna = grup; }
    public void retornar(){ grupRetorna.robar(this) ;}

    // Compara dues cartes
    @Override
    public boolean equals(Object o) {
        //if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Carta carta = ( Carta ) o;
        return num == carta.num && palo == carta.palo;
    }
    public int hashCode() { return Objects.hash(num, palo); }
    //ordernar cartes
    public int compareTo(Object o) {
        Carta carta = (Carta) o;
        int comp = 0;
        if( num == carta.num() ){
            if(palo < carta.palo()) comp = -1;
            else if( palo > carta.palo() ) comp = 1;
        }
        else if(num < carta.num()) comp = -1;
        else if(num > carta.num()) comp = 1;
        return comp;
    }
}

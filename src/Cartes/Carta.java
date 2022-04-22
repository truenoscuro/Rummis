package Cartes;

import java.util.Objects;

public class Carta implements Comparable{
    private final int num ;
    private final int palo;
    private int pes;
    public Carta( int num , int palo ){
        this.num = num;
        this.pes = num;
        this.palo = palo;
    }
    // Sets
    public void canviarPes(int n ){ pes = n; }
    // Gets
    public int num(){ return num; }
    public int pes(){ return pes; }
    public int palo(){ return palo; }

    //Imprimir
    public void imprimir(){System.out.print(num+""+palo+"");}
    
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
        if( pes == carta.pes() ){
            if( palo < carta.palo() ) comp = -1;
            else if( palo > carta.palo() ) comp = 1;
        }
        else if( pes < carta.pes() ) comp = -1;
        else if( pes > carta.pes() ) comp = 1;
        return comp;
    }


    @Override

    public Object clone() throws CloneNotSupportedException {
        Carta carta = new Carta( this.num , this.palo );
        carta.canviarPes( this.pes );
        return  carta;
    }
}

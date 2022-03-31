public class Carta {
    private final int num ;
    private final int palo;

    public Carta( int num , int palo ){
        this.num = num;
        this.palo = palo;
    }
    public int num(){ return num; }
    public int palo(){ return palo; }

    public void imprimir(){System.out.print(num+""+palo+"");}
}

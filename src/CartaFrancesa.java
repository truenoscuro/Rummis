public class CartaFrancesa extends Carta {
    /*
    Hi ha 4 cartes de cada
    NUM:
    0   --> JOKER
    1   --> A
    2-9 --> normals
    10  --> J
    11  --> Q
    12  --> K
    PALOS
    0 --> Joker
    1 --> Trebol
    2 --> Diamants
    3 --> Corazon
    4 --> Pica

     */
    public CartaFrancesa( int num, int palo ){
        super(num,num==0?0:palo);
    }
    private String numF() {
        return switch (super.num()) {
            case 0 -> "Joker";
            case 1 -> "A";
            case 10 -> "J";
            case 11 -> "Q";
            case 12 -> "K";
            default -> ""+super.num();
        };
    }
    private String paloF(){
        return switch (super.palo()){
            case 1 -> "T";
            case 2 -> "D";
            case 3 -> "C";
            case 4 -> "P";
            default ->""; // Aquest no afectará
        };
    }
    @Override
    public void imprimir() { System.out.print( numF() + paloF() ); }
}



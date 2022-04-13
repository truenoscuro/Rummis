package Cartes;

public class FRummikub extends  Carta{

    public FRummikub(int num, int palo) {
        super(num, palo);
    }

    private String numF() { return super.num() == 0 ? "c":""+super.num(); }

    private String colorF(){
        return switch (super.palo()){
            case 1  -> "B";
            case 2  -> "V";
            case 3  -> "N";
            case 4  -> "G";
            default ->" "; // Aquest no afectará
        };
    }
    @Override
    public void imprimir() { System.out.print( numF() + colorF() ); }
}


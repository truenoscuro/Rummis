package Normes;

import Cartes.Carta;

public class Gin extends Rummikub{
    private int numCartes = 10;
    @Override
    public int cartesInit() { return numCartes; }

    protected int puntCartes(Carta carta){
        /*
        Punts de cartes
        J,Q,K --> 10
        */
        return switch ( carta.num( ) ){
            case 10,11,13  -> 10;
            default -> carta.num();
        };
    }



}

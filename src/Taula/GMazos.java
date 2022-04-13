package Taula;

import Cartes.*;

public class GMazos {

    public static void cFa(Mazo mazo){
        for( int p = 1; p < 5 ; p++ )
            for( int n = 1; n < 13 ; n++ )
                mazo.agregar( new CartaFrancesa( n , p ) );

    }

    public static void cFaJ(Mazo mazo){
        cFa(mazo);
        for(int i = 0; i < 2;i++)  mazo.agregar(new CartaFrancesa(0,0));
    }
    public static void RummiKub(Mazo mazo){
        for(int i = 0; i<2;i++) {
            for (int p = 1; p < 5; p++)
                for (int n = 1; n < 13; n++)
                    mazo.agregar(new FRummikub(n, p));
        }
        for(int i = 0; i< 2;i++) mazo.agregar(new Carta(0,0));
    }



}

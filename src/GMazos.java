public class GMazos {

    static void cFaJ(Mazo mazo){
        for( int p = 1; p < 5 ; p++ )
            for( int n = 0; n < 13 ; n++ )
                mazo.agregarCarta( new CartaFrancesa( n , p ) );
    }


}

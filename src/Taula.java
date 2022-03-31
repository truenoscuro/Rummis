public class Taula {
    Ma [] jugadors;


    public Taula( int numJugadors ){ jugadors = new Jugador[numJugadors]; }

    public static void CementeriAMazo(Mazo mazo, Cementeri cementeri){ // Canviar aquest nom
        Carta primeraCementeri = cementeri.extreure();
        while(!cementeri.esBuid()) mazo.agregar( cementeri.extreure() );
        mazo.barallar();
        cementeri.agregar(primeraCementeri);
    }

}

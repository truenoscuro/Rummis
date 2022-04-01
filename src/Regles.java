import java.util.ArrayList;

interface Regles {

    boolean esGuanyador();


    boolean esJugadaInicial(ArrayList<Ma> grups); // Et diu si la teva sortita es legal
    boolean esJugadaValida(ArrayList<Ma> grups);


    void sumarPuntuacio(Ma jugador);


}

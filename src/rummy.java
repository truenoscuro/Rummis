import java.util.ArrayList;

public class rummy implements Regles{

    /*
    Punts de cartes
    1-7  -> 5 punts
    8-13 -> 10 punts
     */


    @Override
    public boolean esGuanyador() {
        return false;
    }

    @Override
    public boolean esJugadaInicial(ArrayList<Ma> grups) {
        return false;
    }

    @Override
    public boolean esJugadaValida(ArrayList<Ma> grups) {
        return false;
    }
}
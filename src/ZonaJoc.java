import java.util.ArrayList;

public class ZonaJoc {
    ArrayList<GCartes> grupCarta;

    public ZonaJoc(){ grupCarta = new ArrayList<>();}


    public Carta seleccionar(int g , int c){ return grupCarta.get(g).seleccionar(c); }
    public void extreure(int g , Carta carta){ grupCarta.get(g).robar(carta); }



    public void agregarMa(GCartes grup){ grupCarta.add(grup); }
    public void agregarCarta(int g, Carta carta){ grupCarta.get(g).robar(carta); }



    public void imprimir(){
        for(int i = 0; i < grupCarta.size();i++){
            System.out.print(i+": ");
            grupCarta.get(i).imprimir();
        }
    }


}

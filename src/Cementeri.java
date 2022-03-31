import java.util.ArrayDeque;
import java.util.Deque;

public class Cementeri {
    private final Deque<Carta> cementeri;

    public Cementeri(){ cementeri = new ArrayDeque<>();}

    public boolean esBuid(){ return cementeri.isEmpty();}

    public void agregar(Carta carta){ cementeri.push(carta); }
    public Carta primera(){ return cementeri.peek(); }
    public Carta extreure(){ return cementeri.pop(); }



    public void imprimir(){ primera().imprimir(); }


}

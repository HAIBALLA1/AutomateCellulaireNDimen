package Voisinages;
import Automate.* ;

public class G6 extends Neighbors {

    public G6(Cellule G0, Grillev2 Grille)  {
        super(G0, 3, new int[][]{{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}}, Grille);
    }


}

package Voisinages;
import Automate.* ;
import  operateurs.*;

public class G8 extends Neighbors {


    public G8(Cellule G0, Grillev2 grille) {
        super(G0, 2, new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}}, grille);
    }




}

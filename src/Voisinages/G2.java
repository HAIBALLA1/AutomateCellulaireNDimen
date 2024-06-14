package Voisinages;
import Automate.* ;

public class G2 extends Neighbors {
    public G2(Cellule cellule, Grillev2 grille) {
        super(cellule, 1, new int[][]{{-1}, {0}, {1}}, grille);
    }


}

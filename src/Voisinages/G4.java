package Voisinages;
import Automate.* ;


public class G4 extends Neighbors {

    public G4(Cellule cellule, Grillev2 grille) {
        super(cellule, 2, new int[][]{
                {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        }, grille);
    }

}

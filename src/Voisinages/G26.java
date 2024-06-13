package Voisinages;
import Automate.* ;
import  operateurs.*;

public class G26 extends Neighbors {
    public G26(Cellule G0, Grillev2 grille) {
        super(G0, 3, generateG26Rules(), grille);
    }

    private static int[][] generateG26Rules() {
        int[][] rules = new int[26][3];
        int index = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (!(i == 0 && j == 0 && k == 0)) {
                        rules[index][0] = i;
                        rules[index][1] = j;
                        rules[index][2] = k;
                        index++;
                    }
                }
            }
        }
        return rules;
    }


}

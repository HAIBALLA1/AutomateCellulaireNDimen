public class G0 extends Neighbors {
    public G0(Cellule cellule, Grillev2 grille) {
        super(cellule, 1, new int[][]{{0}}, grille);
    }

    @Override
    public int evaluer() {
        return G0.evaluer();
    }
}

public class G8 extends Neighbors {
    public G8(Cellule G0, Grillev2 grille) {
        super(G0, 2, new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}}, grille);
    }

    @Override
    public int evaluer() {
        System.out.println("Je suis passé par là ");
        return 0; // Implement as per the requirement
    }
}

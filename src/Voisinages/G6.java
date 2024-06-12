public class G6 extends Neighbors {

    public G6(Cellule G0, Grillev2 Grille) throws InvalidNeighborException {
        super(G0, 3, new int[][]{{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}}, Grille);
        if (Grille.dimension.size() != 3) {
            throw new InvalidNeighborException(Grille.dimension.size(), 3, this);
        }
    }

    @Override
    public int evaluer() {
        System.out.println("Je suis passé par là ");
        // Implémentation de l'évaluation pour le voisinage G6
        // Cette méthode doit être définie en fonction des besoins spécifiques
        return 0; // Valeur par défaut, à modifier selon l'implémentation réelle
    }
}

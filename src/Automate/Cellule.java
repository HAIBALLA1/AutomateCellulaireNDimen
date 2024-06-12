import java.util.ArrayList;

public class Cellule extends Grillev2 {
    private boolean status;
    private static int compteur;
    protected ArrayList<Integer> coordonnees;

    public Cellule() {
        this.status = false;
        this.taille = 1;
        this.coordonnees = new ArrayList<>(); // Initialisation des coordonnées
        compteur++;
    }

    public Cellule(boolean status) {
        this.status = status;
        this.coordonnees = new ArrayList<>(); // Initialisation des coordonnées
        compteur++;
    }

    public int evaluer() {
        return isStatus() ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Cellule{" + "status=" + status + ", coordonnees=" + coordonnees + '}';
    }

    public boolean isStatus() {
        return status;
    }

    public static int getCompteur() {
        return compteur;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    public void setCoordonnees(ArrayList<Integer> coordonnees) {
        this.coordonnees = coordonnees;
    }

    public ArrayList<Integer> getCoordonnees() {
        return coordonnees;
    }

    public boolean equals_coord(ArrayList<Integer> coord) {
        if (coord == null || this.coordonnees.size() != coord.size()) {
            return false;
        }
        for (int i = 0; i < coord.size(); i++) {
            if (!this.coordonnees.get(i).equals(coord.get(i))) {
                return false;
            }
        }
        return true;
    }
}

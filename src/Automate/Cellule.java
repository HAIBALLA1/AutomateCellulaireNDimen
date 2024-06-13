package Automate;

import java.util.ArrayList;

public class Cellule extends Grillev2 {
    private int status;
    private static int compteur;
    protected ArrayList<Integer> coordonnees;

    public Cellule() {
        this.status = 0;
        this.taille = 1;
        this.coordonnees = new ArrayList<>(); // Initialisation des coordonnées
        compteur++;
    }

    public Cellule(int status) {
        this.status = status;
        this.coordonnees = new ArrayList<>(); // Initialisation des coordonnées
        compteur++;
    }

    public int evaluer() {
        return isStatus() ;
    }

    @Override
    public String toString() {
        return "Cellule{" + "status=" + status + ", coordonnees=" + coordonnees + '}';
    }

    public int isStatus() {
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

    public void setEtat(int status) {
        this.status = status;
    }
}

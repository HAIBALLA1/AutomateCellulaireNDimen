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
        return getStatus() ;
    }

    @Override
    public String toString() {
        return "Cellule{" + "status=" + status + ", coordonnees=" + coordonnees + '}';
    }

    public int getStatus() {
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

    public void setEtat(int status) {
        this.status = status;
    }
}

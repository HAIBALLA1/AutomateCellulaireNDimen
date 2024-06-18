package Voisinages;

import Automate.Cellule;
import Automate.Grillev2;
import Automate.TableauDynamiqueND;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Neighbors {
    protected Cellule G0;
    protected ArrayList<Cellule> neighbors;
    protected ArrayList<ArrayList<Integer>> neighbors_coordinates;
    protected int[][] rules;
    protected int dimension;
    protected Grillev2 grille;

    protected Neighbors(Cellule G0, int dimension, int[][] tab, Grillev2 grille) {
        if (G0 == null || G0.getCoordonnees() == null || G0.getCoordonnees().isEmpty()) {
            throw new IllegalArgumentException("Cellule G0 ou ses coordonnées sont nulles ou vides");
        }
        this.G0 = G0;
        this.dimension = dimension;
        this.grille = grille;
        this.neighbors = new ArrayList<>();
        this.neighbors_coordinates = new ArrayList<>();
        this.rules = tab;
        makeNeighbor();
    }

    protected ArrayList<Integer> calculate_coord(ArrayList<Integer> coord, int[] offset) {
        ArrayList<Integer> newCoord = new ArrayList<>(coord);
        for (int i = 0; i < dimension; i++) {
            int newValue = newCoord.get(i) + offset[i];
            if (newValue < 0 || newValue >= grille.getTaille()) {
                throw new IndexOutOfBoundsException("Index " + newValue + " out of bounds for length " + grille.getTaille());
            }
            newCoord.set(i, newValue);
        }
        return newCoord;
    }

    protected void calculate_coord(int[][] rules) {
        for (int[] rule : rules) {
            ArrayList<Integer> newCoord = new ArrayList<>(G0.getCoordonnees());
            newCoord = calculate_coord(newCoord, rule);
            neighbors_coordinates.add(newCoord);
        }
    }

    protected boolean verifie_limite(ArrayList<Integer> coord) {
        for (int i = 0; i < coord.size(); i++) {
            if (coord.get(i) < 0 || coord.get(i) >= grille.getDimension().get(i)) {
                return true;
            }
        }
        return false;
    }

    protected void correction(ArrayList<ArrayList<Integer>> neighbors_coordinates) {
        Iterator<ArrayList<Integer>> iterator = neighbors_coordinates.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> coordinates = iterator.next();
            if (verifie_limite(coordinates)) {
                iterator.remove();
            }
        }
    }

    protected void init_Neighbor() {
        neighbors.add(G0);
        for (ArrayList<Integer> coord : neighbors_coordinates) {
            Cellule c = ((TableauDynamiqueND) grille).getCellulev2(coord);
            if (c != null) {
                neighbors.add(c);
            }
        }
    }

    public void makeNeighbor() {
        calculate_coord(rules);
        correction(neighbors_coordinates);
        init_Neighbor();
    }

    public ArrayList<Cellule> getNeighbors() {
        return neighbors;
    }

    public void getNeighborsk() {
        neighbors.remove(G0);
    }
}

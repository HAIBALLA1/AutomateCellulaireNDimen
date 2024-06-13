package Voisinages;
import Automate.* ;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Neighbors  {
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

    protected void calculate_coord(int[][] tab) {
        int index_i = 0;
        for (int[] T : tab) {
            ArrayList<Integer> coord = new ArrayList<>(G0.getCoordonnees());
            neighbors_coordinates.add(coord);
            int index_j = 0;
            for (int index : T) {
                if (index_j < coord.size()) {
                    int new_coord = coord.get(index_j) + index;
                    coord.set(index_j, new_coord);
                } else {
                    throw new IndexOutOfBoundsException("Index " + index_j + " out of bounds for length " + coord.size());
                }
                index_j++;
            }
            index_i++;
        }
    }

    protected boolean check_border(ArrayList<Integer> coordinates) {
        int border = 0;
        for (Integer i : coordinates) {
            if (i < 0 || i >= grille.getDimension().get(border)) {
                return true;
            }
            border++;
        }
        return false;
    }

    protected void correction(ArrayList<ArrayList<Integer>> neighbors_coordinates) {
        Iterator<ArrayList<Integer>> iterator = neighbors_coordinates.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> coordinates = iterator.next();
            if (check_border(coordinates)) {
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

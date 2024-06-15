package Automate ;
import projectException.Exception1;
import java.util.ArrayList;
import java.util.Iterator;

public class TableauDynamiqueND extends Grillev2 implements Iterable{
    //private static final int MAX_DIM = 20;
    public ArrayList<Grillev2> tab_dynamique;
    public int level;

    public TableauDynamiqueND(ArrayList<Integer> dimension) throws Exception1 {
        this(dimension.size(), dimension);
    }
    private TableauDynamiqueND(int d, ArrayList<Integer> taille_tab) throws Exception1 {
        this.level = taille_tab.size();
        this.dimension = taille_tab;
        tab_dynamique = new ArrayList<>(taille_tab.get(0));

        if (d == 1) {
            initializeCells(taille_tab.get(0));
        } else {
            initializeSubGrids(d, taille_tab);
        }
    }

    private void initializeCells(int size) {
        this.taille = size;
        for (int i = 0; i < taille; i++) {
            int status = 0;
            Cellule c = new Cellule(status);
            tab_dynamique.add(c);
        }
    }

    private void initializeSubGrids(int d, ArrayList<Integer> taille_tab) throws Exception1 {
        ArrayList<Integer> copy = new ArrayList<>(taille_tab);
        this.taille = taille_tab.get(0);
        if (taille == 1) {
            throw new Exception1(level);
        }
        copy.remove(0);
        for (int i = 0; i < taille; i++) {
            try {
                TableauDynamiqueND new_tab = new TableauDynamiqueND(d - 1, copy);
                tab_dynamique.add(new_tab);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de l'initialisation des sous-tableaux", e);
            }
        }
    }

    @Override
    public Iterator<Grillev2> iterator() {
        return new parcoursTab(this.taille, this.tab_dynamique);
    }

    public void set_coord(Grillev2 grille) {
        init_coord(grille, new ArrayList<>());
    }


    private void init_coord(Grillev2 grille, ArrayList<Integer> coord) {
        Iterator<Grillev2> iter = ((TableauDynamiqueND)grille).iterator();
        if(grille.getLevel() == 1) {
            int i = 0;
            coord.add(0);
            while (iter.hasNext()) {
                Cellule c = (Cellule) iter.next();
                ArrayList<Integer> newCoord = new ArrayList<>(coord);
                newCoord.set(newCoord.size()-1, i);
                c.setCoordonnees(newCoord); // Assurez-vous de définir correctement les coordonnées
                if (c.getCoordonnees() == null) {
                    System.err.println("Coordonnées non définies pour la cellule " + c);
                }
                i++;
            }
        } else {
            coord.add(0);
            int i = 0;
            while (iter.hasNext()) {
                Grillev2 sub_tab = ((TableauDynamiqueND)grille).tab_dynamique.get(i);
                ArrayList<Integer> copy = new ArrayList<>(coord);
                copy.set(coord.size() - 1, i); // On incrémente de 1, l'indice grâce à la donnée Level
                init_coord(sub_tab, copy);
                i++;
                if(!((parcoursTab)iter).increment()) {
                    break;
                }
            }
        }
    }




    public Cellule getCellulev2(ArrayList<Integer> coordinates) {
        int position = coordinates.get(0);
        if (coordinates.size() == 1) {
            Cellule cell = (Cellule) tab_dynamique.get(position);
            if (cell != null && cell.getCoordonnees() == null) {
                cell.setCoordonnees(coordinates);
            }
            return cell;
        }
        ArrayList<Integer> copy = new ArrayList<>(coordinates);
        copy.remove(0);
        TableauDynamiqueND sub_tab = (TableauDynamiqueND) tab_dynamique.get(position);
        return sub_tab.getCellulev2(copy);
    }



    public void DFS() {
        DFS_search_example(this);
    }

    public void DFS_search_example(Grillev2 grille) {
        if (grille.getLevel() == 1) {
            TableauDynamiqueND cells = (TableauDynamiqueND) grille;
            for (int i = 0; i < cells.tab_dynamique.size(); i++) {
                System.out.println(cells.tab_dynamique.get(i));
            }
        } else {
            for (int i = 0; i < grille.getDimension().get(0); i++) {
                Grillev2 sub_tab = ((TableauDynamiqueND) grille).tab_dynamique.get(i);
                DFS_search_example(sub_tab);
            }
        }
    }

    @Override
    public int getLevel() {
        return level;
    }
}

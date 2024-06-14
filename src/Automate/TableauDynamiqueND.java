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


    private void init_coord(Grillev2 grille, ArrayList<Integer> coord)
    {
        Iterator<Grillev2> iter = ((TableauDynamiqueND)grille).iterator();
        if(grille.getLevel() == 1)
        {
            int i = 0;
            coord.add(0);
            while (iter.hasNext())
            {
                Cellule c = (Cellule) iter.next();
                c.setCoordonnees(new ArrayList<>(coord));// On associe à la cellule une copie d'une arrayList de coord (De la même manière que le constructeur
                i++;
                coord.set(coord.size()-1,i);

            }
        }
        else
        {
            coord.add(0);
            int i=0;
            while (iter.hasNext())
            {
                Grillev2 sub_tab = ((TableauDynamiqueND)grille).tab_dynamique.get(i);
                ArrayList<Integer> copy = new ArrayList<>(coord);
                copy.set(coord.size() -1 ,i);//On incrémente de 1, l'indice grâce à la donnée Level
                init_coord(sub_tab,copy);
                i++;
                if( !((parcoursTab)iter).increment())
                {
                    break;
                }
            }
        }
    }



    public Cellule getCellulev2(ArrayList<Integer> coordinates) {
        int position = coordinates.get(0);
        if (coordinates.size() == 1) {
            // Return the cell directly if it's the final level
            return (Cellule) tab_dynamique.get(position);
        }
        ArrayList<Integer> copy = new ArrayList<>(coordinates);
        copy.remove(0);
        Grillev2 sub_tab = tab_dynamique.get(position);
        if (sub_tab instanceof TableauDynamiqueND) {

            return ((TableauDynamiqueND) sub_tab).getCellulev2(copy);
        } else if (sub_tab instanceof Cellule) {

            return (Cellule) sub_tab;
        } else {
            throw new ClassCastException("Expected TableauDynamiqueND or Cellule but found " + sub_tab.getClass().getSimpleName());
        }
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

package Automate ;
import java.util.ArrayList;

public abstract class Grillev2 {
    public ArrayList<Integer> dimension = null; // Chaque niveau de case doit connaitre son tab de dimension
    public int taille; //taille est juste la dimension indiqué dans le tableau d'Integer, il correspond en réalité à tab_dimension.get(0)

    public ArrayList<Integer> getDimension()
    {
        //On envoie une copie et non directement le tableau
        return new ArrayList<>(dimension);
    }

    public abstract int getLevel();

    public int getTaille()
    {
        return taille;
    }

}
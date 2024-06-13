package Automate ;
import java.util.ArrayList;
import java.util.Iterator;

public class parcoursTab implements Iterable<Grillev2>,Iterator<Grillev2>
{
    private int taille;
    private ArrayList<Grillev2> grille;
    private int index=0;

    public parcoursTab(int taille, ArrayList<Grillev2> grill)
    {
        this.taille = taille;
        this.grille = grill;
    }

    @Override
    public boolean hasNext()
    {
        return index < taille;
    }
    public boolean increment()
    {
        if( index + 1 < taille)
        {
            index +=1;
            return true;
        }
        else
        {

            return false;
        }
    }

    @Override
    public Grillev2 next()
    {
        try
        {
            return grille.get(index++);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Accès à la case d'index " + index + " mais le tab dynamique ne contient que " + taille +" élements");
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<Grillev2> iterator()
    {
        return this;
    }

}

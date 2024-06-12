import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
            //System.out.println("Imposible d'incrémenter outOfBound tab_dynamique");
            return false;
        }
    }


//Problème avec la façon d'itérer
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


//    hasNext_range vérifie qu'on n'est pas en dehors de l'ArrayList
//    public boolean hasNext_range()
//    {
//        if( index < grille.size())
//        {
//
//            return true;
//        }
//    }

    //
//    @Override
//    public boolean hasNext()
//    {
//        //level and Instanceof Cellule
//        if(index<grille.size())
//        {
//            if (!(grille.get(index) instanceof Cellule))
//            {
//                TableauDynamiqueND tab = (TableauDynamiqueND) grille.get(index);
//                if (tab.getLevel() == 1)
//                {
//                    return false;
//                }
//                else
//                {
//                    index++;
//                    return true;
//                }
//            }
//            return false;
//            }
//        return false;
//    }
//
//    //hasNext2 ne s'occupe plus de savoir si on accède a des cellules hors case, cette tache est confié aux fct appelantes
//    public boolean hasNext2()
//    {
//        Grillev2 tab_case = grille.get(index);
//        if(tab_case instanceof Cellule && tab_case.getDimension().size() != 1)
//        {
//            return true;
//        }
//        else
//        {
//            index+=1;
//            return false;
//        }
//    }

}

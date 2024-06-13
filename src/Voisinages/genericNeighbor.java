package Voisinages;
import Automate.* ;
import  operateurs.*;

public class genericNeighbor extends Neighbors
{
    public genericNeighbor(Cellule G0, int [][] rules, int dimension,Grillev2 grill) throws InvalidNeighborException {
        super(G0,dimension,rules,grill);
        if(grill.dimension.size() != dimension )
        {
            throw new InvalidNeighborException(grill.dimension.size(), dimension,this);
        }

    }


}

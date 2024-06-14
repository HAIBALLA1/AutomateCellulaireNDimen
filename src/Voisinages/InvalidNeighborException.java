package Voisinages;

public class InvalidNeighborException extends Exception
{
    private int dimension_grille;
    private int dimension_neighbor;
    private Neighbors neighbors;

    public InvalidNeighborException(int dimension1,int dimension2, Neighbors neighbors)
    {
        this.dimension_grille = dimension1;
        this.dimension_neighbor = dimension2;
        this.neighbors = neighbors;
    }

    public String toString()
    {
        return "Erreur : La dimension du voisinage : " + neighbors.getClass() + " requiert une grille de dimension : " + dimension_neighbor +" Mais la dimension de la grille est de " + dimension_grille;
    }
}

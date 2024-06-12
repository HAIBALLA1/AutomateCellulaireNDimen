package projectException;

public class Exception2 extends Exception{
    private int iteration;
    public Exception2(int iteration)
    {
        this.iteration = iteration;
    }

    public String toString()
    {
        return "Alerte boucle infinie, plus de " + iteration + ".";
    }

}

package projectException;

public class Exception1 extends Exception {
    public int d;
    public Exception1() {}
    public Exception1(int d)
    {
        this.d=d;
    }

    public String toString()
    {
        return "projectException.Exception1 - Niveau " + d + ": Impossible de supprimer le dernier élement du tableau dimension.";
    }
}

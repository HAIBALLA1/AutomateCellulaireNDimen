public class COMPTER extends Node {
    private Neighbors voisinage;

    public COMPTER() {}

    public COMPTER(Neighbors voisinage) {
        this.voisinage = voisinage;
    }

    public Neighbors getVoisinage() { return voisinage; }
    public void setVoisinage(Neighbors voisinage) { this.voisinage = voisinage; }

    @Override
    public int evaluer() {
        int count = 0;
        for (Cellule cellule : voisinage.getNeighbors()) {
            if (cellule.evaluer() == 1) {
                count++;
            }
        }
        return count;
    }
}

public abstract class Node {
    protected Node gauche;
    protected Node milieu;
    protected Node droit;

    public Node() {
        // Constructeur par défaut
    }

    public Node(Node gauche, Node milieu, Node droit) {
        this.gauche = gauche;
        this.milieu = milieu;
        this.droit = droit;
    }

    public abstract int evaluer();
}

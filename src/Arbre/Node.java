package Arbre;
import operateurs.RegleGen;

public class Node {
    private RegleGen regle;
    private Node gauche;
    private Node droite;
    private Node milieu;

    public Node(RegleGen regle) {
        this.regle = regle;
    }

    public RegleGen getRegle() {
        return regle;
    }

    public void setRegle(RegleGen regle) {
        this.regle = regle;
    }

    public Node getGauche() {
        return gauche;
    }

    public void setGauche(Node gauche) {
        this.gauche = gauche;
    }

    public Node getDroite() {
        return droite;
    }

    public void setDroite(Node droite) {
        this.droite = droite;
    }

    public Node getMilieu() {
        return milieu;
    }

    public void setMilieu(Node milieu) {
        this.milieu = milieu;
    }
    public int evaluer() {
        return regle.evaluer();
    }
}

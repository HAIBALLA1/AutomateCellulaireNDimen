import projectException.Exception1;
import java.util.ArrayList;
import java.util.Arrays;

public class Testmain {
    public static void main(String[] args) throws Exception1, InvalidNeighborException {
        // Définition des dimensions pour la grille
        ArrayList<Integer> dim = new ArrayList<>(Arrays.asList(4, 3, 2));

        // Création de la grille dynamique multidimensionnelle
        TableauDynamiqueND tab = new TableauDynamiqueND(dim);

        // Vérification de la classe des objets dans le tableau dynamique
        System.out.println("Niveau 0 " + tab.tab_dynamique.get(0).getClass());

        // Initialisation des coordonnées
        tab.set_coord(tab);

        // Définition des coordonnées pour récupérer une cellule
        ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(2, 2, 1));

        // Récupération de la cellule et création du voisinage G0
        Cellule c1 = tab.getCellulev2(coord);
        if (c1 == null) {
            throw new NullPointerException("Cellule c1 est null");
        }
        if (c1.getCoordonnees() == null) {
            throw new NullPointerException("Coordonnées de la cellule c1 sont nulles");
        }

        G8 n1 = new G8(c1,tab);

        G0 voisinageG0 = new G0(c1, tab);
        System.out.println("État de la cellule dans le voisinage G0: " + voisinageG0.evaluer());

        // Affichage des cellules de la grille
        tab.DFS();

        System.out.println("Application de la regle");

        // Test de la construction et de l'évaluation d'un arbre de règles
        String regle = "SI(EQ(COMPTER(G0), 1), SI(SUPEQ(COMPTER(G8), 4), 0, 1), SI(EQ(COMPTER(G8), 2), 1, 0))";
        Node root = Regle.parseRegle(regle,c1,tab);
        System.out.println("Résultat de l'évaluation de la règle: " + root.evaluer());

        // Affichage des cellules de la grille
        tab.DFS();
    }
}

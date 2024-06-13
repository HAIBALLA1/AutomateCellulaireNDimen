import projectException.Exception1;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import Voisinages.*;
import Automate.*;
import InterfaceGraphique.*;
import Arbre.TraitRegle;

public class Testmain {
    public static void main(String[] args) throws Exception1, InvalidNeighborException {
        // Définition des dimensions pour la grille
        ArrayList<Integer> dim = new ArrayList<>(Arrays.asList(40, 30));

        // Création de la grille dynamique multidimensionnelle
        TableauDynamiqueND tab = new TableauDynamiqueND(dim);

        // Initialisation des coordonnées
        tab.set_coord(tab);

        // Initialisation de ColorGrid
        int taillecase = 40;
        int largeur = 30; // Largeur de la grille
        int hauteur = 40; // Hauteur de la grille
        ColorGrid.initialiser(taillecase, largeur, hauteur);

        // Initialiser certaines cellules comme vivantes (1) pour observer les changements
        initialiserGrille(tab);

        // Définir une règle qui provoque des changements intéressants
        String regle = "SI(OU(EQ(COMPTER(G8), 2), EQ(COMPTER(G8), 3)), 1, 0)";

        TraitRegle traitRegle = new TraitRegle();

        int cycles = 500; // Nombre de cycles à exécuter
        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("Cycle " + cycle);

            // Parcourir toutes les cellules du tableau 2D
            for (int i = 0; i < hauteur; i++) {
                for (int j = 0; j < largeur; j++) {
                    // Définition des coordonnées pour récupérer une cellule
                    ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(i, j));

                    // Récupération de la cellule
                    Cellule c1 = tab.getCellulev2(coord);
                    if (c1 == null) {
                        throw new NullPointerException("Cellule c1 est null");
                    }
                    if (c1.getCoordonnees() == null) {
                        throw new NullPointerException("Coordonnées de la cellule c1 sont nulles");
                    }

                    // Construire l'arbre à partir de la règle
                    String[] tokens = traitRegle.construireArbreDepuisRegle(regle);
                    int result = traitRegle.recurs(tokens, new int[]{0}, c1, tab);

                    System.out.println("Résultat de la règle pour la cellule (" + i + ", " + j + ") : " + result);

                    // Mettre à jour la couleur de la cellule en fonction du résultat
                    Color newColor = result == 1 ? Color.WHITE : Color.BLACK;
                    ColorGrid.setCellColor(i, j, newColor, cycle);

                    // Mettre à jour l'état de la cellule en fonction du résultat
                    c1.setEtat(result);
                }
            }

            // Pause pour visualiser les changements
            ColorGrid.pause(0.5);

            // Affichage des cellules de la grille
            tab.DFS();
        }

        // Fermeture de l'affichage
        ColorGrid.stop();
    }

    private static void initialiserGrille(TableauDynamiqueND tab) {
        // Initialiser certaines cellules comme vivantes (1) pour observer les changements
        tab.getCellulev2(new ArrayList<>(Arrays.asList(1, 1))).setEtat(1);
        tab.getCellulev2(new ArrayList<>(Arrays.asList(1, 2))).setEtat(1);
        tab.getCellulev2(new ArrayList<>(Arrays.asList(1, 3))).setEtat(1);
        tab.getCellulev2(new ArrayList<>(Arrays.asList(2, 2))).setEtat(1);
        tab.getCellulev2(new ArrayList<>(Arrays.asList(3, 3))).setEtat(1);
    }
}

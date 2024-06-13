import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import Automate.*;
import InterfaceGraphique.*;
import Arbre.TraitRegle;

public class Testmain {
    public static void main(String[] args) throws Exception {
        // Lire le fichier XML
        File inputFile = new File("/home/ing/Bureau/myProject/src/Fichiers/automate.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // Lire les dimensions
        NodeList dimensionList = doc.getElementsByTagName("dimension");
        ArrayList<Integer> dimensions = new ArrayList<>();
        for (int i = 0; i < dimensionList.getLength(); i++) {
            dimensions.add(Integer.parseInt(dimensionList.item(i).getTextContent()));
        }
        System.out.println(dimensions);
        // Lire la règle
        String regle = doc.getElementsByTagName("Regle").item(0).getTextContent().trim();

        // Lire l'état initial
        String etatInitial = doc.getElementsByTagName("EtatInitial").item(0).getTextContent().trim();

        // Lire les voisinages
        NodeList voisinageList = doc.getElementsByTagName("Voisinage");
        ArrayList<String> voisinages = new ArrayList<>();
        for (int i = 0; i < voisinageList.getLength(); i++) {
            voisinages.add(voisinageList.item(i).getTextContent().trim());
        }

        // Création de la grille dynamique multidimensionnelle
        TableauDynamiqueND tab = new TableauDynamiqueND(dimensions);
        tab.set_coord(tab);

        // Initialisation de ColorGrid
        int taillecase = 20;
        ColorGrid.initialiser(taillecase, dimensions.get(1), dimensions.get(0));

        // Initialiser les cellules de la grille selon l'état initial
        initialiserGrille(tab, etatInitial, dimensions);

        TraitRegle traitRegle = new TraitRegle();

        int cycles = 200; // Nombre de cycles à exécuter
        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("Cycle " + cycle);

            // Parcourir toutes les cellules du tableau 2D
            for (int i = 0; i < dimensions.get(0); i++) {
                for (int j = 0; j < dimensions.get(1); j++) {
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
                    Color newColor = result == 1 ? Color.BLUE : Color.WHITE;
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

    private static void initialiserGrille(TableauDynamiqueND tab, String etatInitial, ArrayList<Integer> dimensions) {
        if (etatInitial.startsWith("RANDOM")) {
            int pourcentage = Integer.parseInt(etatInitial.replaceAll("[^0-9]", ""));
            for (int i = 0; i < dimensions.get(0); i++) {
                for (int j = 0; j < dimensions.get(1); j++) {
                    if (Math.random() * 100 < pourcentage) {
                        tab.getCellulev2(new ArrayList<>(Arrays.asList(i, j))).setEtat(1);
                    } else {
                        tab.getCellulev2(new ArrayList<>(Arrays.asList(i, j))).setEtat(0);
                    }
                }
            }
        }
    }
}

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
        System.out.println(regle);

        // Lire l'état initial
        String etatInitial = doc.getElementsByTagName("EtatInitial").item(0).getTextContent().trim();
        System.out.println(etatInitial);

        // Lire les voisinages
        NodeList voisinageList = doc.getElementsByTagName("Voisinage");
        ArrayList<String> voisinages = new ArrayList<>();
        for (int i = 0; i < voisinageList.getLength(); i++) {
            voisinages.add(voisinageList.item(i).getTextContent().trim());
        }

        // Création de la grille dynamique multidimensionnelle
        TableauDynamiqueND tab = new TableauDynamiqueND(dimensions);
        tab.set_coord(tab);

        // Lire le plan à afficher en 3D, s'il est présent
        int plan = -1;
        if (dimensions.size() >= 3) {
            String planStr = doc.getElementsByTagName("Plan").item(0).getTextContent().trim();
            plan = Integer.parseInt(planStr);
        }


        // Initialisation de ColorGrid
        int taillecase = 20;
        ColorGrid.initialiser(taillecase, dimensions.size() == 1 ? dimensions.get(0) : dimensions.get(1), dimensions.get(0));

        // Initialiser les cellules de la grille selon l'état initial
        initialiserGrille(tab, etatInitial, dimensions);
        afficherEtatsDesCellules(tab, dimensions);


        TraitRegle traitRegle = new TraitRegle();

        int cycles = 50; // Nombre de cycles à exécuter

        //verifier quelle dimension est en le fichier xml
        if (dimensions.size() == 1) {
            afficherAutomate1D(tab, dimensions.get(0), cycles, traitRegle);
        } else if (dimensions.size() == 2) {
            afficherAutomate2D(tab, dimensions, cycles, traitRegle, regle);
        } else if (dimensions.size() == 3) {
                afficherAutomate3D(tab, dimensions, cycles, traitRegle, regle, plan);
        }

        // Fermeture de l'affichage
        ColorGrid.stop();
    }

    private static void initialiserGrille(TableauDynamiqueND tab, String etatInitial, ArrayList<Integer> dimensions) {
        if (etatInitial.startsWith("RANDOM")) {
            int pourcentage = Integer.parseInt(etatInitial.replaceAll("[^0-9]", ""));
            for (int i = 0; i < dimensions.get(0); i++) {
                if (dimensions.size() == 2) {
                    for (int j = 0; j < dimensions.get(1); j++) {
                        if (Math.random() * 100 < pourcentage) {
                            tab.getCellulev2(new ArrayList<>(Arrays.asList(i, j))).setEtat(1);
                        } else {
                            tab.getCellulev2(new ArrayList<>(Arrays.asList(i, j))).setEtat(0);
                        }
                    }
                } else {
                    if (Math.random() * 100 < pourcentage) {
                        tab.getCellulev2(new ArrayList<>(Arrays.asList(i))).setEtat(1);
                    } else {
                        tab.getCellulev2(new ArrayList<>(Arrays.asList(i))).setEtat(0);
                    }
                }
            }
        } else if (etatInitial.startsWith("(") && etatInitial.endsWith(")")) {
            String[] coordonnees = etatInitial.substring(1, etatInitial.length() - 1).split(",");
            if (coordonnees.length == 2 && dimensions.size() == 2) {
                try {
                    int x = Integer.parseInt(coordonnees[0].trim());
                    int y = Integer.parseInt(coordonnees[1].trim());
                    if (x >= 0 && x < dimensions.get(0) && y >= 0 && y < dimensions.get(1)) {
                        tab.getCellulev2(new ArrayList<>(Arrays.asList(x, y))).setEtat(1);
                    } else {
                        System.err.println("Coordonnées hors limites: (" + x + ", " + y + ")");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format des coordonnées: " + etatInitial);
                }
            } else if (coordonnees.length == 1 && dimensions.size() == 1) {
                try {
                    int x = Integer.parseInt(coordonnees[0].trim());
                    if (x >= 0 && x < dimensions.get(0)) {
                        tab.getCellulev2(new ArrayList<>(Arrays.asList(x))).setEtat(1);
                    } else {
                        System.err.println("Coordonnées hors limites: (" + x + ")");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format des coordonnées: " + etatInitial);
                }
            } else {
                System.err.println("Erreur de format des coordonnées: " + etatInitial);
            }
        } else {
            System.err.println("Format d'état initial non reconnu: " + etatInitial);
        }
    }

    private static void afficherEtatsDesCellules(TableauDynamiqueND tab, ArrayList<Integer> dimensions) {
        for (int i = 0; i < dimensions.get(0); i++) {
            if (dimensions.size() == 2) {
                for (int j = 0; j < dimensions.get(1); j++) {
                    Cellule cell = tab.getCellulev2(new ArrayList<>(Arrays.asList(i, j)));
                    System.out.println("Cellule (" + i + ", " + j + ") - Etat : " + cell.getStatus());
                }
            } else {
                Cellule cell = tab.getCellulev2(new ArrayList<>(Arrays.asList(i)));
                System.out.println("Cellule (" + i + ") - Etat : " + cell.getStatus());
            }
        }
    }



    private static void afficherAutomate2D(TableauDynamiqueND tab, ArrayList<Integer> dimensions, int cycles, TraitRegle traitRegle, String regle) throws Exception {
        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("Cycle " + cycle);

            // Création d'une copie du tableau des cellules
            TableauDynamiqueND newTab = new TableauDynamiqueND(dimensions);
            newTab.set_coord(newTab);

            // Appliquer les règles à la copie
            for (int i = 0; i < dimensions.get(0); i++) {
                for (int j = 0; j < dimensions.get(1); j++) {
                    ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(i, j));
                    Cellule c1 = tab.getCellulev2(coord);
                    if (c1 == null || c1.getCoordonnees() == null) {
                        throw new IllegalArgumentException("Cellule c1 ou ses coordonnées sont nulles");
                    }

                    String[] tokens = traitRegle.construireArbreDepuisRegle(regle);
                    int result = traitRegle.recurs(tokens, new int[]{0}, c1, tab);

                    Cellule newCell = newTab.getCellulev2(coord);
                    newCell.setEtat(result);
                }
            }

            // Mettre à jour les cellules actuelles à partir de la copie
            for (int i = 0; i < dimensions.get(0); i++) {
                for (int j = 0; j < dimensions.get(1); j++) {
                    ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(i, j));
                    Cellule c1 = tab.getCellulev2(coord);
                    Cellule newCell = newTab.getCellulev2(coord);

                    if (c1.getStatus() != newCell.getStatus()) {
                        c1.setEtat(newCell.getStatus());
                        Color newColor = newCell.getStatus() == 1 ? Color.BLUE : Color.WHITE;
                        ColorGrid.setCellColor(i, j, newColor, cycle);
                    }
                }
            }

            // Pause pour visualiser les changements
            ColorGrid.pause(0.1);

            // Affichage des cellules de la grille
            tab.DFS();

            // Pause pour mieux visualiser les différences entre les cycles
            Thread.sleep(50); // 50 millisecondes
        }
    }



    private static void afficherAutomate1D(TableauDynamiqueND tab, int taille, int cycles, TraitRegle traitRegle) throws Exception {
        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("Cycle " + cycle);
            TableauDynamiqueND newtab = new TableauDynamiqueND(new ArrayList<>(Arrays.asList(taille)));
            for (int i = 0; i < taille; i++) {
                ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(i));
                Cellule c1 = tab.getCellulev2(coord);
                if (c1 == null) throw new NullPointerException("Cellule c1 est null");
                if (c1.getCoordonnees() == null) throw new NullPointerException("Coordonnées de la cellule c1 sont nulles");
                int left = (i == 0) ? 0 : tab.getCellulev2(new ArrayList<>(Arrays.asList(i - 1))).getStatus();
                int center = c1.getStatus();
                int right = (i == taille - 1) ? 0 : tab.getCellulev2(new ArrayList<>(Arrays.asList(i + 1))).getStatus();
                int result = (left + center + right) > 0 ? 1 : 0; // Règle de Sierpiński simple
                System.out.println("Résultat de la règle pour la cellule (" + i + ") : " + result);
                Color newColor = result == 1 ? Color.BLUE : Color.WHITE;
                ColorGrid.setCellColor(i, cycle, newColor, cycle);
                newtab.getCellulev2(coord).setEtat(result);
            }
            ColorGrid.pause(0.1);
            tab = newtab;
            tab.DFS();
            Thread.sleep(50); // 50 millisecondes
        }
    }

    private static void afficherAutomate3D(TableauDynamiqueND tab, ArrayList<Integer> dimensions, int cycles, TraitRegle traitRegle, String regle, int plan) throws InterruptedException {
        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("Cycle " + cycle);
            for (int i = 0; i < dimensions.get(0); i++) {
                for (int j = 0; j < dimensions.get(1); j++) {
                    // Définition des coordonnées pour récupérer une cellule
                    ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(i, j, plan));

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

                    System.out.println("Résultat de la règle pour la cellule (" + i + ", " + j + ", " + plan + ") : " + result);

                    // Mettre à jour la couleur de la cellule en fonction du résultat
                    Color newColor = result == 1 ? Color.BLUE : Color.WHITE;
                    ColorGrid.setCellColor(i, j, newColor, cycle);

                    // Mettre à jour l'état de la cellule en fonction du résultat
                    c1.setEtat(result);
                }
            }

            // Pause pour visualiser les changements
            ColorGrid.pause(0.1);

            // Affichage des cellules de la grille
            tab.DFS();

            // Pause pour mieux visualiser les différences entre les cycles
            Thread.sleep(500);
        }
    }
}


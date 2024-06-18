package Arbre;
import operateurs.*;
import Voisinages.*;
import Automate.*;

public class TraitRegle {

    // une methode pour construire un arbre depuis les tokens de la regle
    public String [] construireArbreDepuisRegle(String regle) {

        String[] tokens = regle.split("(?<=[,()])|(?=[,()])|\\s+");// Un tableau de chaînes de caractères représentant les tokens de la regle

        return tokens;
    }

     // une methode recursive pour parcourir les tokens recuperes de la regle et les evaluer
    public int recurs(String [] tokens,int[] index,Cellule cellule, Grillev2 grille) {

        int f = 0;//variable utilisee comme valeur par défaut si aucun autre calcul n'est effectué

        //condition d'arret , elle varifie si la long des tokens est depassee. Si c'est le cas, cela signifie que nous avons parcouru tous les tokens
        if (index[0] >= tokens.length) {
            return f;
        }

        //le tokens actuel est lu a l'aide du index[0] puis ce dernier est incrumente.trim() est utilise pour enlever le sespaces.
        String token = tokens[index[0]++].trim();

        // un swich pour determiner l'action a effectuer  en fonction du token lu
        switch (token) {
            case "ADD":
                return evaluerOperation(tokens, index, "ADD",cellule,  grille);
            case "SI":
                return evaluerOperationSI(tokens, index,cellule,  grille);
            case "ET":
                return evaluerOperation(tokens, index, "ET",cellule,  grille);
            case "OU":
                return evaluerOperation(tokens, index, "OU",cellule,  grille);
            case "NON":
                return evaluerOperationNon(tokens, index,cellule,  grille);
            case "EQ":
                return evaluerOperation(tokens, index, "EQ",cellule,  grille);
            case "SUP":
                return evaluerOperation(tokens, index, "SUP",cellule,  grille);
            case "SUPEQ":
                return evaluerOperation(tokens, index, "SUPEQ",cellule,  grille);
            case "SUB":
                return evaluerOperation(tokens, index, "SUB",cellule,  grille);
            case "MUL":
                return evaluerOperation(tokens, index, "MUL",cellule,  grille);
            case "COMPTER":
                return evaluerOperationCOMPTER(tokens,index,  cellule,  grille);

            case "(":// appelle récursivement  de la methode recurs et vérification de la correspondance des parenthèses fermantes
                int result = recurs(tokens, index,cellule,grille);
                if (index[0] < tokens.length && tokens[index[0]].trim().equals(")")) {
                    index[0]++; // on saute ')'
                }
                return result;

            case ",": //Si une virgule est rencontrée, la méthode appelle récursivement recurs pour évaluer le token suivant
                return recurs(tokens, index,cellule,grille);
            default:
                try {
                    return Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("le token n'est pas valide: " + token);
                }
        }
    }

    // Une methode pour evaluer tous les operateurs binaires sauf COMPTER , SI et NON
    private int evaluerOperation(String[] tokens, int[] index, String operation,Cellule cellule, Grillev2 grille) {

        //vérification et saut de la parenthèse ouvrante
        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on saute  '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres " + operation);
        }

        //évaluation de l'opérande gauche
        int gauche = recurs(tokens, index,cellule,grille);
        if (tokens[index[0]].trim().equals(",")) {
            index[0]++; // on saute ','
        } else {
            throw new IllegalArgumentException("on doit avoir ',' dans  " + operation);
        }

        //Évaluation de l'opérande droite
        int droite = recurs(tokens, index,cellule,grille);

        //vérification et saut de la virgule
        if (index[0] < tokens.length && tokens[index[0]].trim().equals(")")) {
            index[0]++; // on saute ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres " + operation);
        }

        // on switch sur les operateurs
        switch (operation) {
            case "ADD":
                return new ADD(gauche, droite).evaluer();
            case "ET":
                return new ET(gauche, droite).evaluer();
            case "OU":
                return new OU(gauche, droite).evaluer();
            case "EQ":
                return new EQ(gauche, droite).evaluer();
            case "SUP":
                return new SUP(gauche, droite).evaluer();
            case "SUPEQ":
                return new SUPEQ(gauche, droite).evaluer();
            case "SUB":
                return new SUB(gauche, droite).evaluer();
            case "MUL":
                return new MUL(gauche, droite).evaluer();
            default:
                throw new IllegalArgumentException("operation non connue: " + operation);
        }
    }

    // evaluation de l'op SI
    private int evaluerOperationSI(String[] tokens, int[] index,Cellule cellule, Grillev2 grille) {

        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on saute'('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres SI");
        }

        // La condition de l'expression "SI" est évaluée en appelant récursivement la méthode recurs
        int condition = recurs(tokens, index,cellule,  grille);

        if (tokens[index[0]].trim().equals(",")) {
            index[0]++; // on saute ',' index un  tableau contenant un seul élément, qui représente l'indice actuel dans le tab de tokens (utilisé pour maintenir l'état entre les appels récursifs)
        } else {
            throw new IllegalArgumentException("on doit avoir ',' dans SI");
        }

         //L'expression "alors" de l'expression "SI" est évaluée en appelant récursivement la méthode recurs
        int alors = recurs(tokens, index,cellule,  grille);

        if (tokens[index[0]].trim().equals(",")) {
            index[0]++; // on saute ','
        } else {
            throw new IllegalArgumentException("on doit avoir ',' dans SI");
        }

        //L'expression "sinon" de l'expression "SI" est évaluée en appelant récursivement la méthode recurs
        int sinon = recurs(tokens, index,cellule,  grille);

        if (index[0] < tokens.length && tokens[index[0]].trim().equals(")")) {
            index[0]++; // on saute ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres SI");
        }

        return new SI(condition, alors, sinon).evaluer();
    }

    // un methode pour evaluer l'op NON
    private int evaluerOperationNon(String[] tokens, int[] index,Cellule cellule, Grillev2 grille) {

        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on saute '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres NON");
        }

        int val = recurs(tokens, index,cellule,  grille);

        if (index[0] < tokens.length && tokens[index[0]].trim().equals(")")) {
            index[0]++; // on saute ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres NON");
        }
        return new NON(val).evaluer();
    }

    //Methode pour Evaluer COMPTER
    private int evaluerOperationCOMPTER(String[] tokens, int[] index, Cellule cellule, Grillev2 grille) {

        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on saute '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' après COMPTER");
        }

         //pour eviter un objet Cellule Null
        if (cellule == null || cellule.getCoordonnees() == null || cellule.getCoordonnees().isEmpty()) {
            throw new IllegalArgumentException("Cellule G0 ou ses coordonnées sont nulles ou vides");
        }

        // Le token suivant, qui représente le type de voisinage (G0, G2, G4, G6, G8, G26), est récupéré et l'indice est incrémenté
        String voisinageToken = tokens[index[0]++].trim();
        Neighbors voisinage;

        switch (voisinageToken) {
            case "G0":
                voisinage = new G0(cellule, grille);
                break;
            case "G2":
                voisinage = new G2(cellule, grille);
                break;
            case "G4":
                voisinage = new G4(cellule, grille);
                break;
            case "G6":
                voisinage = new G6(cellule, grille);
                break;
            case "G8":
                voisinage = new G8(cellule, grille);
                break;
            case "G26":
                voisinage = new G26(cellule, grille);
                break;
            default:
                throw new IllegalArgumentException("voisins non connu type: " + voisinageToken);
        }

        if (tokens[index[0]].trim().equals(")")) {
            index[0]++; // on saute ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' après COMPTER ");
        }

        return new COMPTER(voisinage).evaluer();
    }

}

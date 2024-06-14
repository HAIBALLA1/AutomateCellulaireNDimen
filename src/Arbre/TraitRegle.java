package Arbre;
import operateurs.*;
import Voisinages.*;
import Automate.*;

public class TraitRegle {

    public String [] construireArbreDepuisRegle(String regle) {
        String[] tokens = regle.split("(?<=[,()])|(?=[,()])|\\s+");
        return tokens;
    }

    public int recurs(String [] tokens,int[] index,Cellule cellule, Grillev2 grille) {
        int f = 0;
        //condition d'arret
        if (index[0] >= tokens.length) {
            return f;
        }
        String token = tokens[index[0]++].trim();

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
            case "(":
                int result = recurs(tokens, index,cellule,grille);
                if (tokens[index[0]].trim().equals(")")) {
                    index[0]++; // Skip ')'
                }
                return result;
            case ",":
                return recurs(tokens, index,cellule,grille);
            default:
                try {
                    return Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("le token n'est pas valide: " + token);
                }
        }
    }
    private int evaluerOperation(String[] tokens, int[] index, String operation,Cellule cellule, Grillev2 grille) {
        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on sote  '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres " + operation);
        }
        int gauche = recurs(tokens, index,cellule,grille);
        if (tokens[index[0]].trim().equals(",")) {
            index[0]++; // on sote ','
        } else {
            throw new IllegalArgumentException("on doit avoir ',' dans  " + operation);
        }
        int droite = recurs(tokens, index,cellule,grille);

        if (tokens[index[0]].trim().equals(")")) {
            index[0]++; // on sote ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres " + operation);
        }

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
    private int evaluerOperationSI(String[] tokens, int[] index,Cellule cellule, Grillev2 grille) {
        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on sote '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres SI");
        }
        int condition = recurs(tokens, index,cellule,  grille);

        if (tokens[index[0]].trim().equals(",")) {
            index[0]++; // on sote ','
        } else {
            throw new IllegalArgumentException("on doit avoir ',' dans SI");
        }

        int alors = recurs(tokens, index,cellule,  grille);

        if (tokens[index[0]].trim().equals(",")) {
            index[0]++; // on sote ','
        } else {
            throw new IllegalArgumentException("on doit avoir ',' dans SI");
        }
        int sinon = recurs(tokens, index,cellule,  grille);

        if (tokens[index[0]].trim().equals(")")) {
            index[0]++; // on sote ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres SI");
        }
        return new SI(condition, alors, sinon).evaluer();
    }
    private int evaluerOperationNon(String[] tokens, int[] index,Cellule cellule, Grillev2 grille) {
        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on sote '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres NON");
        }
        int val = recurs(tokens, index,cellule,  grille);

        if (tokens[index[0]].trim().equals(")")) {
            index[0]++; // on sote ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres NON");
        }
        return new NON(val).evaluer();
    }
    private int evaluerOperationCOMPTER(String[] tokens, int[] index, Cellule cellule, Grillev2 grille) {
        if (tokens[index[0]].trim().equals("(")) {
            index[0]++; // on sote '('
        } else {
            throw new IllegalArgumentException("on doit avoir '(' apres COMPTER");
        }
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
            index[0]++; // on sote ')'
        } else {
            throw new IllegalArgumentException("on doit avoir ')' apres COMPTER ");
        }

        return new COMPTER(voisinage).evaluer();
    }


}

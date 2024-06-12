import java.util.*;

public class Regle {

    public static Node parseRegle(String regle, Cellule cellule, Grillev2 grille) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> parentStack = new Stack<>();
        String[] tokens = regle.split("\\s*(,|\\(|\\))\\s*|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=,)|(?=,)");

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) {
                continue; // Ignore empty tokens
            }
            switch (token) {
                case "(":
                    // Push the current node to parent stack and reset stack for new subtree
                    if (!stack.isEmpty()) {
                        parentStack.push(stack.peek());
                    }
                    break;
                case ")":
                    // Pop from parent stack to continue evaluation at the parent level
                    if (!parentStack.isEmpty()) {
                        Node completedNode = stack.pop();
                        Node parentNode = parentStack.pop();
                        if (parentNode instanceof SI) {
                            SI siNode = (SI) parentNode;
                            if (siNode.getVal1() == null) {
                                siNode.setVal1(completedNode);
                            } else if (siNode.getVal2() == null) {
                                siNode.setVal2(completedNode);
                            } else {
                                siNode.setVal3(completedNode);
                            }
                        }
                        stack.push(parentNode);
                    }
                    break;
                case ",":
                    // No action needed; it indicates separation of parameters
                    break;
                case "SI":
                case "EQ":
                case "SUPEQ":
                case "COMPTER":
                case "ET":
                case "OU":
                case "NON":
                case "SUP":
                case "ADD":
                case "SUB":
                case "MUL":
                    // Create a new node based on the operator
                    Node node = createNode(token, stack);
                    if (node != null) {
                        stack.push(node);
                    }
                    break;
                case "G0":
                    // Create a new G0 instance
                    stack.push(new G0(cellule, grille));
                    break;
                case "G8":
                    // Create a new G8 instance
                    stack.push(new G8(cellule, grille));
                    break;
                default:
                    // Handle numerical values or variable names
                    try {
                        int value = Integer.parseInt(token);
                        stack.push(new Constant(value));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Token non reconnu : " + token);
                    }
                    break;
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("La règle est vide ou mal formée");
        }
        return stack.pop();
    }

    private static Node createNode(String token, Stack<Node> stack) {
        switch (token) {
            case "SI":
                return new SI();
            case "EQ":
                return new EQ();
            case "SUPEQ":
                return new SUPEQ();
            case "COMPTER":
                return new COMPTER();
            case "ET":
                return new ET();
            case "OU":
                return new OU();
            case "NON":
                return new NON();
            case "SUP":
                return new SUP();
            case "ADD":
                return new ADD();
            case "SUB":
                return new SUB();
            case "MUL":
                return new MUL();
            default:
                throw new IllegalArgumentException("Opérateur inconnu: " + token);
        }
    }
}
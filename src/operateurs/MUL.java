public class MUL extends Node {
    private Node val1;
    private Node val2;

    public MUL() {}

    public MUL(Node val1, Node val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public Node getVal1() { return val1; }
    public void setVal1(Node val1) { this.val1 = val1; }
    public Node getVal2() { return val2; }
    public void setVal2(Node val2) { this.val2 = val2; }

    @Override
    public int evaluer() {
        return val1.evaluer() * val2.evaluer();
    }
}

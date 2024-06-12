public class NON extends Node {
    private Node val;

    public NON() {}

    public NON(Node val) {
        this.val = val;
    }

    public Node getVal() { return val; }
    public void setVal(Node val) { this.val = val; }

    @Override
    public int evaluer() {
        return val.evaluer() == 0 ? 1 : 0;
    }
}

public class SI extends Node {
    private Node val1;
    private Node val2;
    private Node val3;

    public SI() {}

    public SI(Node val1, Node val2, Node val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }

    public Node getVal1() { return val1; }
    public void setVal1(Node val1) { this.val1 = val1; }
    public Node getVal2() { return val2; }
    public void setVal2(Node val2) { this.val2 = val2; }
    public Node getVal3() { return val3; }
    public void setVal3(Node val3) { this.val3 = val3; }

    @Override
    public int evaluer() {
        int v1 = val1.evaluer();
        int v2 = val2.evaluer();
        int v3 = val3.evaluer();
        System.out.println("SI: val1=" + v1 + ", val2=" + v2 + ", val3=" + v3);
        return v1 != 0 ? v2 : v3;
    }
}
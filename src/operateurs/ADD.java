package operateurs;
import Arbre.*;

public class ADD extends RegleGen {


    public ADD() {};

    public ADD(int val1, int val2) {
        super(val1, val2);

    }

    public int getVal1() { return val1; }
    public void setVal1(int val1) { this.val1 = val1; }
    public int getVal2() { return val2; }
    public void setVal2(int val2) { this.val2 = val2; }

    @Override
    public int evaluer() {
        return val1+ val2;
    }
}


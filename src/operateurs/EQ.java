package operateurs;
import Arbre.*;

public class EQ extends RegleGen {




    public EQ(int val1, int val2) {
        super(val1, val2);

    }

    public int getVal1() { return super.val1; }

    public int getVal2() { return super.val2; }


    @Override
    public int evaluer() {

        return val1 == val2 ? 1 : 0;
    }
}

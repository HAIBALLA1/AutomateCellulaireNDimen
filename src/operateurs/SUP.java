package operateurs;
import Arbre.*;

public class SUP extends RegleGen {

    public SUP(int val1, int val2) {
        super(val1, val2);
    }

    @Override
    public int evaluer() {
        return val1 > val2 ? 1 : 0;
    }
}

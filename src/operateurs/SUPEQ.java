package operateurs;

public class SUPEQ extends RegleGen {

    public SUPEQ(int val1, int val2) {
        super(val1, val2);
    }

    @Override
    public int evaluer() {
        return val1 >= val2 ? 1 : 0;
    }
}

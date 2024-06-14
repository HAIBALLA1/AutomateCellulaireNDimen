package operateurs;

public class SUB extends RegleGen {

    public SUB(int val1, int val2) {
        super(val1, val2);
    }

    @Override
    public int evaluer() {
        return val1 - val2;
    }
}

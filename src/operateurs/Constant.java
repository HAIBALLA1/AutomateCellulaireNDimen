package operateurs;

public class Constant extends RegleGen {
    private final int value;

    public Constant(int value) {

        this.value = value;
    }

    @Override
    public int evaluer() {
        return value;
    }
}

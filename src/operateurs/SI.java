package operateurs;

public class SI extends RegleGen {
    public int val3;
    public SI(int val1, int val2,int val3) {
        super(val1, val2);
        this.val3=val3;
    }

    @Override
    public int evaluer() {
        return val1 != 0 ? val2 : val3;
    }
}

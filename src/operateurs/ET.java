package operateurs;

public class ET extends RegleGen {
    public ET(int val1, int val2) {
        super(val1, val2);
    }

    @Override
    public int evaluer() {
        if(val1==1 && val2==1)
        {
            return 1;
        }
        return 0;
    }
}

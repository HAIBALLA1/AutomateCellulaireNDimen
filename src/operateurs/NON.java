package operateurs;

public class NON extends RegleGen {
    public int val;
    public NON(int val) {
       super();

       this.val = val;
    }

    @Override
    public int evaluer() {
        if (val==0)
        {
            return 1;
        }
        return 0;
    }
}


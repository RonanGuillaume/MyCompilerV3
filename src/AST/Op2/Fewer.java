package AST.Op2;

/**
 * Created by ronan
 * on 03/08/2017.
 */
public class Fewer extends Op2 {
    private Eq_A eq_a;

    public Fewer(Eq_A eq_a) {
        this.eq_a = eq_a;
    }

    @Override
    public String toString() {
        String result = "<";
        if (eq_a != null){
            result += "= ";
        }
        return result;
    }
}

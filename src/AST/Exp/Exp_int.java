package AST.Exp;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Exp_int extends Exp {
    private int number;

    public Exp_int(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}

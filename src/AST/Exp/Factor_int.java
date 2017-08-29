package AST.Exp;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_int extends Factor {
    private int number;

    public Factor_int(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}

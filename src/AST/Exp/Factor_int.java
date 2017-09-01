package AST.Exp;

import AST.Type.Basic_Int;
import AST.Type.Type;

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
    public Type getType() {
        return new Basic_Int();
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}

package AST.Exp;

import AST.Type.Basic_Bool;
import AST.Type.Type;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_false extends Factor {
    public Factor_false() {
    }

    @Override
    public Type getType() {
        return new Basic_Bool();
    }

    @Override
    public String toString() {
        return "False";
    }
}

package AST;

import AST.Exp.Exp;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class FunCall_Field extends FunCall {
    private Field field;
    private Exp exp;

    public FunCall_Field(Field field, Exp exp) {
        this.field = field;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return field.toString() + " = " + exp.toString();
    }
}

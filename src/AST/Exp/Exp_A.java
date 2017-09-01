package AST.Exp;

import AST.*;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Exp_A extends AST {
    private Exp exp;

    public Exp_A(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return exp.toString();
    }
}

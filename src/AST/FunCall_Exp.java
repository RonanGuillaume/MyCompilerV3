package AST;

import AST.Exp.Exp;

/**
 * Created by ronan
 * on 01/09/2017.
 */
public class FunCall_Exp extends FunCall {
    private Exp exp;

    public FunCall_Exp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "= " + exp;
    }
}

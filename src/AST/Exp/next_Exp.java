package AST.Exp;

import AST.AST;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class next_Exp extends AST {
    private Exp exp;

    public next_Exp(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return ", " + exp;
    }
}

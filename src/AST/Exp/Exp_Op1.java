package AST.Exp;

import AST.Op1.Op1;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class Exp_Op1 extends Exp {
    private Op1 op1;
    private Exp exp;

    public Exp_Op1(Op1 op1, Exp exp) {
        this.op1 = op1;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "" + op1 + " " + exp;
    }
}

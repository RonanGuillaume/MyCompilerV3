package AST;

import AST.Exp.Exp;
import AST.Op2.Op2;

/**
 * Created by ronan
 * on 28/08/2017.
 */
public class second_Exp extends AST {
    private Op2 op2;
    private Exp exp;

    public second_Exp(Op2 op2, Exp exp) {
        this.op2 = op2;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return op2.toString() + " " + exp.toString();
    }
}

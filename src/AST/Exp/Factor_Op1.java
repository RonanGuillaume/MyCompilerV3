package AST.Exp;

import AST.Op1.Op1;
/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_Op1 extends Factor {
    private Op1 op1;
    private Factor factor;

    public Factor_Op1(Op1 op1, Factor factor) {
        this.op1 = op1;
        this.factor = factor;
    }

    @Override
    public String toString() {
        return op1.toString() + " " + factor.toString();
    }
}

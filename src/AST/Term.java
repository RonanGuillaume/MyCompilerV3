package AST;

import AST.Exp.Factor;
import AST.Op2.Op2;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Term extends AST {
    private Op2 op2;
    private Factor factor;
    private Term term;

    public Term(Op2 op2, Factor factor, Term term) {
        this.op2 = op2;
        this.factor = factor;
        this.term = term;
    }

    @Override
    public String toString() {
        String result = op2.toString() + factor.toString();

        if (term != null){
            result += term;
        }

        return result;
    }
}

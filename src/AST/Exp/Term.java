package AST.Exp;

import AST.*;
import AST.Op2.Op2;
import AST.Type.Type;
import AST.Type.Type_List;
import AST.Type.Type_poly;

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

    public Op2 getOp2() {
        return op2;
    }

    public Type getType(){
        if (factor.getType().equals(new Type_List(new Type_poly()))){
            return factor.getType();
        }
        else {
            return new Type_List(factor.getType());
        }
    }

    @Override
    public String toString() {
        String result = op2.toString() + " " + factor.toString();

        if (term != null){
            result += " " + term;
        }

        return result;
    }
}

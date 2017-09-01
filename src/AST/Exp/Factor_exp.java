package AST.Exp;

import AST.Type.Type;
import AST.Type.Type_Pair;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_exp extends Factor {
    private Exp exp;
    private next_Exp next_exp;

    public Factor_exp(Exp exp, next_Exp next_exp) {
        this.exp = exp;
        this.next_exp = next_exp;
    }

    @Override
    public Type getType() {
        return new Type_Pair(exp.getType(), next_exp.getExp().getType());
    }

    @Override
    public String toString() {
        String result = "(" + exp;

        if (next_exp != null){
            result += " " + next_exp.toString();
        }

        result += ")";

        return result;
    }
}

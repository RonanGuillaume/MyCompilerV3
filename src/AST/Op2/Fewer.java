package AST.Op2;

import AST.RetType;
import AST.RetType_Type;
import AST.Type.Basic_Bool;
import AST.Type.Basic_Int;
import AST.Type.Type;
import AST.Type.Type_Basic;

/**
 * Created by ronan
 * on 03/08/2017.
 */
public class Fewer extends Op2 {
    private Eq_A eq_a;

    public Fewer(Eq_A eq_a) {
        this.eq_a = eq_a;
    }

    @Override
    public RetType getResultType() {
        return new RetType_Type(new Type_Basic(new Basic_Bool()));
    }

    @Override
    public Type getInputType() {
        return new Type_Basic(new Basic_Int());
    }

    @Override
    public String toString() {
        String result = "<";
        if (eq_a != null){
            result += "= ";
        }
        return result;
    }
}

package AST.Op1;

import AST.RetType;
import AST.RetType_Type;
import AST.Type.Basic_Int;
import AST.Type.Type;
import AST.Type.Type_Basic;

/**
 * Created by ronan
 * on 03/08/2017.
 */
public class Minus extends Op1 {
    public Minus() {
    }

    @Override
    public RetType getResultType() {
        return new RetType_Type(new Type_Basic(new Basic_Int()));
    }

    @Override
    public Type getInputType() {
        return new Type_Basic(new Basic_Int());
    }

    @Override
    public String toString() {
        return " - ";
    }
}

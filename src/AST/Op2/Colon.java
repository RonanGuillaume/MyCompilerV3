package AST.Op2;

import AST.RetType;
import AST.RetType_Type;
import AST.Type.Type;
import AST.Type.Type_Basic;
import AST.Type.Type_List;
import AST.Type.Type_poly;

/**
 * Created by ronan
 * on 03/08/2017.
 */
public class Colon extends Op2 {
    public Colon() {
    }

    @Override
    public RetType getResultType() {
        return new RetType_Type(new Type_List(new Type_poly()));
    }

    @Override
    public Type getInputType() {
        return new Type_poly();
    }

    @Override
    public String toString() {
        return ":";
    }
}

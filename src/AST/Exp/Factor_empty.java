package AST.Exp;

import AST.Type.Type;
import AST.Type.Type_List;
import AST.Type.Type_poly;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public class Factor_empty extends Factor{
    public Factor_empty() {
    }

    @Override
    public Type getType() {
        return new Type_List(new Type_poly());
    }

    @Override
    public String toString() {
        return "[]";
    }
}

package AST;

import AST.Type.Type;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public abstract class VarDecl extends AST{
    public abstract Type getType();

    public abstract String getName();
}

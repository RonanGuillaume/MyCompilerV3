package AST.Exp;

import AST.AST;
import AST.Type.Type;

/**
 * Created by ronan
 * on 29/08/2017.
 */
public abstract class Factor extends AST{
    public abstract Type getType();
}

package AST.Op1;

import AST.*;
import AST.Type.Type;

/**
 * Created by ronan
 * on 03/08/2017.
 */
public abstract class Op1 extends AST{

    public abstract RetType getResultType();

    public abstract Type getInputType();
}

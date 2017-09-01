package AST;

import AST.Type.Type;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public abstract class VarDecl extends AST{
    public abstract Type getType();

    public abstract String getName();

    public boolean equals(Object o){
        if (o == null || (o.getClass() != VarDecl_type.class && o.getClass() != VarDecl_id.class)){
            return false;
        }
        return getName().equals(((VarDecl)o).getName());
    }
}

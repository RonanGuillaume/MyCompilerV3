package AST;

import AST.Exp.Exp;
import AST.Type.Type;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class VarDecl_type extends VarDecl {
    private Type type;
    private String id;
    private Exp exp;

    public VarDecl_type(Type type, String id, Exp exp) {
        this.type = type;
        this.id = id;
        this.exp = exp;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return id;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return "" + type + " " + id + " = " + exp + ";\n";
    }
}

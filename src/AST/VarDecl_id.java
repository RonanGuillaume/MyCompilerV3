package AST;

import AST.Exp.Exp;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class VarDecl_id extends VarDecl {
    private String id;
    private Exp exp;

    public VarDecl_id(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "var " + id + " = " + exp + ";\n";
    }
}

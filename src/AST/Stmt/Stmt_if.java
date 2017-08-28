package AST.Stmt;

import AST.Exp.Exp;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_if extends Stmt {
    private Exp exp;
    private ArrayList<Exp> exps;
    private Else_Stmt else_stmt_;

    public Stmt_if(Exp exp, Else_Stmt else_stmt_) {
        this.exp = exp;
        this.else_stmt_ = else_stmt_;

        this.exps = new ArrayList<>();
    }

    public void addExp (Exp exp){
        exps.add(exp);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("if (" + exp + "){\n");
        for (Exp exp:exps) {
            result.append(exp).append("\n");
        }
        result.append("}\n");
        if (else_stmt_ != null){
            result.append(else_stmt_).append("\n");
        }
        return result.toString();
    }
}

package AST.Stmt;

import AST.Exp.Exp;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_if extends Stmt {
    private Exp exp;
    private ArrayList<Stmt> stmts;
    private Else_Stmt else_stmt;

    public Stmt_if(Exp exp) {
        this.exp = exp;
        this.else_stmt = null;

        this.stmts = new ArrayList<>();
    }

    public void addStmt(Stmt stmt){
        stmts.add(stmt);
    }

    public void addElseStmt(Else_Stmt else_stmt){
        this.else_stmt = else_stmt;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("if (" + exp + "){\n");
        for (Stmt stmt: stmts) {
            result.append(exp).append("\n");
        }
        result.append("}\n");
        if (else_stmt != null){
            result.append(else_stmt).append("\n");
        }
        return result.toString();
    }
}

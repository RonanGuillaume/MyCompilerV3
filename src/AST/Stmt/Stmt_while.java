package AST.Stmt;

import AST.Exp.Exp;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 06/08/2017.
 */
public class Stmt_while extends Stmt {
    private Exp exp;
    private ArrayList<Stmt> stmts;

    public Stmt_while(Exp exp) {
        this.exp = exp;

        stmts = new ArrayList<>();
    }

    public void addStmt(Stmt stmt){
        stmts.add(stmt);
    }

    public Exp getCondition() {
        return exp;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("while (" + exp + "){\n");
        for (Stmt stmt : stmts) {
            result.append(stmt).append("\n");
        }
        return result + "}";
    }
}

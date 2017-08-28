package AST.Stmt;

import AST.AST;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 07/08/2017.
 */
public class Else_Stmt extends AST{
    private ArrayList<Stmt> stmts;

    public Else_Stmt() {
        this.stmts = new ArrayList<>() ;
    }

    public void addStmt(Stmt stmt){
        stmts.add(stmt);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("else {\n");

        for (Stmt stmt : stmts) {
            result.append(stmt).append("\n");
        }

        return result + "}";
    }
}

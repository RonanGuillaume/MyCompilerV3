package AST;

import AST.Stmt.Stmt;

import java.util.ArrayList;

/**
 * Created by ronan
 * on 02/08/2017.
 */
public class FunDecl extends AST {
    private String id;
    private FArgs_A fArgs_a;
    private FunType_A funType_a;
    private ArrayList<VarDecl> varDecls;
    private ArrayList<Stmt> stmts;

    public FunDecl(String id, FArgs_A fArgs_a, FunType_A funType_a) {
        this.id = id;
        this.fArgs_a = fArgs_a;
        this.funType_a = funType_a;

        varDecls = new ArrayList<>();
        stmts = new ArrayList<>();
    }

    public void addVarDecl(VarDecl varDecl){
        varDecls.add(varDecl);
    }

    public void addStmt(Stmt stmt){
        stmts.add(stmt);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(id);

        result.append(" (");
        if (fArgs_a != null){
            result.append(fArgs_a);
        }
        result.append(") ");
        if (funType_a != null){
            result.append(funType_a);
        }
        result.append(" {\n");
        for (VarDecl varDecl:varDecls) {
            result.append(varDecl);
        }
        for (Stmt stmt : stmts) {
            result.append(stmt);
        }
        result.append("\n}");

        return result.toString();
    }
}

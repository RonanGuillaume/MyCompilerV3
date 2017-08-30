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

    public int getNbArgs(){
        int result = 0;
        if (fArgs_a != null){
            result++;

            FArgs2_A fArgs2_a = fArgs_a.getfArgs2_a();

            while (fArgs2_a != null) {
                result++;
                fArgs2_a = fArgs2_a.getfArgs2_a();
            }
        }

        return result;
    }

    public int getNbTypes(){
        int result = 0;

        if (funType_a != null){
            FTypes_A fTypes_a = funType_a.getFunType().getfTypes_a();
            while (fTypes_a != null){
                result++;
                fTypes_a = fTypes_a.getfTypes().getfTypes_a();
            }
        }

        return result;
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
            result.append(varDecl).append("\n");
        }
        for (Stmt stmt : stmts) {
            result.append(stmt).append("\n");
        }
        result.append("}");

        return result.toString();
    }
}

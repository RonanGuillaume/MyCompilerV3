package AST;

import AST.Stmt.Stmt;
import AST.Type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public String getId() {
        return id;
    }

    public List<String> getArgs(){
        ArrayList<String> result = new ArrayList<>();

        if (fArgs_a != null){
            result.add(fArgs_a.getId());

            FArgs2_A fArgs2_a = fArgs_a.getfArgs2_a();

            while (fArgs2_a != null) {
                result.add(fArgs2_a.getId());
                fArgs2_a = fArgs2_a.getfArgs2_a();
            }
        }

        return result;
    }

    public List<Type> getTypes(){
        ArrayList<Type> result = new ArrayList<>();

        if (funType_a != null){
            FTypes_A fTypes_a = funType_a.getFunType().getfTypes_a();
            while (fTypes_a != null){
                result.add(fTypes_a.getfTypes().getType());
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

    @Override
    public boolean equals(Object obj) {
        boolean result;

        if (obj == null || obj.getClass() != FunDecl.class){
            return false;
        }

        FunDecl object = (FunDecl)obj;

        result = Objects.equals(id, object.id);

        if (funType_a != null && object.funType_a == null){
            result = false;
        }

        if (funType_a == null && object.funType_a != null){
            result = false;
        }

        if (funType_a != null && object.funType_a != null){
            if (funType_a.getFunType().getfTypes_a() == null && object.funType_a.getFunType().getfTypes_a() == null){
                return true;
            }

            if (funType_a.getFunType().getfTypes_a() == null && object.funType_a.getFunType().getfTypes_a() != null){
                return false;
            }

            if (funType_a.getFunType().getfTypes_a() != null && object.funType_a.getFunType().getfTypes_a() == null){
                return false;
            }

            result = funType_a.getFunType().getfTypes_a().equals(object.funType_a.getFunType().getfTypes_a());
        }

        return result;
    }
}

import AST.*;
import AST.Exp.*;
import AST.Op1.*;
import AST.Op1.Minus;
import AST.Op2.*;
import AST.Stmt.*;
import AST.Type.*;

/**
 * Created by ronan
 * on 18/07/2017.
 */
public class Parser {
    private Scanner scanner;
    private TypeChecker typeChecker;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
        this.typeChecker = new TypeChecker();
    }


    public AST parse() {
        SPL ast = SPL();
        if (scanner.tok != Scanner.EOF)
            throw parseError("Expected end of file");
        return ast;
    }

    private SPL SPL() {
        SPL spl = new SPL();
        while (scanner.tok != Scanner.EOF){
            FunDecl funDecl = FunDecl();
            typeChecker.addFunction(funDecl);
            spl.addFunDecl(funDecl);
        }
        return spl;
    }

    private FunDecl FunDecl(){
        if (scanner.tok != Scanner.NAME){
            throw parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw parseError("Expected a (");
        }
        scanner.next();
        FArgs_A fArgs_a = FArgs_A();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw parseError("Expected a )");
        }
        scanner.next();
        FunType_A funType_a = FunType_A();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw parseError("Expected a {");
        }
        scanner.next();
        FunDecl funDecl = new FunDecl(string, fArgs_a, funType_a);

        String id;
        boolean stmtFound = false;

        while ((scanner.tok == Scanner.VAR || scanner.tok == Scanner.L_PAR_TOK || scanner.tok == Scanner.L_SQ_BRACKET_TOK
                || scanner.tok == Scanner.NAME || scanner.tok == Scanner.INT || scanner.tok == Scanner.BOOL) && !stmtFound){
            if (scanner.tok == Scanner.NAME){
                id = scanner.sval;
                scanner.next();
                if (scanner.tok == Scanner.NAME){
                    funDecl.addVarDecl(VarDecl_type(id));
                }
                else if (scanner.tok == Scanner.L_PAR_TOK || scanner.tok == Scanner.DOT_TOK || scanner.tok == Scanner.ASSIGN_TOK){
                    funDecl.addStmt(Stmt_id(id));
                    stmtFound = true;
                }
                else {
                    throw parseError("Expected an id, a (, a . or a =");
                }
            }
            else {
                VarDecl varDecl = VarDecl();
                typeChecker.addVariables(varDecl);
                funDecl.addVarDecl(varDecl);
            }

        }
        if (stmtFound){
            while (scanner.tok == Scanner.IF || scanner.tok == Scanner.WHILE || scanner.tok == Scanner.NAME
                    || scanner.tok == Scanner.RETURN){
                Stmt stmt = Stmt();
                if (stmt.getClass() == Stmt_if.class){
                    typeChecker.checkIfClause((Stmt_if)stmt);
                }
                if (stmt.getClass() == Stmt_while.class){
                    typeChecker.checkWhileClause((Stmt_while)stmt);
                }
                funDecl.addStmt(stmt);
            }
        }
        else {
            do{
                Stmt stmt = Stmt();
                if (stmt.getClass() == Stmt_if.class){
                    typeChecker.checkIfClause((Stmt_if)stmt);
                }
                if (stmt.getClass() == Stmt_while.class){
                    typeChecker.checkWhileClause((Stmt_while)stmt);
                }
                funDecl.addStmt(stmt);
            }while (scanner.tok == Scanner.IF || scanner.tok == Scanner.WHILE || scanner.tok == Scanner.NAME
                    || scanner.tok == Scanner.RETURN);
        }

        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw parseError("Expected a }");
        }
        scanner.next();

        return funDecl;
    }

    private RetType RetType(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
            case Scanner.L_SQ_BRACKET_TOK:
            case Scanner.NAME:
            case Scanner.INT:
            case Scanner.BOOL:
                return new RetType_Type(Type());
            case Scanner.VOID:
                scanner.next();
                return new RetType_void();
            default:
                throw parseError("Expected a (, [, an id, 'Int', 'Bool' or 'Void'");
        }
    }

    private FunType FunType(){
        FTypes_A fTypes_a = FTypes_A();

        if (scanner.tok != Scanner.MINUS_TOK){
            throw parseError("Expected a -");
        }
        scanner.next();
        if (scanner.tok != Scanner.GT_TOK){
            throw parseError("Expected a >");
        }
        scanner.next();

        return new FunType(fTypes_a, RetType());
    }

    private FunType_A FunType_A(){
        if (scanner.tok == Scanner.L_BRACKET_TOK){
            return null;
        }

        if (scanner.tok != Scanner.COLON_TOK){
            throw parseError("Expected a :");
        }
        scanner.next();
        if (scanner.tok != Scanner.COLON_TOK){
            throw parseError("Expected a :");
        }
        scanner.next();

        return new FunType_A(FunType());
    }

    private FTypes FTypes(){
        Type type = Type();

        if (scanner.tok == Scanner.MINUS_TOK){
            return new FTypes(type, null);
        }
        else {
            return new FTypes(type, FTypes_A());
        }
    }

    private FTypes_A FTypes_A(){
        if (scanner.tok == Scanner.MINUS_TOK){
            return null;
        }
        return new FTypes_A(FTypes());
    }

    private Type Type(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
                scanner.next();
                Type type1 = Type();
                if (scanner.tok != Scanner.COMMA_TOK){
                    throw parseError("Expected a ,");
                }
                scanner.next();
                Type type2 = Type();
                if (scanner.tok != Scanner.R_PAR_TOK){
                    throw parseError("Expected a )");
                }
                scanner.next();
                return new Type_Pair(type1, type2);
            case Scanner.L_SQ_BRACKET_TOK:
                scanner.next();
                Type type = Type();
                if (scanner.tok != Scanner.R_SQ_BRACKET_TOK){
                    throw parseError("Expected a ]");
                }
                scanner.next();
                return new Type_List(type);
            case Scanner.INT:
                scanner.next();
                return new Basic_Int();
            case Scanner.BOOL:
                scanner.next();
                return new Basic_Bool();
            case Scanner.NAME:
                String variableName = scanner.sval;
                scanner.next();
                return new Type_id(variableName);
            default:
                throw parseError("Expected a 'Int', 'Bool', ( or a [");
        }
    }

    private FArgs_A FArgs_A(){
        if (scanner.tok == Scanner.NAME){
            String string = scanner.sval;
            scanner.next();
            return new FArgs_A(string,FArgs2_A());
        }
        else if (scanner.tok == Scanner.R_PAR_TOK){
            return null;
        }
        else {
            throw parseError("Expected an id or )");
        }
    }

    private FArgs2_A FArgs2_A(){
        if (scanner.tok == Scanner.COMMA_TOK){
            scanner.next();
            if (scanner.tok != Scanner.NAME){
                throw parseError("Expected an id");
            }
            String id = scanner.sval;
            scanner.next();
            return new FArgs2_A(id, FArgs2_A());
        }
        else if (scanner.tok == Scanner.R_PAR_TOK){
            return null;
        }
        else {
            throw parseError("Expected a , or )");
        }
    }

    private Stmt Stmt(){
        switch (scanner.tok){
            case Scanner.WHILE:
                return Stmt_while();
            case Scanner.NAME:
                return Stmt_id();
            case Scanner.RETURN:
                return Stmt_return();
            case Scanner.IF:
                return Stmt_if();
            default:
                throw parseError("Expected an id, if, while or return");
        }
    }

    private Stmt_while Stmt_while(){
        scanner.next();
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw parseError("Expected a (");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw parseError("Expected a )");
        }
        scanner.next();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw parseError("Expected a {");
        }
        scanner.next();
        Stmt_while stmt_while = new Stmt_while(exp);
        while (scanner.tok == Scanner.WHILE || scanner.tok == Scanner.IF || scanner.tok == Scanner.RETURN || scanner.tok == Scanner.NAME){
            stmt_while.addStmt(Stmt());
        }
        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw parseError("Expected a }");
        }
        scanner.next();
        return stmt_while;
    }

    private Stmt_id Stmt_id(){
        String id = scanner.sval;
        scanner.next();
        FunCall funCall = null;
        if (scanner.tok == Scanner.DOT_TOK){
            funCall = FunCall_Field();
        }
        else if (scanner.tok == Scanner.ASSIGN_TOK){
            funCall = FunCall_Exp();
        }
        else if (scanner.tok == Scanner.L_PAR_TOK){
            funCall = FunCall_Args();
            typeChecker.callFunction(id, funCall);
        }
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw parseError("Expected a ;");
        }
        scanner.next();
        return new Stmt_id(id, funCall);
    }

    private FunCall_Exp FunCall_Exp() {
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw parseError("Expected a =");
        }
        scanner.next();
        return new FunCall_Exp(Exp());
    }

    private Stmt_id Stmt_id(String id){
        FunCall funCall;
        if (scanner.tok == Scanner.DOT_TOK){
            funCall = FunCall_Field();
        }
        else {
            funCall = FunCall_Args();
        }
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw parseError("Expected a ;");
        }
        scanner.next();
        return new Stmt_id(id, funCall);
    }

    private FunCall_Field FunCall_Field(){
        Field field = null;
        if (scanner.tok == Scanner.DOT_TOK){
            field = Field();
        }
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw parseError("Expected a =");
        }
        scanner.next();
        return new FunCall_Field(field, Exp());
    }

    private FunCall_Args FunCall_Args(){
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw parseError("Expected a (");
        }
        scanner.next();

        ActArgs_A actArgs_a = ActArgs_A();

        if (scanner.tok != Scanner.R_PAR_TOK){
            throw parseError("Expected a )");
        }
        scanner.next();

        return new FunCall_Args(actArgs_a);
    }

    private Field Field(){
        if (scanner.tok != Scanner.DOT_TOK){
            throw parseError("Expected a .");
        }
        scanner.next();

        return new Field(Position(), Field_A());
    }

    private Field_A Field_A(){
        if (scanner.tok == Scanner.DOT_TOK){
            return new Field_A(Field());
        }
        else {
            return null;
        }
    }

    private Position Position(){
        switch (scanner.tok){
            case Scanner.HD:
                scanner.next();
                return new Position("hd");
            case Scanner.FST:
                scanner.next();
                return new Position("fst");
            case Scanner.SND:
                scanner.next();
                return new Position("snd");
            case Scanner.TL:
                scanner.next();
                return new Position("tl");
            default:
                throw parseError("Expected a hd, tl, fst or snd");
        }
    }

    private Stmt_return Stmt_return(){
        scanner.next();
        Exp_A exp_a = Exp_A();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw parseError("Expected a ;");
        }
        scanner.next();
        return new Stmt_return(exp_a);
    }

    private Stmt_if Stmt_if(){
        scanner.next();
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw parseError("Expected a (");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw parseError("Expected a )");
        }
        scanner.next();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw parseError("Expected a {");
        }
        scanner.next();
        Stmt_if stmt_if = new Stmt_if(exp);
        while (scanner.tok == Scanner.WHILE || scanner.tok == Scanner.IF || scanner.tok == Scanner.RETURN || scanner.tok == Scanner.NAME){
            stmt_if.addStmt(Stmt());
        }
        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw parseError("Expected a }");
        }
        scanner.next();
        if (scanner.tok == Scanner.ELSE){
            stmt_if.addElseStmt(Else_Stmt());
            return stmt_if;
        }
        else if (scanner.tok == Scanner.R_PAR_TOK || scanner.tok == Scanner.WHILE || scanner.tok == Scanner.IF
                || scanner.tok == Scanner.RETURN || scanner.tok == Scanner.NAME){
            return stmt_if;
        }
        else {
            throw parseError("Expected a else, } or another Stmt");
        }
    }

    private Else_Stmt Else_Stmt(){
        scanner.next();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw parseError("Expected a {");
        }
        scanner.next();
        Else_Stmt else_stmt = new Else_Stmt();
        while (scanner.tok == Scanner.WHILE || scanner.tok == Scanner.IF || scanner.tok == Scanner.RETURN || scanner.tok == Scanner.NAME){
            else_stmt.addStmt(Stmt());
        }
        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw parseError("Expected a }");
        }
        scanner.next();
        return else_stmt;
    }

    private Exp Exp(){
        return new Exp(Factor(), Term());
    }

    private Factor Factor(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
                scanner.next();
                Exp exp = Exp();
                next_Exp next_exp = next_Exp();
                if (scanner.tok != Scanner.R_PAR_TOK){
                    throw parseError("Expected a )");
                }
                scanner.next();
                return new Factor_exp(exp, next_exp);
            case Scanner.NOT_TOK:
            case Scanner.MINUS_TOK:
                return new Factor_Op1(Op1(), Factor());
            case Scanner.DOUBLE:
                int number = scanner.nval;
                scanner.next();
                return new Factor_int(number);
            case Scanner.FALSE:
                scanner.next();
                return new Factor_false();
            case Scanner.TRUE:
                scanner.next();
                return new Factor_true();
            case Scanner.NAME:
                String id = scanner.sval;
                scanner.next();
                FunCall_A funCall_a = FunCall_A();
                if (funCall_a != null && funCall_a.getClass() == FunCall_A_Args.class){
                    typeChecker.callFunction(id, new FunCall_Args(((FunCall_A_Args)funCall_a).getActArgs_a()));
                }
                return new Factor_funCall_A(id, funCall_a);
            case Scanner.L_SQ_BRACKET_TOK:
                scanner.next();
                if (scanner.tok != Scanner.R_SQ_BRACKET_TOK){
                    throw parseError("Expected a ]");
                }
                scanner.next();
                return new Factor_empty();
            default:
                throw parseError("Expected an id, if, while or return");
        }
    }

    private Term Term(){
        if (scanner.tok == Scanner.COMMA_TOK || scanner.tok == Scanner.R_PAR_TOK || scanner.tok == Scanner.SEMICOLON_TOK){
            return null;
        }
        else {
            return new Term(Op2(), Factor(), Term());
        }
    }

    private FunCall_A FunCall_A(){
        if (scanner.tok == Scanner.L_PAR_TOK){
            scanner.next();
            ActArgs_A actArgs_a = ActArgs_A();
            if (scanner.tok != Scanner.R_PAR_TOK){
                throw parseError("Expected a )");
            }
            scanner.next();
            return new FunCall_A_Args(actArgs_a);
        }
        else if (scanner.tok == Scanner.DOT_TOK){
            return new FunCall_A_Field(Field());
        }
        else {
            return null;
        }
    }

    private next_Exp next_Exp(){
        if (scanner.tok == Scanner.R_PAR_TOK){
            return null;
        }
        else {
            if (scanner.tok != Scanner.COMMA_TOK){
                throw parseError("Expected a ,");
            }
            scanner.next();
            return new next_Exp(Exp());
        }
    }

    private Exp_A Exp_A(){
        if (scanner.tok == Scanner.SEMICOLON_TOK){
            return null;
        }
        else {
            return new Exp_A(Exp());
        }
    }

    private ActArgs ActArgs(){
        return new ActArgs(Exp(), ActArgs2_A());
    }

    private ActArgs_A ActArgs_A(){
        if (scanner.tok == Scanner.R_PAR_TOK){
            return null;
        }
        else {
            return new ActArgs_A(ActArgs());
        }
    }

    private ActArgs2_A ActArgs2_A(){
        if (scanner.tok == Scanner.R_PAR_TOK){
            return null;
        }
        else {
            if (scanner.tok != Scanner.COMMA_TOK){
                throw parseError("Expected a ,");
            }
            scanner.next();
            return new ActArgs2_A(ActArgs());
        }

    }

    private VarDecl VarDecl(){
        switch (scanner.tok){
            case Scanner.VAR:
                return VarDecl_id();
            case Scanner.L_PAR_TOK:
            case Scanner.L_SQ_BRACKET_TOK:
            case Scanner.NAME:
            case Scanner.INT:
            case Scanner.BOOL:
                return VarDecl_type();
            default:
                throw parseError("Expected a var, (, [, an id, 'Int' or 'Bool'");
        }
    }

    private VarDecl_type VarDecl_type(){
        Type type = Type();
        if (scanner.tok != Scanner.NAME){
            throw parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw parseError("Expected a ;");
        }
        scanner.next();
        return new VarDecl_type(type, string, exp);
    }

    private VarDecl_type VarDecl_type(String id) {
        Type type = new Type_id(id);
        if (scanner.tok != Scanner.NAME){
            throw parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw parseError("Expected a ;");
        }
        scanner.next();
        return new VarDecl_type(type, string, exp);
    }

    private VarDecl_id VarDecl_id(){
        scanner.next();
        if (scanner.tok != Scanner.NAME){
            throw parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw parseError("Expected a ;");
        }
        scanner.next();
        return new VarDecl_id(string, exp);
    }

    private Op1 Op1(){
        switch (scanner.tok){
            case Scanner.NOT_TOK:
                scanner.next();
                return new Fact();
            case Scanner.MINUS_TOK:
                scanner.next();
                return new Minus();
            default:
                throw parseError("Expected ! or -");
        }
    }

    private Op2 Op2() {
        switch (scanner.tok){
            case Scanner.MINUS_TOK:
                return new Minus_Op2();
            case Scanner.PLUS_TOK:
                scanner.next();
                return new Plus();
            case Scanner.TIMES_TOK:
                scanner.next();
                return new Times();
            case Scanner.DIV_TOK:
                scanner.next();
                return new Divide();
            case Scanner.MOD_TOK:
                scanner.next();
                return new Module();
            case Scanner.ASSIGN_TOK:
                scanner.next();
                if (scanner.tok != Scanner.ASSIGN_TOK){
                    throw parseError("Expected ==");
                }
                scanner.next();
                return new Equal();
            case Scanner.LT_TOK:
                scanner.next();
                return new Fewer(Eq_A());
            case Scanner.GT_TOK:
                scanner.next();
                return new Higher(Eq_A());
            case Scanner.NOT_TOK:
                scanner.next();
                if (scanner.tok != Scanner.ASSIGN_TOK){
                    throw parseError("Expected !=");
                }
                scanner.next();
                return new Diff();
            case Scanner.AND_TOK:
                scanner.next();
                return new And();
            case Scanner.OR_TOK:
                scanner.next();
                return new Or();
            case Scanner.COLON_TOK:
                scanner.next();
                return new Colon();
            default:
                throw parseError("Expected +, *, /, %, ==, <, >, <=, >=, !=, &&, || or :");
        }
    }

    private Eq_A Eq_A(){
        switch (scanner.tok){
            case Scanner.ASSIGN_TOK:
                scanner.next();
                return new Eq_A();
            case Scanner.L_PAR_TOK:
            case Scanner.NOT_TOK:
            case Scanner.MINUS_TOK:
            case Scanner.NAME:
            case Scanner.DOUBLE:
            case Scanner.FALSE:
            case Scanner.TRUE:
            case Scanner.L_SQ_BRACKET_TOK:
                return null;
            default:
                throw parseError("Expected =, !, -, an id, a number, 'False', 'True', [ or (");
        }
    }

    public IllegalArgumentException parseError(String msg){
        return new ParseError(msg + " but found " + scanner);
    }

}

class ParseError extends IllegalArgumentException {
    ParseError(String s) {
        super(s);
    }
}

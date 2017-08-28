import AST.*;
import AST.Exp.Exp;
import AST.Op1.Minus;
import AST.Op1.No;
import AST.Op1.Op1;
import AST.Op2.Plus;
import AST.Op2.*;
import AST.Stmt.*;
import AST.Type.*;

/**
 * Created by ronan
 * on 18/07/2017.
 */
public class Parser {
    private Scanner scanner;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }


    public AST parse() {
        SPL ast = SPL();
        if (scanner.tok != Scanner.EOF)
            throw scanner.parseError("Expected end of file");
        return ast;
    }

    private SPL SPL() {
        SPL spl = new SPL();
        while (scanner.tok != Scanner.EOF){
            spl.addFunDecl(FunDecl());
        }
        return spl;
    }

    private FunDecl FunDecl(){
        if (scanner.tok != Scanner.NAME){
            throw scanner.parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw scanner.parseError("Expected a (");
        }
        scanner.next();
        FArgs_A fArgs_a = FArgs_A();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw scanner.parseError("Expected a )");
        }
        scanner.next();
        FunType_A funType_a = FunType_A();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw scanner.parseError("Expected a {");
        }
        scanner.next();
        FunDecl funDecl = new FunDecl(string, fArgs_a, funType_a);

        while (scanner.tok == Scanner.VAR || scanner.tok == Scanner.L_PAR_TOK || scanner.tok == Scanner.L_SQ_BRACKET_TOK
                || scanner.tok == Scanner.NAME || scanner.tok == Scanner.INT || scanner.tok == Scanner.BOOL){
            funDecl.addVarDecl(VarDecl());
        }
        do{
            funDecl.addStmt(Stmt());
        }while (scanner.tok == Scanner.IF || scanner.tok == Scanner.WHILE || scanner.tok == Scanner.NAME
                || scanner.tok == Scanner.RETURN);

        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw scanner.parseError("Expected a }");
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
                throw scanner.parseError("Expected a (, [, an id, 'Int', 'Bool' or 'Void'");
        }
    }

    private FunType FunType(){
        FunType_A funType_a = FunType_A();

        if (scanner.tok != Scanner.MINUS_TOK){
            throw scanner.parseError("Expected a -");
        }
        scanner.next();
        if (scanner.tok != Scanner.GT_TOK){
            throw scanner.parseError("Expected a >");
        }
        scanner.next();

        return new FunType(funType_a, RetType());
    }

    private FunType_A FunType_A(){
        if (scanner.tok == Scanner.L_BRACKET_TOK){
            return null;
        }

        if (scanner.tok != Scanner.COLON_TOK){
            throw scanner.parseError("Expected a :");
        }
        scanner.next();
        if (scanner.tok != Scanner.COLON_TOK){
            throw scanner.parseError("Expected a :");
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

    private Rep_Decl_A Rep_Decl_A() {
        if (scanner.tok == Scanner.EOF){
            return null;
        }
        return new Rep_Decl_A(Decl(), Rep_Decl_A());
    }

    private Decl Decl() {
        switch (scanner.tok){
            case Scanner.VAR:
               return VarDecl_Decl();
            case Scanner.NAME:
            case Scanner.L_PAR_TOK:
            case Scanner.L_SQ_BRACKET_TOK:
            case Scanner.INT:
            case Scanner.BOOL:
                return FunDecl_();
            default:
                throw scanner.parseError("Expected var, id, (, [, Int or Bool");
        }

    }

    private FunDecl_ FunDecl_(){
        switch (scanner.tok){
            case Scanner.NAME:
                return FunDecl_id();
            case Scanner.L_PAR_TOK:
            case Scanner.L_SQ_BRACKET_TOK:
            case Scanner.INT:
            case Scanner.BOOL:
                return FunDecl_type();
            default:
                throw scanner.parseError("Expected an id, (, [, Int or Bool");
        }
    }

    private FunDecl_type FunDecl_type(){
        Type type_no_id = Type();
        if (scanner.tok != Scanner.NAME){
            throw scanner.parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw scanner.parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw scanner.parseError("Expected a ;");
        }
        scanner.next();
        return new FunDecl_type(type_no_id, string, exp);
    }

    private FunDecl_id FunDecl_id(){
        String string = scanner.sval;
        scanner.next();
        return new FunDecl_id(string, FunDecl2());
    }

    private FunDecl2 FunDecl2(){
        switch (scanner.tok){
            case Scanner.NAME:
                return FunDecl2_id();
            case Scanner.L_PAR_TOK:
                return FunDecl2_args();
            default:
                throw scanner.parseError("Expected an id or (");
        }
    }

    private FunDecl2_id FunDecl2_id(){
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw scanner.parseError("Expected an =");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw scanner.parseError("Expected an ;");
        }
        scanner.next();
        return new FunDecl2_id(string, exp);
    }

    private FunDecl2_args FunDecl2_args(){
        scanner.next();
        FArgs_Op_A fArgs_op_a = FArgs_Op_A();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw scanner.parseError("Expected a )");
        }
        scanner.next();
        FunType_Op_A funType_op_a = FunType_Op_A();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw scanner.parseError("Expected a {");
        }
        scanner.next();
        Rep_VarDecl_A rep_varDecl_a = Rep_VarDecl_A();
        Stmt stmt = Stmt();
        Rep_Stmt_A rep_stmt_a = Rep_Stmt_A();
        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw scanner.parseError("Expected a }");
        }
        scanner.next();

        return new FunDecl2_args(fArgs_op_a, funType_op_a, rep_varDecl_a, stmt, rep_stmt_a);
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
                throw scanner.parseError("Expected an id or )");
        }
    }

    private Stmt_while Stmt_while(){
        scanner.next();
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw scanner.parseError("Expected a (");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw scanner.parseError("Expected a )");
        }
        scanner.next();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw scanner.parseError("Expected a {");
        }
        scanner.next();
        Rep_Stmt_A rep_stmt_a = Rep_Stmt_A();
        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw scanner.parseError("Expected a }");
        }
        scanner.next();
        return new Stmt_while(exp, rep_stmt_a);
    }

    private Stmt_id Stmt_id(){
        String string = scanner.sval;
        scanner.next();
        return new Stmt_id(string, Stmt_next());
    }

    private Stmt_next Stmt_next(){
        switch (scanner.tok){
            case Scanner.DOT_TOK:
            case Scanner.ASSIGN_TOK:
                return Stmt_next_Field_A();
            case Scanner.L_PAR_TOK:
                return Stmt_next_FunCall();
            default:
                throw scanner.parseError("Expected a while, an id, return, if or }");
        }
    }

    private Stmt_next_Field_A Stmt_next_Field_A(){
        Field_A field_a = Field_A();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw scanner.parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw scanner.parseError("Expected a ;");
        }
        scanner.next();
        return new Stmt_next_Field_A(field_a, exp);
    }

    private Stmt_next_FunCall Stmt_next_FunCall(){
        FunCall_Without_id funCall_without_id = FunCall_Without_id();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw scanner.parseError("Expected a ;");
        }
        scanner.next();
        return new Stmt_next_FunCall(funCall_without_id);
    }

    private FunCall_Without_id FunCall_Without_id(){
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw scanner.parseError("Expected a (");
        }
        scanner.next();

        Rep_ActArgs_A rep_actArgs_a = Rep_ActArgs_A();

        if (scanner.tok != Scanner.R_PAR_TOK){
            throw scanner.parseError("Expected a )");
        }
        scanner.next();
        return new FunCall_Without_id(rep_actArgs_a);
    }

    private Rep_ActArgs_A Rep_ActArgs_A(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
            case Scanner.PLUS_TOK:
            case Scanner.NOT_TOK:
            case Scanner.MINUS_TOK:
            case Scanner.NAME:
            case Scanner.DOUBLE:
            case Scanner.FALSE:
            case Scanner.TRUE:
            case Scanner.L_SQ_BRACKET_TOK:
                return new Rep_ActArgs_A(ActArgs());
            case Scanner.R_PAR_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a while, an id, return, if or }");
        }
    }

    private ActArgs ActArgs(){
        return new ActArgs(Exp(), ActArgs_Op1_A());
    }

    private ActArgs_Op1_A ActArgs_Op1_A(){
        switch (scanner.tok){
            case Scanner.COMMA_TOK:
                scanner.next();
                return new ActArgs_Op1_A(ActArgs());
            case Scanner.R_PAR_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a ',' or )");
        }
    }

    private Rep_Stmt_A Rep_Stmt_A(){
        switch (scanner.tok){
            case Scanner.WHILE:
            case Scanner.NAME:
            case Scanner.RETURN:
            case Scanner.IF:
                return new Rep_Stmt_A(Stmt(), Rep_Stmt_A());
            case Scanner.R_BRACKET_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a while, an id, return, if or }");
        }
    }

    private Stmt_if Stmt_if(){
        scanner.next();
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw scanner.parseError("Expected a (");
        }
        scanner.next();
        Exp exp = Exp();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw scanner.parseError("Expected a )");
        }
        scanner.next();
        if (scanner.tok != Scanner.L_BRACKET_TOK){
            throw scanner.parseError("Expected a {");
        }
        scanner.next();
        Rep_Stmt_A rep_stmt_a = Rep_Stmt_A();
        if (scanner.tok != Scanner.R_BRACKET_TOK){
            throw scanner.parseError("Expected a }");
        }
        scanner.next();
        return new Stmt_if(exp, rep_stmt_a, Else_Stmt_A());
    }

    private Else_Stmt Else_Stmt_A(){
        switch (scanner.tok){
            case Scanner.ELSE:
                scanner.next();
                if (scanner.tok != Scanner.L_BRACKET_TOK){
                    throw scanner.parseError("Expected a {");
                }
                scanner.next();
                Rep_Stmt_A rep_stmt_a = Rep_Stmt_A();
                if (scanner.tok != Scanner.R_BRACKET_TOK){
                    throw scanner.parseError("Expected a }");
                }
                scanner.next();
                return new Else_Stmt(rep_stmt_a);
            case Scanner.IF:
            case Scanner.WHILE:
            case Scanner.NAME:
            case Scanner.RETURN:
            case Scanner.R_PAR_TOK:
            case Scanner.R_BRACKET_TOK:
                return null;
            default:
                throw scanner.parseError("Expected an id or )");
        }
    }

    private Stmt_return Stmt_return(){
        scanner.next();
        Exp_Op_A exp_op_a = Exp_Op_A();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw scanner.parseError("Expected a ;");
        }
        scanner.next();
        return new Stmt_return(exp_op_a);
    }

    private Exp_Op_A Exp_Op_A(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
            case Scanner.PLUS_TOK:
            case Scanner.MINUS_TOK:
            case Scanner.NOT_TOK:
            case Scanner.NAME:
            case Scanner.DOUBLE:
            case Scanner.FALSE:
            case Scanner.TRUE:
            case Scanner.L_SQ_BRACKET_TOK:
                return new Exp_Op_A(Exp());
            case Scanner.SEMICOLON_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a (, +, -, !, an id, 'Int', False, True, [ or ;");
        }
    }

    private VarDecl_Decl VarDecl_Decl() {
        if (scanner.tok != Scanner.VAR){
            throw scanner.parseError("Expected a 'var'");
        }
        scanner.next();
        if (scanner.tok != Scanner.NAME){
            throw scanner.parseError("Expected an id");
        }
        String idValue = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw scanner.parseError("Expected an '='");
        }
        Exp exp = Exp();
        if (scanner.tok != Scanner.SEMICOLON_TOK){
            throw scanner.parseError("Expected a ';'");
        }
        scanner.next();
        return new VarDecl_Decl(idValue, exp);
    }

    private Rep_VarDecl_A Rep_VarDecl_A(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
            case Scanner.VAR:
            case Scanner.INT:
            case Scanner.BOOL:
            case Scanner.L_SQ_BRACKET_TOK:
                VarDecl varDecl = VarDecl();
                if (scanner.tok != Scanner.SEMICOLON_TOK){
                        throw scanner.parseError("Expected a ;");
                }
                scanner.next();
                return new Rep_VarDecl_A(varDecl, Rep_VarDecl_A());
            case Scanner.IF:
            case Scanner.RETURN:
            case Scanner.WHILE:
            case Scanner.NAME:
                return null;
            default:
                throw scanner.parseError("Expected a (, +, -, !, an id, 'Int', False, True, [ or ;");
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
                throw scanner.parseError("Expected a var, (, [, an id, 'Int' or 'Bool'");
        }
    }

    private VarDecl_type VarDecl_type(){
        Type type = Type();
        if (scanner.tok != Scanner.NAME){
            throw scanner.parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw scanner.parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        return new VarDecl_type(type, string, exp);
    }

    private VarDecl_id VarDecl_id(){
        scanner.next();
        if (scanner.tok != Scanner.NAME){
            throw scanner.parseError("Expected an id");
        }
        String string = scanner.sval;
        scanner.next();
        if (scanner.tok != Scanner.ASSIGN_TOK){
            throw scanner.parseError("Expected a =");
        }
        scanner.next();
        Exp exp = Exp();
        return new VarDecl_id(string, exp);
    }

    private FArgs_Op_A FArgs_Op_A(){
        switch (scanner.tok){
            case Scanner.NAME:
                return new FArgs_Op_A(FArgs());
            case Scanner.R_PAR_TOK:
                return null;
            default:
                throw scanner.parseError("Expected an id or )");
        }
    }

    private AST.FArgs_A FArgs(){
        String string = scanner.sval;
        scanner.next();
        return new AST.FArgs_A(string,FunType_Op1_A());
    }

    private FArgs_Op1_A FunType_Op1_A() {
        switch (scanner.tok){
            case Scanner.COMMA_TOK:
                scanner.next();
                return new FArgs_Op1_A(FArgs());
            case Scanner.R_PAR_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a ',' or )");
        }
    }

    private Exp Exp(){
        return new Exp(Term(), Exp2());
    }

    private Exp2 Exp2(){
        switch (scanner.tok){
            case Scanner.PLUS_TOK:
            case Scanner.NOT_TOK:
            case Scanner.MINUS_TOK:
                return new Exp2(Op1(), Term(), Exp2());
            case Scanner.R_PAR_TOK:
            case Scanner.SEMICOLON_TOK:
            case Scanner.COMMA_TOK:
                return null;
            default:
                throw scanner.parseError("Expected +, !, -, ), ; or ,");
        }
    }

    private Term Term(){
        return new Term(Factor(), Term2());
    }

    private Term2 Term2(){
        switch (scanner.tok){
            case Scanner.TIMES_TOK:
            case Scanner.DIV_TOK:
            case Scanner.MOD_TOK:
            case Scanner.ASSIGN_TOK:
            case Scanner.LT_TOK:
            case Scanner.GT_TOK:
            case Scanner.AND_TOK:
            case Scanner.OR_TOK:
            case Scanner.COLON_TOK:
            case Scanner.NOT_TOK:
                return new Term2(Op2(), Factor(), Term2());
            case Scanner.R_PAR_TOK:
            case Scanner.PLUS_TOK:
            case Scanner.MINUS_TOK:
            case Scanner.SEMICOLON_TOK:
            case Scanner.COMMA_TOK:
                return null;
            default:
                throw scanner.parseError("Expected *, /, %, ==, <, >, <=, >=, !=, &&, ||, :, ), +, !, -, ',' or ;");
        }
    }

    private Op1 Op1(){
        switch (scanner.tok){
            case Scanner.PLUS_TOK:
                scanner.next();
                return new Plus();
            case Scanner.MINUS_TOK:
                scanner.next();
                return new Minus();
            default:
                throw scanner.parseError("Expected + or -");
        }
    }

    private Op2 Op2() {
        switch (scanner.tok){
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
                    throw scanner.parseError("Expected ==");
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
                    throw scanner.parseError("Expected !=");
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
                throw scanner.parseError("Expected *, /, %, ==, <, >, <=, >=, !=, &&, || or :");
        }
    }

    private Eq_A Eq_A(){
        switch (scanner.tok){
            case Scanner.ASSIGN_TOK:
                scanner.next();
                return new Eq_A();
            case Scanner.L_PAR_TOK:
            case Scanner.PLUS_TOK:
            case Scanner.MINUS_TOK:
            case Scanner.NAME:
            case Scanner.DOUBLE:
            case Scanner.FALSE:
            case Scanner.TRUE:
            case Scanner.L_SQ_BRACKET_TOK:
                return null;
            default:
                throw scanner.parseError("Expected =, +, -, an id, a number, 'False', 'True', [ or (");
        }
    }

    private Factor Factor(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
                return Factor_Exp();
            case Scanner.PLUS_TOK:
            case Scanner.MINUS_TOK:
                return Factor_op1();
            case Scanner.NAME:
            case Scanner.DOUBLE:
            case Scanner.FALSE:
            case Scanner.TRUE:
                return Factor_Exp_Type();
            case Scanner.L_SQ_BRACKET_TOK:
                scanner.next();
                if (scanner.tok != Scanner.R_SQ_BRACKET_TOK){
                    throw scanner.parseError("Expected an empty list []");
                }
                scanner.next();
                return Factor_Exp_Type();
            default:
                throw scanner.parseError("Expected var, id, (, [, Int or Bool");
        }
    }

    private Factor_op1 Factor_op1(){
        Op1 op1;
        switch (scanner.tok){
            case Scanner.PLUS_TOK:
                op1 = new Plus();
                break;
            case Scanner.NOT_TOK:
                op1 = new No();
                break;
            case Scanner.MINUS_TOK:
                op1 = new Minus();
                break;
            default:
                throw scanner.parseError("Expected -, + or !");
        }
        scanner.next();
        return new Factor_op1(op1, Factor());
    }

    private Factor_Exp Factor_Exp(){
        if (scanner.tok != Scanner.L_PAR_TOK){
            throw scanner.parseError("Expected a '('");
        }
        scanner.next();
        Exp exp = Exp();
        Exp_next exp_next = Exp_next();
        if (scanner.tok != Scanner.R_PAR_TOK){
            throw scanner.parseError("Expected a ')'");
        }
        scanner.next();
        return new Factor_Exp(exp, exp_next);
    }

    private Exp_next Exp_next(){
        switch (scanner.tok){
            case Scanner.COMMA_TOK:
                scanner.next();
                return new Exp_next(Exp());
            case Scanner.R_PAR_TOK:
                return null;
            default:
                throw scanner.parseError("Expected , or )");
        }
    }

    private Exp_Type Factor_Exp_Type(){
        switch (scanner.tok){
            case Scanner.NAME:
                String string = scanner.sval;
                scanner.next();
                return new Exp_Type_id(string, Field_A());
            case Scanner.DOUBLE:
                int integer = scanner.nval;
                scanner.next();
                return new Exp_Type_int(integer);
            case Scanner.FALSE:
                scanner.next();
                return new Exp_Type_False();
            case Scanner.TRUE:
                scanner.next();
                return new Exp_Type_True();
            case Scanner.L_SQ_BRACKET_TOK:
                scanner.next();
                return new Exp_Type_List();
            default:
                throw scanner.parseError("Expected an id, an integer, False, True or []");
        }
    }

    private Field_A Field_A(){
        switch (scanner.tok){
            case Scanner.DOT_TOK:
                scanner.next();
                return new Field_A(Field_Op(), Field_A());
            case Scanner.L_PAR_TOK:
            case Scanner.PLUS_TOK:
            case Scanner.MINUS_TOK:
            case Scanner.NAME:
            case Scanner.DOUBLE:
            case Scanner.FALSE:
            case Scanner.TRUE:
            case Scanner.L_SQ_BRACKET_TOK:
            case Scanner.TIMES_TOK:
            case Scanner.DIV_TOK:
            case Scanner.MOD_TOK:
            case Scanner.ASSIGN_TOK:
            case Scanner.GT_TOK:
            case Scanner.LT_TOK:
            case Scanner.NOT_TOK:
            case Scanner.AND_TOK:
            case Scanner.OR_TOK:
            case Scanner.COLON_TOK:
            case Scanner.COMMA_TOK:
            case Scanner.R_PAR_TOK:
            case Scanner.SEMICOLON_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a ., =, (, +, -, *, /, %, !, an id, a number, False, True, " +
                        "[, , ==, <, >, <=, >=, !=, &&, ||, :, ,, ) or ;");
        }
    }

    private Field_Op Field_Op(){
        switch (scanner.tok){
            case Scanner.HD:
                scanner.next();
                return new Head();
            case Scanner.TL:
                scanner.next();
                return new Tales();
            case Scanner.FST:
                scanner.next();
                return new First();
            case Scanner.SND:
                scanner.next();
                return new Second();
            default:
                throw scanner.parseError("Expected hd, tl, fst or snd");
        }
    }

    private FunType_Op_A FunType_Op_A(){
        switch (scanner.tok){
            case Scanner.COLON_TOK:
                scanner.next();
                if (scanner.tok != Scanner.COLON_TOK){
                    throw scanner.parseError("Expected ::");
                }
                scanner.next();
                return new FunType_Op_A(FunType());
            case Scanner.L_BRACKET_TOK:
                return null;
            default:
                throw scanner.parseError("Expected :: or {");
        }
    }

    private Rep_FTypes_A Rep_FTypes_A(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
            case Scanner.L_SQ_BRACKET_TOK:
            case Scanner.NAME:
            case Scanner.INT:
            case Scanner.BOOL:
                return new Rep_FTypes_A(Type(), Rep_FTypes_A());
            case Scanner.MINUS_TOK:
                return null;
            default:
                throw scanner.parseError("Expected a (, [, an id, 'Int', 'Bool' or a ->");
        }
    }



    private Type Type(){
        switch (scanner.tok){
            case Scanner.L_PAR_TOK:
                scanner.next();
                Type type1 = Type();
                if (scanner.tok != Scanner.COMMA_TOK){
                    throw scanner.parseError("Expected a ,");
                }
                scanner.next();
                Type type2 = Type();
                if (scanner.tok != Scanner.R_PAR_TOK){
                    throw scanner.parseError("Expected a )");
                }
                scanner.next();
                return new Type_Pair(type1, type2);
            case Scanner.L_SQ_BRACKET_TOK:
                scanner.next();
                Type type = Type();
                if (scanner.tok != Scanner.R_SQ_BRACKET_TOK){
                    throw scanner.parseError("Expected a ]");
                }
                scanner.next();
                return new Type_List(type);
            case Scanner.INT:
                scanner.next();
                return new Int();
            case Scanner.BOOL:
                scanner.next();
                return new Bool();
            default:
                throw scanner.parseError("Expected a 'Int', 'Bool', ( or a [");
        }
    }
}

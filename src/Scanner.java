import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * Created by ronan
 * on 18/07/2017.
 */
public class Scanner {
    final static int DOUBLE = -97;
    final static int NAME = -98;
    final static int EOF = -100;
    final static int WHILE = -102;
    final static int INT = -103;
    final static int BOOL = -104;
    final static int VOID = -106;
    final static int ELSE = -107;
    final static int RETURN = -108;
    final static int IF = -109;
    final static int VAR = -110;
    final static int FALSE = -111;
    final static int TRUE = -112;
    final static int ASSIGN_TOK = -117;
    final static int SEMICOLON_TOK = -118;
    final static int L_PAR_TOK = -119;
    final static int R_PAR_TOK = -120;
    final static int L_BRACKET_TOK = -121;
    final static int R_BRACKET_TOK = -122;
    final static int COMMA_TOK = -123;
    final static int L_SQ_BRACKET_TOK = -124;
    final static int R_SQ_BRACKET_TOK = -125;
    final static int DOT_TOK = -126;
    final static int PLUS_TOK = -127;
    final static int MINUS_TOK = -128;
    final static int TIMES_TOK = -129;
    final static int DIV_TOK = -130;
    final static int MOD_TOK = -131;
    final static int LT_TOK = -132;
    final static int GT_TOK = -133;
    final static int COLON_TOK = -134;
    final static int NOT_TOK = -135;
    final static int OR_TOK = -143;
    final static int AND_TOK = -144;

    public int tok;
    public int nval;
    public String sval;
    private StreamTokenizer streamTokenizer;

    public Scanner(String filename) {
        try {
            streamTokenizer = new StreamTokenizer(new FileReader(filename));
            setup();
            next();
        } catch (FileNotFoundException e) {
            throw new TokenError("Error opening file " + filename);
        }
    }

    private void setup() {
        streamTokenizer.resetSyntax();
        streamTokenizer.whitespaceChars(' ',' ');
        streamTokenizer.whitespaceChars('\t', '\t');
        streamTokenizer.whitespaceChars('\n', '\n');
        streamTokenizer.whitespaceChars('\r', '\r');
        streamTokenizer.wordChars('a', 'z');
        streamTokenizer.wordChars('A', 'Z');
        streamTokenizer.wordChars('0', '9');
        streamTokenizer.wordChars('_', '_');
        streamTokenizer.wordChars('-', '-');
        streamTokenizer.slashSlashComments(true);
        streamTokenizer.slashStarComments(true);
    }

    public void next() {
        boolean found = true;
        if (tok != EOF)
            try {
                tok = streamTokenizer.nextToken();
                switch (tok) {
                    case StreamTokenizer.TT_EOF:
                        tok = EOF;
                        break;
                    case StreamTokenizer.TT_WORD:
                        sval = streamTokenizer.sval;
                        switch (sval){
                            case ("while"):
                                tok = WHILE;
                                break;

                            case  ("-"):
                                tok = MINUS_TOK;
                                break;
                            case ("Int"):
                                tok = INT;
                                break;
                            case ("Bool"):
                                tok = BOOL;
                                break;
                            case ("Void"):
                                tok = VOID;
                                break;
                            case ("else"):
                                tok = ELSE;
                                break;
                            case ("return"):
                                tok = RETURN;
                                break;
                            case ("if"):
                                tok = IF;
                                break;
                            case ("var"):
                                tok = VAR;
                                break;
                            case ("FALSE"):
                                tok = FALSE;
                                break;
                            case ("TRUE"):
                                tok = TRUE;
                                break;
                            default:
                                found = false;
                        }
                        if (isANumber(sval) && !found){
                            tok = DOUBLE;
                            nval = Integer.parseInt(sval);
                        }
                        else if (sval.indexOf('-')!=-1 && !found){
                            throw new TokenError("Illegal token " + this);
                        }
                        else if(!found) tok = NAME;
                        break;
                    case '=':
                        tok = ASSIGN_TOK;
                        break;
                    case ';':
                        tok = SEMICOLON_TOK;
                        break;
                    case '(':
                        tok = L_PAR_TOK;
                        break;
                    case ')':
                        tok = R_PAR_TOK;
                        break;
                    case '{':
                        tok = L_BRACKET_TOK;
                        break;
                    case '}':
                        tok = R_BRACKET_TOK;
                        break;
                    case ',':
                        tok = COMMA_TOK;
                        break;
                    case '[':
                        tok = L_SQ_BRACKET_TOK;
                        break;
                    case ']':
                        tok = R_SQ_BRACKET_TOK;
                        break;
                    case '.':
                        tok = DOT_TOK;
                        break;
                    case '+':
                        tok = PLUS_TOK;
                        break;
                    case '*':
                        tok = TIMES_TOK;
                        break;
                    case '/':
                        tok = DIV_TOK;
                        break;
                    case '%':
                        tok = MOD_TOK;
                        break;
                    case '<':
                        tok = LT_TOK;
                        break;
                    case '>':
                        tok = GT_TOK;
                        break;
                    case ':':
                        tok = COLON_TOK;
                        break;
                    case '!':
                        tok = NOT_TOK;
                        break;
                    case '|':
                        tok = streamTokenizer.nextToken();
                        if (tok == '|'){
                            tok = OR_TOK;
                        }
                        else {
                            streamTokenizer.pushBack();
                            throw new TokenError("Illegal token " + this);
                        }
                        break;
                    case '&':
                        tok = streamTokenizer.nextToken();
                        if (tok == '&'){
                            tok = AND_TOK;
                        }
                        else {
                            streamTokenizer.pushBack();
                            throw new TokenError("Illegal token " + this);
                        }
                        break;
                    default:
                        throw new TokenError("Illegal token " + this);
                }
            }
            catch (IOException e) {
                throw new TokenError(e.getMessage());
            }
    }

    private boolean isANumber(String sval) {
        boolean result = true;
        if (sval.lastIndexOf('-')!=0 && sval.lastIndexOf('-')!=-1){
            result = false;
        }
        for (int i = 0; i < sval.length(); i++) {
            if ((sval.charAt(i) < '0' || sval.charAt(i) > '9') && sval.charAt(i) != '-'){
                result = false;
            }
        }
        return result;
    }

    public IllegalArgumentException parseError(String msg){
        return new ParseError(msg + " but found " + this);
    }

    public void pushBack(){
        streamTokenizer.pushBack();
    }

    @Override
    public String toString() {
        switch (tok){
            case StreamTokenizer.TT_EOF:
            case EOF:
                return "<EOF>";
            default:
                return ""+streamTokenizer;
        }
    }
}

class ParseError extends IllegalArgumentException {
    public ParseError(String s) {
        super(s);
    }
}

class TokenError extends IllegalArgumentException {
    public TokenError(String s) {
        super(s);
    }
}

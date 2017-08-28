import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ronan
 * on 31/07/2017.
 */
public class ScannerTest {
    private Scanner scanner = new Scanner("test/testData/simple/Scanner.txt");

    @Test
    public void next() throws Exception {
        assertTrue(scanner.tok==Scanner.VAR);
        scanner.next();
        assertTrue(scanner.tok==Scanner.NAME);
        scanner.next();
        assertTrue(scanner.tok==Scanner.ASSIGN_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.SEMICOLON_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.L_PAR_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.R_PAR_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.L_BRACKET_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.R_BRACKET_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.VOID);
        scanner.next();
        assertTrue(scanner.tok==Scanner.MINUS_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.GT_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.COLON_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.COLON_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.COMMA_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.R_SQ_BRACKET_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.L_SQ_BRACKET_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.INT);
        scanner.next();
        assertTrue(scanner.tok==Scanner.BOOL);
        scanner.next();
        assertTrue(scanner.tok==Scanner.L_SQ_BRACKET_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.R_SQ_BRACKET_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.IF);
        scanner.next();
        assertTrue(scanner.tok==Scanner.ELSE);
        scanner.next();
        assertTrue(scanner.tok==Scanner.WHILE);
        scanner.next();
        assertTrue(scanner.tok==Scanner.RETURN);
        scanner.next();
        assertTrue(scanner.tok==Scanner.PLUS_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.TIMES_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.DOUBLE);
        scanner. next();
        assertTrue(scanner.tok==Scanner.FALSE);
        scanner. next();
        assertTrue(scanner.tok==Scanner.TRUE);
//        scanner. next();
//        assertTrue(scanner.tok==Scanner.DOT_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.HD);
        scanner.next();
        assertTrue(scanner.tok==Scanner.TL);
        scanner. next();
        assertTrue(scanner.tok==Scanner.FST);
        scanner. next();
        assertTrue(scanner.tok==Scanner.SND);
        scanner. next();
        assertTrue(scanner.tok==Scanner.MINUS_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.DIV_TOK);
        scanner.next();
        assertTrue(scanner.tok==Scanner.MOD_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.ASSIGN_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.ASSIGN_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.LT_TOK);
        scanner.  next();
        assertTrue(scanner.tok==Scanner.GT_TOK);
        scanner.  next();
        assertTrue(scanner.tok==Scanner.LT_TOK);
        scanner.  next();
        assertTrue(scanner.tok==Scanner.ASSIGN_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.GT_TOK);
        scanner.  next();
        assertTrue(scanner.tok==Scanner.ASSIGN_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.NOT_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.ASSIGN_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.AND_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.OR_TOK);
        scanner. next();
        assertTrue(scanner.tok==Scanner.NOT_TOK);
        scanner.  next();
        assertTrue(scanner.tok==Scanner.COLON_TOK);
        scanner.  next();
        assertTrue(scanner.tok==Scanner.EOF);
    }

}
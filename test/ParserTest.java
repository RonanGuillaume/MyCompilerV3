import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ronan
 * on 09/08/2017.
 */
public class ParserTest {
    @Test
    public void parse() throws Exception {
        parseWithArgs("test/3-ok/simpleArithmetic.spl");
        parseWithArgs("test/3-ok/lists.spl");
        parseWithArgs("test/3-ok/tuples.spl");
//        parseWithArgs("test/3-ok/assignments.spl");
    }

    private void parseWithArgs(String path){
        Scanner ts = new Scanner(path);
        Parser p = new Parser(ts);
        System.out.println(p.parse());
    }

}
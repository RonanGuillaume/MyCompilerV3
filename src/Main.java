public class Main {

    public static void main(String[] args) {
        Scanner ts = new Scanner(args[0]);
        Parser p = new Parser(ts);
        System.out.println(p.parse());
    }
}
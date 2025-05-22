import java.io.FileNotFoundException;

public class Interpretador {
    private static int computeExp(ArvoreSintatica arv) {
        if (arv instanceof Num) {
            return ((Num) arv).num;
        }

        int arg1, arg2;

        if (arv instanceof Mult) {
            arg1 = computeExp(((Mult) arv).arg1);
            arg2 = computeExp(((Mult) arv).arg2);

            return arg1 * arg2;
        }

        if (arv instanceof Div) {
            arg1 = computeExp(((Div) arv).arg1);
            arg2 = computeExp(((Div) arv).arg2);

            return (int) (arg1 / arg2);
        }

        if (arv instanceof Soma) {
            arg1 = computeExp(((Soma) arv).arg1);
            arg2 = computeExp(((Soma) arv).arg2);

            return arg1 + arg2;
        }

        if (arv instanceof Sub) {
            arg1 = computeExp(((Sub) arv).arg1);
            arg2 = computeExp(((Sub) arv).arg2);

            // Como a máquina hipotética trabalha com números naturais, não podemos
            // retornar números negativos. Portanto, o menor valor retornado é 0.
            return Integer.max(arg1 - arg2, 0);
        }

        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        try {
            AnaliseLexica aLexica = new AnaliseLexica(args[0]);
            Parser parser = new Parser(aLexica);

            ArvoreSintatica arv = parser.parseProg();

            int result = computeExp(arv);

            System.out.println("RESULTADO: " + result);
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo '" + args[0] + "' não existe.");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}

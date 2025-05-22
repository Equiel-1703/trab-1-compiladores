import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class MaquinaPilha {
    private static LinkedList<Integer> stack = new LinkedList<>();

    private static String[] getCommand(String line) {
        String cleanLine = line.replaceAll("\\s+", " ").trim().toUpperCase();

        return cleanLine.split(" ");
    }

    private static void processCommand(String[] command) {
        if (command.length == 2) {
            switch (command[0]) {
                case "PUSH":
                    int value = Integer.parseInt(command[1]);
                    stack.push(value);
                    break;

                default:
                    break;
            }

            return;
        }

        if (command.length == 1) {
            switch (command[0]) {
                case "PRINT":
                    if (stack.isEmpty()) {
                        System.out.println("Pilha vazia.");
                    } else {
                        System.out.println(stack.peek());
                    }
                    return;

                default:
                    break;
            }

            // Se o comando não for PRINT, é uma operação
            // Vamos desempilhar os dois últimos números da pilha
            int num2 = stack.pop();
            int num1 = stack.pop();

            switch (command[0]) {
                case "SUM":
                    stack.push(num1 + num2);
                    break;
                case "SUB":
                    stack.push(Integer.max(num1 - num2, 0));
                    break;
                case "MULT":
                    stack.push(num1 * num2);
                    break;
                case "DIV":
                    stack.push(Math.floorDiv(num1, num2));
                    break;
                default:
                    System.out.println("Comando inválido: " + command[0]);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Informe o nome do arquivo de entrada.");
            return;
        }

        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader bReader = new BufferedReader(fr);

            String line;
            String[] command;

            while ((line = bReader.readLine()) != null) {
                command = getCommand(line);
                processCommand(command);
            }

            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo '" + args[0] + "' não existe.");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}

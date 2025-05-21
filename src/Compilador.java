import java.io.FileNotFoundException;

class Compilador {

	public static void main(String[] args) {
		ArvoreSintatica arv = null;

		try {
			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);

			arv = as.parseProg();

			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);

			System.out.println(codigo);

		} catch (FileNotFoundException e) {
			System.out.println("ERRO: Arquivo '" + args[0] + "' não encontrado.");
		} catch (Exception e) {
			System.out.println("ERRO:\n" + e);
		}
	}
}

import java.io.*;

enum TokenType {
	NUM, SOMA, SUB, MULT, DIV, APar, FPar, EOF
}

class Token {
	String lexema;
	TokenType tToken;

	Token(String l, TokenType t) {
		lexema = l;
		tToken = t;
	}
}

class AnaliseLexica {
	private BufferedReader arquivo;

	AnaliseLexica(String filePath) throws Exception {
		this.arquivo = new BufferedReader(new FileReader(filePath));
	}

	/**
	 * Reads and returns the next non-whitespace character from the input stream.
	 * Skips over newline ('\n'), space (' '), tab ('\t'), and carriage return
	 * ('\r') characters.
	 *
	 * @return the integer value of the next non-whitespace character read from the
	 *         input stream,
	 *         or -1 if the end of the stream is reached.
	 * @throws Exception if an I/O error occurs while reading from the input stream.
	 */
	private int getNextChar() throws Exception {
		char currchar;
		int currcharI;

		do {
			currcharI = arquivo.read();
			currchar = (char) currcharI;
		} while (currchar == '\n' || currchar == ' ' || currchar == '\t' || currchar == '\r');

		return currcharI;
	}

	Token getNextToken() throws Exception {
		final int EOF = -1;

		int currchar1 = getNextChar();
		char currchar = (char) currchar1;

		if (currchar1 != EOF && currchar1 != 10) {
			if (currchar >= '0' && currchar <= '9') {
				int nextCharI;
				char nextChar;

				StringBuilder num = new StringBuilder(Character.toString(currchar));

				while (true) {
					// Pode ler no máximo mais um caracter sem perder essa marcação
					// Nós vamos só ler 1, então tá ótimo
					arquivo.mark(1);

					nextCharI = getNextChar();
					nextChar = (char) nextCharI;

					if (Character.isDigit(nextChar)) {
						num.append(nextChar);
					} else {
						arquivo.reset();
						break;
					}
				}

				return (new Token(num.toString(), TokenType.NUM));

			} else
				switch (currchar) {
					case '(':
						return (new Token(Character.toString(currchar), TokenType.APar));
					case ')':
						return (new Token(Character.toString(currchar), TokenType.FPar));
					case '+':
						return (new Token(Character.toString(currchar), TokenType.SOMA));
					case '-':
						return (new Token(Character.toString(currchar), TokenType.SUB));
					case '*':
						return (new Token(Character.toString(currchar), TokenType.MULT));
					case '/':
						return (new Token(Character.toString(currchar), TokenType.DIV));

					default:
						throw (new Exception("Caractere inválido: " + ((int) currchar)));
				}
		}

		arquivo.close();

		return (new Token(Character.toString(currchar), TokenType.EOF));
	}
}

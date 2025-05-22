class Parser {
	private AnaliseLexica scanner;

	Parser(AnaliseLexica s) {
		scanner = s;
	}

	public ArvoreSintatica parseProg() throws Exception {
		ArvoreSintatica resultado = Exp();

		Token tokenCorrente = scanner.getNextToken();
		if (tokenCorrente.tToken != TokenType.EOF)
			throw (new Exception("Estava esperando EOF"));

		return resultado;

	}

	private Exp Exp() throws Exception {
		Token tokenCorrente = scanner.getNextToken();

		if (tokenCorrente.tToken == TokenType.NUM) {
			return new Num(Integer.parseInt(tokenCorrente.lexema));
		}

		Exp exp1, exp2;

		if (tokenCorrente.tToken == TokenType.APar) {
			exp1 = Exp();
			if (exp1 == null)
				throw (new Exception("Não encontrei expressão!"));

			Operador op = Op();

			exp2 = Exp();
			if (exp2 == null)
				throw (new Exception("Não encontrei expressão!"));

			op.arg1 = exp1;
			op.arg2 = exp2;

			tokenCorrente = scanner.getNextToken();
			if (tokenCorrente.tToken != TokenType.FPar)
				throw (new Exception("Estava esperando:)"));

			return op;
		} else
			throw (new Exception("Estava esperando: ( ou <NUM>"));
	}

	private Operador Op() throws Exception {
		Token tokenCorrente = scanner.getNextToken();

		switch (tokenCorrente.tToken) {
			case SOMA:
				return new Soma(null, null);
			case MULT:
				return new Mult(null, null);
			case SUB:
				return new Sub(null, null);
			case DIV:
				return new Div(null, null);
			default:
				throw (new Exception("Estava esperando um dos operadores: +, -, *, /"));
		}
	}
}

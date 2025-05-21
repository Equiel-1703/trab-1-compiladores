class CodeGen {
	public String geraCodigo(ArvoreSintatica arv) {
		return (geraCodigo2(arv) + "PRINT");
	}

	private String geraCodigo2(ArvoreSintatica arv) {
		if (arv instanceof Mult) {
			return (geraCodigo2(((Mult) arv).arg1) +
					geraCodigo2(((Mult) arv).arg2) +
					"MULT\n");
		}

		if (arv instanceof Div) {
			return (geraCodigo2(((Div) arv).arg1) +
					geraCodigo2(((Div) arv).arg2) +
					"DIV\n");
		}

		if (arv instanceof Soma) {
			return (geraCodigo2(((Soma) arv).arg1) +
					geraCodigo2(((Soma) arv).arg2) +
					"SUM\n");
		}

		if (arv instanceof Sub) {
			return (geraCodigo2(((Sub) arv).arg1) +
					geraCodigo2(((Sub) arv).arg2) +
					"SUB\n");
		}

		// Se for um número, retorna o código para empilhar o número
		if (arv instanceof Num) {
			return ("PUSH " + ((Num) arv).num + "\n");
		}

		return "";
	}
}

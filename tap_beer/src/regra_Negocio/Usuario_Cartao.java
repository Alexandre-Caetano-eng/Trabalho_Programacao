package regra_Negocio;

public class Usuario_Cartao {
	boolean verificaCPF;
	
	public boolean adicionarSaldo(String cpf, String valor) {
		long cpf_v;
		double valor_v;
		String[] array;
		//tratando cpf, tenta transformar o cpf String em long, se der erro, avisa.
		try {
			cpf_v=Long.parseLong(cpf);
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return false;
		}
		//tranforma a String valor em um array
		array=valor.split("");
		//zera valor
		valor="";
		//verifica se no array existe virgula, se existir altera para ponto,
		//no brasil usamos virgula, mas o programa precisa que esteja em ponto o separador
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(",")) {
				array[i]=".";
			}
			valor=valor+array[i];
		}
		//tratando o valor, tenta converter ele em double
		try {
			valor_v=Double.parseDouble(valor);
		}catch(Exception e) {
			System.out.println("Erro no valor inserido, digite um double válido.");
			return false;
		}
		verificaCPF=false;
		if(valor_v>0) {
			//verifica cpf_v no banco de dados e salva o retorno no verificaCPF
			if(verificaCPF==true) {
				//insere o valor no saldo
				return true;
			}else {
				System.out.println("CPF inválido.");
			}
		}else {
			if(valor_v==0) {
				System.out.println("Tem que ser um valor maior que zero para adicionar saldo.");
			}else {
				System.out.println("Tem que ser um valor positivo para adicionar saldo.");
			}
		}
		return false;
	}
	
	public double verificaSaldo(String cpf) {
		long cpf_v;
		verificaCPF=false;
		double saldo=0;
		//tratando cpf, tenta transformar o cpf String em long, se der erro, avisa.
		try {
			cpf_v=Long.parseLong(cpf);
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return saldo;
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		if(verificaCPF==true) {
			//puxa o saldo do usuário no banco de dados e insere em saldo
		}else {
			System.out.println("CPF inválido.");
		} 
		return saldo;
	}
	
	public boolean cadastrarUsuario(String cpf, String nome) {
		verificaCPF=false;
		long cpf_v;
		//tratando cpf, tenta transformar o cpf String em long, se der erro, avisa.
		try {
			cpf_v=Long.parseLong(cpf);
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return false;
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		if(verificaCPF==true) {
			if(!nome.equals("")) {
				//insere o novo usuario
				return true;
			}else {
				System.out.println("Nome não pode ser vazio.");
			}
		}else {
			System.out.println("CPF inválido.");
		} 
		return false;
	}
	
	public boolean alterarNomeUsuario(String cpf, String nome) {
		verificaCPF=false;
		long cpf_v;
		//tratando cpf, tenta transformar o cpf String em long, se der erro, avisa.
		try {
			cpf_v=Long.parseLong(cpf);
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return false;
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		if(verificaCPF==true) {
			//verifica se o nome não está vazio.
			if(!nome.equals("")) {
				//altera o nome do usuario
				return true;
			}else {
				System.out.println("Nome não pode ser vazio.");
			}
		}else {
			System.out.println("CPF inválido.");
		} 
		return false;
	}
	
	public boolean excluirUsuario(String cpf) {
		verificaCPF=false;
		long cpf_v;
		//tratando cpf, tenta transformar o cpf String em long, se der erro, avisa.
		try {
			cpf_v=Long.parseLong(cpf);
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return false;
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		if(verificaCPF==true) {
			//exclui o usuario
			return true;
		}else {
			System.out.println("CPF inválido.");
		} 
		return false;
	}
	
	public Usuario_Cartao() {
		
	}
}

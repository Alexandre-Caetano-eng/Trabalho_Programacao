package regra_Negocio;

public class Usuario_Cartao {
	boolean verificaCPF;
	
	public boolean adicionarSaldo(String cpf_Usuario, String valor) {
		long cpf_v;
		double valor_v;
		String cpf="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e traços
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//trata o cpf do usuario
		try {
			cpf_v=Long.parseLong(cpf.trim());
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
	
	public double verificaSaldo(String cpf_Usuario) {
		long cpf_v;
		verificaCPF=false;
		double saldo=0;
		String cpf="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e traços
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//trata o cpf do usuario
		try {
			cpf_v=Long.parseLong(cpf.trim());
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
	
	public boolean cadastrarUsuario(String cpf_Usuario, String nome) {
		verificaCPF=false;
		long cpf_v;
		String cpf="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e traços
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//trata o cpf do usuario
		try {
			cpf_v=Long.parseLong(cpf.trim());
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
	
	public boolean alterarNomeUsuario(String cpf_Usuario, String nome) {
		verificaCPF=false;
		long cpf_v;
		String cpf="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e traços
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//trata o cpf do usuario
		try {
			cpf_v=Long.parseLong(cpf.trim());
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
	
	public boolean excluirUsuario(String cpf_Usuario) {
		verificaCPF=false;
		long cpf_v;
		String cpf="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e traços
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//trata o cpf do usuario
		try {
			cpf_v=Long.parseLong(cpf.trim());
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

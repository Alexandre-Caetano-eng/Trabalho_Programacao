package regra_Negocio;

public class Transacao {
	boolean verificaCPF, verificaTor;
	public static Torneira Tor = new Torneira();
	
	public boolean cadastrarTransacao(String cpf_Usuario, String id_Torneira, String quant_Prod) {
		long cpf_v;
		int tor_v;
		double quantp_v, valor_p=0, valor_t=0;
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
			cpf_v=Integer.parseInt(cpf.trim());
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return false;
		}
		//trata o id do produto
		try {
			tor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			System.out.println("Erro no id da Torneira inserido, digite um inteiro válido.");
			return false;
		}
		//trata a quantidade de produto
		try {
			quantp_v=Double.parseDouble(quant_Prod);
		}catch(Exception e) {
			System.out.println("Erro no valor inserido, digite um double válido.");
			return false;
		}
		//busca no banco o valor do produto e salva em valor_p
		//calcula o valor a pagar e testa no if, valor_t=quantp_v * valor_p
		if(valor_t>0) {
			verificaCPF=false;
			//verifica se o cpf do usuario é válido
			if(verificaCPF==true) {
				verificaTor=false;
				//verifica no banco de a torneira está cadastrada
				if(verificaTor==true) {
					try {
						//chama funçao cadastrar a função no banco, passando(cpf_v, prod_v, quantp_v, )
						return true;
					}catch(Exception e) {
						System.out.println("Erro ao inserir no banco.");
						return false;
					}
				}else {
					System.out.println("Torneira não cadastrada.");
					return false;
				}
			}else {
				System.out.println("Usuário não cadastrado.");
				return false;
			}
		}else {
			System.out.println("Valor da transação igual ou menor que zero.");
			return false;
		}
	}
	
	public double encherCopo(String cpf_Usuario, String id_Torneira) {
		double permitido=0, usu_p=0, quant_t=0;
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
			cpf_v=Integer.parseInt(cpf.trim());
		}catch(Exception e) {
			System.out.println("Erro no cpf inserido, digite um long válido.");
			return permitido;
		}
		//busca no banco o valor de do produto que está na torneira usando o id_torneira e coloca dentro
		//de outra requisiçao, essa vendo quanto o usuario tem de saldo e calcula o máximo que o usuário
		//pode pegar em ml, coloca isso dentro do usu_p.
		quant_t=Tor.liberarProdutoEncherCopo(id_Torneira);
		if(usu_p>quant_t) {
			permitido=quant_t;
		}else {
			permitido=usu_p;
		}
		return permitido;
	}
	
	public String[][] consultarTransacao(String cpf_Usuario, String id_Transacao) {
		long cpf_v;
		int trans_v;
		double quantp_v, valor_p=0, valor_t=0;
		String cpf="";
		String[] array;
		String[][] retorno= {{"erro","erro","erro","erro","erro","erro"}};
		if(!cpf_Usuario.equals("")) {
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
				cpf_v=Integer.parseInt(cpf.trim());
			}catch(Exception e) {
				System.out.println("Erro no cpf inserido, digite um long válido.");
				return retorno;
			}
			if(id_Transacao.equals("")) {
				//consulta no banco de dados todas as transação feitas pelo usuario e coloca em retorno
				return retorno;
			}
		}
		if(!id_Transacao.equals("")) {
			//trata o id da transacao
			try {
				trans_v=Integer.parseInt(id_Transacao);
			}catch(Exception e) {
				System.out.println("Erro no id da Transação inserida, digite um inteiro válido.");
				return retorno;
			}
			if(cpf_Usuario.equals("")) {
				//consulta no banco de dados a transação e coloca em retorno
				return retorno;
			}
		}
		return retorno;
	}
	
	public Transacao() {
		
	}
}

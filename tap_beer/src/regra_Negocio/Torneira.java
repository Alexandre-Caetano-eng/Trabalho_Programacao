package regra_Negocio;

public class Torneira {
	boolean verificaTor;
	boolean verificaProd;
	
	public boolean adicionarProduto(String id_Torneira, String id_Produto, String quant_Produto) {
		int idtor_v, idprod_v;
		double quantp_v;
		verificaTor=false;
		verificaProd=false;
		//tratando o id da torneira
		try {
			idtor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			System.out.println("Erro na id da torneira inserida, digite um inteiro válido.");
			return false;
		}
		//tratando o id do produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			System.out.println("Erro no id do Produto inserido, digite um inteiro válido.");
			return false;
		}
		//tratando a quantidade recebida de produto
		try {
			quantp_v=Double.parseDouble(quant_Produto);
		}catch(Exception e) {
			System.out.println("Erro na quantidade de Produto inserido, digite um double válido.");
			return false;
		}
		//verifica se a torneira está cadastrada
		if(verificaTor==true) {
			//verifica se o produto está cadastrado
			if(verificaProd==true) {
				if(quantp_v>0) {
					//insere o produto na torneira
					return true;
				}else {
					if(quantp_v==0) {
						System.out.println("Tem que ser uma quantidade maior que zero de produto para torneira.");
					}else {
						System.out.println("Quantidade informada inválida.");
					}
				}
			}else {
				System.out.println("Produto não cadastrado");
			}
		}else {
			System.out.println("Torneira não cadastrada");
		}
		return false;
	}
	
	public double liberarProdutoEncherCopo(String id_Torneira) {
		int idtor_v;
		double quantp=0;
		verificaTor=false;
		verificaProd=false;
		//tratando o id da torneira
		try {
			idtor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			System.out.println("Erro na id da torneira inserida, digite um inteiro válido.");
			return quantp;
		}
		//verifica se a torneira está cadastrada
		if(verificaTor==true) {
			//puxa a quantidade de produto na torneira atual e coloca em quantp
			return quantp;
		}else {
			System.out.println("Id torneira inválido.");
		} 
		return quantp;
	}
	
	public boolean cadastrarTorneira(String localizacao) {
		if(!localizacao.equals("")) {
			//insere o novo usuario
			return true;
		}else {
			System.out.println("Localizacao não pode ser vazio.");
		}
		return false;
	}
	
	public boolean informarFimProduto(String id_Torneira) {
		verificaTor=false;
		int idtor_v;
		//tratando a id da torneira
		try {
			idtor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			System.out.println("Erro na id da torneira inserido, digite um inteiro válido.");
			return false;
		}
		//verifica se a torneira existe no banco de dados e salva o retorno no verificaTor
		if(verificaTor==true) {
			//manda uma mensagem de que o produto acabou no sistema.
			return true;
		}else {
			System.out.println("Id da torneira inválida.");
		} 
		return false;
	}
	
	public String[][] consultarTorneiras(String id_Torneira, String id_Produto, String localizacao, String quant_Prod) {
		int idtor_v, idprod_v;
		double quantp_v;
		String[] array;
		String[][] retorno = new String[200][4];
		//transforma a String onde vem o valor do produto em um array
		array=quant_Prod.split("");
		quant_Prod="";
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(",")) {
				array[i]=".";
			}
			quant_Prod=quant_Prod+array[i];
		}
		//tratando a quantidade recebida de produto
		try {
			quantp_v=Double.parseDouble(quant_Prod);
		}catch(Exception e) {
			System.out.println("Erro na quantidade de Produto inserido, digite um double válido.");
			return retorno;
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		if(!id_Torneira.equals("")) {
			if(id_Torneira.equals("todas")) {
				//select de todas as torneiras inseridas inseridas no retorno.
				return retorno;
			}else {
				try {
					idtor_v=Integer.parseInt(id_Torneira);
					//select da torneira especifica
					return retorno;
				}catch(Exception e) {
					System.out.println("Erro na id da torneira inserida, digite um inteiro válido.");
					return retorno;
				}
			}
		}
		if(!id_Produto.equals("")) {
			//tratando o id do produto
			try {
				idprod_v=Integer.parseInt(id_Produto);
			}catch(Exception e) {
				System.out.println("Erro no id do Produto inserido, digite um inteiro válido.");
				return retorno;
			}
			if(!localizacao.equals("")) {
				if(!quant_Prod.equals("")) {
					//select das toneiras com determinado produto, em tal localizacao e com uma quantidade menor ou igaul de produto
					return retorno;
				}
				//select de todos as torneiras da localidade localizacao com o determinado produto
				return retorno;
			}else {
				if(!quant_Prod.equals("")) {
					//select com as torneiras que possuem a quantidade menor ou igual de produto
					return retorno;
				}
			}
			//select com o id do produto especifico
			return retorno;
		}else {
			if(!localizacao.equals("")) {
				if(!quant_Prod.equals("")) {
					
				}
				//select de todos as torneiras da localidade localizacao
			}else {
				if(!quant_Prod.equals("")) {
					//select com as torneiras que possuem a quantidade menor ou igual de produto
					return retorno;
				}
			}
		}
		return retorno;
	}
	
	public Torneira() {
		
	}
}

package regra_Negocio;

import Banco_de_Dados.Produto_Selecionar;
import Banco_de_Dados.Torneira_Alterar;
import Banco_de_Dados.Torneira_Cadastro;
import Banco_de_Dados.Torneira_Selecionar;

public class Torneira {
	Produto_Selecionar PS = new Produto_Selecionar();
	Torneira_Selecionar TS = new Torneira_Selecionar();
	Torneira_Cadastro TC = new Torneira_Cadastro();
	Torneira_Alterar TA = new Torneira_Alterar();
	boolean verificaTor;
	boolean verificaProd;
	int numTorneiras=200, numCamposTorneira=4;
	
	public String adicionarProduto(String id_Torneira, String id_Produto, String quant_Produto) {
		int idtor_v, idprod_v;
		double quantp_v;
		verificaTor=false;
		verificaProd=false;
		//tratando o id da torneira
		try {
			idtor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			return "Erro na id da torneira inserida, digite um inteiro válido.";
		}
		//tratando o id do produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			return "Erro no id do Produto inserido, digite um inteiro válido.";
		}
		//tratando a quantidade recebida de produto
		try {
			quantp_v=Double.parseDouble(quant_Produto);
		}catch(Exception e) {
			return "Erro na quantidade de Produto inserido, digite um double válido.";
		}
		//verifica se a torneira está cadastrada
		verificaTor=TS.verificarTorneiraExiste(idtor_v);
		if(verificaTor==true) {
			//verifica se o produto está cadastrado
			verificaProd=PS.verificarProdutoExiste(idprod_v);
			if(verificaProd==true) {
				if(quantp_v>0) {
					//insere o produto na torneira
					if(TA.adicionarProdutoTorneira(idtor_v, idprod_v, quantp_v)==true) {
						return "Adicionado.";
					}else {
						return "Não adicionado.";
					}
					
				}else {
					if(quantp_v==0) {
						return "Tem que ser uma quantidade maior que zero de produto para torneira.";
					}else {
						return "Quantidade informada inválida.";
					}
				}
			}else {
				return "Produto não cadastrado";
			}
		}else {
			return "Torneira não cadastrada";
		}
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
			return 0;
			//"Erro na id da torneira inserida, digite um inteiro válido.";
		}
		//verifica se a torneira está cadastrada
		verificaTor=TS.verificarTorneiraExiste(idtor_v);
		if(verificaTor==true) {
			//puxa a quantidade de produto na torneira atual e coloca em quantp
			quantp=TS.verificarQuantProdutoTorneira(idtor_v);
			return quantp;
		}else {
			return 0;
			//"Id torneira inválido.";
		}
	}
	
	public String cadastrarTorneira(String localizacao) {
		if(!localizacao.equals("")) {
			//insere a nova torneira
			TC.cadastrarTorneira(localizacao);
			return "Cadastrada.";
		}else {
			return "Localizacao não pode ser vazio.";
		}
	}
	
	public String informarFimProduto(String id_Torneira) {
		String mensagem;
		verificaTor=false;
		int idtor_v;
		//tratando a id da torneira
		try {
			idtor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			mensagem="Erro na id da torneira inserido, digite um inteiro válido.";
			return mensagem;
		}
		//verifica se a torneira existe no banco de dados e salva o retorno no verificaTor
		verificaTor=TS.verificarTorneiraExiste(idtor_v);
		if(verificaTor==true) {
			//manda uma mensagem de que o produto acabou no sistema.
			mensagem="Fim do produto na torneira "+id_Torneira;
		}else {
			mensagem="Id da torneira inválida.";
		}
		return mensagem;
	}
	
	public String[][] consultarTorneiras(String id_Torneira, String id_Produto, String localizacao, String quant_Prod) {
		int idtor_v, idprod_v;
		double quantp_v;
		String[] array;
		String[][] retorno = new String[numTorneiras][numCamposTorneira];
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
			retorno[0][0]="Erro na quantidade de Produto inserido, digite um double válido.";
			return retorno;
		}
		//verifica se a torneira existe no banco
		if(!id_Torneira.equals("")) {
			if(id_Torneira.equals("todas")) {
				//select de todas as torneiras inseridas no retorno.
				retorno = TS.consultarTodasTorneira();
				return retorno;
			}else {
				try {
					idtor_v=Integer.parseInt(id_Torneira);
					//select da torneira especifica
					retorno=TS.consultarTorneira(idtor_v);
					return retorno;
				}catch(Exception e) {
					retorno[0][0]="Erro na id da torneira inserida, digite um inteiro válido.";
					return retorno;
				}
			}
		}
		if(!id_Produto.equals("")) {
			//tratando o id do produto
			try {
				idprod_v=Integer.parseInt(id_Produto);
			}catch(Exception e) {
				retorno[0][0]="Erro no id do Produto inserido, digite um inteiro válido.";
				return retorno;
			}
			if(!localizacao.equals("")) {
				if(quantp_v>=0) {
					retorno = TS.consultarTorneiraProdutoLocalizada(idprod_v, localizacao, quantp_v);
					//select das toneiras com determinado produto, em tal localizacao e com uma quantidade menor ou igaul de produto
					return retorno;
				}
				retorno=TS.consultarTorneiraProdutoApenasLocalizada(idprod_v, localizacao);
				//select de todos as torneiras da localidade localizacao com o determinado produto
				return retorno;
			}else {
				if(quantp_v>=0) {
					//select com as torneiras que possuem a quantidade menor ou igual de produto
					retorno=TS.consultarTorneiraProdutoQuant(idprod_v, quantp_v);
					return retorno;
				}
			}
			//select com o id do produto especifico
			retorno=TS.consultarTorneiraProduto(idprod_v);
			return retorno;
		}else {
			if(!localizacao.equals("")) {
				if(!quant_Prod.equals("")) {
					//select de todos as torneiras da localidade e quantidade de produto menor de produto
					retorno=TS.consultarTorneiraLocalizacaoQuant(localizacao, quantp_v);
					return retorno;
				}
			}else {
				if(!quant_Prod.equals("")) {
					//select com as torneiras que possuem a quantidade menor ou igual de produto
					retorno=TS.consultarTorneiraQuant(quantp_v);
					return retorno;
				}
			}
		}
		return retorno;
	}
	
	public Torneira() {
		
	}
}

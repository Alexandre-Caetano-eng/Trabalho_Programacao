package regra_Negocio;

import Banco_de_Dados.Produto_Selecionar;
import Banco_de_Dados.Torneira_Selecionar;
import Banco_de_Dados.Transacao_Cadastrar;
import Banco_de_Dados.Transacao_Selecionar;
import Banco_de_Dados.Usuario_Alterar;
import Banco_de_Dados.Usuario_Selecionar;

public class Transacao {
	Verifica_CPF ve = new Verifica_CPF();
	boolean verificaCPF, verificaTor;
	public static Torneira Tor = new Torneira();
	Produto_Selecionar PS = new Produto_Selecionar();
	Torneira_Selecionar TS = new Torneira_Selecionar();
	Usuario_Selecionar US = new Usuario_Selecionar();
	Usuario_Alterar UA = new Usuario_Alterar();
	Transacao_Selecionar TRS = new Transacao_Selecionar();
	Transacao_Cadastrar TRC = new Transacao_Cadastrar();
	
	public String cadastrarTransacao(String cpf_Usuario, String id_Torneira, String quant_Prod) {
		int tor_v, idProd;
		double quantp_v, valor_p=0, valor_t=0;
		String cpf="", nomeP="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e tra�os
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//verifica o tamanho do cpf
		if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
			return "Erro no cpf inserido, digite um cpf com tamanho v�lido.";
		}
		//verifica se o cpf � v�lido
		if(ve.verifica(cpf.trim())==false) {
			return "Erro no cpf inserido, digite um cpf v�lido.";
		}
		//trata o id do produto
		try {
			tor_v=Integer.parseInt(id_Torneira);
		}catch(Exception e) {
			return "Erro no id da Torneira inserido, digite um inteiro v�lido.";
		}
		//trata a quantidade de produto
		try {
			quantp_v=Double.parseDouble(quant_Prod);
		}catch(Exception e) {
			return "Erro no valor inserido, digite um double v�lido.";
		}
		//busca no banco o valor do produto e salva em valor_p, ap�s buscar o produto na torneira
		idProd=TS.verificarTorneiraProduto(tor_v);
		if(idProd>-1) {
			valor_p=PS.valorProduto(idProd);
		}else {
			return ("Erro, sem produto na torneira "+id_Torneira);
		}
		//calcula o valor a pagar e testa no if, valor_t=quantp_v * valor_p
		valor_t=quantp_v * valor_p;
		if(valor_t>0) {
			verificaCPF=ve.verifica(cpf.trim());
			//verifica se o cpf do usuario � v�lido
			if(verificaCPF==true) {
				verificaTor=TS.verificarTorneiraExiste(tor_v);
				//verifica no banco de a torneira est� cadastrada
				if(verificaTor==true) {
					try {
						nomeP=PS.nomeProduto(idProd);
						//chama fun�ao cadastrar a fun��o no banco, passando(cpf_v, nome_produto,prod_v, quantp_v, )
						if(TRC.cadastrarTransacao(cpf, nomeP, quantp_v, valor_t)==true) {
							if(UA.adicionarSaldo(cpf, US.saldoUsuario(cpf)-valor_t)==true) {
								return "Transa��o Finalizada.";
							}else {
								return "Cadastrado com problemas.";
							}
						}else {
							return "N�o cadastrado.";
						}
					}catch(Exception e) {
						return "Erro ao tentar inserir no banco.";
					}
				}else {
					return "Torneira n�o cadastrada.";
				}
			}else {
				return "Usu�rio n�o cadastrado.";
			}
		}else {
			return "Valor da transa��o igual ou menor que zero.";
		}
	}
	
	public double encherCopo(String cpf_Usuario, String id_Torneira) {
		double permitido=0, usu_p=0, quant_t=0;
		String cpf="";
		String[] array;
		array=cpf_Usuario.split("");
		//pega o cpf e retira os pontos e tra�os
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(".")) {
				array[i]="";
			}
			if(array[i].equals("-")) {
				array[i]="";
			}
			cpf=cpf+array[i];
		}
		//verifica o tamanho do cpf
		if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
			//Erro no cpf inserido, digite um cpf com tamanho v�lido."
			return permitido;
		}
		//verifica se o cpf � v�lido
		if(ve.verifica(cpf.trim())==false) {
			//"Erro no cpf inserido, digite um cpf v�lido."
			return permitido;
		}
		//busca no banco o valor de do produto que est� na torneira usando o id_torneira e coloca dentro
		//de outra requisi�ao, essa vendo quanto o usuario tem de saldo e calcula o m�ximo que o usu�rio
		//pode pegar em ml, coloca isso dentro do usu_p.
		quant_t=Tor.liberarProdutoEncherCopo(id_Torneira);
		usu_p=US.saldoUsuario(cpf.trim());
		if(usu_p/PS.valorProduto(TS.verificarTorneiraProduto(Integer.parseInt(id_Torneira)))>quant_t) {
			permitido=quant_t;
		}else {
			permitido=usu_p;
		}
		return permitido;
	}
	
	public String[][] consultarTransacao(String cpf_Usuario, String id_Transacao) {
		int trans_v;
		String cpf="";
		String[] array;
		String[][] retorno= {{"erro","erro","erro","erro","erro","erro"}};
		if(!cpf_Usuario.equals("")) {
			array=cpf_Usuario.split("");
			//pega o cpf e retira os pontos e tra�os
			for(int i=0;i<array.length;i++) {
				if(array[i].equals(".")) {
					array[i]="";
				}
				if(array[i].equals("-")) {
					array[i]="";
				}
				cpf=cpf+array[i];
			}
			//verifica o tamanho do cpf
			if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
				retorno[0][0]="Erro no cpf inserido, digite um cpf com tamanho v�lido.";
				return retorno;
			}
			//verifica se o cpf � v�lido
			if(ve.verifica(cpf.trim())==false) {
				retorno[0][0]="Erro no cpf inserido, digite um cpf v�lido.";
				return retorno;
			}
			if(id_Transacao.equals("")) {
				//consulta no banco de dados todas as transa��o feitas pelo usuario e coloca em retorno
				retorno = TRS.verificaTransacaoUsuario(cpf.trim());
				return retorno;
			}
		}
		if(!id_Transacao.equals("")) {
			//trata o id da transacao
			try {
				trans_v=Integer.parseInt(id_Transacao);
			}catch(Exception e) {
				retorno[0][0]="Erro no id da Transa��o inserida, digite um inteiro v�lido.";
				return retorno;
			}
			if(cpf_Usuario.equals("")) {
				//consulta no banco de dados a transa��o e coloca em retorno
				retorno = TRS.verificaTransacao(trans_v);
				return retorno;
			}
		}
		return retorno;
	}
	
	public Transacao() {
		
	}
}

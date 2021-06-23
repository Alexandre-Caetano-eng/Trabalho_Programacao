package regra_Negocio;

import Banco_de_Dados.Produto_Cadastro;
import Banco_de_Dados.Produto_Alterar;
import Banco_de_Dados.Produto_Excluir;
import Banco_de_Dados.Produto_Selecionar;

public class Produto {
	boolean verificaProd;
	Produto_Cadastro PdC = new Produto_Cadastro();
	Produto_Alterar PdA = new Produto_Alterar();
	Produto_Excluir PdE = new Produto_Excluir();
	Produto_Selecionar PdS = new Produto_Selecionar();
	
	public String cadastrarProduto(String nome, String valor) {
		String mensagem="";
		boolean retorno=false;
		double valor_v;
		String[] array;
		//transforma a String onde vem o valor do produto em um array
		array=valor.split("");
		valor="";
		//troca a vigula no valor recebido por ponto
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(",")) {
				array[i]=".";
			}
			valor=valor+array[i];
		}
		//trata o valor do produto
		try {
			valor_v=Double.parseDouble(valor);
		}catch(Exception e) {
			return mensagem="Erro no valor inserido, digite um double válido.";
		}
		//verifica se nome não é vazio
		if(!nome.equals("")) {
			retorno = PdC.cadastroProduto(nome, valor_v);
			if(retorno==true) {
				return mensagem="Produto Cadastrado.";
			}else {
				return mensagem="Produto não Cadastrado.";
			}
		}else {
			return mensagem="Nome não pode ser vazio.";
		}
	}
	
	public String alterarProdutoNome(String id_Produto, String nome) {
		boolean retorna=false;
		String mensagem="";
		verificaProd=false;
		int idprod_v;
		//trata o id_Produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			return mensagem="Erro no id do produto inserido, digite um inteiro válido.";
		}
		//verifica se o id_Produto está no banco de dados e salva o retorno no verificaProd
		verificaProd = PdS.verificarProdutoExiste(idprod_v);
		if(verificaProd==true) {
			//verifica se o nome não está vazio
			if(!nome.equals("")) {
				retorna = PdA.alterarProdutoNome(idprod_v, nome);
				if(retorna==true) {
					return mensagem="Nome alterado.";
				}else {
					return mensagem="Nome não alterado.";
				}
			}else {
				return mensagem="Nome não pode ser vazio.";
			}
		}else {
			return mensagem="Id do produto inválido.";
		} 
	}
	
	public String alterarProdutoPreco(String id_Produto, String valor) {
		String mensagem="";
		boolean retorno=false;
		verificaProd=false;
		int idprod_v;
		double valor_v;
		String[] array;
		//transforma a String onde vem o valor do produto em um array
		array=valor.split("");
		valor="";
		//troca a vigula no valor recebido por ponto
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(",")) {
				array[i]=".";
			}
			valor=valor+array[i];
		}
		//trata o id_Produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			return mensagem="Erro no id do produto inserido, digite um inteiro válido.";
		}
		//trata o preço(valor) do produto inserido
		try {
			valor_v=Double.parseDouble(valor);
		}catch(Exception e) {
			return mensagem="Erro no preço do produto inserido, digite um double válido.";
		}
		//verifica se o id_Produto está no banco de dados e salva o retorno no verificaProd
		verificaProd = PdS.verificarProdutoExiste(idprod_v);
		if(verificaProd==true) {
			//verifica se o valor do produto não é menor que zero
			if(valor_v>=0) {
				retorno = PdA.alterarProdutoPreco(idprod_v, valor_v);
				if(retorno==true) {
					return mensagem="Preço alterado.";
				}else {
					return mensagem="Preço não alterado.";
				}
			}else {
				return mensagem="O valor do produto não pode ser menor que zero.";
			}
		}else {
			return mensagem="Id do produto inválido.";
		}
	}
	
	public String excluirProduto(String id_Produto) {
		String mensagem="";
		boolean retorno=false;
		verificaProd=false;
		int idprod_v;
		//trata a id do produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			return mensagem="Erro no id do produto inserido, digite um inteiro válido.";
		}
		//verifica se o id_Produto está no banco de dados e salva o retorno no verificaProd
		verificaProd = PdS.verificarProdutoExiste(idprod_v);
		if(verificaProd==true) {
			//exclui o produto
			retorno = PdE.excluirProduto(idprod_v);
			if(retorno==true) {
				return mensagem="Produto excluido";
			}else {
				return mensagem="Produto não excluido";
			}
		}else {
			return mensagem="Id do produto inválido.";
		}
	}
	
	public Produto() {
		
	}
}

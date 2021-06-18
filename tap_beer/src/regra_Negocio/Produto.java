package regra_Negocio;

public class Produto {
	boolean verificaProd;
	
	public boolean cadastrarProduto(String nome, String valor) {
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
			System.out.println("Erro no valor inserido, digite um double válido.");
			return false;
		}
		//verifica se nome não é vazio
		if(!nome.equals("")) {
			//insere o novo produto
			return true;
		}else {
			System.out.println("Nome não pode ser vazio.");
		}
		return false;
	}
	
	public boolean alterarProdutoNome(String id_Produto, String nome) {
		verificaProd=false;
		int idprod_v;
		//trata o id_Produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			System.out.println("Erro no id do produto inserido, digite um inteiro válido.");
			return false;
		}
		//verifica se o id_Produto está no banco de dados e salva o retorno no verificaProd
		if(verificaProd==true) {
			//verifica se o nome não está vazio
			if(!nome.equals("")) {
				//altera o nome do produto
				return true;
			}else {
				System.out.println("Nome não pode ser vazio.");
			}
		}else {
			System.out.println("Id do produto inválido.");
		} 
		return false;
	}
	
	public boolean alterarProdutoPreco(String id_Produto, String valor) {
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
			System.out.println("Erro no id do produto inserido, digite um inteiro válido.");
			return false;
		}
		//trata o preço(valor) do produto inserido
		try {
			valor_v=Double.parseDouble(valor);
		}catch(Exception e) {
			System.out.println("Erro no preço do produto inserido, digite um double válido.");
			return false;
		}
		//verifica se o id_Produto está no banco de dados e salva o retorno no verificaProd
		if(verificaProd==true) {
			//verifica se o valor do produto não é menor que zero
			if(valor_v>=0) {
				//altera o produto
				return true;
			}else {
				System.out.println("O valor do produto não pode ser menor que zero.");
			}
		}else {
			System.out.println("Id do produto inválido.");
		} 
		return false;
	}
	
	public boolean excluirProduto(String id_Produto) {
		verificaProd=false;
		int idprod_v;
		//trata a id do produto
		try {
			idprod_v=Integer.parseInt(id_Produto);
		}catch(Exception e) {
			System.out.println("Erro no id do produto inserido, digite um inteiro válido.");
			return false;
		}
		//verifica se o id_Produto está no banco de dados e salva o retorno no verificaProd
		if(verificaProd==true) {
			//exclui o produto
			return true;
		}else {
			System.out.println("Id do produto inválido.");
		} 
		return false;
	}
	
	public Produto() {
		
	}
}

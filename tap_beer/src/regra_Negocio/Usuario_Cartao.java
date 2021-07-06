package regra_Negocio;

import Banco_de_Dados.Usuario_Alterar;
import Banco_de_Dados.Usuario_Cadastrar;
import Banco_de_Dados.Usuario_Excluir;
import Banco_de_Dados.Usuario_Selecionar;

public class Usuario_Cartao {
	boolean verificaCPF;
	Verifica_CPF ve = new Verifica_CPF();
	Usuario_Alterar UA = new Usuario_Alterar();
	Usuario_Cadastrar UC = new Usuario_Cadastrar();
	Usuario_Excluir UE = new Usuario_Excluir();
	Usuario_Selecionar US = new Usuario_Selecionar();
	
	public String adicionarSaldo(String cpf_Usuario, String valor) {
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
		//verifica o tamanho do cpf
		if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
			return "Erro no cpf inserido, digite um cpf com tamanho válido.";
		}
		//verifica se o cpf é válido
		if(ve.verifica(cpf.trim())==false) {
			return "Erro no cpf inserido, digite um cpf válido.";
		}
		//tranforma a String valor em um array
		array=valor.split("");
		//zera valor
		valor="";
		//verifica se no array existe virgula, se existir altera para ponto,
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
			return "Erro no valor inserido, digite um double válido.";
		}
		if(valor_v>0) {
			//verifica cpf_v no banco de dados e salva o retorno no verificaCPF
			verificaCPF=US.verificaUsuarioExiste(cpf.trim());
			if(verificaCPF==true) {
				//insere o valor no saldo
				if(UA.adicionarSaldo(cpf, valor_v+US.saldoUsuario(cpf))==true) {
					return "Saldo inserido "+valor_v;
				}else {
					return "Saldo não inserido";
				}
			}else {
				return "CPF inválido.";
			}
		}else {
			if(valor_v==0) {
				return "Tem que ser um valor maior que zero para adicionar saldo.";
			}else {
				return "Tem que ser um valor positivo para adicionar saldo.";
			}
		}
	}
	
	public double verificaSaldo(String cpf_Usuario) {
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
		//verifica o tamanho do cpf
		if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
			//Erro no cpf inserido, digite um cpf com tamanho válido.
			return saldo;
		}
		//verifica se o cpf é válido
		if(ve.verifica(cpf.trim())==false) {
			//Erro no cpf inserido, digite um cpf válido.
			return saldo;
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		verificaCPF=US.verificaUsuarioExiste(cpf.trim());
		if(verificaCPF==true) {
			//puxa o saldo do usuário no banco de dados e insere em saldo
			saldo=US.saldoUsuario(cpf.trim());
		}else {
			//CPF inválido."
		}
		return saldo;
	}
	
	public String cadastrarUsuario(String cpf_Usuario, String nome) {
		verificaCPF=false;
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
		//verifica o tamanho do cpf
		if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
			return "Erro no cpf inserido, digite um cpf com tamanho válido.";
		}
		//verifica se o cpf é válido
		if(ve.verifica(cpf.trim())==false) {
			return "Erro no cpf inserido, digite um cpf válido.";
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		verificaCPF=ve.verifica(cpf.trim());
		if(verificaCPF==true) {
			if(!nome.equals("")) {
				//insere o novo usuario
				if(UC.cadastrarUsuario(cpf.trim(), nome)==true) {
					return "Usuario criado com sucesso.";
				}else {
					return "Usuario não criado.";
				}
			}else {
				return "Nome não pode ser vazio.";
			}
		}else {
			return "CPF inválido.";
		}
	}
	
	public String alterarNomeUsuario(String cpf_Usuario, String nome) {
		verificaCPF=false;
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
		//verifica o tamanho do cpf
		if(cpf.trim().length()<11 ||cpf.trim().length()>11) {
			return "Erro no cpf inserido, digite um cpf com tamanho válido.";
		}
		//verifica se o cpf é válido
		if(ve.verifica(cpf.trim())==false) {
			return "Erro no cpf inserido, digite um cpf válido.";
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		verificaCPF=US.verificaUsuarioExiste(cpf.trim());
		if(verificaCPF==true) {
			//verifica se o nome não está vazio.
			if(!nome.equals("")) {
				//altera o nome do usuario
				if(UA.alterarNome(cpf.trim(), nome)==true) {
					return "Nome alterado para "+nome;
				}else {
					return "Nome não pode ser alterado.";
				}
				
			}else {
				return "Nome não pode ser vazio.";
			}
		}else {
			return "CPF inválido.";
		} 
	}
	
	public String excluirUsuario(String cpf_Usuario) {
		verificaCPF=false;
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
		//verifica se o cpf é válido
		if(ve.verifica(cpf.trim())==false) {
			return "Erro no cpf inserido, digite um cpf válido.";
		}
		//verifica cpf no banco de dados e salva o retorno no verificaCPF
		verificaCPF=US.verificaUsuarioExiste(cpf.trim());
		if(verificaCPF==true) {
			//exclui o usuario
			if(UE.excluirUsuario(cpf.trim())==true) {
				return "Usuário Excluído.";
			}else {
				return "Usuário não pode ser excluído.";
			}
		}else {
			return "CPF inválido.";
		}
	}
	
	public Usuario_Cartao() {
		
	}
}

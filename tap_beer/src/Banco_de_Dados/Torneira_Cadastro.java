package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Torneira_Cadastro {
	
	public boolean cadastrarTorneira(String localizacao) {
		//cadastra uma torneira
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `torneira`(`localizacao`) VALUES (?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,localizacao);
				if(comando.executeUpdate()>0) {
					retorno = true;
				}
			}
		}catch(SQLException e) {
			return false;
		}finally{
			try {
				comando.close();
			}catch(SQLException es) {
				return false;
			}
		}
		return retorno;
	}
	
	public Torneira_Cadastro() {
		
	}
}

package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Torneira_Selecionar {
	int numTorneiras=200, numCamposTorneira=4;
	
	public boolean verificarTorneiraExiste(int id_Torneira) {
		boolean retorno=false;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE id_Torneira="+id_Torneira;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=true;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public int verificarTorneiraProduto(int id_Torneira) {
		int retorno=-1;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT id_Produto FROM torneira WHERE id_Torneira="+id_Torneira;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=resultado.getInt("id_Produto");
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public double verificarQuantProdutoTorneira(int id_Torneira) {
		double retorno=0;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT quant_Produto FROM torneira WHERE id_Torneira="+id_Torneira;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=resultado.getDouble("quant_Produto");
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTodasTorneira() {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		retorno[0][0]="nehum produto cadastrado";
		int i=0;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getDouble("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneira(int id_Torneira) {
		String[][] retorno=new String[0][numCamposTorneira];
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE id_Torneira="+id_Torneira;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[0][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[0][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[0][2]=resultado.getString("localizacao");
					retorno[0][3]=String.valueOf(resultado.getInt("quant_Produto"));
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneiraProdutoLocalizada(int id_Produto, String localizacao, double quant) {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		int i=0;
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE id_Produto="+id_Produto+" and localizacao="+localizacao+" and quant_Produto<="+quant;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getInt("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneiraProdutoApenasLocalizada(int id_Produto, String localizacao) {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		int i=0;
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE id_Produto="+id_Produto+" and localizacao="+localizacao;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getInt("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneiraProdutoQuant(int id_Produto, double quant) {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		int i=0;
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE id_Produto="+id_Produto+" and quant_Produto<="+quant;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getInt("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneiraLocalizacaoQuant(String localizacao, double quant) {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		int i=0;
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE localizacao="+localizacao+" and quant_Produto<="+quant;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getInt("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneiraQuant(double quant) {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		int i=0;
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE quant_Produto<="+quant;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getInt("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public String[][] consultarTorneiraProduto(int id_Produto) {
		String[][] retorno=new String[numTorneiras][numCamposTorneira];
		int i=0;
		retorno[0][0]="nehum produto cadastrado";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM torneira WHERE id_Produto="+id_Produto;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Torneira"));
					retorno[i][1]=String.valueOf(resultado.getInt("id_Produto"));
					retorno[i][2]=resultado.getString("localizacao");
					retorno[i][3]=String.valueOf(resultado.getInt("quant_Produto"));
					i++;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public Torneira_Selecionar() {
		
	}
}

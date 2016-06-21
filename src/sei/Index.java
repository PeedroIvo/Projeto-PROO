package sei;

import java.sql.Connection;

import sei.database.ConexaoMySQL;

public class Index {

	public static void main(String[] args) { 
		
		ConexaoMySQL mysql = new ConexaoMySQL();
	    Connection conexao = mysql.getConexao("jdbc:mysql", "localhost:3306", "sei", "root", "");
		
		Sessao sessao = new Sessao(conexao);
		
		System.out.println("\nOlá, " + sessao.getUsuarioAtual().getNome() + "! O que deseja fazer?");
		
		if (sessao.getUsuarioAtual().getTipoUsuario() == '0') {
			if(!sessao.getUsuarioAtual().menu(conexao)) {
				sessao.telaBoasVindas();
			}
		}
		
		mysql.fecharConexao();

    }

}


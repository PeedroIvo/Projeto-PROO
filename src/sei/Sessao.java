package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import sei.persistencia.conexao.ConexaoMySQL;

public class Sessao {
	
	private ConexaoMySQL mysql = new ConexaoMySQL();
	private Connection conexao = mysql.getConexao("jdbc:mysql", "localhost:3306", "sei", "root", "");

	private Usuario usuarioAtual;
	private Scanner input = new Scanner(System.in);
	
	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public Connection getConexao() {
		return conexao;
	}
	
	public boolean iniciarSessao() {
		System.out.println("-------------------------------------");
		System.out.println("BEM-VINDO AO SISTEMA ESCOLA INTEGRADA");
		System.out.println("-------------------------------------");
		
		System.out.println("[1] Login");
		System.out.println("[2] Sair");
		
		int opcao;
		
		do {
			System.out.print("Digite sua opção: ");
			opcao = input.nextInt();

			if (opcao <= 0 || opcao > 2) {
				System.out.println("Opção inválida! Tente novamente!");
			}
		} while (opcao <= 0 || opcao > 2);
		
		System.out.println();
		
		if(opcao == 1) {
			this.login();
			return true;
		} else if(opcao == 2) {
			mysql.fecharConexao();
		}
		
		return false;
	}
	
	public void login() {
		String loginDigitado;
		String senhaDigitada;
		
		do {
			System.out.println("-------------------------------------");
			System.out.print("- Login: ");
			loginDigitado = input.next();
	        System.out.print("- Senha: ");
	        senhaDigitada = input.next();
		} while (!this.validarLogin(this.conexao, loginDigitado.toLowerCase(), senhaDigitada));
		
		System.out.println();
	}
	
	public boolean validarLogin(Connection conexao, String loginDigitado, String senhaDigitada) {
		try (PreparedStatement stmt = conexao.prepareStatement("select * from usuario where login='" + loginDigitado + "'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				if (rs.getString("senha").equals(senhaDigitada)) {
					
					if(rs.getString("tipoUsuario").equals("0")) {
						this.setUsuarioAtual(new Administrador());
					} else if(rs.getString("tipoUsuario").equals("a")) {
						this.setUsuarioAtual(new Aluno());
					} else if(rs.getString("tipoUsuario").equals("p")) {
						this.setUsuarioAtual(new Professor());
					}
					
					this.usuarioAtual.setCodUsuario(rs.getInt("codUsuario"));
					this.usuarioAtual.setLogin(rs.getString("login"));
					this.usuarioAtual.setSenha(rs.getString("senha"));
					this.usuarioAtual.setNome(rs.getString("nome"));
					this.usuarioAtual.setTipoUsuario(rs.getString("tipoUsuario").charAt(0));
					
					return true;
					
				} else {
					System.out.println("-------------------------------------");
					System.out.println("A senha digitada está incorreta! Tente novamente!");
				}
			} else {
				System.out.println("-------------------------------------");
				System.out.println("Este login não está cadastrado! Tente novamente!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}

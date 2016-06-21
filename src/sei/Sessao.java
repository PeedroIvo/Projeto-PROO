package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sessao {
	
	private Connection conexao;
	private Usuario usuarioAtual;
	private Scanner input = new Scanner(System.in);
	
	public Sessao(Connection conexao) {
		this.conexao = conexao;
		this.telaBoasVindas();
	}
	
	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public void telaBoasVindas() {
		System.out.println("\nBEM-VINDO AO SISTEMA ESCOLA INTEGRADA\nFaça o login abaixo");
		
		String loginDigitado;
		String senhaDigitada;
		
		do {
			System.out.print("- Login: ");
			loginDigitado = input.next();
	        System.out.print("- Senha: ");
	        senhaDigitada = input.next();
		} while (!this.fazerLogin(this.conexao, loginDigitado.toLowerCase(), senhaDigitada));
	}
	
	public boolean fazerLogin(Connection conexao, String loginDigitado, String senhaDigitada) {
		try (PreparedStatement stmt = conexao.prepareStatement("select * from usuario where login='" + loginDigitado + "'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				if (rs.getString("senha").equals(senhaDigitada)) {
					
					if(rs.getString("tipoUsuario").equals("0")) {
						this.setUsuarioAtual(new Administrador());
					}
					
					this.usuarioAtual.setCodUsuario(rs.getInt("codUsuario"));
					this.usuarioAtual.setLogin(rs.getString("login"));
					this.usuarioAtual.setSenha(rs.getString("senha"));
					this.usuarioAtual.setNome(rs.getString("nome"));
					this.usuarioAtual.setTipoUsuario(rs.getString("tipoUsuario").charAt(0));
					
					return true;
					
				} else {
					System.out.println("A senha digitada está incorreta! Tente novamente!");
				}
			} else {
				System.out.println("Este login não está cadastrado! Tente novamente!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}

package sei;

public class Index {

	public static void main(String[] args) { 
		Sessao sessao = new Sessao();
		
		boolean statusSessao;
		
		do {
			statusSessao = sessao.iniciarSessao();
			
			if (statusSessao) {
				sessao.getUsuarioAtual().menu(sessao.getConexao());
			}
		} while (statusSessao);
	}
}


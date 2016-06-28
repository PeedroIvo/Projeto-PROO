package sei;

import java.sql.Connection;
import java.sql.SQLException;

public interface Menu {
	void menu(Connection conexao) throws SQLException;
}

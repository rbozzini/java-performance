package it.rbozzini.corso_java_ee_developer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class EsempioDatabase {

	private Connection conn;

	public static void main(String[] args) {
		EsempioDatabase edb = new EsempioDatabase();
		System.out.println("DB connesso [corso_java]");
		try {
			edb.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			edb.esempioSelect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			edb.insert("Diego", "Romani", "d.romani@yoox.it", "334567889");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			edb.update();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			edb.delete("Diego", "Romani");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update() throws SQLException {
		String sql = "UPDATE clienti SET telefono='0123456789' WHERE nome='Diego' AND cognome='Romani'";
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.executeUpdate();

		System.out.println("\n\nModificato il record.");
	}

	private void delete(String nome, String cognome) throws SQLException {
		String sql = "DELETE FROM clienti WHERE cognome='" + cognome + "' AND nome='" + nome + "'";

		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.executeUpdate();

		System.out.println("\n\nIl record [" + nome + " " + cognome + "] è stato eliminato.");
		printClienti();
	}

	private void insert(String nome, String cognome, String email, String telefono) throws SQLException {
		if (!existsCliente(nome, cognome)) {
			String sql = "INSERT INTO clienti(nome, cognome, email, telefono) " + "VALUES('" + nome + "', '" + cognome
					+ "', '" + email + "', '" + telefono + "')";
			PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			System.out.println("\n\nAggiunto id = " + rs.getInt(1));
			printClienti();
		} else {
			System.out.println("\n\nIl record [" + nome + " " + cognome + "] esiste già.");
		}
	}

	private boolean existsCliente(String nome, String cognome) throws SQLException {
		String sql = "SELECT id FROM clienti WHERE cognome='" + cognome + "' AND nome='" + nome + "'";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs.first();
	}

	private void printClienti() throws SQLException {
		String sql = "SELECT * FROM clienti";
		System.out.println("\n\nStampo sql = " + sql);
		PreparedStatement ps = getConnection().prepareStatement(sql);
		printRS(ps.executeQuery());

	}

	private void printRS(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.format("%3d %-10s %-12s %-27s %-10s", rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5));
			System.out.println();
		}
	}

	private void esempioSelect() throws SQLException {
		// Esempio 1

		String sql = "SELECT id, nome, cognome, email, telefono FROM clienti";
		System.out.println("\n\n1) sql = " + sql);
		PreparedStatement ps = getConnection().prepareStatement(sql);
		printRS(ps.executeQuery());

		// Esempio 2

		sql = "SELECT id, nome, cognome, email, telefono FROM clienti WHERE cognome LIKE 'Bozz%'";
		System.out.println("\n\n2) sql = " + sql);
		ps = getConnection().prepareStatement(sql);
		printRS(ps.executeQuery());

		// Esempio 3 (prevenire attacchi SQL injection con i PreparedStatement)

		sql = "SELECT id, nome, cognome, email, telefono FROM clienti WHERE nome = ? AND cognome = ?";
		System.out.println("\n\n3) sql = " + sql);
		ps = getConnection().prepareStatement(sql);
		ps.setString(1, "Valentina");
		ps.setString(2, "Brandolini");
		printRS(ps.executeQuery());
	}

	private Connection getConnection() throws SQLException {
		if (conn == null) {
			MysqlDataSource datasource = new MysqlDataSource();

			datasource.setDatabaseName("corso_java");
			datasource.setPortNumber(3306);
			datasource.setServerName("127.0.0.1");
			datasource.setUser("root");
			datasource.setPassword("RW09xJOB3Y95");
			datasource.setServerTimezone("UTC");

			conn = datasource.getConnection();
		}

		return conn;
	}

}

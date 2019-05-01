package it.rbozzini.corso_java_ee_developer.rubrica.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

import it.rbozzini.corso_java_ee_developer.rubrica.model.Contatto;

public class RubricaBusiness {

	private Connection conn;

	private static RubricaBusiness rb = null;

	public static RubricaBusiness getInstance() {
		if (rb == null) {
			rb = new RubricaBusiness();
		}
		return rb;
	}

	public int aggiungiContatto(Contatto c) throws SQLException {
		String sql = "insert into contatti(nome, cognome, telefono) values(?, ?, ?)";

		PreparedStatement ps = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getCognome());
		ps.setString(3, c.getTelefono());

		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		rs.next();

		return rs.getInt(1);
	}

	public List<Contatto> ricercaContatti() throws SQLException {

		String sql = "SELECT id, nome, cognome, telefono FROM contatti";
		PreparedStatement ps = getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Contatto> contatti = new ArrayList<Contatto>();

		while (rs.next()) {
			Contatto c = new Contatto(rs.getString(2), rs.getString(3), rs.getString(4));
			c.setId(rs.getInt(1));

			contatti.add(c);
		}

		return contatti;
	}

	private Connection getConnection() throws SQLException {
		if (conn == null) {
			MysqlDataSource datasource = new MysqlDataSource();

			datasource.setDatabaseName("rubrica");
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

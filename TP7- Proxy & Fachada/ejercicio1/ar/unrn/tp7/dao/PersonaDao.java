package ar.unrn.tp7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import ar.unrn.tp7.modelo.Persona;
import ar.unrn.tp7.modelo.Proxy;
import ar.unrn.tp7.modelo.Telefono;

public class PersonaDao {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tp7_objetos2";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";

	private Connection obtenerConexion() throws Exception {

		Connection conexion = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Error al cargar el controlador");

		} catch (SQLException e) {
			throw new SQLException("Error en la conexion");
		}

		return conexion;
	}

	public Persona personaPorId(int id) throws Exception {

		String sql = "select nombre from personas " + "where id = ?";

		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			String nombrePersona = null;

			while (result.next()) {
				nombrePersona = result.getString(1);
			}

			return new Persona(id, nombrePersona, new Proxy<Telefono>(this, id));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Set<Telefono> telefonosPersonaPorId(int id) throws Exception {

		String sql = "select t.numero " + "from telefonos t " + "where t.idPersona = ?";

		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();

			while (result.next()) {

				telefonos.add(new Telefono(result.getString(1)));
			}

			return telefonos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
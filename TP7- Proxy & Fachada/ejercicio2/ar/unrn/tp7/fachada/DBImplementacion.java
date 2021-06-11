package ar.unrn.tp7.fachada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBImplementacion implements DBFacade {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tp7_objetos2";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	private Connection conexio;

	@Override
	public void open() {

		Connection conexion = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			this.conexio = conexion;

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Error al cargar el controlador", e);

		} catch (SQLException e) {
			throw new RuntimeException("Error en la conexion", e);
		}
	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {

		this.open();

		List<Map<String, String>> lista = new ArrayList<Map<String, String>>();

		try (PreparedStatement statement = this.conexio.prepareStatement(sql);) {

			ResultSet result = statement.executeQuery();

			ResultSetMetaData rsmd = result.getMetaData();

			while (result.next()) {

				for (int i = 1; i < rsmd.getColumnCount(); i++) {

					Map<String, String> mapa = new HashMap<>();

					mapa.put(rsmd.getColumnName(i), result.getString(i));

					lista.add(mapa);
				}
			}

			this.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error en la conexion", e);
		}

		return lista;
	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {

		this.open();

		List<String[]> lista = new ArrayList<>();

		try (PreparedStatement statement = this.conexio.prepareStatement(sql);) {

			ResultSet result = statement.executeQuery();

			ResultSetMetaData rsmd = result.getMetaData();

			while (result.next()) {

				for (int i = 1; i < rsmd.getColumnCount(); i++) {

					String[] arreglo = { rsmd.getColumnName(i), result.getString(i) };

					lista.add(arreglo);
				}
			}

			this.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error en la conexion", e);
		}

		return lista;
	}

	@Override
	public void close() {

		try {
			this.conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error en la conexion", e);
		}
	}
}
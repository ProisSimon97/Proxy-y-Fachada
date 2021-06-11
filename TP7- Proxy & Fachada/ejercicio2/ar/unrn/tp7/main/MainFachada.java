package ar.unrn.tp7.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ar.unrn.tp7.fachada.DBFacade;
import ar.unrn.tp7.fachada.DBImplementacion;

public class MainFachada {
	public static void main(String[] args) {

		DBFacade db = new DBImplementacion();
	
		List<Map<String, String>> mapa = db.queryResultAsAsociation("select * from personas, telefonos where telefonos.idPersona = personas.id");
		
		List<String[]> lista = db.queryResultAsArray("select * from personas, telefonos where telefonos.idPersona = personas.id");
		
		System.out.println(mapa);
		
		System.out.println("-------------------------------------------------------------------------------------");
		
		for (String[] strings : lista) {
			
			System.out.println(Arrays.deepToString(strings));
		}
	}
}

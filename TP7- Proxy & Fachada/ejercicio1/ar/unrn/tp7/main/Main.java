package ar.unrn.tp7.main;

import ar.unrn.tp7.dao.PersonaDao;
import ar.unrn.tp7.modelo.Persona;
import ar.unrn.tp7.modelo.Telefono;

public class Main {

	public static void main(String[] args) throws Exception {

		PersonaDao dao = new PersonaDao();
		
		Persona p = dao.personaPorId(1);
		
		System.out.println(p.nombre());
		
		for (Telefono telefonos : p.telefonos()) {
			System.out.println(telefonos);
		}
	}
}
// Sujeto: Interfaz Set
// Proxy: Clase Proxy que implementa la interfaz Set
// Sujeto Real: HashSet
// Cliente: Persona
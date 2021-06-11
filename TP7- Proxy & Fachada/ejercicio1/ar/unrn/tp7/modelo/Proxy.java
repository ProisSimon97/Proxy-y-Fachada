package ar.unrn.tp7.modelo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import ar.unrn.tp7.dao.PersonaDao;

public class Proxy<E> implements Set<E> {

	private Set<Telefono> telefonos;
	private PersonaDao personaDao;
	private int id;

	public Proxy(PersonaDao personaDao, int id) {
		this.personaDao = personaDao;
		this.id = id;
	}
	
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		try {
			this.telefonos = personaDao.telefonosPersonaPorId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return telefonos.toArray(arg0);
	}

	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
}

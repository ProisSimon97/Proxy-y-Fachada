package ar.unrn.tp7.fachada;

import java.util.List;
import java.util.Map;

public interface DBFacade {

	public void open() throws Exception;

	public List<Map<String, String>> queryResultAsAsociation(String sql);

	public List<String[]> queryResultAsArray(String sql);

	public void close();
}

package pe.gob.serfor.osutd.sgd.service.logic;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Trabajador;

//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//
//import pe.gob.serfor.wssisged.logic.model.Trabajador;

public interface TrabajadorService {
	public String  pasoValidaciones( String usuario,  String clave,String ideDependencia) throws JsonGenerationException, JsonMappingException, IOException;
	public List<Trabajador> busquePersonal(Integer ideDependencia);

}

package pe.gob.serfor.osutd.sgd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.gob.serfor.osutd.sgd.repository.bean.DestinatarioBean;


public interface DestinatarioService    {
	
	
	List<DestinatarioBean> listarDestinatarioSafiff() throws Exception;

}

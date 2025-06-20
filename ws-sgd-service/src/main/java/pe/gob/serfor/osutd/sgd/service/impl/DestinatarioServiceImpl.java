package pe.gob.serfor.osutd.sgd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import pe.gob.serfor.osutd.sgd.repository.DestinatarioRepository;
//import pe.gob.serfor.osutd.sgd.repository.DestinatarioRepository;
//import pe.gob.serfor.osutd.sgd.repository.TipoDocumentoRepository;
import pe.gob.serfor.osutd.sgd.repository.bean.DestinatarioBean;
import pe.gob.serfor.osutd.sgd.repository.logic.DestinatarioRepository;
import pe.gob.serfor.osutd.sgd.service.DestinatarioService;

@Service("destinatarioService")
public class DestinatarioServiceImpl implements DestinatarioService {
	
	@Autowired
	private  DestinatarioRepository destinatarioRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<DestinatarioBean> listarDestinatarioSafiff() throws Exception {
		// TODO Auto-generated method stub
		return destinatarioRepository.listarDestinatarioSafiff();
	}

}

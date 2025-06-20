package pe.gob.serfor.osutd.sgd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import pe.gob.serfor.osutd.sgd.repository.TipoDocumentoRepository;
import pe.gob.serfor.osutd.sgd.repository.bean.TipoDocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.logic.TipoDocumentoRepository;
import pe.gob.serfor.osutd.sgd.service.TipoDocumentoService;

@Service("tipoDocumentoService")
public class TipoDocumentoServiceImpl  implements TipoDocumentoService {
	
	@Autowired
	private  TipoDocumentoRepository  tipoDocumentoReposotory;
	
	@Transactional(readOnly = true)
	@Override
	public List<TipoDocumentoBean> listarTipoDocumentoMp() throws Exception {
		// TODO Auto-generated method stub
		return tipoDocumentoReposotory.listarTipoDocumentoMP();
	}

}

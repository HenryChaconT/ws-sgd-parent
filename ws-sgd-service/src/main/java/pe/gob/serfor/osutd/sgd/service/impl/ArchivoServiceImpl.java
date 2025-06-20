package pe.gob.serfor.osutd.sgd.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.serfor.osutd.sgd.repository.bean.ArchivoBean;
import pe.gob.serfor.osutd.sgd.service.ArchivoService;

;

@Service("archivoService")
public class ArchivoServiceImpl implements ArchivoService {
	
	private final Logger log = LoggerFactory.getLogger(ArchivoServiceImpl.class);

	@Override
	public ArchivoBean upload(MultipartFile inputFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

package pe.gob.serfor.osutd.sgd.service;



import org.springframework.web.multipart.MultipartFile;

import pe.gob.serfor.osutd.sgd.repository.bean.ArchivoBean;



public interface ArchivoService {
	
	public ArchivoBean upload(MultipartFile inputFile) throws Exception;
	

}

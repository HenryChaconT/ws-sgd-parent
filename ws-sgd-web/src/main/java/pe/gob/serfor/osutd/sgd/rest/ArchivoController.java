package pe.gob.serfor.osutd.sgd.rest;

import java.net.URISyntaxException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.serfor.osutd.sgd.service.ArchivoService;
import pe.gob.serfor.osutd.sgd.repository.bean.ArchivoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.ResponseVO;
import pe.gob.serfor.osutd.sgd.repository.util.Constantes;
import pe.gob.serfor.osutd.sgd.repository.util.Urls;



/**
 * @autor: rventocilla [25-08-2020]
 * @modificado:
 * @descripción: {Clase controladora para publicar los rest de los documentos}
 *
 */
@RestController
@RequestMapping(Urls.archivo.BASE)
public class ArchivoController extends AbstractRestController {

	private final Logger log = LoggerFactory.getLogger(ArchivoController.class);

	@Autowired
	private ArchivoService archivoService;

	/**
	 * @autor: rventocilla [01-09-2020]
	 * @descripción: {Valida y carga los archivos que se adjuntan}
	 * @param: MultipartFile inputFile
	 * @return: ResponseVO
	 */
	@PostMapping(value = "/fileupload"/* , headers = ("content-type=multipart/*") */, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseVO> upload(@RequestParam("file") MultipartFile inputFile, HttpServletRequest request)
			throws URISyntaxException {
		log.debug("REST metodo upload");

		ResponseVO result = null;
		ArchivoBean archivo = new ArchivoBean();

		try {

			// 1.- Validamos que exista un file adjunto
			if (inputFile.isEmpty()) {
				result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.NO_EXISTE_DOCUMENTO_ADJUNTO, null);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}

			// 2.- Validamos la extensión del archivo
			String extension = FilenameUtils.getExtension(inputFile.getOriginalFilename());

			if (!Arrays.asList(Constantes.EXTENSIONES_UPLOAD).contains(extension.toLowerCase())) {
				result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.MESSAGE_EXTENSIONES_PERMITIDAS, null);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}

			// 3.- Validamos el peso del archivo no sea mayor a 10 MB
			if (inputFile.getSize() > Constantes.PESO_FILE_BYTES) {
				result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.MESSAGE_PESO_MAYOR_10MB, null);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			// 4.- Validamos la longitud del nombre del archivo
			if (inputFile.getOriginalFilename().length() > 100) {
				result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.MESSAGE_LONGITUD_TEXTO_EXCEDIDO, null);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}

			archivo = archivoService.upload(inputFile);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.MESSAGE_ERROR_500, e);
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		result = buildResponse(Constantes.STATUS_SUCCESS, archivo, null, null);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}



	
}

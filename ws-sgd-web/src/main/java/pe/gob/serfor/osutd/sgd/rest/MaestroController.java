package pe.gob.serfor.osutd.sgd.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.serfor.osutd.sgd.repository.bean.DestinatarioBean;
import pe.gob.serfor.osutd.sgd.repository.bean.ResponseVO;
import pe.gob.serfor.osutd.sgd.repository.bean.TipoDocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.util.Constantes;
import pe.gob.serfor.osutd.sgd.repository.util.Urls;

import pe.gob.serfor.osutd.sgd.service.DestinatarioService;
import pe.gob.serfor.osutd.sgd.service.TipoDocumentoService;


@RestController
@RequestMapping(Urls.maestro.BASE)
public class MaestroController extends AbstractRestController{
	
	private final Logger log = LoggerFactory.getLogger(MaestroController.class);
	
	private final TipoDocumentoService tipoDocumentoService;
	
	private final DestinatarioService destinatarioService;
	
	@Autowired
	public MaestroController(TipoDocumentoService tipoDocumentoService, DestinatarioService destinatarioService) {
		this.tipoDocumentoService = tipoDocumentoService;
		this.destinatarioService = destinatarioService;
	}

	@GetMapping(path = {"/tipodocumentos"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseEntity<ResponseVO> obtenerDocumentos() {
		
		
		log.debug("REST request to obtenerTipoDocumentoTramite()");

		ResponseVO result = null;
		String mensaje = StringUtils.EMPTY;
		List<TipoDocumentoBean> tipo = new ArrayList<TipoDocumentoBean>();

		try {

				tipo = tipoDocumentoService.listarTipoDocumentoMp();

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.MESSAGE_ERROR_500, e);
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		result = buildResponse(Constantes.STATUS_SUCCESS, tipo, mensaje, null);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(path = {"/destinatarios"}, produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseEntity<ResponseVO> obtenerDestinatarios() {
		
		
		log.debug("REST request to obtenerTipoDocumentoTramite()");

		ResponseVO result = null;
		String mensaje = StringUtils.EMPTY;
		List<DestinatarioBean> destinatario = new ArrayList<DestinatarioBean>();

		try {

			destinatario = destinatarioService.listarDestinatarioSafiff();

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			result = buildResponse(Constantes.STATUS_ERROR, null, Constantes.MESSAGE_ERROR_500, e);
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		result = buildResponse(Constantes.STATUS_SUCCESS, destinatario, mensaje, null);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

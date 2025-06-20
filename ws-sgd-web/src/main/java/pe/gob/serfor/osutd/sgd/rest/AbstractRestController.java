package pe.gob.serfor.osutd.sgd.rest;

import org.apache.commons.lang3.StringUtils;

import pe.gob.serfor.osutd.sgd.repository.bean.ResponseVO;
import pe.gob.serfor.osutd.sgd.repository.util.Constantes;



public abstract class AbstractRestController {

	/**
	 * @author rventocilla
	 * @param boolean estado, Object data, Exception e, String message
	 * @return ResponseVO
	 * **/
	public static ResponseVO buildResponse(boolean estado, Object data, String message, Exception e) {

		ResponseVO response = new ResponseVO();
		response.setData(data);
		response.setMessage(StringUtils.EMPTY);
		
		if(estado) {
			if(StringUtils.isBlank(message)){
				response.setMessage(Constantes.MESSAGE_SUCCESS.toString());
			}else{
				response.setMessage(message.toString());
			}
			
			response.setSuccess(true);
		}else {
			if(e == null) {
				response.setMessage(message.toString());
			}else {
				response.setMessage(Constantes.MESSAGE_ERROR.concat(e.getMessage()));
			}
			
			response.setSuccess(false);
		}
		
		return response;
	}
}

package pe.gob.serfor.osutd.sgd.repository.bean.integracion.response;

public class ResponseBean {
	private String code;
	private String Result;
	private String Message;
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
}

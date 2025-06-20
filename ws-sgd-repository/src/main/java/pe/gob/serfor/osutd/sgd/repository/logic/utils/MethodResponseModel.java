package pe.gob.serfor.osutd.sgd.repository.logic.utils;

public class MethodResponseModel<T>
{
    private String Code;
    private boolean success;
    private Object OtherCode ;
    private String Message;
    private T Result;
    
    public MethodResponseModel() {
    	Code = "0";
    	success= true;
    }
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getOtherCode() {
		return OtherCode;
	}
	public void setOtherCode(Object otherCode) {
		OtherCode = otherCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public T getResult() {
		return Result;
	}
	public void setResult(T result) {
		Result = result;
	}
    
    
}
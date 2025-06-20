package pe.gob.serfor.osutd.sgd.repository.logic.utils;

import java.util.Map;

public class GeneralException  extends Exception{
	
	 public GeneralException(String message) {
	      super(message);
	  }
	 private Map<String, Object> errors;
	 private static final long serialVersionUID = 1L;
	    

    public GeneralException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public GeneralException(String msg, Throwable cause, Map<String, Object> errors) {
        super(msg, cause);
        this.errors = errors;
    }

    
    public Map<String, Object> getErrors() {
		return errors;
	}

}

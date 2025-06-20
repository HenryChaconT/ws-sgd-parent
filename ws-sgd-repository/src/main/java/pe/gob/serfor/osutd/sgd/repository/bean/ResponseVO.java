package pe.gob.serfor.osutd.sgd.repository.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;
	private String message;
	private Object data;
}

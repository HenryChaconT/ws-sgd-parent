package pe.gob.serfor.osutd.sgd.repository.bean.integracion;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
//import org.springframework.web.multipart.MultipartFile;

public class AnexoParametroBean {
	private String pNuAnn;
	private String pNuEmi;
	private String pNuAne;
	//private String pkEmi;
	
	//private MultipartFile archivos;

	//private FormDataMultiPart archivos;
	
	public String getpNuAnn() {
		return pNuAnn;
	}

	public void setpNuAnn(String pNuAnn) {
		this.pNuAnn = pNuAnn;
	}

	public String getpNuEmi() {
		return pNuEmi;
	}

	public void setpNuEmi(String pNuEmi) {
		this.pNuEmi = pNuEmi;
	}

	public String getpNuAne() {
		return pNuAne;
	}

	public void setpNuAne(String pNuAne) {
		this.pNuAne = pNuAne;
	}

	
	/*
	 * public String getPkEmi() { return pkEmi; }
	 * 
	 * public void setPkEmi(String pkEmi) { this.pkEmi = pkEmi; }
	 */

	/*
	 * public FormDataMultiPart getArchivos() { return archivos; }
	 * 
	 * public void setArchivos(FormDataMultiPart archivos) { this.archivos =
	 * archivos; }
	 */
}

package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

public class Anexo {
	private Integer nuAnn;
	private String nuEmi;
	public String getNuEmi() {
		return nuEmi;
	}
	public void setNuEmi(String nuEmi) {
		this.nuEmi = nuEmi;
	}
	private Integer nuAne;
	private byte[] blDoc;
	private String deRuta;
	private Integer tiPublic;
	private String vnomDoc;
    private String deRutOri; 
	
	
	 
	
	public Integer getNuAne() {
		return nuAne;
	}
	public void setNuAne(Integer nuAne) {
		this.nuAne = nuAne;
	}
	public String getVnomDoc() {
		return vnomDoc;
	}
	public void setVnomDoc(String vnomDoc) {
		this.vnomDoc = vnomDoc;
	}
	public String getDeRutOri() {
		return deRutOri;
	}
	public void setDeRutOri(String deRutOri) {
		this.deRutOri = deRutOri;
	}
	public String getDeRuta() {
		return deRuta;
	}
	public void setDeRuta(String deRuta) {
		this.deRuta = deRuta;
	}
	public Integer getTiPublic() {
		return tiPublic;
	}
	public void setTiPublic(Integer tiPublic) {
		this.tiPublic = tiPublic;
	}
	
	
	 public Integer getNuAnn() {
		return nuAnn;
	}
	public void setNuAnn(Integer nuAnn) {
		this.nuAnn = nuAnn;
	}


	public byte[] getBlDoc() {
		return blDoc;
	}
	public void setBlDoc(byte[] blDoc) {
		this.blDoc = blDoc;
	}
	
	

}

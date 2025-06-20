package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.codehaus.jackson.annotate.JsonIgnore;

public class DocumentoPass {

	// DIG.IDE_DIGITAL,FLG_CARGADO,FEC_CARGA,
	// TTD.TXT_RUTA_CUD,DIG.TXT_CUD,DIG.TXT_EXTENSION

	@JsonIgnore
	private Integer ideDigital;

	private String flgCargado;

	@JsonIgnore
	private String fecCarga;
	@JsonIgnore
	private String txt_Rutacud;
	private String txtCud;
	@JsonIgnore
	private Integer numPeriodo;
	@JsonIgnore
	private Integer ideDocumento;
	private List<Destinatario> destinatario;
	@JsonIgnore
	private Integer numCut;
	@JsonIgnore
	private String txtExtension;
	private String linkCud;
	private String nombreFile;
	@JsonIgnore
	private String byteFile;
	private String tipoDocumento;
	private String unidadorgRemitente;
	private String nroDocumento;
	@JsonIgnore
	private Date fecDocumento;
	private String fechaDocumento;
	private String desFlgCargado;
	@JsonIgnore
	private String nombres;
	@JsonIgnore
	private String apePaterno;
	@JsonIgnore
	private String apeMaterno;
	private String nombreCompleto;
	private String asunto;
	@JsonIgnore
	private String estado;
	@JsonIgnore
	private Date fecRegTramite;
	@JsonIgnore
	private Integer folios;
	@JsonIgnore
	private String tipoTramite;

	public String getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecRegTramite() {
		return fecRegTramite;
	}

	public void setFecRegTramite(Date fecRegTramite) {
		this.fecRegTramite = fecRegTramite;
	}

	public Integer getFolios() {
		return folios;
	}

	public void setFolios(Integer folios) {
		this.folios = folios;
	}

	public String getDesFlgCargado() {
		return desFlgCargado;
	}

	public void setDesFlgCargado(String desFlgCargado) {
		this.desFlgCargado = desFlgCargado;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public List<Destinatario> getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(List<Destinatario> destinatario) {
		this.destinatario = destinatario;
	}

	public Date getFecDocumento() {
		return fecDocumento;
	}

	public void setFecDocumento(Date fecDocumento) {
		this.fecDocumento = fecDocumento;
	}

	public String getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getUnidadorgRemitente() {
		return unidadorgRemitente;
	}

	public void setUnidadorgRemitente(String unidadorgRemitente) {
		this.unidadorgRemitente = unidadorgRemitente;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTxtCud() {
		return txtCud;
	}

	public String getTxtExtension() {
		return txtExtension;
	}

	public void setTxtExtension(String txtExtension) {
		this.txtExtension = txtExtension;
	}

	public void setTxtCud(String txtCud) {
		this.txtCud = txtCud;
	}

	public Integer getIdeDigital() {
		return ideDigital;
	}

	public void setIdeDigital(Integer ideDigital) {
		this.ideDigital = ideDigital;
	}

	public String getFlgCargado() {
		return flgCargado;
	}

	public void setFlgCargado(String flgCargado) {
		this.flgCargado = flgCargado;
	}

	public String getFecCarga() {
		return fecCarga;
	}

	public void setFecCarga(String fecCarga) {
		this.fecCarga = fecCarga;
	}

	public Integer getNumCut() {
		return numCut;
	}

	public void setNumCut(Integer numCut) {
		this.numCut = numCut;
	}

	public Integer getNumPeriodo() {
		return numPeriodo;
	}

	public void setNumPeriodo(Integer numPeriodo) {
		this.numPeriodo = numPeriodo;
	}

	public String getLinkCud() {
		return linkCud;
	}

	public void setLinkCud(String linkCud) {
		this.linkCud = linkCud;
	}

	public String getNombreFile() {
		return nombreFile;
	}

	public void setNombreFile(String nombreFile) {
		this.nombreFile = nombreFile;
	}

	public String getByteFile() {
		return byteFile;
	}

	public void setByteFile(String byteFile) {
		this.byteFile = byteFile;
	}

	public String getTxt_Rutacud() {
		return txt_Rutacud;
	}

	public void setTxt_Rutacud(String txt_Rutacud) {
		this.txt_Rutacud = txt_Rutacud;
	}

	public Integer getIdeDocumento() {
		return ideDocumento;
	}

	public void setIdeDocumento(Integer ideDocumento) {
		this.ideDocumento = ideDocumento;
	}

}

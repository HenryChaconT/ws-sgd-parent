package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import java.util.Date;

public class DocumentoMesaPartes {
	
	
	private String   deTiEmi;
	private String  origen;
	private String nuExpediente;
	private String nuDocumento;
	private String tipoDocumento;
	private String asunto;
	private String codEstDocEmitido;
	private String estado;
	private Date feEmi;
	private String local;
	private String dependenciaOrigen;
	private String nuEmi;
	private String anio;
	private String codTipDoc;
	private String coDepEmi;
	private String coEmpEmi;
	private String nuRucEmi;
	private String codDep;

    public String getDeTiEmi() {
		return deTiEmi;
	}
	public void setDeTiEmi(String deTiEmi) {
		this.deTiEmi = deTiEmi;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getNuExpediente() {
		return nuExpediente;
	}
	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}
	public String getNuDocumento() {
		return nuDocumento;
	}
	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCodEstDocEmitido() {
		return codEstDocEmitido;
	}
	public void setCodEstDocEmitido(String codEstDocEmitido) {
		this.codEstDocEmitido = codEstDocEmitido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFeEmi() {
		return feEmi;
	}
	public void setFeEmi(Date feEmi) {
		this.feEmi = feEmi;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getDependenciaOrigen() {
		return dependenciaOrigen;
	}
	public void setDependenciaOrigen(String dependenciaOrigen) {
		this.dependenciaOrigen = dependenciaOrigen;
	}
	public String getNuEmi() {
		return nuEmi;
	}
	public void setNuEmi(String nuEmi) {
		this.nuEmi = nuEmi;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getCodTipDoc() {
		return codTipDoc;
	}
	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}
	public String getCoDepEmi() {
		return coDepEmi;
	}
	public void setCoDepEmi(String coDepEmi) {
		this.coDepEmi = coDepEmi;
	}
	public String getCoEmpEmi() {
		return coEmpEmi;
	}
	public void setCoEmpEmi(String coEmpEmi) {
		this.coEmpEmi = coEmpEmi;
	}
	public String getNuRucEmi() {
		return nuRucEmi;
	}
	public void setNuRucEmi(String nuRucEmi) {
		this.nuRucEmi = nuRucEmi;
	}
	public String getCodDep() {
		return codDep;
	}
	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}
	
	
	

//	cadena.append(" SELECT A.CO_GRU, [IDOSGD].[PK_SGD_DESCRIPCION_TI_DESTINO](A.TI_EMI) DE_TI_EMI,a.FE_EMI, "); 
//	cadena.append(" [IDOSGD].PK_SGD_DESCRIPCION_TI_EMI_EMP(A.NU_ANN, A.NU_EMI) ORIGEN,  ");   
//	cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_DE_DOCUMENTO](A.CO_TIP_DOC_ADM) tipoDocumento, ");      
//	cadena.append(" CASE A.TI_EMI       WHEN '01' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG ");   
//	cadena.append("WHEN '05' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG    ELSE A.DE_DOC_SIG ");  
//	cadena.append(" END nuDocumento,    ");      
//	cadena.append("  A.ES_DOC_EMI codEstDocEmitido, [IDOSGD].[PK_SGD_DESCRIPCION_ESTADOS_MP](A.ES_DOC_EMI, 'TDTV_REMITOS') estado,");  
//	cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_DE_LOCAL](A.CO_LOC_EMI) local,  ");   
//	cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_DE_DEPENDENCIA](A.CO_DEP) dependenciaOrigen, ");
//	cadena.append(" B.NU_EXPEDIENTE, A.DE_ASU  ASUNTO,A.CO_GRU,A.CO_TIP_DOC_ADM  as codTipDoc,A.CO_DEP_EMI,A.CO_EMP_EMI,A.CO_OTR_ORI_EMI, ");   
//	cadena.append("  A.NU_DOC_EMI,A.DE_DOC_SIG, A.TI_EMI,A.NU_RUC_EMI, A.NU_DNI_EMI, A.ES_DOC_EMI, A.CO_DEP, A.NU_COR_EMI  ");	
//	cadena.append(" FROM IDOSGD.TDTV_REMITOS A  INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN B ON B.NU_ANN=A.NU_ANN AND B.NU_EMI=A.NU_EMI WHERE 1=1  ");
//	cadena.append("  AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.CO_GRU = '3' AND A.TI_EMI<>'01' AND A.ES_ELI='0'  ");

}

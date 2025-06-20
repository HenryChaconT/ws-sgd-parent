package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

public class Destinatario {
	
	private String coDepDes;
	private String coEmpDes;
	private String tiDes;
	private String nuDniDes;
	
	private String dependenciaDestino;
	private String destinatario;
	public String getDependenciaDestino() {
		return dependenciaDestino;
	}
	public void setDependenciaDestino(String dependenciaDestino) {
		this.dependenciaDestino = dependenciaDestino;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getCoDepDes() {
		return coDepDes;
	}
	public void setCoDepDes(String coDepDes) {
		this.coDepDes = coDepDes;
	}
	public String getCoEmpDes() {
		return coEmpDes;
	}
	public void setCoEmpDes(String coEmpDes) {
		this.coEmpDes = coEmpDes;
	}
	public String getTiDes() {
		return tiDes;
	}
	public void setTiDes(String tiDes) {
		this.tiDes = tiDes;
	}
	public String getNuDniDes() {
		return nuDniDes;
	}
	public void setNuDniDes(String nuDniDes) {
		this.nuDniDes = nuDniDes;
	}
	
	
//	
//	cadena.append(" SELECT co_dep_des, co_emp_des, ti_des, nu_dni_des, nu_ruc_des, co_otr_ori_des, ");
//	cadena.append("	(CASE WHEN TI_DES='01' THEN IDOSGD.PK_SGD_DESCRIPCION_DE_NOM_EMP (CO_EMP_DES) ");
//	cadena.append("  WHEN TI_DES='02' THEN IDOSGD.PK_SGD_DESCRIPCION_DE_NOM_EMP (NU_RUC_DES)  ");
//	cadena.append("	 WHEN TI_DES='03' THEN IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL (NU_DNI_DES) ");
//	cadena.append("	 WHEN TI_DES='04' THEN IDOSGD.PK_SGD_DESCRIPCION_OTRO_ORIGEN (CO_OTR_ORI_DES)    END)AS DESTINATARIO, ");
//	cadena.append("	case   when ti_des='01' then IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(co_dep_des) ");
//	cadena.append("	 when ti_des='02' then 'RUC -'+ IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(nu_ruc_des) ");
//	cadena.append(" when ti_des='03' then 'CIUDADANO -'+ IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(nu_dni_des) ");
//	cadena.append("	when ti_des='04' then 'OTROS -'+ IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(co_otr_ori_des) ");
//	cadena.append("  when ti_des='05' then  IDOSGD.PK_SGD_DESCRIPCION_DE_NOM_EMP(CO_EMP_DES) END ");
//	cadena.append("	 AS dependenciaDestino  FROM   IDOSGD.tdtv_destinos WHERE  es_eli = '0'");



	

}

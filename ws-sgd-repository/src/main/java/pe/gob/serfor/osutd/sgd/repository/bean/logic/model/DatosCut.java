package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

import java.util.Date;

public class DatosCut {
	
	   private Integer numeroCut;
	    private Integer AnnoCut;
	    
	    private String TipoTramite;
	    private String  fechaRegistro;
	   
		private String  estadoCut;
	    private Integer cantidadDocumentos;
	    
		
		private Integer totalFolios;
		
	    
		 public String getFechaRegistro() {
			return fechaRegistro;
		}
		public void setFechaRegistro(String fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
	    
	    public Integer getNumeroCut() {
			return numeroCut;
		}
		public void setNumeroCut(Integer numeroCut) {
			this.numeroCut = numeroCut;
		}
		public Integer getAnnoCut() {
			return AnnoCut;
		}
		public void setAnnoCut(Integer annoCut) {
			AnnoCut = annoCut;
		}
		public String getTipoTramite() {
			return TipoTramite;
		}
		public void setTipoTramite(String tipoTramite) {
			TipoTramite = tipoTramite;
		}
		
		public String getEstadoCut() {
			return estadoCut;
		}
		public void setEstadoCut(String estadoCut) {
			this.estadoCut = estadoCut;
		}
		
		public Integer getTotalFolios() {
			return totalFolios;
		}
		public void setTotalFolios(Integer totalFolios) {
			this.totalFolios = totalFolios;
		}
		public Integer getCantidadDocumentos() {
			return cantidadDocumentos;
		}
		public void setCantidadDocumentos(Integer cantidadDocumentos) {
			this.cantidadDocumentos = cantidadDocumentos;
		}
		
		
	
	
	
}

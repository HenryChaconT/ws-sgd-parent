package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaRemitoBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.*;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoObjBean;

public interface EmiDocumentoAdmDao {
	String updDocumentoObj(final DocumentoObjBean docObjBean) throws Exception;

	DocumentoEmiBean getEstadoDocumentoAudi(String pnuAnn, String pnuEmi);

	String updRemitoResumenDestinatario(String pnuAnn, String pnuEmi, String vti_des, String vco_pri,
			String vnu_cant_des, String vresOriDes);

	String updRemitoResumenReferencia(String pnuAnn, String pnuEmi, String vti_ori, String vdeOriEmi);

	List<DestinoBean> getListaDestinosCodDepTipoDes(String nu_ann, String nu_emi);

	List<DestinoBean> getListaDestinosCodPri(String nu_ann, String nu_emi);

	String getNumDestinos(String nu_ann, String nu_emi);

	List<ReferenciaRemitoBean> getListaReferenciaRemitos(String nu_ann, String nu_emi);

	List<DestinoBean> getListaDestinosOriTipoDes(String nu_ann, String nu_emi);

	List<ReferenciaRemitoBean> getOriReferenciaLista(String nu_ann, String nu_emi);
}

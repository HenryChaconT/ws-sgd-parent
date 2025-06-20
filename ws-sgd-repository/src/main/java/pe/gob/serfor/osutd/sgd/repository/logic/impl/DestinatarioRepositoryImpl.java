package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

//import pe.gob.serfor.osutd.sgd.repository.DestinatarioRepository;
import pe.gob.serfor.osutd.sgd.repository.bean.DestinatarioBean;
import pe.gob.serfor.osutd.sgd.repository.bean.TipoDocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.logic.DestinatarioRepository;

@Repository
public class DestinatarioRepositoryImpl  implements DestinatarioRepository{

	private static final Logger log = LogManager.getLogger(DestinatarioRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DestinatarioBean> listarDestinatarioSafiff() throws Exception {
		List<DestinatarioBean> result = new ArrayList<DestinatarioBean>();
		try {
			
		
			StringBuilder sql = new StringBuilder();
			sql.append("select CO_DEPENDENCIA,DE_DEPENDENCIA,CO_EMPLEADO CO_JEFE from IDOSGD.RHTM_DEPENDENCIA ");
			sql.append(" innner join  IDOSGD.SI_ELEMENTO on ( CO_DEPENDENCIA= CELE_DESELE and CTAB_CODTAB in('ATTFFS_SERFOR','OFICINA_SAFIFF'))  ");
			
			
			List<Object[]> destinatarios = entityManager.createNativeQuery(sql.toString()).getResultList();
			
			if(!CollectionUtils.isEmpty(destinatarios)) {
				
				for (Object[] objeto : destinatarios) {
					DestinatarioBean destinatario= new DestinatarioBean();
					destinatario.setCodigoDependencia( String.valueOf(objeto[0]));
					destinatario.setNombreDependencia(String.valueOf(objeto[1]));
					destinatario.setCodJefe(String.valueOf(objeto[2]));
					
				
					result.add( destinatario);
				}
				
			}
			
		}catch (NoResultException nre ) {
			log.info(nre.getMessage());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
		
		return result;
	}
	
	

}

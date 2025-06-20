package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

//import pe.gob.serfor.osutd.sgd.repository.TipoDocumentoReposotory;
import pe.gob.serfor.osutd.sgd.repository.bean.DestinatarioBean;
import pe.gob.serfor.osutd.sgd.repository.bean.TipoDocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.logic.TipoDocumentoRepository;

@Repository
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository {
	private static final Logger log = LogManager.getLogger(TipoDocumentoRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TipoDocumentoBean> listarTipoDocumentoMP() throws Exception {
		// TODO Auto-generated method stub
		List<TipoDocumentoBean> result = new ArrayList<TipoDocumentoBean>();
		try {

			StringBuilder sql = new StringBuilder();
			sql.append(
					"select M.CDOC_TIPDOC, case when IN_USOABREVIA IS NOT NULL THEN M.CDOC_DESDONOMREAL ELSE M.CDOC_DESDOC END CDOC_DESDOC ");
			sql.append("from IDOSGD.SI_MAE_TIPO_DOC M where IN_MESAPARTES='1' ");

			List<Object[]> tiposDocumentos = entityManager.createNativeQuery(sql.toString()).getResultList();

			if (!CollectionUtils.isEmpty(tiposDocumentos)) {

				for (Object[] objeto : tiposDocumentos) {
					TipoDocumentoBean tipo = new TipoDocumentoBean();
					tipo.setCodigo(String.valueOf(objeto[0]));
					tipo.setDescripcion(String.valueOf(objeto[1]));

					result.add(tipo);
				}

			}

		} catch (NoResultException nre) {
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

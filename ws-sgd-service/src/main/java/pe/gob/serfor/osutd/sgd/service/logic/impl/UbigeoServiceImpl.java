package pe.gob.serfor.osutd.sgd.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDepartamento;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDistrito;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroProvincia;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UbigeoResponse;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.UbigeoResponseProvincia;
import pe.gob.serfor.osutd.sgd.repository.logic.UbigeoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;
import pe.gob.serfor.osutd.sgd.service.logic.UbigeoService;

//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDepartamento;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDistrito;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroProvincia;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UbigeoResponse;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.UbigeoResponseProvincia;
//import pe.gob.serfor.wssisged.logic.dao.UbigeoDao;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;

//import pe.gob.serfor.wssisged.logic.service.UbigeoService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.Util;

@Component
@Transactional(readOnly = true)
public class UbigeoServiceImpl implements UbigeoService {

	@Autowired
	UbigeoDao ubigeoDao;

	@Override
	public UbigeoResponse buscarDepartamento(ParametroDepartamento parametro) throws Exception {

		UbigeoResponse ubigeoresponse = new UbigeoResponse();
		ubigeoresponse.setLstUbigeo(ubigeoDao.buscarDepartamento(parametro));
		return ubigeoresponse;

	}

	@Override
	public UbigeoResponseProvincia buscarProvincia(ParametroProvincia parametro) throws Exception {

		UbigeoResponseProvincia ubigeo = new UbigeoResponseProvincia();
		if (Util.esVacio(parametro.getCodDep())) {
			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(ConstantesUtil.ERR_CAMPO_COD_DEP_VACIO);
			mensaje.setDesMensaje(ConstantesUtil.ERR_CAMPO_COD_DEP_VACIO_DES);

		}

		ubigeo.setLstUbigeo(ubigeoDao.buscarProvincia(parametro));
		return ubigeo;
	}

	@Override
	public UbigeoResponse buscarDistrito(ParametroDistrito parametro) throws Exception {
		UbigeoResponse ubigeo = new UbigeoResponse();
		if (Util.esVacio(parametro.getCodDep())) {
			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(ConstantesUtil.ERR_CAMPO_COD_DEP_VACIO);
			mensaje.setDesMensaje(ConstantesUtil.ERR_CAMPO_COD_DEP_VACIO_DES);

		}
		if (Util.esVacio(parametro.getCodProv())) {
			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(ConstantesUtil.ERR_CAMPO_COD_PROV_VACIO);
			mensaje.setDesMensaje(ConstantesUtil.ERR_CAMPO_COD_PROV_VACIO_DES);

		}
		ubigeo.setLstUbigeo(ubigeoDao.buscarDistrito(parametro));
		return ubigeo;
	}

}

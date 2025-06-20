package pe.gob.serfor.osutd.sgd.repository.logic.utils;

import java.io.Serializable;

public class ConstantesUtil implements Serializable {

    public static final String URL_RUTA_RECURSOS= "//home//Administrador//apps//config//sisgedws";
    public static final String RUTA_ARCHIVOS="/mnt/imagen_remoto/prueba";


    //public static final String URL_RUTA_RECURSOS= "C:\\apps\\config\\sisgedws";
    /**
	 * 
	 */
	private static final long serialVersionUID = -5528340761184626261L;
	//public static final String URL_BASE = "\\\\10.6.0.59\\Principal\\";

    //CAMBIAR PARA PRODUCCION HENRY


    public static final String IDENTIFICADOR_RUTA ="RecursosApp";
    // public static final Integer FLG_DIGITALIZACION = Integer.valueOf(1);
    public static final Integer IDE_ENTIDAD = Integer.valueOf(5);
    public static final Integer ID_APLICACION_SISGED = Integer.valueOf(12);
    public static final Integer ID_APLICACION_SGD = Integer.valueOf(11);
    public static final Integer ID_APLICACION_MPV_ATE_CIU = Integer.valueOf(13);
    
    public static final String ERR_NO_RESULTADOS = "905";
    public static final String ERR_NO_RESULTADOS_DES = "No se encontraron Resultados";
    public static final String ERR_MAS_DE_UNA_COINCIDENCIA= "906";
    public static final String ERR_MAS_DE_UNA_COINCIDENCIA_DES="Se encontró más de una coincidencia";
    public static final String ERR_CAMPO_DISTINTO_FORMATO= "907";
    
    public static final String ERR_CAMPO_COD_DEP_VACIO= "908";
    public static final String ERR_CAMPO_COD_DEP_VACIO_DES="Campo Código de Departamento Vacío";
    public static final String ERR_CAMPO_COD_PROV_VACIO= "909";
    public static final String ERR_CAMPO_COD_PROV_VACIO_DES="Campo Código de Provincia Vacío";
    public static final String ERR_PER_NATURAL_YA_EXISTE= "910";
    public static final String ERR_PER_NATURAL_YA_EXISTE_DES="No se ingreso registro, Persona Natural ya existe";
    public static final String COD_PER_NATURAL_REGISTRADO_SATISF= "911";
    public static final String COD_PER_NATURAL_REGISTRADO_SATISF_DESC="Se registro Persona Natural,satisfactoriamente";
    public static final String COD_PER_NATURAL_ACTUALIZADO_SATIS= "912";
    public static final String COD_PER_NATURAL_ACTUALIZADO_SATIS_DES="Se actualizó persona natural,satisfactoriamente";
    public static final String COD_ANEXO_REGISTRADO_SATISF="913";
    public static final String COD_ANEXO_REGISTRADO_SATISF_DESC="Se registró correctamente el anexo";
    public static final String COD_USU_NO_EXISTE="914";
    public static final String COD_USU_NO_EXISTE_DESC="Usuario de Sesión no existe";
    public static final String COD_ANEXO_ACTUALIZADO_SATISF="915";
    public static final String COD_ANEXO_ACTUALIZADO_SATISF_DESC="Se actualizó correctamente el anexo";
    
    public static final String COD_USUARIO_NO_EXISTE="916";
    public static final String COD_USUARIO_NO_EXISTE_DESC="Usuario no existe";
    
    
    public static final String COD_PER_JURID_ACTUALIZADO_SATIS= "917";
    public static final String COD_PER_JURID_ACTUALIZADO_SATIS_DES="Se actualizó persona jurídica,satisfactoriamente";
    public static final String COD_PER_JURID_INSERTA_SATISF="918";
    public static final String COD_PER_JURID_INSERTA_SATISF_DESC="Se registró correctamente persona jurídica";
    public static final String ERR_PER_JURID_YA_EXISTE= "919";
    public static final String ERR_PER_JURID_YA_EXISTE_DESC="No se ingreso registro, Persona Natural ya existe";
    
    
    public static final String COD_OTR_OR_ACTUALIZADO_SATIS= "920";
    public static final String COD_OTR_OR_ACTUALIZADO_SATIS_DES="Se actualizó otro origen,satisfactoriamente";
    public static final String COD_OTR_OR_INSERTA_SATISF="921";
    public static final String COD_OTR_OR_INSERTA_SATISF_DESC="Se registró correctamente otro origen";
    public static final String COD_OTR_OR_YA_EXISTE= "922";
    public static final String COD_OTR_OR_YA_EXISTE_DESC="No se ingreso registro, Otro origen ya existe";
   
 
    
    public static final String ERR_NO_AUTORIZADO = "401";
    public static final String ERR_NO_AUTORIZADO_DESCRIPCION = "ERROR DE CREDENCIALES DE ACCESO AL WS.";
    
    public static final String COD_AUTORIZADO= "200";
    public static final String COD_AUTORIZADO_DESCRIPCION= "Datos obtenidos Satisfactoriamente.";
    
    public static final String COD_NO_AUTORIZADO= "201"; 
    public static final String COD_NO_AUTORIZADO_DESCRIPCION ="No cuenta con permiso para acceder al sistema";
    public static final String IND_MESA_PARTES="1";
    

    
    
    
}
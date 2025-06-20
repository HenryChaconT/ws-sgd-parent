package pe.gob.serfor.osutd.sgd.repository.bean.logic.model;

//import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DocumentoDigital {

    @JsonIgnore
    private Integer ideDocumento;

    private String txtCud;

    private String flgCargado;

    @JsonIgnore
    private String fecCarga;

    @JsonIgnore
    private Integer numPeriodo;

    @JsonIgnore
    private Integer numCut;

    private String linkCud;
    private String nombreFile;

    private Integer coError;
    private String deError;

//    private byte[] byteFile;
    private String byteFile;

    public Integer getIdeDocumento() {
        return ideDocumento;
    }

    public void setIdeDocumento(Integer ideDocumento) {
        this.ideDocumento = ideDocumento;
    }

    public String getTxtCud() {
        return txtCud;
    }

    public void setTxtCud(String txtCud) {
        this.txtCud = txtCud;
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

    public Integer getNumPeriodo() {
        return numPeriodo;
    }

    public void setNumPeriodo(Integer numPeriodo) {
        this.numPeriodo = numPeriodo;
    }

    public Integer getNumCut() {
        return numCut;
    }

    public void setNumCut(Integer numCut) {
        this.numCut = numCut;
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

    public Integer getCoError() {
        return coError;
    }

    public void setCoError(Integer coError) {
        this.coError = coError;
    }

    public String getDeError() {
        return deError;
    }

    public void setDeError(String deError) {
        this.deError = deError;
    }

    public String getByteFile() {
        return byteFile;
    }

    public void setByteFile(String byteFile) {
        this.byteFile = byteFile;
    }

    @Override
    public String toString() {
        return "DocumentoDigital{" +
                "ideDocumento=" + ideDocumento +
                ", txtCud='" + txtCud + '\'' +
                ", flgCargado='" + flgCargado + '\'' +
                ", fecCarga='" + fecCarga + '\'' +
                ", numPeriodo=" + numPeriodo +
                ", numCut=" + numCut +
                ", linkCud='" + linkCud + '\'' +
                ", nombreFile='" + nombreFile + '\'' +
                ", coError=" + coError +
                ", deError='" + deError + '\'' +
                ", byteFile='" + byteFile + '\'' +
                '}';
    }

}
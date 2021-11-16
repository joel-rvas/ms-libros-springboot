package com.libros.jpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_exchange")
public class TblExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "mnda_orign")
    private String mndaOrign;

    @Column(name = "mnda_dest")
    private String mndaDest;

    @Column(name = "tasa_cambio")
    private Double tasaCambio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "factor")
    private String factor;

    @Column(name = "ind_estado")
    private int indEstado;

    @Transient
    private Double valorMndaOrigen;

    @Transient
    private Double valorConversion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMndaOrign() {
        return mndaOrign;
    }

    public void setMndaOrign(String mndaOrign) {
        this.mndaOrign = mndaOrign;
    }

    public String getMndaDest() {
        return mndaDest;
    }

    public void setMndaDest(String mndaDest) {
        this.mndaDest = mndaDest;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIndEstado() {
        return indEstado;
    }

    public void setIndEstado(int indEstado) {
        this.indEstado = indEstado;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public Double getTasaCambio() {
        return tasaCambio;
    }

    public void setTasaCambio(Double tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    public Double getValorMndaOrigen() {
        return valorMndaOrigen;
    }

    public void setValorMndaOrigen(Double valorMndaOrigen) {
        this.valorMndaOrigen = valorMndaOrigen;
    }

    public Double getValorConversion() {
        return valorConversion;
    }

    public void setValorConversion(Double valorConversion) {
        this.valorConversion = valorConversion;
    }
}
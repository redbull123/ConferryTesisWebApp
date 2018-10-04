package org.banco.model;

import java.io.Serializable;

import java.util.Date;

/**
 *
 * @author rjsan
 */

public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date fecha;
    private Date hora;
    private int itinerarioId;
    private int tipoBoletoId;
    private Usuario usuarioId;

    public Boleto() {
    }

    public Boleto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getItinerarioId() {
        return itinerarioId;
    }

    public void setItinerarioId(int itinerarioId) {
        this.itinerarioId = itinerarioId;
    }

    public int getTipoBoletoId() {
        return tipoBoletoId;
    }

    public void setTipoBoletoId(int tipoBoletoId) {
        this.tipoBoletoId = tipoBoletoId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tesis.Boleto[ id=" + id + " ]";
    }

    
}

package org.banco.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rjsan
 */

public class Itinerario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date fecha;
    private Date time;
  //  private Collection<Boleto> boletoCollection;
    private int barcoId;
    private int rutaId;

    public Itinerario() {
    }

    public Itinerario(Integer id) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

//    @XmlTransient
//    public Collection<Boleto> getBoletoCollection() {
//        return boletoCollection;
//    }
//
//    public void setBoletoCollection(Collection<Boleto> boletoCollection) {
//        this.boletoCollection = boletoCollection;
//    }

    public int getBarcoId() {
        return barcoId;
    }

    public void setBarcoId(int barcoId) {
        this.barcoId = barcoId;
    }

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
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
        if (!(object instanceof Itinerario)) {
            return false;
        }
        Itinerario other = (Itinerario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id + "" + fecha + " " + time;
    }
    
}

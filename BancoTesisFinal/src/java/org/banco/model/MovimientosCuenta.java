/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rjsan
 */
@Entity
@Table(name = "movimientos_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientosCuenta.findAll", query = "SELECT m FROM MovimientosCuenta m")
    , @NamedQuery(name = "MovimientosCuenta.findById", query = "SELECT m FROM MovimientosCuenta m WHERE m.id = :id")
    , @NamedQuery(name = "MovimientosCuenta.findByTipo", query = "SELECT m FROM MovimientosCuenta m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "MovimientosCuenta.findByMonto", query = "SELECT m FROM MovimientosCuenta m WHERE m.monto = :monto")
    , @NamedQuery(name = "MovimientosCuenta.findByFecha", query = "SELECT m FROM MovimientosCuenta m WHERE m.fecha = :fecha")})
public class MovimientosCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo")
    private Integer tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "cuentas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cuentas cuentasId;

    public MovimientosCuenta() {
    }

    public MovimientosCuenta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cuentas getCuentasId() {
        return cuentasId;
    }

    public void setCuentasId(Cuentas cuentasId) {
        this.cuentasId = cuentasId;
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
        if (!(object instanceof MovimientosCuenta)) {
            return false;
        }
        MovimientosCuenta other = (MovimientosCuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MovimientosCuenta[ id=" + id + " ]";
    }
    
}

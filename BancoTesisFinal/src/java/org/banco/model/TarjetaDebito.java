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
@Table(name = "tarjeta_debito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaDebito.findAll", query = "SELECT t FROM TarjetaDebito t")
    , @NamedQuery(name = "TarjetaDebito.findById", query = "SELECT t FROM TarjetaDebito t WHERE t.id = :id")
    , @NamedQuery(name = "TarjetaDebito.findByNumero", query = "SELECT t FROM TarjetaDebito t WHERE t.numero = :numero")
    , @NamedQuery(name = "TarjetaDebito.findByMarca", query = "SELECT t FROM TarjetaDebito t WHERE t.marca = :marca")
    , @NamedQuery(name = "TarjetaDebito.findByFechaVencimiento", query = "SELECT t FROM TarjetaDebito t WHERE t.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "TarjetaDebito.findByCodigoSeguridad", query = "SELECT t FROM TarjetaDebito t WHERE t.codigoSeguridad = :codigoSeguridad")})
public class TarjetaDebito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numero")
    private String numero;
    @Column(name = "marca")
    private String marca;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "codigo_seguridad")
    private Integer codigoSeguridad;
    @JoinColumn(name = "cuentas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cuentas cuentasId;

    public TarjetaDebito() {
    }

    public TarjetaDebito(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Integer codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
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
        if (!(object instanceof TarjetaDebito)) {
            return false;
        }
        TarjetaDebito other = (TarjetaDebito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TarjetaDebito[ id=" + id + " ]";
    }
    
}

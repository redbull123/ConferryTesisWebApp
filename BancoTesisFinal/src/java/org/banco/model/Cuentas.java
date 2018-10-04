/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Entity
@Table(name = "cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c")
    , @NamedQuery(name = "Cuentas.findById", query = "SELECT c FROM Cuentas c WHERE c.id = :id")
    , @NamedQuery(name = "Cuentas.findByNumero", query = "SELECT c FROM Cuentas c WHERE c.numero = :numero")
    , @NamedQuery(name = "Cuentas.findByTipo", query = "SELECT c FROM Cuentas c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Cuentas.findByFechaApertura", query = "SELECT c FROM Cuentas c WHERE c.fechaApertura = :fechaApertura")
    , @NamedQuery(name = "Cuentas.findByFondo", query = "SELECT c FROM Cuentas c WHERE c.fondo = :fondo")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numero")
    private String numero;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Basic(optional = false)
    @Column(name = "fondo")
    private double fondo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentasId")
    private Collection<TarjetaDebito> tarjetaDebitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentasId")
    private Collection<MovimientosCuenta> movimientosCuentaCollection;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;

    public Cuentas() {
    }

    public Cuentas(Integer id) {
        this.id = id;
    }

    public Cuentas(Integer id, double fondo) {
        this.id = id;
        this.fondo = fondo;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getFondo() {
        return fondo;
    }

    public void setFondo(double fondo) {
        this.fondo = fondo;
    }

    @XmlTransient
    public Collection<TarjetaDebito> getTarjetaDebitoCollection() {
        return tarjetaDebitoCollection;
    }

    public void setTarjetaDebitoCollection(Collection<TarjetaDebito> tarjetaDebitoCollection) {
        this.tarjetaDebitoCollection = tarjetaDebitoCollection;
    }

    @XmlTransient
    public Collection<MovimientosCuenta> getMovimientosCuentaCollection() {
        return movimientosCuentaCollection;
    }

    public void setMovimientosCuentaCollection(Collection<MovimientosCuenta> movimientosCuentaCollection) {
        this.movimientosCuentaCollection = movimientosCuentaCollection;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
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
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cuentas[ id=" + id + " ]";
    }
    
}

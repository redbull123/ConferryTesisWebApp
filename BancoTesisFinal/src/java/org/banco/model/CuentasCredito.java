/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rjsan
 */
@Entity
@Table(name = "cuentas_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentasCredito.findAll", query = "SELECT c FROM CuentasCredito c")
    , @NamedQuery(name = "CuentasCredito.findById", query = "SELECT c FROM CuentasCredito c WHERE c.id = :id")
    , @NamedQuery(name = "CuentasCredito.findByNumero", query = "SELECT c FROM CuentasCredito c WHERE c.numero = :numero")
    , @NamedQuery(name = "CuentasCredito.findByFechaApertura", query = "SELECT c FROM CuentasCredito c WHERE c.fechaApertura = :fechaApertura")})
public class CuentasCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numero")
    private String numero;
    @Column(name = "fecha_apertura")
    private String fechaApertura;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;

    public CuentasCredito() {
    }

    public CuentasCredito(Integer id) {
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

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
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
        if (!(object instanceof CuentasCredito)) {
            return false;
        }
        CuentasCredito other = (CuentasCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CuentasCredito[ id=" + id + " ]";
    }
    
}

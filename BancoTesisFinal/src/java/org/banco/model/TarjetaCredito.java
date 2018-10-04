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
@Table(name = "tarjeta_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaCredito.findAll", query = "SELECT t FROM TarjetaCredito t")
    , @NamedQuery(name = "TarjetaCredito.findById", query = "SELECT t FROM TarjetaCredito t WHERE t.id = :id")
    , @NamedQuery(name = "TarjetaCredito.findByNumero", query = "SELECT t FROM TarjetaCredito t WHERE t.numero = :numero")
    , @NamedQuery(name = "TarjetaCredito.findByMarca", query = "SELECT t FROM TarjetaCredito t WHERE t.marca = :marca")
    , @NamedQuery(name = "TarjetaCredito.findByLimite", query = "SELECT t FROM TarjetaCredito t WHERE t.limite = :limite")
    , @NamedQuery(name = "TarjetaCredito.findByStatus", query = "SELECT t FROM TarjetaCredito t WHERE t.status = :status")
    , @NamedQuery(name = "TarjetaCredito.findBySaldo", query = "SELECT t FROM TarjetaCredito t WHERE t.saldo = :saldo")
    , @NamedQuery(name = "TarjetaCredito.findByCvc", query = "SELECT t FROM TarjetaCredito t WHERE t.cvc = :cvc")
    , @NamedQuery(name = "TarjetaCredito.findByFechaVencimiento", query = "SELECT t FROM TarjetaCredito t WHERE t.fechaVencimiento = :fechaVencimiento")})
public class TarjetaCredito implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limite")
    private Double limite;
    @Column(name = "status")
    private Integer status;
    @Column(name = "saldo")
    private Double saldo;
    @Basic(optional = false)
    @Column(name = "cvc")
    private int cvc;
    @Basic(optional = false)
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjetaCreditoId")
    private Collection<MovimientoTarjeta> movimientoTarjetaCollection;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;

    public TarjetaCredito() {
    }

    public TarjetaCredito(Integer id) {
        this.id = id;
    }

    public TarjetaCredito(Integer id, int cvc, Date fechaVencimiento) {
        this.id = id;
        this.cvc = cvc;
        this.fechaVencimiento = fechaVencimiento;
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

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @XmlTransient
    public Collection<MovimientoTarjeta> getMovimientoTarjetaCollection() {
        return movimientoTarjetaCollection;
    }

    public void setMovimientoTarjetaCollection(Collection<MovimientoTarjeta> movimientoTarjetaCollection) {
        this.movimientoTarjetaCollection = movimientoTarjetaCollection;
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
        if (!(object instanceof TarjetaCredito)) {
            return false;
        }
        TarjetaCredito other = (TarjetaCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TarjetaCredito[ id=" + id + " ]";
    }
    
}

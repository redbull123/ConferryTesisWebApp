package org.tesis.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tipo_boleto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBoleto.findAll", query = "SELECT t FROM TipoBoleto t")
    , @NamedQuery(name = "TipoBoleto.findById", query = "SELECT t FROM TipoBoleto t WHERE t.id = :id")
    , @NamedQuery(name = "TipoBoleto.findByNombre", query = "SELECT t FROM TipoBoleto t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoBoleto.findByPrecio", query = "SELECT t FROM TipoBoleto t WHERE t.precio = :precio")
    , @NamedQuery(name = "TipoBoleto.findByStatus", query = "SELECT t FROM TipoBoleto t WHERE t.status = :status")
    , @NamedQuery(name = "TipoBoleto.findByFechaStatus", query = "SELECT t FROM TipoBoleto t WHERE t.fechaStatus = :fechaStatus")})
public class TipoBoleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "status")
    private Integer status;
    @Column(name = "fecha_status")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoBoletoId")
    private Collection<Boleto> boletoCollection;

    public TipoBoleto() {
    }

    public TipoBoleto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFechaStatus() {
        return fechaStatus;
    }

    public void setFechaStatus(Date fechaStatus) {
        this.fechaStatus = fechaStatus;
    }
    
    public String getEstado() {
        String estado = null;
        switch(status){
                case 1:
                    estado ="Activo";
                    break;
                case 2:
                    estado ="Inactivo";
                    break;
            }
        return estado;
    }
    @XmlTransient
    public Collection<Boleto> getBoletoCollection() {
        return boletoCollection;
    }

    public void setBoletoCollection(Collection<Boleto> boletoCollection) {
        this.boletoCollection = boletoCollection;
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
        if (!(object instanceof TipoBoleto)) {
            return false;
        }
        TipoBoleto other = (TipoBoleto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + nombre + "";
    }
}

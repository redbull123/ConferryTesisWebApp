package org.tesis.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "boleto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b")
    , @NamedQuery(name = "Boleto.findById", query = "SELECT b FROM Boleto b WHERE b.id = :id")
    , @NamedQuery(name = "Boleto.findByFecha", query = "SELECT b FROM Boleto b WHERE b.fecha = :fecha")
    , @NamedQuery(name = "Boleto.findByHora", query = "SELECT b FROM Boleto b WHERE b.hora = :hora")})
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boletoId")
    private Collection<Pasajero> pasajeroCollection;
    @JoinColumn(name = "itinerario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Itinerario itinerarioId;
    @JoinColumn(name = "tipo_boleto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoBoleto tipoBoletoId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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

    @XmlTransient
    public Collection<Pasajero> getPasajeroCollection() {
        return pasajeroCollection;
    }

    public void setPasajeroCollection(Collection<Pasajero> pasajeroCollection) {
        this.pasajeroCollection = pasajeroCollection;
    }

    public Itinerario getItinerarioId() {
        return itinerarioId;
    }

    public void setItinerarioId(Itinerario itinerarioId) {
        this.itinerarioId = itinerarioId;
    }

    public TipoBoleto getTipoBoletoId() {
        return tipoBoletoId;
    }

    public void setTipoBoletoId(TipoBoleto tipoBoletoId) {
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

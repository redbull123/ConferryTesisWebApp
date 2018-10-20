package org.tesis.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "barco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barco.findAll", query = "SELECT b FROM Barco b")
    , @NamedQuery(name = "Barco.findById", query = "SELECT b FROM Barco b WHERE b.id = :id")
    , @NamedQuery(name = "Barco.findByNombre", query = "SELECT b FROM Barco b WHERE b.nombre = :nombre")
    , @NamedQuery(name = "Barco.findByCapacidadPersonas", query = "SELECT b FROM Barco b WHERE b.capacidadPersonas = :capacidadPersonas")
    , @NamedQuery(name = "Barco.findByCapacidadAutos", query = "SELECT b FROM Barco b WHERE b.capacidadAutos = :capacidadAutos")
    , @NamedQuery(name = "Barco.findByCapacidadCarga", query = "SELECT b FROM Barco b WHERE b.capacidadCarga = :capacidadCarga")
    , @NamedQuery(name = "Barco.findByCapacidadAutobus", query = "SELECT b FROM Barco b WHERE b.capacidadAutobus = :capacidadAutobus")
    , @NamedQuery(name = "Barco.findByCapacidadMoto", query = "SELECT b FROM Barco b WHERE b.capacidadMoto = :capacidadMoto")})
public class Barco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "capacidad_personas")
    private Integer capacidadPersonas;
    @Column(name = "capacidad_autos")
    private Integer capacidadAutos;
    @Column(name = "capacidad_carga")
    private Integer capacidadCarga;
    @Column(name = "capacidad_autobus")
    private Integer capacidadAutobus;
    @Column(name = "capacidad_moto")
    private Integer capacidadMoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barcoId")
    private Collection<Itinerario> itinerarioCollection;

    public Barco() {
    }

    public Barco(Integer id) {
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

    public Integer getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Integer capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public Integer getCapacidadAutos() {
        return capacidadAutos;
    }

    public void setCapacidadAutos(Integer capacidadAutos) {
        this.capacidadAutos = capacidadAutos;
    }

    public Integer getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Integer capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public Integer getCapacidadAutobus() {
        return capacidadAutobus;
    }

    public void setCapacidadAutobus(Integer capacidadAutobus) {
        this.capacidadAutobus = capacidadAutobus;
    }

    public Integer getCapacidadMoto() {
        return capacidadMoto;
    }

    public void setCapacidadMoto(Integer capacidadMoto) {
        this.capacidadMoto = capacidadMoto;
    }

    @XmlTransient
    public Collection<Itinerario> getItinerarioCollection() {
        return itinerarioCollection;
    }

    public void setItinerarioCollection(Collection<Itinerario> itinerarioCollection) {
        this.itinerarioCollection = itinerarioCollection;
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
        if (!(object instanceof Barco)) {
            return false;
        }
        Barco other = (Barco) object;
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

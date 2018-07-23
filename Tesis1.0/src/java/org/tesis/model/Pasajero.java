/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tesis.model;

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
@Table(name = "pasajero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasajero.findAll", query = "SELECT p FROM Pasajero p")
    , @NamedQuery(name = "Pasajero.findById", query = "SELECT p FROM Pasajero p WHERE p.id = :id")
    , @NamedQuery(name = "Pasajero.findByNombre", query = "SELECT p FROM Pasajero p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pasajero.findByApellido", query = "SELECT p FROM Pasajero p WHERE p.apellido = :apellido")
    , @NamedQuery(name = "Pasajero.findByCedulaIdentidad", query = "SELECT p FROM Pasajero p WHERE p.cedulaIdentidad = :cedulaIdentidad")
    , @NamedQuery(name = "Pasajero.findByFechaNacimiento", query = "SELECT p FROM Pasajero p WHERE p.fechaNacimiento = :fechaNacimiento")})
public class Pasajero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "cedula_identidad")
    private String cedulaIdentidad;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @JoinColumn(name = "boleto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Boleto boletoId;

    public Pasajero() {
    }

    public Pasajero(Integer id) {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boleto getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(Boleto boletoId) {
        this.boletoId = boletoId;
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
        if (!(object instanceof Pasajero)) {
            return false;
        }
        Pasajero other = (Pasajero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tesis.Pasajero[ id=" + id + " ]";
    }
    
}

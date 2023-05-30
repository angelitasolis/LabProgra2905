/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.clinicadental.model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Administrador
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_CITAS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c"),
    @javax.persistence.NamedQuery(name = "Citas.findByCitaDia", query = "SELECT c FROM Citas c WHERE c.citaDia = :citaDia"),
    @javax.persistence.NamedQuery(name = "Citas.findByCitaHora", query = "SELECT c FROM Citas c WHERE c.citaHora = :citaHora"),
    @javax.persistence.NamedQuery(name = "Citas.findByCitaId", query = "SELECT c FROM Citas c WHERE c.citaId = :citaId")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Column(name = "CITA_DIA")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date citaDia;
    @javax.persistence.Column(name = "CITA_HORA")
    private String citaHora;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CITA_ID")
    @SequenceGenerator(name = "SEC_CITAS", sequenceName = "SEC_CITAS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_CITAS")
    private Long citaId;
    @javax.persistence.JoinColumn(name = "CITA_CEDUPAC", referencedColumnName = "PK_PAC_CEDULA")
    @javax.persistence.ManyToOne(optional = false)
    private Pacientes citaCedupac;

    public Citas() {
    }

    public Citas(Long citaId) {
        this.citaId = citaId;
    }

    public Date getCitaDia() {
        return citaDia;
    }

    public void setCitaDia(Date citaDia) {
        this.citaDia = citaDia;
    }

    public String getCitaHora() {
        return citaHora;
    }

    public void setCitaHora(String citaHora) {
        this.citaHora = citaHora;
    }

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Pacientes getCitaCedupac() {
        return citaCedupac;
    }

    public void setCitaCedupac(Pacientes citaCedupac) {
        this.citaCedupac = citaCedupac;
    }

    public void actualizar(CitasDto citasDto) {
        this.citaHora = citasDto.getcitaHora();
        this.citaDia = Date.from(citasDto.getCitaDia().atStartOfDay(ZoneId.systemDefault()).toInstant());;
        this.citaId = citasDto.getCitaId();
        this.citaCedupac = new Pacientes (citasDto.getCitaCedupac());}
        
    public Citas(CitasDto citaDto) {
        this.citaId = citaDto.getCitaId();
        actualizar(citaDto);
    }


@Override
public int hashCode() {
        int hash = 0;
        hash += (citaId != null ? citaId.hashCode() : 0);
        return hash;
    }

    @Override
public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.citaId == null && other.citaId != null) || (this.citaId != null && !this.citaId.equals(other.citaId))) {
            return false;
        }
        return true;
    }

    @Override
public String toString() {
        return "com.mariangel.clinicadental.model.Citas[ citaId=" + citaId + " ]";
    }

}

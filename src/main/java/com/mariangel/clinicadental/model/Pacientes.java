/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.clinicadental.model;

import java.io.Serializable;
import java.time.ZoneId;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "TBL_PACIENTES")
@NamedQueries({
    @NamedQuery(name = "Pacientes.findAll", query = "SELECT p FROM Pacientes p"),
    @NamedQuery(name = "Pacientes.findByPkPacCedula", query = "SELECT p FROM Pacientes p WHERE p.pkPacCedula = :pkPacCedula"),
    @NamedQuery(name = "Pacientes.findByPacNombre", query = "SELECT p FROM Pacientes p WHERE p.pacNombre = :pacNombre"),
    @NamedQuery(name = "Pacientes.findByPacPapellido", query = "SELECT p FROM Pacientes p WHERE p.pacPapellido = :pacPapellido"),
    @NamedQuery(name = "Pacientes.findByPacSapellido", query = "SELECT p FROM Pacientes p WHERE p.pacSapellido = :pacSapellido"),
    @NamedQuery(name = "Pacientes.findByPacDirec", query = "SELECT p FROM Pacientes p WHERE p.pacDirec = :pacDirec"),
    @NamedQuery(name = "Pacientes.findByPacFecnac", query = "SELECT p FROM Pacientes p WHERE p.pacFecnac = :pacFecnac")})
public class Pacientes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "SEC_PACIENTES", sequenceName = "SEC_PACIENTES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_PACIENTES")
    @Column(name = "PK_PAC_CEDULA")
    private Long pkPacCedula;
    @Column(name = "PAC_NOMBRE")
    private String pacNombre;
    @Column(name = "PAC_PAPELLIDO")
    private String pacPapellido;
    @Column(name = "PAC_SAPELLIDO")
    private String pacSapellido;
    @Column(name = "PAC_DIREC")
    private String pacDirec;
    @Column(name = "PAC_FECNAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pacFecnac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citaCedupac")
    private Collection<Citas> citasCollection;
    @Transient
    private int edad;
    
    public Pacientes() {
    }

    public Pacientes(Long pkPacCedula) {
        this.pkPacCedula = pkPacCedula;
    }

    public Long getPkPacCedula() {
        return pkPacCedula;
    }

    public void setPkPacCedula(Long pkPacCedula) {
        this.pkPacCedula = pkPacCedula;
    }

    public String getPacNombre() {
        return pacNombre;
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre = pacNombre;
    }

    public String getPacPapellido() {
        return pacPapellido;
    }

    public void setPacPapellido(String pacPapellido) {
        this.pacPapellido = pacPapellido;
    }

    public String getPacSapellido() {
        return pacSapellido;
    }

    public void setPacSapellido(String pacSapellido) {
        this.pacSapellido = pacSapellido;
    }

    public String getPacDirec() {
        return pacDirec;
    }

    public void setPacDirec(String pacDirec) {
        this.pacDirec = pacDirec;
    }

    public Date getPacFecnac() {
        return pacFecnac;
    }

    public void setPacFecnac(Date pacFecnac) {
        this.pacFecnac = pacFecnac;
    }

    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPacCedula != null ? pkPacCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacientes)) {
            return false;
        }
        Pacientes other = (Pacientes) object;
        if ((this.pkPacCedula == null && other.pkPacCedula != null) || (this.pkPacCedula != null && !this.pkPacCedula.equals(other.pkPacCedula))) {
            return false;
        }
        return true;
    }
        public Pacientes(PacientesDto pacientesDto) {
        this.pkPacCedula = pacientesDto.getPacCedula();
        actualizar(pacientesDto);
    }

    public void actualizar(PacientesDto pacientesDto) {
        this.pacDirec = pacientesDto.getPacDirec();
        this.pacNombre = pacientesDto.getPacNombre();
        this.pkPacCedula= pacientesDto.getPacCedula();
        this.pacPapellido = pacientesDto.getPacPApellido();
        this.pacSapellido = pacientesDto.getPacSApellido();
        this.pacFecnac = Date.from(pacientesDto.getPacFecnac().atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    @Override
    public String toString() {
        return "com.mariangel.clinicadental.model.Pacientes[ pkPacCedula=" + pkPacCedula + " ]";
    }
    
}

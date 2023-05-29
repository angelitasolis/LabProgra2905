package com.mariangel.clinicadental.model;

import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrador
 */
public class CitasDto {

    public SimpleStringProperty citaId;
    public SimpleStringProperty citaHora;
    public ObjectProperty<LocalDate> citaDia;
    public PacientesDto citaCedupac;

    public CitasDto() {

        this.citaId = new SimpleStringProperty();
        this.citaCedupac = new PacientesDto();
        this.citaHora = new SimpleStringProperty();
        this.citaDia = new SimpleObjectProperty();
    }

    public CitasDto(Citas citas) {
        this();
        this.citaHora.set(citas.getCitaHora()); 
        this.citaId.set(citas.getCitaId().toString());
        this.citaDia.set(citas.getCitaDia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.citaCedupac = new PacientesDto(citas.getCitaCedupac());
    }

    public Long getCitaId() {
        if (citaId.get() != null && !citaId.get().isEmpty()) {
            return Long.valueOf(citaId.get());
        } else {
            return null;
        }
    }
      public PacientesDto getCitaCedupac() {
        return citaCedupac;
    }
    public void setCitaId(Long pacId) {
        this.citaId.set(pacId.toString());
    }
      public void setCitaCedupac(PacientesDto citaCedupac) {
        this.citaCedupac = citaCedupac;
    }

    public String getcitaHora() {
        return citaHora.get();
    }
     public void setCitaHora(String citaHora) {
        this.citaHora.set(citaHora);
    }

    public LocalDate getCitaDia() {
        return citaDia.get();
    }
       public void setCitaDia(LocalDate citaDia) {
        this.citaDia.set(citaDia);
    }
}

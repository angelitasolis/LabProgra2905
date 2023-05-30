/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.clinicadental.service;

import com.mariangel.clinicadental.model.Citas;
import com.mariangel.clinicadental.model.CitasDto;
import com.mariangel.clinicadental.model.Pacientes;
import com.mariangel.clinicadental.model.PacientesDto;
import com.mariangel.clinicadental.util.EntityManagerHelper;
import com.mariangel.clinicadental.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Mari
 */
public class CitasService {

    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getCita(Long id) {
        try {
            Query qryCitas = em.createNamedQuery("Citas.findByCitaId", Citas.class);
            qryCitas.setParameter("citaId", id);

            return new Respuesta(true, "", "", "Citas", new CitasDto((Citas) qryCitas.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe la cita con el código ingresado.", "getCita NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(CitasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la cita.", "getCita NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(CitasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la cita.", "getCita" + ex.getMessage());
        }
    }
 
    public Respuesta guardarCita(CitasDto citasDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Citas cita;
            if (citasDto.getCitaId() != null && citasDto.getCitaId()> 0) {
                cita = em.find(Citas.class, citasDto.getCitaId());
                if (cita == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la cita a modificar.", "guardarCita NoResultException");
                }
                cita.actualizar(citasDto);
                cita = em.merge(cita);
            } else {
                cita = new Citas(citasDto);
                em.persist(cita);
            }
            et.commit();
            return new Respuesta(true, "", "", "Empleado", new CitasDto(cita));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(CitasService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar la cita.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la cita.", "guardarCita " + ex.getMessage());
        }
    }

    public Respuesta eliminarCita(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            Citas cita;
            if (id != null && id > 0) {
                cita = em.find(Citas.class, id);
                if (cita == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la cita a eliminar.", "eliminarCita NoResultException");
                }
                em.remove(cita);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la cita a eliminar.", "eliminarCita NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la cita porque tiene relaciones con otros registros.", "eliminarCita " + ex.getMessage());
            }
            Logger.getLogger(CitasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la cita.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la cita.", "eliminarCita " + ex.getMessage());
        }
    }
}

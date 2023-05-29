/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariangel.clinicadental.service;

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
public class PacientesService {

    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;


    public Respuesta guardarPaciente(PacientesDto pacientesDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Pacientes paciente;
            if (pacientesDto.getPacCedula() == null && pacientesDto.getPacCedula() > 0) {
                paciente = em.find(Pacientes.class, pacientesDto.getPacCedula());
                System.out.println(paciente.getPacDirec());
                if (paciente == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el paciente a modificar.", "guardarPaciente NoResultException");
                }
                paciente.actualizar(pacientesDto);
                paciente = em.merge(paciente);
            } else {
                paciente = new Pacientes(pacientesDto);
                em.persist(paciente);
            }
            et.commit();
            return new Respuesta(true, "", "", "Pacientes", new PacientesDto(paciente));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(PacientesService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el paciente.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el paciente.", "guardarPaciente " + ex.getMessage());
        }
    }

    public Respuesta getPaciente(Long ced) {
        try {
            System.out.println(ced);
            System.out.println("ENTRA EN EL GET PACIENTE");
            Query qryPacientes = em.createNamedQuery("Pacientes.findByPkPacCedula", Pacientes.class);
            qryPacientes.setParameter("pkPacCedula", ced);

            return new Respuesta(true, "", "", "Pacientes", new PacientesDto((Pacientes) qryPacientes.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe el paciente ingresado.", "getTour NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(PacientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el paciente.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el paciente.", "getTour NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(PacientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el paciente.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el paciente.", "getPacientes " + ex.getMessage());
        }
    }
    public Respuesta modificarPaciente(PacientesDto pacienteDto, Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            if (pacienteDto!= null && pacienteDto.getPacCedula() > 0) {
                Query query = em.createNamedQuery("Pacientes.findByPkPacCedula");
                query.setParameter("pkPacCedula", id);
                Pacientes paciente = (Pacientes) query.getSingleResult();
                System.out.println("Cédula del paciente a buscar: " + pacienteDto.getPacCedula());
                if (paciente == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el paciente a actualizar.", "actualizarPaciente NoResultException");
                }

                paciente.actualizar(pacienteDto);
                paciente = em.merge(paciente);
                et.commit();
                em.refresh(paciente);
                paciente = em.find(Pacientes.class, paciente.getPkPacCedula());
                return new Respuesta(true, "", "", "Paciente", new PacientesDto(paciente));
            } else {
                et.rollback();
                return new Respuesta(false, "No se proporcionó una cédula válida para actualizar el paciente.", "actualizarPaciente InvalidParameterException");
            }
        } catch (NoResultException ex) {
            et.rollback();
            Logger.getLogger(PacientesService.class.getName()).log(Level.SEVERE, "Ocurrió un error al actualizar el paciente.", ex);
            return new Respuesta(false, "Ocurrió un error al actualizar el paciente.", "actualizarPaciente " + ex.getMessage());
        }
    }
    


    public Respuesta eliminarPaciente(Long ced) {
        try {
            et = em.getTransaction();
            et.begin();
            Pacientes pacientes;
            if (ced != null && ced > 0) {
                pacientes = em.find(Pacientes.class, ced);
                if (pacientes == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el paciente a eliminar.", "eliminarPaciente NoResultException");
                }
                em.remove(pacientes);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el paciente a eliminar.", "eliminarPaciente NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el paciente porque tiene relaciones con otros registros.", "eliminarPaciente " + ex.getMessage());
            }
            Logger.getLogger(PacientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el paciente.", ex);
            return new Respuesta(false, "Ocurrió un error al eliminar el paciente.", "eliminarPaciente " + ex.getMessage());
        }

    }

}



package com.mariangel.clinicadental.model;

import com.mariangel.clinicadental.model.Pacientes;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-29T12:49:00", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Citas.class)
public class Citas_ { 

    public static volatile SingularAttribute<Citas, Integer> citaId;
    public static volatile SingularAttribute<Citas, String> citaHora;
    public static volatile SingularAttribute<Citas, Pacientes> citaCedupac;
    public static volatile SingularAttribute<Citas, Date> citaDia;

}
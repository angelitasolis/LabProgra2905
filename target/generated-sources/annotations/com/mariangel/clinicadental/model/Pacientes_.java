package com.mariangel.clinicadental.model;

import com.mariangel.clinicadental.model.Citas;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-29T14:23:10", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pacientes.class)
public class Pacientes_ { 

    public static volatile SingularAttribute<Pacientes, Date> pacFecnac;
    public static volatile SingularAttribute<Pacientes, String> pacDirec;
    public static volatile CollectionAttribute<Pacientes, Citas> citasCollection;
    public static volatile SingularAttribute<Pacientes, Long> pkPacCedula;
    public static volatile SingularAttribute<Pacientes, String> pacNombre;
    public static volatile SingularAttribute<Pacientes, String> pacPapellido;
    public static volatile SingularAttribute<Pacientes, String> pacSapellido;

}
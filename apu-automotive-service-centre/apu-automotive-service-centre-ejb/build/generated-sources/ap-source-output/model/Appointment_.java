package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Customer;
import model.ServiceType;
import model.Technician;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-04-14T09:06:14")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-04-22T23:02:33")
>>>>>>> 60a624cdbb4322adf292680e6e7f8f895b68e9e6
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, ServiceType> serviceType;
    public static volatile SingularAttribute<Appointment, String> appointmentTime;
    public static volatile SingularAttribute<Appointment, String> carPlateNumber;
    public static volatile SingularAttribute<Appointment, Technician> technician;
    public static volatile SingularAttribute<Appointment, Long> id;
    public static volatile SingularAttribute<Appointment, Date> appointmentDate;
    public static volatile SingularAttribute<Appointment, String> remarks;
    public static volatile SingularAttribute<Appointment, String> status;
    public static volatile SingularAttribute<Appointment, Customer> customer;

}
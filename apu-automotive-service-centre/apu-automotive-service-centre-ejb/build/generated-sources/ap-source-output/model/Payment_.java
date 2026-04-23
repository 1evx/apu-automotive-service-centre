package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Appointment;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-04-14T09:06:14")
=======
@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2026-04-22T23:02:33")
>>>>>>> 60a624cdbb4322adf292680e6e7f8f895b68e9e6
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Double> amount;
    public static volatile SingularAttribute<Payment, String> method;
    public static volatile SingularAttribute<Payment, Appointment> appointment;
    public static volatile SingularAttribute<Payment, Long> id;
    public static volatile SingularAttribute<Payment, Date> paymentDate;

}
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
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Date> commentDate;
    public static volatile SingularAttribute<Comment, Integer> rating;
    public static volatile SingularAttribute<Comment, Appointment> appointment;
    public static volatile SingularAttribute<Comment, Long> id;
    public static volatile SingularAttribute<Comment, String> content;

}
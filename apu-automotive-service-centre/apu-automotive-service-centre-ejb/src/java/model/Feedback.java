package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FEEDBACK", schema = "APP")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String feedbackType;

    @Column(length = 1000, nullable = false)
    private String comments;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;

    // ==========================================
    // RELATIONSHIPS
    // ==========================================
    
    // Links directly to the Appointment instead of a loose String ID!
    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID", nullable = false)
    private Appointment appointment;

    public Feedback() {
    }

    public Feedback(String feedbackType, String comments, Date submissionDate, Appointment appointment) {
        this.feedbackType = feedbackType;
        this.comments = comments;
        this.submissionDate = submissionDate;
        this.appointment = appointment;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Feedback)) return false;
        Feedback other = (Feedback) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "model.Feedback[ id=" + id + " ]";
    }
}
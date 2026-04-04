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
@Table(name = "COMMENTS", schema = "APP") 
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date commentDate; 

    @Column(length = 1000, nullable = false)
    private String content;

    // Added the rating for the 1-5 star review!
    @Column(nullable = false)
    private int rating; 

    // ==========================================
    // RELATIONSHIPS
    // ==========================================
    
    // Links directly to the Appointment. (We removed 'author' to keep the database clean!)
    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID", nullable = false)
    private Appointment appointment;

    public Comment() {
    }

    public Comment(Date commentDate, String content, int rating, Appointment appointment) {
        this.commentDate = commentDate;
        this.content = content;
        this.rating = rating;
        this.appointment = appointment;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    // --- Getters & Setters ---
    public void setAppointment(Appointment appointment) {    
        this.appointment = appointment;
    }

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comment)) return false;
        Comment other = (Comment) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "model.Comment[ id=" + id + " ]";
    }
}
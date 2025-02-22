package com.example.project.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String booking_id;

    private String disease;
    private Date tentativeDate;
    private String priority;
    private String patientId;
    private Date bookingTime;

    public Appointment(String disease, Date tentativeDate, String priority, String patientId) {
        super();
        this.disease = disease;
        this.tentativeDate = tentativeDate;
        this.priority = priority;
        this.patientId = patientId;
    }
}

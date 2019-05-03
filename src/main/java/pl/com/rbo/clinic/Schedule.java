package pl.com.rbo.clinic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 29.04.19.
 */

@Getter @Setter
@EqualsAndHashCode(of = {"doctor", "date"})
public class Schedule {

    public static final Schedule EMPTY = new Schedule();

    private Doctor doctor;
    private LocalDate date;
    private Integer availableTimeslots;
    private List<Integer> bookedPatientIds = new ArrayList<>();

    public void bookTimeslot(Integer patientId){
        bookedPatientIds.add(patientId);
    }

    public boolean isAvailable(){
        return bookedPatientIds.size() < availableTimeslots;
    }

    public Specialty getDoctorSpecialty(){
        return doctor.getSpecialty();
    }

}

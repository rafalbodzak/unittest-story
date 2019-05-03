package pl.com.rbo.clinic;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by rafal on 29.04.19.
 */

@Getter @Setter
public class SearchCriteria {

    private Doctor doctor;
    private Specialty specialty;
    private LocalDate startDate;

    public SearchCriteria(Doctor doctor){
        this.doctor = doctor;
    }

    public SearchCriteria(Specialty specialty){
        this.specialty = specialty;
    }
}

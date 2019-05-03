package pl.com.rbo.clinic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rafal on 29.04.19.
 */

@EqualsAndHashCode
@Getter @Setter
public class Doctor {

    private String name;
    private Specialty specialty;

    public Doctor(String name, Specialty specialty){
        this.name = name;
        this.specialty = specialty;
    }
}

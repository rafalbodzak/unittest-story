package pl.com.rbo.clinic;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@EqualsAndHashCode(of = {"doctor", "date"})
public class ScheduleInfo {

    public static ScheduleInfo EMPTY = new ScheduleInfo(null, null);

    private Doctor doctor;
    private LocalDate date;
}

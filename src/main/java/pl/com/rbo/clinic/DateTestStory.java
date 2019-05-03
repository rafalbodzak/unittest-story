package pl.com.rbo.clinic;

import java.time.LocalDate;

/**
 * Created by rafal on 29.04.19.
 */
public class DateTestStory {

    public static LocalDate today(){
        return LocalDate.now();
    }

    public static LocalDate tomorrow(){
        return LocalDate.now().plusDays(1);
    }

    public static LocalDate after_tomorrow(){
        return LocalDate.now().plusDays(2);
    }
}

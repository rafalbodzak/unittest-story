package pl.com.rbo.clinic;

import org.junit.Test;

import static pl.com.rbo.clinic.SchedulerTestStory.*;
import static pl.com.rbo.clinic.ScheduleTestStory.*;
import static pl.com.rbo.clinic.DateTestStory.*;
import static pl.com.rbo.clinic.Specialty.*;
import static pl.com.rbo.clinic.SearchCriteriaTestStory.*;
import static pl.com.rbo.clinic.DoctorTestStory.*;

/**
 * Created by rafal on 29.04.19.
 */
public class SchedulerTest {

    @Test
    public void test_booking_specific_doctor(){

        GetSchedule()
            .with(Dr(Reynolds_Cardiologist).works(today()))
            .with(Dr(Johnson_Cardiologist).works(tomorrow()))
                .search_appointment(With(Johnson_Cardiologist).from(today()))
                .expect_appointment_with(Johnson_Cardiologist)
                .expect_appointment(tomorrow())
        ;

        GetSchedule()
            .with(Dr(Reynolds_Cardiologist).works(today()))
            .with(Dr(Taylor_Gastrologist).works(tomorrow()))
                .search_appointment(With(GASTROLOGIST).from(today()))
                .expect_appointment(tomorrow())
                .expect_appointment_with(Taylor_Gastrologist)
        ;

        GetSchedule()
                .with(Dr(Reynolds_Cardiologist).works(today()))
                .search_appointment(With(Johnson_Cardiologist).from(today()))
                .expect_no_free_timeslots()
        ;

        GetSchedule()
                .with(Dr(Reynolds_Cardiologist).works(today()))
                .with(Dr(Johnson_Cardiologist).works(today()))
                .search_appointment(With(Johnson_Cardiologist).from(tomorrow()))
                .expect_no_free_timeslots()
        ;

    }

    @Test
    public void test_free_timeslots(){

        GetSchedule()
            .with(Dr(Johnson_Cardiologist).works(today()).available_timeslots(2))
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_appointment(today())
                .book()
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_appointment(today())
                .book()
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_no_free_timeslots()
        ;
    }

    @Test
    public void test_searching_forward(){

        GetSchedule()
            .with(Dr(Johnson_Cardiologist).works(today()).available_timeslots(1))
            .with(Dr(Johnson_Cardiologist).works(tomorrow()).available_timeslots(1))
            .with(Dr(Johnson_Cardiologist).works(after_tomorrow()).available_timeslots(1))
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_appointment(today())
                .book()
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_appointment(tomorrow())
                .book()
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_appointment(after_tomorrow())
                .book()
                .search_appointment(With(CARDIOLOGIST).from(today()))
                .expect_no_free_timeslots()
        ;

        GetSchedule()
            .with(Dr(Johnson_Cardiologist).works(today()))
                .search_appointment(With(CARDIOLOGIST).from(tomorrow()))
                .expect_no_free_timeslots()
        ;
    }

}

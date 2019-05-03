package pl.com.rbo.clinic;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by rafal on 29.04.19.
 */
public class SchedulerTestStory {

    private Scheduler scheduler = new Scheduler();
    private ScheduleInfo lastSearchResult;

    private SchedulerTestStory(){}

    public static SchedulerTestStory GetSchedule(){
        return new SchedulerTestStory();
    }

    public SchedulerTestStory with(ScheduleTestStory scheduleTestStory){
        scheduler.addDoctorSchedule(scheduleTestStory.get());
        return this;
    }

    public SchedulerTestStory search_appointment(SearchCriteriaTestStory searchCriteriaTestStory){
        lastSearchResult = scheduler.search(searchCriteriaTestStory.get());
        return this;
    }

    public SchedulerTestStory book(){
        if (lastSearchResult == null){
            throw new IllegalArgumentException("Search visit before booking.");
        }
        scheduler.bookTimeslot(lastSearchResult, somePatientId());
        return this;
    }

    public SchedulerTestStory expect_appointment(LocalDate expectedDate){
        assertEquals(expectedDate, lastSearchResult.getDate());
        return this;
    }

    public SchedulerTestStory expect_appointment_with(Doctor expectedDoctor){
        assertEquals(expectedDoctor, lastSearchResult.getDoctor());
        return this;
    }

    public SchedulerTestStory expect_no_free_timeslots(){
        assertEquals(ScheduleInfo.EMPTY, lastSearchResult);
        return this;
    }

    private Integer somePatientId(){
        return (int)Math.round(Math.random());
    }
}

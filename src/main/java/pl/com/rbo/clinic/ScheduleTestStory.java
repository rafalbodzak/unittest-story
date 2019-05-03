package pl.com.rbo.clinic;

import java.time.LocalDate;

/**
 * Created by rafal on 29.04.19.
 */
public class ScheduleTestStory {

    private Schedule schedule;

    private ScheduleTestStory(Doctor doctor){
        schedule = new Schedule();
        schedule.setDoctor(doctor);
        schedule.setAvailableTimeslots(1);
    }


    public static ScheduleTestStory Dr(Doctor doctor){
        ScheduleTestStory scheduleTestStory = new ScheduleTestStory(doctor);
        return scheduleTestStory;
    }


    public ScheduleTestStory works(LocalDate date){
        schedule.setDate(date);
        return this;
    }

    public ScheduleTestStory available_timeslots(Integer avaliableTimeslots){
        schedule.setAvailableTimeslots(avaliableTimeslots);
        return this;
    }

    public Schedule get(){
        return schedule;
    }

}
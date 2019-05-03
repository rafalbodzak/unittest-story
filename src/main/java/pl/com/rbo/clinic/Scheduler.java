package pl.com.rbo.clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 29.04.19.
 */
public class Scheduler {

    private List<Schedule> schedules = new ArrayList<>();

    public void addDoctorSchedule(Schedule schedule){
        schedules.add(schedule);
    }

    public ScheduleInfo search(SearchCriteria criteria){

        return schedules.stream()
                .filter(s -> s.isAvailable())
                .filter(s -> ! criteria.getStartDate().isAfter(s.getDate()))
                .filter(s -> criteria.getSpecialty() == null || criteria.getSpecialty().equals(s.getDoctorSpecialty()))
                .filter(s -> criteria.getDoctor() == null || criteria.getDoctor().equals(s.getDoctor()))
                .map(s -> new ScheduleInfo(s.getDoctor(), s.getDate()))
                .findAny().orElse(ScheduleInfo.EMPTY);
    }

    public void bookTimeslot(ScheduleInfo scheduleInfo, Integer patientId){
        schedules.stream()
                .filter(doctorSchedule -> scheduleInfo.getDoctor().equals(doctorSchedule.getDoctor()))
                .filter(doctorSchedule -> scheduleInfo.getDate().equals(doctorSchedule.getDate()))
                .findAny().get().bookTimeslot(patientId);
    }

}

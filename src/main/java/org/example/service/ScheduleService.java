package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Employee;
import org.example.domain.Schedule;
import org.example.repository.ScheduleRepository;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedule(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getByEmployeeAndWeek(Employee employee, LocalDate weekStart, LocalDate weekEnd){
        return scheduleRepository.findByEmployeeAndShift_DateBetween(employee, weekStart, weekEnd);
    }

    public Long getAssignedMinutes(Employee employee, LocalDate date){
        LocalDate weekStart = date.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = weekStart.plusDays(6);
        List<Schedule> assignedSchedule = getByEmployeeAndWeek(employee, weekStart, weekEnd);
        long sumOfMinutes = 0;

        for(Schedule s : assignedSchedule){
           long currentMinutes = s.getShift().calculateMinutes();

            sumOfMinutes += currentMinutes;
        }

        return sumOfMinutes;



    }


    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }
}

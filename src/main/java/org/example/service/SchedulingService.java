package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Availability;
import org.example.domain.Employee;
import org.example.domain.Shift;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final AvailabilityService availabilityService;
    private final ScheduleService scheduleService;

    public boolean isEligible(Employee employee, Shift shift){
            Optional<Availability> availability = availabilityService.getByEmployeeAndDay
                    (employee, shift.getDate().getDayOfWeek());



            //encounter null result from availability
            if(availability.isPresent() && Boolean.FALSE.equals(availability.get().getAvailability())) {
                return false;
            }



            //
            if(shift.isNeedSenior() && !employee.isSenior()){
                return false;
            }


            Long maxHour = employee.getMaxHour();
            if(maxHour != null) {

                LocalDate date = shift.getDate();
                LocalDate weekStart = date.with(DayOfWeek.MONDAY);
                LocalDate weekEnd = weekStart.plusDays(6);
                long sumOfMinutes = (scheduleService.getAssignedMinutes(employee, weekStart, weekEnd));

                //compare the limit on max hour (should be discussed if employee has no limit on max hour
                if (sumOfMinutes + (Duration.between(shift.getStartTime(), shift.getEndTime()).toMinutes()) > (maxHour * 60)) {
                    return false;
                }
            }

                return true;




    }
}

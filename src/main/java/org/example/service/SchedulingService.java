package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Availability;
import org.example.domain.Employee;
import org.example.domain.Schedule;
import org.example.domain.Shift;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final AvailabilityService availabilityService;
    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;

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
                long sumOfMinutes = (scheduleService.getAssignedMinutes(employee, date));

                //compare the limit on max hour (should be discussed if employee has no limit on max hour
                if (sumOfMinutes + (Duration.between(shift.getStartTime(), shift.getEndTime()).toMinutes()) > (maxHour * 60)) {
                    return false;
                }
            }

                return true;




    }

    public Optional<Schedule> autoAssign(Shift shift){

        List<Employee> employeeList = employeeService.getAllEmployee();

        Employee suitableEmployee = null;
        long employeeMinutes = Long.MAX_VALUE;

        for(Employee e : employeeList){

            if(!isEligible(e, shift)){
                continue;
            }

            long currentMinutes = scheduleService.getAssignedMinutes(e, shift.getDate());

            if( currentMinutes < employeeMinutes){
                suitableEmployee = e;
                employeeMinutes = currentMinutes;
            }



        }
        if(suitableEmployee==null){
            return Optional.empty();
        }


        Schedule schedule = new Schedule();
        schedule.setEmployee(suitableEmployee);
        schedule.setShift(shift);
        schedule.setStartTime(shift.getStartTime());
        schedule.setEndTime(shift.getEndTime());

        Schedule savedSchedule =scheduleService.saveSchedule(schedule);
        return Optional.of(savedSchedule);
    }


}

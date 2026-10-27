package org.example.service;



import org.example.domain.Availability;
import org.example.domain.Employee;
import org.example.domain.Shift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SchedulingServiceTest {

    @Mock
    AvailabilityService availabilityService;
    @Mock
    ScheduleService scheduleService;
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    SchedulingService schedulingService;

   @Test
   void notEligible_returnFalse(){
       Employee employee = new Employee();
       Shift shift = new Shift();
       Availability availability = new Availability();
       LocalDate date = LocalDate.of(2026, 10,21);
       shift.setDate(date);
       availability.setAvailability(false);

       when(availabilityService.getByEmployeeAndDay(employee, date.getDayOfWeek()))
               .thenReturn(Optional.of(availability));

       boolean result = schedulingService.isEligible(employee, shift);
       assertFalse(result);



   }

   @Test
    void exceedMaxhour_returnFalse(){
       Employee employee = new Employee();
       Shift shift = new Shift();
       LocalDate date = LocalDate.of(2026, 10, 21);
       shift.setDate(date);
       LocalDate weekStart = date.with(DayOfWeek.MONDAY);
       LocalDate weekEnd = weekStart.plusDays(6);
       employee.setMaxHour(25L);
       Long assignedMinutes = 1380L;

       when(scheduleService.getAssignedMinutes(employee, weekStart, weekEnd))
               .thenReturn(assignedMinutes);

       shift.setStartTime((LocalTime.of(10,0)));
       shift.setEndTime(LocalTime.of(15,0));


       boolean result = schedulingService.isEligible(employee, shift);
       assertFalse(result);
   }



}

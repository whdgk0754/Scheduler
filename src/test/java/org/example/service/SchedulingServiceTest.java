package org.example.service;



import org.example.domain.Availability;
import org.example.domain.Employee;
import org.example.domain.Shift;

import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SchedulingServiceTest {

    Employee employee;
    Shift shift;
    LocalDate date;

    @Mock
    AvailabilityService availabilityService;
    @Mock
    ScheduleService scheduleService;
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    SchedulingService schedulingService;

    @BeforeEach
    void setUp(){
        employee = new Employee();
        shift = new Shift();
        date = LocalDate.of(2026, 10, 21);
        shift.setDate(date);
    }
   @Test
   void notEligible_returnFalse(){

       Availability availability = new Availability();

       availability.setAvailability(false);

       when(availabilityService.getByEmployeeAndDay(employee, date.getDayOfWeek()))
               .thenReturn(Optional.of(availability));

       boolean result = schedulingService.isEligible(employee, shift);
       assertFalse(result);



   }

   @Test
    void exceedMaxhour_returnFalse(){


       employee.setMaxHour(25L);
       Long assignedMinutes = 1380L;

       when(scheduleService.getAssignedMinutes(employee,date))
               .thenReturn(assignedMinutes);

       shift.setStartTime((date.atTime(10,0)));
       shift.setEndTime(date.atTime(15,0));


       boolean result = schedulingService.isEligible(employee, shift);
       assertFalse(result);
   }


   @Test
    void exactlyMaxhour_returnTrue(){


       Long assignedMinutes = 1380L;
       employee.setMaxHour(25L);

       when(scheduleService.getAssignedMinutes(employee, date)
               ).thenReturn(assignedMinutes);

       shift.setStartTime((date.atTime(10,0)));
       shift.setEndTime(date.atTime(12,0));

       boolean result = schedulingService.isEligible(employee, shift);
       assertTrue(result);
   }

    @Test
    void SeniorRequireANDnotSenior_returnFalse(){


        shift.setNeedSenior(true);
        employee.setSenior(false);

        boolean result = schedulingService.isEligible(employee, shift);
        assertFalse(result);

    }

    @Test
    void SeniorRequireANDisSenior_returnTrue(){


        shift.setNeedSenior(true);
        employee.setSenior(true);

        boolean result = schedulingService.isEligible(employee, shift);
        assertTrue(result);
    }

}

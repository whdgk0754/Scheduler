package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Availability;
import org.example.domain.Employee;
import org.example.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.DayOfWeek;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public List<Availability> getAllAvailability(){
        return availabilityRepository.findAll();
    }

    public Optional<Availability> getByEmployeeAndDay(Employee employee, DayOfWeek dayOfWeek){
        return availabilityRepository.findByEmployeeAndDayOfWeek(employee, dayOfWeek);
    }


}

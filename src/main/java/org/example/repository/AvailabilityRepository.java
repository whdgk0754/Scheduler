package org.example.repository;

import org.example.domain.Availability;
import org.example.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    Optional<Availability> findByEmployeeAndDayOfWeek(Employee employee, DayOfWeek dayOfWeek);


}

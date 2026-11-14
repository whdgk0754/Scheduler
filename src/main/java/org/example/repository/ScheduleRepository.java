package org.example.repository;

import org.example.domain.Employee;
import org.example.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import org.example.domain.Shift;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByEmployeeAndShift_DateBetween(Employee employee, LocalDate weekStart, LocalDate weekEnd);

    boolean existsByShift(Shift shift);
}


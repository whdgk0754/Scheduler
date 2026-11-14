package org.example.repository;

import org.example.domain.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    List<Shift> findByDateBetween(LocalDate start, LocalDate end);

}

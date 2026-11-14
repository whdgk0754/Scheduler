package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Shift;
import org.example.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public List<Shift> getAllShift(){
        return shiftRepository.findAll();
    }

    public List<Shift> getShiftsBetweenDate(LocalDate start, LocalDate end){

        return shiftRepository.findByDateBetween(start, end);
    }

    

}

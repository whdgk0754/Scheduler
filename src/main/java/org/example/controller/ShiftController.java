package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Shift;
import org.example.service.ShiftService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shift")
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public List<Shift> getAllShift(){
        return shiftService.getAllShift();
    }
}

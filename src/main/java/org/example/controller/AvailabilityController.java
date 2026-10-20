package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Availability;
import org.example.service.AvailabilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping
    public List<Availability> getAllAvailability(){
        return availabilityService.getAllAvailability();
    }
}

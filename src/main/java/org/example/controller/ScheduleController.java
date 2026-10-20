package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Schedule;
import org.example.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public List<Schedule> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }
}

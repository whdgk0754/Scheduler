package org.example.service;

import org.example.domain.Shift;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftTest {

    @Test
    void overMidnight_return420(){
        Shift shift = new Shift();
        shift.setStartTime(LocalTime.of(18,0));
        shift.setEndTime(LocalTime.of(1,0));

        long result = shift.calculateMinutes();

        assertEquals(420, result);
    }

    @Test
    void sameDay_return120(){
        Shift shift = new Shift();
        shift.setStartTime(LocalTime.of(18,0));
        shift.setEndTime(LocalTime.of(20,0));

        long result = shift.calculateMinutes();

        assertEquals(120, result);
    }
}

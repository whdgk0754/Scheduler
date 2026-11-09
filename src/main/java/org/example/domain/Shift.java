package org.example.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalTime startTime;
    private LocalTime endTime;

    private static final long dayToMinutes = 1440;

    private Integer requiredHeadCount;

    private boolean needSenior;

    public long calculateMinutes(){

        
        long minutes = Duration.between(startTime, endTime).toMinutes();
        if(minutes<0){
            return minutes+dayToMinutes;
        }
        return minutes;
    }


}

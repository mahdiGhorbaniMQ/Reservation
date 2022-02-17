package com.example.visit.payload.response;

import lombok.Getter;
import lombok.Setter;

public class VisitProfileResponse {
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private int year;

    @Getter @Setter
    private int month;

    @Getter @Setter
    private int day;

    @Getter @Setter
    private int startHour;

    @Getter @Setter
    private int endHour;

    @Getter @Setter
    private int startMinute;

    @Getter @Setter
    private int endMinute;

    @Getter @Setter
    private Boolean isTaken;

    public VisitProfileResponse(Long id, int year,int month,int day, int startHour,int endHour,int startMinute, int endMinute, Boolean isTaken){
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.isTaken = isTaken;
    }
}

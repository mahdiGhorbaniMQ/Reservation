package com.example.visit.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Visit {
    @Id
    @Getter @Setter @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter @Column
    private int year;

    @Getter @Setter @Column
    private int month;

    @Getter @Setter @Column
    private int day;

    @Getter @Setter @Column
    private int startHour;

    @Getter @Setter @Column
    private int endHour;

    @Getter @Setter @Column
    private int startMinute;

    @Getter @Setter @Column
    private int endMinute;

    @Getter @Setter @Column
    private Boolean isTaken;

    public Visit(){}
    public Visit(int year,int month,int day, int startHour,int endHour,int startMinute, int endMinute,Boolean isTaken){
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

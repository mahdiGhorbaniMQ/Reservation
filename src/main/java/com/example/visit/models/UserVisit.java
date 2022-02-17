package com.example.visit.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class UserVisit {
    @Id @Getter @Setter @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter @Column
    private Long visitId;

    @Getter @Setter @Column
    private String email;

    public UserVisit(){}
    public UserVisit(Long visitId, String email){
        this.visitId = visitId;
        this.email = email;
    }
}

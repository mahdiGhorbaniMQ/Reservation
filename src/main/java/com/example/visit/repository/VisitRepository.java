package com.example.visit.repository;

import com.example.visit.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {

    @Override
    Visit getById(Long aLong);

    @Override
    List<Visit> findAll();
    //    Collection<Visit> findAllByYear(int year);
//    Collection<Visit> findAllByYearAndMonth(int year,int month);
    Collection<Visit> findAllByYearAndMonthAndDay(int year,int month,int day);
//    Collection<Visit> findAllByYearAndMonthAndDayAndStartHourAndStartMinute(int year,int month,int day,int startHour,int startMinute);
}

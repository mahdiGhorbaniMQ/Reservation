package com.example.visit.controllers;

import com.example.visit.models.User;
import com.example.visit.models.UserVisit;
import com.example.visit.models.Visit;
import com.example.visit.payload.response.MessageResponse;
import com.example.visit.payload.response.VisitDetailsResponse;
import com.example.visit.payload.response.VisitProfileResponse;
import com.example.visit.repository.UserRepository;
import com.example.visit.repository.UserVisitRepository;
import com.example.visit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/visit")
public class VisitController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserVisitRepository userVisitRepository;

    @Autowired
    VisitRepository visitRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createVisit(@RequestBody Visit newVisit){
        Visit visit = new Visit(
                newVisit.getYear(),
                newVisit.getMonth(),
                newVisit.getDay(),
                newVisit.getStartHour(),
                newVisit.getEndHour(),
                newVisit.getStartMinute(),
                newVisit.getEndMinute(),
                false);

        visitRepository.save(visit);
        return ResponseEntity.ok(new MessageResponse("visit successfully created!"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteVisit(@RequestParam Long visitId){
        Visit visit = visitRepository.getById(visitId);
        Collection<UserVisit> userVisits = new HashSet<>();
        userVisits.forEach(item->{
            userVisitRepository.delete(item);
        });
        visitRepository.delete(visit);
        return ResponseEntity.ok(new MessageResponse("visit successfully deleted!"));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateVisit(@RequestParam Long visitId,@RequestBody Visit newVisit){
        Visit visit = visitRepository.getById(visitId);
        visit.setYear(newVisit.getYear());
        visit.setMonth(newVisit.getMonth());
        visit.setDay(newVisit.getDay());
        visit.setStartHour(newVisit.getStartHour());
        visit.setEndHour(newVisit.getEndHour());
        visit.setStartMinute(newVisit.getStartMinute());
        visit.setEndMinute(newVisit.getEndMinute());
        visitRepository.save(visit);
        return ResponseEntity.ok(new MessageResponse("visit successfully updated!"));
    }


    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllByYearForAdmin(){
        Collection<Visit> visits = visitRepository.findAll();
        Set<VisitDetailsResponse> visitsResponse = new HashSet<>();
        visits.forEach(visit->{
            VisitDetailsResponse visitDetailsResponse = new VisitDetailsResponse(
                    visit.getId(),
                    visit.getYear(),
                    visit.getMonth(),
                    visit.getDay(),
                    visit.getStartHour(),
                    visit.getEndHour(),
                    visit.getStartMinute(),
                    visit.getEndMinute(),
                    visit.getIsTaken()
            );
            if(visit.getIsTaken()){
                UserVisit userVisit = userVisitRepository.getByVisitId(visit.getId());
                User user = userRepository.findByEmail(userVisit.getEmail());
                visitDetailsResponse.setUser(user);
            }
            visitsResponse.add(visitDetailsResponse);
        });

        return ResponseEntity.ok(visitsResponse);
    }

    @GetMapping("/admin/all/day")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllByYearAndMonthAndDayForAdmin(@RequestParam int year,@RequestParam int month,@RequestParam int day){
        Collection<Visit> visits = visitRepository.findAllByYearAndMonthAndDay(year,month,day);
        Set<VisitDetailsResponse> visitsResponse = new HashSet<>();
        visits.forEach(visit->{
            VisitDetailsResponse visitDetailsResponse = new VisitDetailsResponse(
                    visit.getId(),
                    visit.getYear(),
                    visit.getMonth(),
                    visit.getDay(),
                    visit.getStartHour(),
                    visit.getEndHour(),
                    visit.getStartMinute(),
                    visit.getEndMinute(),
                    visit.getIsTaken()
            );
            if(visit.getIsTaken()){
                UserVisit userVisit = userVisitRepository.getByVisitId(visit.getId());
                User user = userRepository.findByEmail(userVisit.getEmail());
                visitDetailsResponse.setUser(user);
            }
            visitsResponse.add(visitDetailsResponse);
        });
        return ResponseEntity.ok(visitsResponse);
    }






    @GetMapping("/all")
    public ResponseEntity<?> getAllByYear(){
        Collection<Visit> visits = visitRepository.findAll();
        Set<VisitProfileResponse> visitsResponse = new HashSet<>();
        visits.forEach(visit->{
            VisitProfileResponse visitProfileResponse  = new VisitProfileResponse(
                    visit.getId(),
                    visit.getYear(),
                    visit.getMonth(),
                    visit.getDay(),
                    visit.getStartHour(),
                    visit.getEndHour(),
                    visit.getStartMinute(),
                    visit.getEndMinute(),
                    visit.getIsTaken()
            );
            visitsResponse.add(visitProfileResponse);
        });
        return ResponseEntity.ok(visitsResponse);
    }

    @GetMapping("/all/day")
    public ResponseEntity<?> getAllByYearAndMonthAndDay(@RequestParam int year,@RequestParam int month,@RequestParam int day){
        Collection<Visit> visits = visitRepository.findAllByYearAndMonthAndDay(year,month,day);
        Set<VisitProfileResponse> visitsResponse = new HashSet<>();
        visits.forEach(visit->{
            VisitProfileResponse visitProfileResponse  = new VisitProfileResponse(
                    visit.getId(),
                    visit.getYear(),
                    visit.getMonth(),
                    visit.getDay(),
                    visit.getStartHour(),
                    visit.getEndHour(),
                    visit.getStartMinute(),
                    visit.getEndMinute(),
                    visit.getIsTaken()
            );
            visitsResponse.add(visitProfileResponse);
        });
        return ResponseEntity.ok(visitsResponse);
    }


    @GetMapping("/add")
    public ResponseEntity<?> addVisitforUser(Principal principal, @RequestParam Long visitId){
        String email = principal.getName();
        Visit visit = visitRepository.getById(visitId);
        if(visit == null){
            return ResponseEntity.badRequest().body(new MessageResponse("visit not found!"));
        }
        if(visit.getIsTaken()){
            return ResponseEntity.badRequest().body(new MessageResponse("visit is already taken!"));
        }
        visit.setIsTaken(true);
        visitRepository.save(visit);
        UserVisit userVisit = new UserVisit(visitId,email);
        userVisitRepository.save(userVisit);
        return ResponseEntity.ok(new MessageResponse("visit time successfully added for user!"));
    }
    @GetMapping("/remove")
    public ResponseEntity<?> removeVisitForUser(Principal principal, @RequestParam Long visitId){
        String email = principal.getName();
        Visit visit = visitRepository.getById(visitId);
        if(visit == null){
            return ResponseEntity.badRequest().body(new MessageResponse("visit not found!"));
        }
        UserVisit userVisit = userVisitRepository.getByVisitId(visitId);
        if(!userVisit.getEmail().equals(email)){
            return ResponseEntity.badRequest().body(new MessageResponse("you have not access to this visit time!"));
        }
        visit.setIsTaken(false);
        visitRepository.save(visit);
        userVisitRepository.delete(userVisit);
        return ResponseEntity.ok(new MessageResponse("visit time successfully removed for user!"));
    }
}

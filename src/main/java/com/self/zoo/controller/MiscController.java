package com.self.zoo.controller;

import com.self.zoo.service.MiscService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/misc")
public class MiscController {

    MiscService miscService;

    @GetMapping("/happinessReport")
    public ResponseEntity<?> getHappinessReport(){
               Map<String, Long> happinessMap = miscService.getHappinessReport();
               return ResponseEntity.ok(happinessMap);
    }
}

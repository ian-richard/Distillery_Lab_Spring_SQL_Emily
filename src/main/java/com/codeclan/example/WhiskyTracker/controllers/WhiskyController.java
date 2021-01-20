package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesAndFilterByYear
            (@RequestParam(name="year", required = false) Integer year,
             @RequestParam(name="id", required= false) Long id,
             @RequestParam(name="age", required=false) Integer age){
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if (id != null && age !=null){
            List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryIdAndAge(id, age);
            return new ResponseEntity<>(foundWhiskies, HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

//    @GetMapping(value="/whiskies/{Id}")
//    public ResponseEntity<List<Whisky>> findWhiskiesByDistilleryAndAge(@RequestParam(name="distillery") Long id, @RequestParam(name="age") Integer age){
//        Distillery distillery;
//        new Distillery =
//    }


}

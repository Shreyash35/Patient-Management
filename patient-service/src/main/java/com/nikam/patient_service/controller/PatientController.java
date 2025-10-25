package com.nikam.patient_service.controller;

import com.nikam.patient_service.dto.PatientDTO;
import com.nikam.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/get-all-patient")
    public List<PatientDTO> getAllPatient(){
        return this.patientService.getAllPatient();
    }
}

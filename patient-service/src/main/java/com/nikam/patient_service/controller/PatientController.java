package com.nikam.patient_service.controller;

import com.nikam.patient_service.dto.PatientRequestDto;
import com.nikam.patient_service.dto.PatientResponseDto;
import com.nikam.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/get-all-patient")
    public ResponseEntity<List<PatientResponseDto>> getAllPatient(){
        return ResponseEntity.ok().body(this.patientService.getAllPatient());
    }

    @PostMapping("/create-patient")
    public ResponseEntity<PatientResponseDto> createNewPatient(@Valid @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.patientService.createNewPatient(patientRequestDto));
    }
}

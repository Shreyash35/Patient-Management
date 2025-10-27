package com.nikam.patient_service.controller;

import com.nikam.patient_service.dto.PatientRequestDto;
import com.nikam.patient_service.dto.PatientResponseDto;
import com.nikam.patient_service.dto.validations.CreatePatientValidationGroup;
import com.nikam.patient_service.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PatientResponseDto> createNewPatient(@Validated({Default.class, CreatePatientValidationGroup.class})
                                                                   @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.patientService.createNewPatient(patientRequestDto));
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.updatePatient(id, patientRequestDto));
    }
}

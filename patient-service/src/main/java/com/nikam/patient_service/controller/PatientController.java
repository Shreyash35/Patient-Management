package com.nikam.patient_service.controller;

import com.nikam.patient_service.dto.PatientRequestDto;
import com.nikam.patient_service.dto.PatientResponseDto;
import com.nikam.patient_service.dto.validations.CreatePatientValidationGroup;
import com.nikam.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Patient", description = "API's for managing patient info")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/get-all-patient")
    @Operation(summary = "Get All Patients Details")
    public ResponseEntity<List<PatientResponseDto>> getAllPatient(){
        return ResponseEntity.ok().body(this.patientService.getAllPatient());
    }

    @PostMapping("/create-patient")
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDto> createNewPatient(@Validated({Default.class, CreatePatientValidationGroup.class})
                                                                   @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.patientService.createNewPatient(patientRequestDto));
    }

    @PutMapping("/update-patient/{id}")
    @Operation(summary = "Update Patient Info")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.updatePatient(id, patientRequestDto));
    }

    @DeleteMapping("/patient-delete/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        this.patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

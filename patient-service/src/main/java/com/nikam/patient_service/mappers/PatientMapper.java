package com.nikam.patient_service.mappers;

import com.nikam.patient_service.dto.PatientRequestDto;
import com.nikam.patient_service.dto.PatientResponseDto;
import com.nikam.patient_service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDto toDto (Patient patient){
        PatientResponseDto patientResponseDTO = new PatientResponseDto();
        patientResponseDTO.setId(String.valueOf(patient.getId()));
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setDateOfBirth(String.valueOf(patient.getDateOfBirth()));
        return patientResponseDTO;
    }

    public static Patient toModel (PatientRequestDto patientRequestDto){
        Patient patient = new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisterDate(LocalDate.parse(patientRequestDto.getRegisterDate()));
        return patient;
    }
}

package com.nikam.patient_service.mappers;

import com.nikam.patient_service.dto.PatientDTO;
import com.nikam.patient_service.model.Patient;

public class PatientMapper {

    public static PatientDTO toDTO (Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(String.valueOf(patient.getId()));
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(String.valueOf(patient.getDateOfBirth()));
        return patientDTO;
    }
}

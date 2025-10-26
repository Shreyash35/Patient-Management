package com.nikam.patient_service.service;

import com.nikam.patient_service.dto.PatientRequestDto;
import com.nikam.patient_service.dto.PatientResponseDto;
import com.nikam.patient_service.exceptions.EmailAlreadyExistException;
import com.nikam.patient_service.mappers.PatientMapper;
import com.nikam.patient_service.model.Patient;
import com.nikam.patient_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponseDto> getAllPatient(){
        var allPatient = this.patientRepository.findAll();

        // Here stream().map() it works like for loop and for each patient we mapped it into dto.
        // map(patient -> PatientMapper.toDTO(patient)) this is inside the map function,
        // and we replaced Lambda function with method reference.
        // For just understanding I kept the patientDTOS variable otherwise we can directly return the method reference.

        List<PatientResponseDto> patientResponseDtos = allPatient.stream().map(PatientMapper::toDto).toList();
        return patientResponseDtos;
    }

    public PatientResponseDto createNewPatient(PatientRequestDto patientRequestDto){
        if (this.patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistException(patientRequestDto.getEmail() + " email already exists in database");
        }

        Patient patient = PatientMapper.toModel(patientRequestDto);
        this.patientRepository.save(patient);
        return PatientMapper.toDto(patient);
    }
}

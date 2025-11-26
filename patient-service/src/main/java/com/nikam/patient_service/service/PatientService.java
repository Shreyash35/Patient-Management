package com.nikam.patient_service.service;

import com.nikam.patient_service.dto.PatientRequestDto;
import com.nikam.patient_service.dto.PatientResponseDto;
import com.nikam.patient_service.exceptions.EmailAlreadyExistException;
import com.nikam.patient_service.exceptions.NoPatientFoundException;
import com.nikam.patient_service.grpc.BillingServiceGrpcClient;
import com.nikam.patient_service.mappers.PatientMapper;
import com.nikam.patient_service.model.Patient;
import com.nikam.patient_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingService;

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
        this.billingService.createBillingAccount(patient.getId().toString(), patient.getName(), patient.getEmail());
        return PatientMapper.toDto(patient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = this.patientRepository.findById(id).orElseThrow(() ->
                new NoPatientFoundException("There is no such a patient having patient id " + id));

        if (this.patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)){
            throw new EmailAlreadyExistException(patientRequestDto.getEmail() + " email already exists in database");
        }

        patient.setId(id);
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        this.patientRepository.save(patient);
        return PatientMapper.toDto(patient);
    }

    public void deletePatient(UUID id){
        this.patientRepository.deleteById(id);
    }
}

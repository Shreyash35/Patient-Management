package com.nikam.patient_service.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PatientResponseDto {

    private String id;
    private String name;
    private String address;
    private String email;
    private String dateOfBirth;
}

package uk.co.mycompany.healthservice.service;

import uk.co.mycompany.healthservice.domain.dto.DoctorDto;

import java.util.List;

public interface HealthService {

    List<DoctorDto> listAllDoctors();

    List<DoctorDto> listDoctorsByTitle(String specialty);

    List<DoctorDto> listDoctorsByTitleAndLocation(String title, Double latitude, Double longitude);

    List<DoctorDto> listDoctorsByTitleAndLocation(String title, String address);

    List<String> listAllDoctorsTitles();

}

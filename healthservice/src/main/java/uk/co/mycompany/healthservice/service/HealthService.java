package uk.co.mycompany.healthservice.service;

import uk.co.mycompany.healthservice.domain.dto.DoctorDto;

import java.util.List;

public interface HealthService {

    public List<DoctorDto> listAllDoctors();

    public List<DoctorDto> listDoctorsForSpecilty(String specialty);

    public List<DoctorDto> listDoctorsByTitleAndLocation(String title, Double latitude, Double longitude);

    public List<DoctorDto> listDoctorsByTitleAndLocation(String title, String address);

    public List<String> listAllDoctorsTitles();

}

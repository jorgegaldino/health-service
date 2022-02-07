package uk.co.mycompany.healthservice.handle;

import uk.co.mycompany.healthservice.domain.dto.DoctorDto;

import java.util.List;

public interface HealthServiceHandle {

    List<DoctorDto> listAll();

    List<DoctorDto> listByTitle(String title);

    List<DoctorDto> listByTitleAndLocation(String title, Double latitude, Double longitude);

    List<DoctorDto> listByTitleAndLocation(String title, String address);

    List<String> listAllDoctorsTitles();
}

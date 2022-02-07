package uk.co.mycompany.healthservice.handle;

import uk.co.mycompany.healthservice.domain.dto.DoctorDto;

import java.util.List;

public interface HeathServiceHandle {

    public List<DoctorDto> listAll();

    public List<DoctorDto> listForTitle(String specialty);

    public List<DoctorDto> listByTitleAndLocation(String title, Double latitude, Double longitude);

    public List<DoctorDto> listByTitleAndLocation(String title, String address);

    public List<String> listAllDoctorsTitles();
}

package uk.co.mycompany.healthservice.handle.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mycompany.healthservice.domain.DoctorModel;
import uk.co.mycompany.healthservice.domain.TitleModel;
import uk.co.mycompany.healthservice.domain.dto.DoctorDto;
import uk.co.mycompany.healthservice.exception.HealthServiceException;
import uk.co.mycompany.healthservice.handle.HealthServiceHandle;
import uk.co.mycompany.healthservice.mapper.DoctorMapper;
import uk.co.mycompany.healthservice.repository.DoctorRepository;
import uk.co.mycompany.healthservice.repository.DoctorTitleRepository;
import uk.co.mycompany.healthservice.util.LocationUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HealthServiceDefaultHandler implements HealthServiceHandle {

    @Autowired
    private LocationUtil locationUtil;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorTitleRepository doctorTitleRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    private static final String ALL = "all";

    @Override
    public List<DoctorDto> listAll() {
        List<DoctorModel> doctorModelList = doctorRepository.findAll();

        return doctorModelList.stream().map(
                doctorMapper::toDoctorDto).collect(Collectors.toList());

    }

    @Override
    public List<DoctorDto> listByTitle(String title) {
        List<DoctorModel> doctorModelList = doctorRepository.findByTitle(title);

        return doctorModelList.stream().map(
                doctorMapper::toDoctorDto).collect(Collectors.toList());

    }

    @Override
    public List<DoctorDto> listByTitleAndLocation(String title, Double latitude, Double longitude)
            throws HealthServiceException {
        List<DoctorModel> doctorModelList = doctorRepository.findByTitle(title);

        return doctorModelList.stream().map(doctorModel -> {
            DoctorDto doctorDto = doctorMapper.toDoctorDto(doctorModel);
            Double distance = locationUtil.calcDistance(latitude, longitude, doctorModel);
            doctorDto.setDistance(distance);
            return doctorDto;}).sorted(Comparator.comparingDouble(DoctorDto::getDistance))
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDto> listByTitleAndLocation(String title, String address)
            throws HealthServiceException {

        List<DoctorModel> doctorModelList;

        if (ALL.equals(title)){
            doctorModelList = doctorRepository.findAll();
        } else {
            doctorModelList = doctorRepository.findByTitle(title);
        }

        return doctorModelList.stream().map(doctorModel -> {
            DoctorDto doctorDto = doctorMapper.toDoctorDto(doctorModel);
            Double distance = locationUtil.calcDistance(address, doctorModel);
            doctorDto.setDistance(distance);
            return doctorDto;}).sorted(Comparator.comparingDouble(DoctorDto::getDistance))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listAllDoctorsTitles() {

        List<TitleModel> titleModels = doctorTitleRepository.findAll();

        return titleModels.stream().map(TitleModel::getDescription).sorted().collect(Collectors.toList());

    }
}

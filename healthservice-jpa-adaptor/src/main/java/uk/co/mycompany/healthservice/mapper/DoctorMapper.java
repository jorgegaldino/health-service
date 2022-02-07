package uk.co.mycompany.healthservice.mapper;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import uk.co.mycompany.healthservice.domain.DoctorModel;
import uk.co.mycompany.healthservice.domain.dto.DoctorDto;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class DoctorMapper {

    /**
     * Convert a {@link DoctorModel} to a {@link DoctorDto}.
     *
     * @param doctorModel The Doctor data layer model.
     * @return The Doctor DTO.
     */
    public DoctorDto toSubscription(DoctorModel doctorModel) {
        if (doctorModel == null) {
            return null;
        }
        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setName(doctorModel.getName());

        doctorDto.setAddress(doctorModel.getAddress());

        doctorDto.setTitle(doctorModel.getTitle().getDescription());

        return doctorDto;
    }


}

package uk.co.mycompany.healthservice.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertNull;

public class DoctorMapperTests {

    DoctorMapper doctorMapper = Mappers.getMapper(DoctorMapper.class);


    @Test
    public void toDoctorDto_WhenDoctorModelProvidedIsNull_ShouldReturnNull() {
        assertNull(doctorMapper.toDoctorDto(null));
    }
}

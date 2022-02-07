package uk.co.mycompany.healthservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import uk.co.mycompany.healthservice.domain.DoctorModel;

import java.util.List;

public interface DoctorRepository extends
        JpaRepository<DoctorModel, Long>,
        JpaSpecificationExecutor<DoctorModel> {

    @Query("select od from DoctorModel od where od.title.description like ?1%")
    List<DoctorModel> findByTitle(String title);
}


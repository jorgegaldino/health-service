package uk.co.mycompany.healthservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import uk.co.mycompany.healthservice.domain.DoctorModel;
import uk.co.mycompany.healthservice.domain.TitleModel;

import java.util.List;

public interface DoctorTitleRepository extends
        JpaRepository<TitleModel, Long>,
        JpaSpecificationExecutor<TitleModel> {

}


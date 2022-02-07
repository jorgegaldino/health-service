package uk.co.mycompany.healthservice.domain;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "title")
@Entity
@Data
public class TitleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @Setter
    private List<DoctorModel> doctors;

}

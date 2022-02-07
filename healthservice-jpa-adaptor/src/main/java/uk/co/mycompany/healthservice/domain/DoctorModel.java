package uk.co.mycompany.healthservice.domain;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "doctor")
@Entity
@Data
public class DoctorModel implements Serializable {

    private static final long serialVersionUID = -4397106574089376359L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String telephone;

    private Double longitude;

    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    private TitleModel title;
}

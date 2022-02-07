package uk.co.mycompany.healthservice.strategy;

import uk.co.mycompany.healthservice.handle.HealthServiceHandle;

import java.util.Optional;

public interface HealthServiceStrategy {

    Optional<HealthServiceHandle> findSearchDoctorsHandler(String title);

    Optional<HealthServiceHandle> findSearchDoctorsHandler(String title, Double latitude, Double longitude);

    Optional<HealthServiceHandle> findSearchDoctorsHandler(String title, String address);

    Optional<HealthServiceHandle> findSearchDoctorsHandler();

    Optional<HealthServiceHandle> findSearchDoctorsTitlesHandler();
}

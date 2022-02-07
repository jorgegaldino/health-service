package uk.co.mycompany.healthservice.strategy;

import uk.co.mycompany.healthservice.handle.HeathServiceHandle;

import java.util.Optional;

public interface HealthServiceStrategy {

    Optional<HeathServiceHandle> findSearchDoctorsHandler(String title);

    Optional<HeathServiceHandle> findSearchDoctorsHandler(String title, Double latitude, Double longitude);

    Optional<HeathServiceHandle> findSearchDoctorsHandler(String title, String address);

    Optional<HeathServiceHandle> findSearchDoctorsHandler();

}

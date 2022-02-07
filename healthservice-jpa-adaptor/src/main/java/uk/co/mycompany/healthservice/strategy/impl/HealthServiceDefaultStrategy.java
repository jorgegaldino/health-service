package uk.co.mycompany.healthservice.strategy.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.co.mycompany.healthservice.handle.HealthServiceHandle;
import uk.co.mycompany.healthservice.handle.impl.HealthServiceDefaultHandler;
import uk.co.mycompany.healthservice.strategy.HealthServiceStrategy;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class HealthServiceDefaultStrategy implements HealthServiceStrategy {

    private final HealthServiceDefaultHandler healthServiceDefaultHandler;


    @Override
    public Optional<HealthServiceHandle> findSearchDoctorsHandler(String specialty) {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HealthServiceHandle> findSearchDoctorsHandler(String title, Double latitude, Double longitude) {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HealthServiceHandle> findSearchDoctorsHandler(String title, String address) {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HealthServiceHandle> findSearchDoctorsHandler() {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HealthServiceHandle> findSearchDoctorsTitlesHandler() {
        return Optional.of(healthServiceDefaultHandler);
    }
}

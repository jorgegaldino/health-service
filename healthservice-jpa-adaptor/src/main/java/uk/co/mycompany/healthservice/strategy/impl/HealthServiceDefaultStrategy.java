package uk.co.mycompany.healthservice.strategy.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uk.co.mycompany.healthservice.handle.HeathServiceHandle;
import uk.co.mycompany.healthservice.handle.impl.HealthServiceDefaultHandler;
import uk.co.mycompany.healthservice.strategy.HealthServiceStrategy;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class HealthServiceDefaultStrategy implements HealthServiceStrategy {

    private final HealthServiceDefaultHandler healthServiceDefaultHandler;


    @Override
    public Optional<HeathServiceHandle> findSearchDoctorsHandler(String specialty) {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HeathServiceHandle> findSearchDoctorsHandler(String title, Double latitude, Double longitude) {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HeathServiceHandle> findSearchDoctorsHandler(String title, String address) {
        return Optional.of(healthServiceDefaultHandler);
    }

    @Override
    public Optional<HeathServiceHandle> findSearchDoctorsHandler() {
        return Optional.of(healthServiceDefaultHandler);
    }
}

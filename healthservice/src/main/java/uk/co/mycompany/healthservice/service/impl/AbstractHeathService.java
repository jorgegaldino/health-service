package uk.co.mycompany.healthservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import uk.co.mycompany.healthservice.domain.dto.DoctorDto;
import uk.co.mycompany.healthservice.exception.HealthServiceException;
import uk.co.mycompany.healthservice.handle.HealthServiceHandle;
import uk.co.mycompany.healthservice.service.HealthService;
import uk.co.mycompany.healthservice.strategy.HealthServiceStrategy;

import java.util.List;

@Slf4j
public abstract class AbstractHeathService implements HealthService {

    private HealthServiceStrategy strategy;

        /**
     * Adds a Strategy to the list of applied Strategies.
     *
     * @param healthServiceStrategy the strategy to add
     */
    public void addStrategy(final HealthServiceStrategy healthServiceStrategy) {
        strategy = healthServiceStrategy;
    }




    @Override
    public List<DoctorDto> listDoctorsByTitle(String title) {
        HealthServiceHandle handler =
            strategy.findSearchDoctorsHandler(title)
                .orElseThrow(() -> new HealthServiceException("handler_not_found",
                    "Handler not found request."));

        return handler.listByTitle(title);
    }

    @Override
    public List<DoctorDto> listAllDoctors() {
        HealthServiceHandle handler =
                strategy.findSearchDoctorsHandler()
                        .orElseThrow(() -> new HealthServiceException("handler_not_found",
                                "Handler not found request."));

        return handler.listAll();
    }

    @Override
    public List<DoctorDto> listDoctorsByTitleAndLocation(String title, Double latitude, Double longitude) {
        HealthServiceHandle handler =
                strategy.findSearchDoctorsHandler(title, latitude, longitude)
                        .orElseThrow(() -> new HealthServiceException("handler_not_found",
                                "Handler not found request."));

        return handler.listByTitleAndLocation(title, latitude, longitude);
    }

    @Override
    public List<DoctorDto> listDoctorsByTitleAndLocation(String title, String address) {
        HealthServiceHandle handler =
                strategy.findSearchDoctorsHandler(title, address)
                        .orElseThrow(() -> new HealthServiceException("handler_not_found",
                                "Handler not found request."));

        return handler.listByTitleAndLocation(title, address);
    }

    @Override
    public List<String> listAllDoctorsTitles() {
        HealthServiceHandle handler =
                strategy.findSearchDoctorsTitlesHandler()
                        .orElseThrow(() -> new HealthServiceException("handler_not_found",
                                "Handler not found request."));

        return handler.listAllDoctorsTitles();
    }


}

package com.hitachi.api;

import org.springframework.stereotype.Service;
import java.util.List;

import java.util.stream.Collectors;

/**
 *
 * @author Jhersey Cruz
 */

@Service
public interface DroneMedicationService {

    Drone loadDroneWithMedications(Long droneId, List<Long> medicationIds);

    List<Medication> getLoadedMedications(Long droneId);

    List<Drone> getAvailableDrones();

    int getDroneBatteryLevel(Long droneId);

    void completeDelivery(Long droneId);


}

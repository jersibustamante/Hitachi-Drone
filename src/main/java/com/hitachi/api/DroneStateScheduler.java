package com.hitachi.api;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jhersey Cruz
 */
@Service
public class DroneStateScheduler {

    private final DroneRepository droneRepository;

    public DroneStateScheduler(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void checkDroneStates() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            if (drone.getState() == Drone.State.DELIVERED) {
                // If drone is delivered and battery is above 25%, transition to IDLE
                if (drone.getBatteryCapacity() >= 25) {
                    drone.setState(Drone.State.IDLE);
                    droneRepository.save(drone);
                }
            }
        }
    }
}
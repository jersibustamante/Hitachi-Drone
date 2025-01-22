package com.hitachi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jhersey Cruz
 */
@Service
public class DroneServiceImpl implements DroneService {
    private static final Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Drone registerDrone(Drone drone) {
        // Validate serial number
        if (drone.getSerial() == null || drone.getSerial().isEmpty()) {
            throw new IllegalArgumentException("Serial number cannot be null or empty.");
        }

        // Check for duplicate serial numbers
        if (droneRepository.findAll().stream().anyMatch(d -> d.getSerial().equals(drone.getSerial()))) {
            throw new IllegalArgumentException("A api with the same serial number already exists.");
        }

        // Validate weight limit
        if (drone.getWeightLimit() > 1000 || drone.getWeightLimit() <= 0) {
            throw new IllegalArgumentException("Weight limit must be between 1 and 1000 grams.");
        }

        // Default to IDLE state if not set
        if (drone.getState() == null) {
            drone.setState(Drone.State.IDLE);
        }

        // Save api to the repository
        return droneRepository.save(drone);

    }

    @Override
    public List<Drone> getAllDrones() {
        // Fetch all drones from the H2 database
        return droneRepository.findAll();
    }

    @Override
    public Drone getDroneById(Long id) {
        // Get Specific drone by id
        return droneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drone with ID " + id + " not found."));
    }

    @Override
    public Drone updateDrone(Long id, Drone updatedDrone) {
        return droneRepository.findById(id)
                .map(existingDrone -> {
                    // Update serial number if provided
                    if (updatedDrone.getSerial() != null) {
                        existingDrone.setSerial(updatedDrone.getSerial());
                    }
                    // Update model if provided
                    if (updatedDrone.getModel() != null) {
                        existingDrone.setModel(updatedDrone.getModel());
                        // No need to manually set weight limit, it will be handled by getWeightLimit()
                    }
                    // Update battery capacity if provided
                    if (updatedDrone.getBatteryCapacity() != null) {
                        existingDrone.setBatteryCapacity(updatedDrone.getBatteryCapacity());
                    }
                    // Update state if provided
                    if (updatedDrone.getState() != null) {
                        existingDrone.setState(updatedDrone.getState());
                    }
                    // Save the updated drone
                    return droneRepository.save(existingDrone);
                })
                .orElseThrow(() -> new IllegalArgumentException("Drone with ID " + id + " not found."));
    }


    //Delete Drone using id
    @Override
    public void deleteDrone(Long id) {
        if (!droneRepository.existsById(id)) {
            throw new IllegalArgumentException("Drone with ID " + id + " not found.");
        }
        droneRepository.deleteById(id);
    }


}

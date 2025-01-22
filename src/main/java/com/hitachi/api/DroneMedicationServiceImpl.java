package com.hitachi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jhersey Cruz
 */
@Service
public class DroneMedicationServiceImpl implements DroneMedicationService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    // 1. Loading drone with medications
    @Override
    public Drone loadDroneWithMedications(Long droneId, List<Long> medicationIds) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found"));

        // Prevent loading if battery is below 25%
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone battery is too low to load medications.");
        }

        // Ensure the drone is in IDLE state before loading medications
        if (drone.getState() != Drone.State.IDLE) {
            throw new IllegalArgumentException("Drone is not available for loading. Current state: " + drone.getState());
        }

        // Validate and fetch medications
        List<Medication> medications = medicationRepository.findAllById(medicationIds);
        double totalWeight = medications.stream().mapToDouble(Medication::getWeight).sum();

        if (totalWeight > drone.getWeightLimit()) {
            System.out.println("Total Weight " + totalWeight);
            System.out.println("Drone Weight Limit " + drone.getWeightLimit());
            throw new IllegalArgumentException("Medications exceed weight limit.");
        }

        // Set the medications list on the drone
        drone.setMedications(medications);

        // Ensure each medication is associated with this drone
        for (Medication medication : medications) {
            medication.setDrone(drone); // Assuming Medication has a drone property with @ManyToOne
        }

        // Update the drone state to LOADING
        drone.setState(Drone.State.LOADING);

        // Save the drone with the medications
        drone = droneRepository.save(drone);

        // Once medications are loaded, update the state to LOADED
        drone.setState(Drone.State.LOADED);

        // Save the updated drone state
        return droneRepository.save(drone);
    }

    // 2. Checking loaded medications for a drone
    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found"));


        if (drone.getState() != Drone.State.LOADED) {
            throw new IllegalStateException("Drone is not loaded.");
        }

        return drone.getMedications();
    }

    // 3. Checking drone availability for loading
    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.findAll().stream()
                .filter(drone -> drone.getState() == Drone.State.IDLE && drone.getBatteryCapacity() >= 25)
                .collect(Collectors.toList());
    }

    // 4. Checking drone battery level
    @Override
    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found"));
        return drone.getBatteryCapacity();
    }

    // Method to complete the delivery and update battery and drone state
    @Override
    public void completeDelivery(Long droneId) {
        Drone drone = getDroneById(droneId);
        int batteryLevel = drone.getBatteryCapacity();

        // Reduce battery by 5% after delivery
        if (batteryLevel - 5 >= 0) {
            drone.setBatteryCapacity(batteryLevel - 5);
        } else {
            drone.setBatteryCapacity(0);
        }

        // Update drone state to 'DELIVERED'
        drone.setState(Drone.State.DELIVERED);
        updateDrone(drone);
    }

    // Helper methods
    private Drone getDroneById(Long droneId) {
        return droneRepository.findById(droneId)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found"));
    }

    private void updateDrone(Drone drone) {
        droneRepository.save(drone);
    }
}

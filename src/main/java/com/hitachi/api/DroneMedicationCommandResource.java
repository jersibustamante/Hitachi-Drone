package com.hitachi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jhersey Cruz
 */
@RestController
@RequestMapping("/droneMedication")
public class DroneMedicationCommandResource {

    @Autowired
    DroneMedicationService droneMedicationService;

    // 1. Load a drone with medications
    @PostMapping("/{droneId}/load")
    public ResponseEntity<Drone> loadDrone(@PathVariable Long droneId, @RequestBody List<Long> medicationIds) {
        Drone drone = droneMedicationService.loadDroneWithMedications(droneId, medicationIds);
        return ResponseEntity.ok(drone);
    }

    // 2. Get medications loaded onto a drone
    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable Long droneId) {
        List<Medication> medications = droneMedicationService.getLoadedMedications(droneId);
        return ResponseEntity.ok(medications);
    }

    // 3. Check available drones for loading
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        List<Drone> availableDrones = droneMedicationService.getAvailableDrones();
        return ResponseEntity.ok(availableDrones);
    }

    // 4. Check drone battery
    @GetMapping("/{droneId}/battery")
    public ResponseEntity<Integer> getDroneBattery(@PathVariable Long droneId) {
        int batteryLevel = droneMedicationService.getDroneBatteryLevel(droneId);
        return ResponseEntity.ok(batteryLevel);
    }

    @PostMapping("/{droneId}/complete-delivery")
    public ResponseEntity<String> completeDelivery(@PathVariable Long droneId) {
        try {
            droneMedicationService.completeDelivery(droneId);
            return ResponseEntity.ok("Delivery completed successfully and battery reduced.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Drone not found: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.ok("Error completing delivery: " + e.getMessage());
        }
    }

}


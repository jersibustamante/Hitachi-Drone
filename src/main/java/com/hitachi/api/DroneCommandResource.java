package com.hitachi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jhersey Cruz
 */
@RestController
@RequestMapping("/drone")
public class DroneCommandResource {

    @Autowired
    DroneService droneService;

    @PostMapping
    public ResponseEntity<RestMessage> registerDrone(@RequestBody Drone drone) {
        Drone registeredDrone = droneService.registerDrone(drone);

        RestMessage restMessage = new RestMessage("Drone registered successfully", registeredDrone);
        return ResponseEntity.status(HttpStatus.CREATED).body(restMessage);
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        List<Drone> drones = droneService.getAllDrones();
        return ResponseEntity.ok(drones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable Long id) {
        Drone drone = droneService.getDroneById(id);
        return ResponseEntity.ok(drone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable Long id, @RequestBody Drone drone) {
        Drone updatedDrone = droneService.updateDrone(id, drone);
        return ResponseEntity.ok(updatedDrone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable Long id) {
        droneService.deleteDrone(id);
        return ResponseEntity.noContent().build();
    }
}


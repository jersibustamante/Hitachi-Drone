package com.hitachi.api;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jhersey Cruz
 */
@Service
public interface DroneService {
    //Register Drone
    Drone registerDrone(Drone drone);

    //Get All Drones
    List<Drone> getAllDrones();

    //Get Specific Drone by id
    Drone getDroneById(Long id);

    //Update specific drone details using id
    Drone updateDrone(Long id, Drone drone);

    //Delete Drone using id
    void deleteDrone(Long id);

}

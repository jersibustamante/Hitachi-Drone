package com.hitachi.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

}

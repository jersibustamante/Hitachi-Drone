package com.hitachi.api;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Jhersey Cruz
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ktp_drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_id")
    private Long id;

    @Column(name = "drone_number", nullable = false, unique = true, length = 100)
    private String serial;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_model", nullable = false)
    private Model model;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_state", nullable = false)
    private State state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<Medication> medications;

    // Enum for Drone Model
    public enum Model {
        LIGHTWEIGHT(500),
        MIDDLEWEIGHT(700),
        CRUISERWEIGHT(800),
        HEAVYWEIGHT(1000);

        private final int weightLimit;

        Model(int weightLimit) {
            this.weightLimit = weightLimit;
        }

        public int getWeightLimit() {
            return weightLimit;
        }
    }

    // Enum for Drone States
    public enum State {
        IDLE,
        LOADING,
        LOADED,
        DELIVERING,
        DELIVERED,
        RETURNING
    }

    public Double getWeightLimit() {
        return (double) this.model.getWeightLimit();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}

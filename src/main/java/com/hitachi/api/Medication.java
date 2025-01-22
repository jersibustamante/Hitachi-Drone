package com.hitachi.api;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Jhersey Cruz
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ktp_medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "image")
    private String image;

    // ManyToOne relationship to Drone
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false) // Foreign key column
    private Drone drone;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}

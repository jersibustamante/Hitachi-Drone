package com.hitachi.api;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.List;

/**
 * @author Jhersey Cruz
 */
@Service
public class MedicationServiceImpl implements MedicationService {
    private static final Logger logger = LoggerFactory.getLogger(MedicationServiceImpl.class);

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Medication registerMedication(Medication meds) {
        // Validate Medication details
        validateMedication(meds);

        // Ensure the Drone exists
        if (meds.getDrone() == null || meds.getDrone().getId() == null) {
            throw new IllegalArgumentException("Drone cannot be null");
        }

        // Optionally: Retrieve the drone from the database if only the id is provided
        Drone drone = droneRepository.findById(meds.getDrone().getId()).orElseThrow(() -> new IllegalArgumentException("Drone not found"));
        meds.setDrone(drone);

        return medicationRepository.save(meds);
    }


    @Override
    public List<Medication> getAllMedications() {
        // Get All Medications
        return medicationRepository.findAll();
    }

    @Override
    public Medication getMedicationById(Long id) {
        // Get Specific medication by id
        return medicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medication with ID " + id + " not found."));
    }

    //Update specific medication by id
    @Override
    public Medication updateMedication(Long id, Medication medication) {
        return medicationRepository.findById(id)
                .map(existingMedication -> {
                    if (medication.getName() != null) {
                        validateName(medication.getName());
                        existingMedication.setName(medication.getName());
                    }
                    if (medication.getWeight() != null) {
                        existingMedication.setWeight(medication.getWeight());
                    }
                    if (medication.getCode() != null) {
                        validateCode(medication.getCode());
                        existingMedication.setCode(medication.getCode());
                    }
                    if (medication.getImage() != null) {
                        existingMedication.setImage(medication.getImage());
                    }
                    return medicationRepository.save(existingMedication);
                })
                .orElseThrow(() -> new IllegalArgumentException("Medication with ID " + id + " not found."));
    }

    //Delete Medication by id
    @Override
    public void deleteMedication(Long id) {
        if (!medicationRepository.existsById(id)) {
            throw new IllegalArgumentException("Medication with ID " + id + " not found.");
        }
        medicationRepository.deleteById(id);
    }

    // Helper Method
    private void validateMedication(Medication medication) {
        validateName(medication.getName());
        validateCode(medication.getCode());
    }

    private void validateName(String name) {
        if (!name.matches("[a-zA-Z0-9-_]+")) {
            throw new IllegalArgumentException("Medication name can only contain letters, numbers, '-', and '_'.");
        }
    }

    private void validateCode(String code) {
        if (!code.matches("[A-Z0-9_]+")) {
            throw new IllegalArgumentException("Medication code can only contain uppercase letters, numbers, and '_'.");
        }
    }


}

package com.hitachi.api;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jhersey Cruz
 */
@Service
public interface MedicationService {
    // Register Medication
    Medication registerMedication(Medication meds);

    // Get All Medications
    List<Medication> getAllMedications();

    // Get Specific Medication
    Medication getMedicationById(Long id);

    // Update specific medication data by id
    Medication updateMedication(Long id, Medication medication);

    // Delete Medication by id
    void deleteMedication(Long id);

}

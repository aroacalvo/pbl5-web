package edu.mondragon.we2.pinkAlert.model;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosisTest {

    @Test
    void testGettersAndSetters() {
        Diagnosis diagnosis = new Diagnosis();

        Doctor doctor = new Doctor("Dr Test");
        Patient patient = new Patient("Patient Test", LocalDate.of(2000, 1, 1));

        diagnosis.setId(1);
        diagnosis.setImagePath("image.jpg");
        diagnosis.setDate(LocalDate.now());
        diagnosis.setDescription("Test description");
        diagnosis.setUrgent(true);
        diagnosis.setReviewed(true);
        diagnosis.setDoctor(doctor);
        diagnosis.setPatient(patient);

        assertEquals(1, diagnosis.getId());
        assertEquals("image.jpg", diagnosis.getImagePath());
        assertEquals("Test description", diagnosis.getDescription());
        assertTrue(diagnosis.isUrgent());
        assertTrue(diagnosis.isReviewed());
        assertEquals(doctor, diagnosis.getDoctor());
        assertEquals(patient, diagnosis.getPatient());
    }

    @Test
    void testStatusPositive() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDescription("Cancer grade II detected");

        assertEquals("Positive", diagnosis.getStatus());
    }

    @Test
    void testStatusNegative() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDescription("False positive screening");

        assertEquals("Negative", diagnosis.getStatus());
    }

    @Test
    void testStatusPending() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDescription("Unclear image");

        assertEquals("Pending", diagnosis.getStatus());
    }

    @Test
    void testStatusWhenDescriptionIsNull() {
        Diagnosis diagnosis = new Diagnosis();

        assertEquals("Pending", diagnosis.getStatus());
    }
}

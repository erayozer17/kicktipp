package com.erayozer.kicktipp.controller;

import com.erayozer.kicktipp.model.Patient;
import com.erayozer.kicktipp.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class PatientController {

    @Autowired
    CrudService patientService;

    @GetMapping("/getPatientDetails")
    public Patient getPatient(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return patientService.getPatientDetails(name);
    }

    @PostMapping("/createPatient")
    public String createPatient(@RequestBody Patient patient ) throws InterruptedException, ExecutionException {
        return patientService.savePatientDetails(patient);
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient patient  ) throws InterruptedException, ExecutionException {
        return patientService.updatePatientDetails(patient);
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam String name){
        return patientService.deletePatient(name);
    }
}
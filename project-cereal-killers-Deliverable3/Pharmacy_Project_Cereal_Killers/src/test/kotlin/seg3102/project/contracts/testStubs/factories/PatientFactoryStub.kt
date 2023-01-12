package seg3102.project.contracts.testStubs.factories

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.patient.factories.PatientFactory


class PatientFactoryStub : PatientFactory {
    override fun createPatient(patientInfo: PatientCreateDto): Patient {
        return Patient(patientInfo.identificationNumber,
            patientInfo.firstName,
            patientInfo.lastName,
            patientInfo.address,
            patientInfo.dateOfBirth,
            patientInfo.languagePreference,
            patientInfo.drugAllergies,
            patientInfo.currentMedications,
            patientInfo.insuranceNumber,
            patientInfo.myPrescriptions)
    }
}
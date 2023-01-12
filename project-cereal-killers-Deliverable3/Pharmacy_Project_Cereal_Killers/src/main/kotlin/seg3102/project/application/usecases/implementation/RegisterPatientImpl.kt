package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.usecases.RegisterPatient
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.patient.facade.PatientFacade

class RegisterPatientImpl  (
    private var patientFacade: PatientFacade
): RegisterPatient{
    override fun registerPatient(patientinfo: PatientCreateDto):Boolean{

        return patientFacade.createPatient(patientinfo)
    }
}
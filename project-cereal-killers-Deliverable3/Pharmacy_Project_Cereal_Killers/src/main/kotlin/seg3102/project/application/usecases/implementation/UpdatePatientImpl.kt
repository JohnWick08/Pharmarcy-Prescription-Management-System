package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.usecases.UpdatePatient
import seg3102.project.domain.patient.facade.PatientFacade

class UpdatePatientImpl (
    var patient:PatientFacade
        ): UpdatePatient {
    override fun updatePatient(patientID: String, patientInfo: PatientCreateDto):Boolean{
        return patient.updatePatient(patientID,patientInfo);
    }
}
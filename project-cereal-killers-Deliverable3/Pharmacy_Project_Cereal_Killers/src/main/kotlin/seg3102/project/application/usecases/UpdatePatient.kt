package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PatientCreateDto

interface UpdatePatient {
    fun updatePatient(patientID: String, patientInfo: PatientCreateDto):Boolean
}
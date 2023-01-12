package seg3102.project.application.dto.queries

import java.util.*
class PatientCreateDto(
    val identificationNumber:String,
    val firstName: String,
    val lastName: String,
    val address:String,
    val dateOfBirth: String,
    val languagePreference: String,
    val drugAllergies:String,
    val currentMedications: String,
    val insuranceNumber: String,
    var myPrescriptions: MutableList<PrescriptionCreateDto>
    ){

}
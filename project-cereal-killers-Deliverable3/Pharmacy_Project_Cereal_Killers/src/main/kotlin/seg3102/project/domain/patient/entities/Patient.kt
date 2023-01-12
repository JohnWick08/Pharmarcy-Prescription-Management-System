package seg3102.project.domain.patient.entities
import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto
import java.util.*

class Patient (val identificationNumber:String,
               var firstName: String,
               var lastName: String,
               var address:String,
               val dateOfBirth: String,
               val languagePreference: String,
               var drugAllergies:String,
               var currentMedications: String,
               var insuranceNumber: String,
               var myPrescriptions: MutableList<PrescriptionCreateDto>
              ){

    fun addMyPrescriptions(prescription:PrescriptionCreateDto) {
        myPrescriptions.add(prescription)
    }

    fun update(updated: Patient) {
        firstName = updated.firstName
        lastName = updated.lastName
        address = updated.address
        drugAllergies = updated.drugAllergies
        currentMedications = updated.currentMedications
        insuranceNumber = updated.insuranceNumber
        myPrescriptions=updated.myPrescriptions

    }
    /*
        This method takes a PatientCreateDto as parameter and update its information
        according to the information in the dto
     */
    fun update(updated: PatientCreateDto) {
        firstName = updated.firstName
        lastName = updated.lastName
        address = updated.address
        drugAllergies = updated.drugAllergies
        currentMedications = updated.currentMedications
        insuranceNumber = updated.insuranceNumber
        myPrescriptions=updated.myPrescriptions

    }
}

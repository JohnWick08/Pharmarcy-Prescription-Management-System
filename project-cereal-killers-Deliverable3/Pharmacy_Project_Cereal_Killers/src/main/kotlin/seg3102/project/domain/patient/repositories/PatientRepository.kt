package seg3102.project.domain.patient.repositories
import seg3102.project.domain.patient.entities.Patient
import java.util.*


interface PatientRepository {
    fun save(patient: Patient): Patient
    fun find(identificationNumber: String): Patient?
}
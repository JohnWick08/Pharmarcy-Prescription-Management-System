package seg3102.project.contracts.testStubs.repositories
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.patient.repositories.PatientRepository
import java.util.*

class PatientRepositoryStub : PatientRepository {
    private val patients: MutableMap<String, Patient> = HashMap()
    override fun save(patient: Patient): Patient {
        patients[patient.identificationNumber] = patient
        return patient
    }
    override fun find(identificationNumber: String):Patient?=patients[identificationNumber]

}
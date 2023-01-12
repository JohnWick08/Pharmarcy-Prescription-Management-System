package seg3102.project.domain.patient.facade.implementation

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.application.services.DomainEventEmitter
import seg3102.project.domain.patient.events.NewPatient
import seg3102.project.domain.patient.events.PatientUpdated
import seg3102.project.domain.patient.events.PrescriptionAdded
import seg3102.project.domain.patient.facade.PatientFacade
import seg3102.project.domain.patient.factories.PatientFactory
import seg3102.project.domain.patient.repositories.PatientRepository
import java.util.*

class PatientFacadeImpl(private val patientRepository: PatientRepository,
                        private val patientFactory: PatientFactory,
                        private var eventEmitter: DomainEventEmitter
): PatientFacade {

    override fun createPatient(patientInfo: PatientCreateDto): Boolean {
        val patientId = patientInfo.identificationNumber
        val existPatient = patientRepository.find(patientId)
        if (existPatient != null) {
            return false
        }
        val newPatient = patientFactory.createPatient(patientInfo)


        patientRepository.save(newPatient)
        eventEmitter.emit(
            NewPatient(
                patientInfo.identificationNumber,
                Date()
            )
        )
        return true
    }

    override fun addToPatient(patientID: String, prescription: PrescriptionCreateDto) {
        val existPatient = patientRepository.find(patientID)
        if (existPatient != null) {
            existPatient.addMyPrescriptions(prescription)
            patientRepository.save(existPatient)
            eventEmitter.emit(
                PrescriptionAdded(
                    patientID,
                    Date())
            )
        }
    }

    override fun updatePatient(patientID: String, patientInfo: PatientCreateDto): Boolean {
        val existPatient = patientRepository.find(patientID)
        if (existPatient != null) {
            val updated = patientFactory.createPatient(patientInfo)
            existPatient.update(updated)
            patientRepository.save(existPatient)
            eventEmitter.emit(
                PatientUpdated(
                    patientID,
                    Date())
            )
            return true
        }
        return false
    }


}

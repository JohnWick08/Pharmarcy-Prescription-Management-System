package seg3102.project.domain.prescription.facade.implementation


import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.services.DomainEventEmitter
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.prescription.events.PrescriptionFillSummaryUpdated
import seg3102.project.domain.prescription.facade.PrescriptionFacade
import seg3102.project.domain.prescription.factories.PrescriptionFactory
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.prescription.repositories.PrescriptionRepository
import seg3102.project.domain.prescription.services.CpsoCheckValid
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.patient.facade.PatientFacade
import seg3102.project.domain.user.entities.User
import seg3102.project.domain.user.facade.UserFacade
import java.util.*

class PrescriptionFacadeImpl
    (private var prescriptionFactory: PrescriptionFactory,
     private var prescriptionFillRepository: PrescriptionFillRepository,
     private var prescriptionRepository: PrescriptionRepository,
     private var eventEmitter: DomainEventEmitter,
    ) : PrescriptionFacade {
//    override fun createPrescription(patientInfo: PatientCreateDto, prescriberInfo: PrescriberCreateDto, medicine: MedicineCreateDto,
//        methodOfAdministration: String, frequencyOfAdministration: String, optionalConsideration: String,
//        ) {
//        var pvalid = userFacade.confirmPrescriber(prescriberInfo)
//        if (pvalid) {
//            var valid = medicineRepository.find()
//        }
//    }



    override fun recordSummary(prescriptionFill: PrescriptionFill, summary: String): Boolean {
        var pre = prescriptionFillRepository.find(prescriptionFill.id)
        if (pre != null) {
            pre.updateSummary(summary)
            prescriptionFillRepository.save(pre)
            eventEmitter.emit(
                PrescriptionFillSummaryUpdated(
                    UUID.randomUUID(),
                    Date(),
                    pre.id
                )
            )
            return true
        }
        return false
    }

    override fun setPrescriptionStatus(prescriptionFill: Prescription, pickedUp: String): Boolean {
        var pre = prescriptionFillRepository.find(prescriptionFill.id)
        if (pre != null) {
            pre.updateStatus(pickedUp)
            prescriptionFillRepository.save(pre)
            eventEmitter.emit(
                PrescriptionFillSummaryUpdated(
                    UUID.randomUUID(),
                    Date(),
                    pre.id
                )
            )
            return true
        }
        return false
    }
}
package seg3102.project.domain.user.facade

import seg3102.project.application.dto.queries.*
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.patient.repositories.PatientRepository
import seg3102.project.domain.prescription.factories.PrescriptionFactory
import seg3102.project.domain.prescription.factories.PrescriptionFillFactory
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.prescription.repositories.PrescriptionRepository
import java.util.*

interface UserFacade {
    fun registerAgent(info:PrescriberCreateDto):Boolean
    fun registerAgent(info:PharmacistCreateDto):Boolean
    fun updateAgent(uid: UUID, info:PrescriberCreateDto):Boolean
    fun updateAgent(uid: UUID, info:PharmacistCreateDto):Boolean
    fun removeAgent(uid:UUID):Boolean
    fun confirmPrescriber(info:PrescriberCreateDto):Boolean
    fun confirmPresribtion(prescription:PrescriptionCreateDto):Boolean


    /*
        An agent finalizes the preparation by printing off the drug
        information and counselling documents to be provided to the
        patient, and contacts the patient to inform that the medicine is
        ready for pick-up
     */
    fun ifFinializesThePreparation(patientInfo:PatientCreateDto, pf:PrescriptionFillCreateDto):Boolean

    //call the function to set the verified variable in prescriptionfill to be true
    fun checkIfValidPrescription(patientInfo:PatientCreateDto, pf:PrescriptionFillCreateDto):Boolean

    fun createPrescription(prescriptionInfo:PrescriptionCreateDto):Boolean
    fun preparePrescriptionFill(id:UUID,prescriptionFillInfo: PrescriptionFillCreateDto,):Boolean
}
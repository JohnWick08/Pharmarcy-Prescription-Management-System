package seg3102.project.domain.user.facade.implementation

import seg3102.project.application.dto.queries.*
import seg3102.project.application.services.DomainEventEmitter
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.prescription.factories.PrescriptionFactory
import seg3102.project.domain.prescription.factories.PrescriptionFillFactory
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.prescription.repositories.PrescriptionRepository
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.user.events.DeleteComplete
import seg3102.project.domain.user.events.EmitAccountUpdate
import seg3102.project.domain.user.events.EmitNewAgent
import seg3102.project.domain.user.facade.UserFacade
import seg3102.project.domain.user.factories.AccountFactory
import seg3102.project.domain.user.repositories.AccountRepository
import seg3102.project.domain.prescription.services.CpsoCheckValid
import java.util.*


class UserFacadeImpl (
    private val cpsoCheckValid:CpsoCheckValid,
    private val accountRepository:AccountRepository,
    private val accountFactory:AccountFactory,
    private var eventEmitter: DomainEventEmitter,
    private var medicineRepository: MedicineRepository,
    private var prescriptionFactory: PrescriptionFactory,
    private var prescriptionRepository: PrescriptionRepository,
    private var prescriptionFillFactory: PrescriptionFillFactory,
    private var prescriptionFillRepository: PrescriptionFillRepository

):UserFacade{
    override fun registerAgent(info: PrescriberCreateDto):Boolean{
        val userId = info.uid
        if(accountRepository.findPharmacist(userId)!=null||accountRepository.findPharmacist(userId)!=null)
            return false
        //1 for pharmacist, 2 for presriber
        if(info.priority.equals("2")){
            val prescriberAccount = accountFactory.createPrescriberAccount(info)
            accountRepository.savePrescriber(prescriberAccount)
            eventEmitter.emit(
                EmitNewAgent(UUID.randomUUID())
            )
            return true
        }
        return false
    }
    override fun registerAgent(info: PharmacistCreateDto):Boolean{
        val userId = info.uid
        if(accountRepository.findPharmacist(userId)!=null||accountRepository.findPharmacist(userId)!=null)
            return false
        //1 for pharmacist, 2 for presriber
        if(info.priority.equals("1")){
            val pharmacistAccount = accountFactory.createPharmacistAccount(info)
            accountRepository.savePharmacist(pharmacistAccount)
            eventEmitter.emit(
                EmitNewAgent(UUID.randomUUID())
            )
            return true
        }

        return false
    }
    override fun updateAgent(uid: UUID, info:PrescriberCreateDto):Boolean{
        //1 for pharmacist, 2 for presriber
        if(info.priority.equals("2")){
            val user = accountRepository.findPrescriber(uid)
            if(user!=null){
                val updated = accountFactory.createPrescriberAccount(info)
                user.updateInformation(updated)
                accountRepository.savePrescriber(user)
                eventEmitter.emit(
                    EmitAccountUpdate(UUID.randomUUID())
                )
                return true
            }
        }
        return false
    }
    override fun updateAgent(uid: UUID, info:PharmacistCreateDto):Boolean{
            if(info.priority.equals("1")) {
                val user = accountRepository.findPharmacist(uid)
                if(user!=null){
                    val updated = accountFactory.createPharmacistAccount(info)
                    user.updateInformation(updated)
                    accountRepository.savePharmacist(user)
                    eventEmitter.emit(
                        EmitAccountUpdate(UUID.randomUUID())
                    )
                    return true
                }
            }
        return false
    }
    override fun removeAgent(uid:UUID):Boolean{

        val user = accountRepository.findPrescriber(uid)
        if(user!=null){
            user.priority = "5"
            eventEmitter.emit(
                DeleteComplete()
            )
            return true
        }
        return false
    }

    override fun confirmPrescriber(info:PrescriberCreateDto): Boolean {
        val prescriberID = info.uid
        val pvalid = cpsoCheckValid.checkValid(prescriberID, accountRepository)
        return pvalid
    }

    override fun confirmPresribtion(prescription:PrescriptionCreateDto): Boolean {
        if(prescription.medicineDIN!=null&&prescription.patient!=null
            &&prescription.prescriber!=null&&prescription.date!=null
            &&prescription.medicineDIN!=null&&prescription.medicineAmount!=null
            &&prescription.medicineName!=null&&prescription.medicineStrength!=null
            &&prescription.methodOfAdministration!=null&&prescription.frequencyOfAdministration!=null){
            var dinvaild=medicineRepository.find(prescription.medicineDIN);
            if(dinvaild!=null&&dinvaild.stock!=0){
                return true;
            }
        }
        return false;
    }


    /*
        An agent finalizes the preparation by printing off the drug
        information and counselling documents to be provided to the
        patient, and contacts the patient to inform that the medicine is
        ready for pick-up
     */
    override fun ifFinializesThePreparation(patientInfo:PatientCreateDto, pf:PrescriptionFillCreateDto):Boolean{
        return true;
    }

    //call the function to set the verified variable in prescriptionfill to be true
    override fun checkIfValidPrescription(patientInfo:PatientCreateDto, pf:PrescriptionFillCreateDto):Boolean{
        return true;
    }

    override fun createPrescription(prescriptionInfo: PrescriptionCreateDto): Boolean {
        val user = accountRepository.findPrescriber(prescriptionInfo.prescriber.uid)
        if(user!=null) {
            user.createPrescription(prescriptionInfo, prescriptionFactory, prescriptionRepository,medicineRepository);
            return true;
        }
        return false;
    }

    override fun preparePrescriptionFill(
        id:UUID,prescriptionFillInfo: PrescriptionFillCreateDto,
    ): Boolean {
        var user = accountRepository.findPrescriber(id)
        //if not prescriber
        if(user==null){
            var user2 = accountRepository.findPharmacist(id)
            if(user2!=null)
                user2.preparePrescriptionFill(prescriptionFillInfo,prescriptionFillFactory,prescriptionFillRepository)
            else
                return false
            return true
        }
        //if pharmacist
        else{
            user.preparePrescriptionFill(prescriptionFillInfo,prescriptionFillFactory,prescriptionFillRepository)
            return true
        }
        return false
    }
}


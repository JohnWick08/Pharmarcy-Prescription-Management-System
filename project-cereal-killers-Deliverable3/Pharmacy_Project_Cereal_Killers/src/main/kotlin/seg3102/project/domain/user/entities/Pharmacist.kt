package seg3102.project.domain.user.entities

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.patient.entities.DrugReport
import seg3102.project.domain.patient.facade.PatientFacade
import seg3102.project.domain.patient.facade.implementation.PatientFacadeImpl
import seg3102.project.domain.patient.factories.PatientFactory
import seg3102.project.domain.patient.repositories.PatientRepository
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.factories.PrescriptionFillFactory
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.prescription.repositories.PrescriptionRepository
import seg3102.project.domain.user.facade.implementation.UserFacadeImpl
import java.util.UUID

class Pharmacist(
    uid: UUID,
    email: String,
    password: String,
    name: String,
    priority: String,
    isSignedIn: Boolean,

): User(uid,email,password,name,priority,isSignedIn)
{
    //This method updates the information using a Pharmacist Object
    fun updateInformation(updated:Pharmacist){

        email = updated.email
        password = updated.password
        name = updated.name
        priority = updated.priority
        isSignedIn = updated.isSignedIn
    }
    //This method update the information using a PharmacistCreateDto
    fun updateInformation(updated:PharmacistCreateDto){
        email = updated.email
        password = updated.password
        name = updated.name
        priority = updated.priority
        isSignedIn = updated.isSignedIn
    }

    /*
     * The pickUpMedicine() method
     * parameter:       prescriptionFill ID, summary, PrescriptionFillRepository
     * Functionality:   It changes the staus and add summary to the prescriptionfill and
     *                  changes its corresponding prescription's counting variables
     * returns:         if successful return true
     */
    fun pickUpMedicine(prescriptionFillID:UUID, summary:String,repository:PrescriptionFillRepository):Boolean{

        //find the prescription fill using its id
        val currentPrescriptionFill = repository.find(prescriptionFillID)

        //if not null
        if(currentPrescriptionFill!=null) {

            //get the corresponding prescription
            val currentPrescription = currentPrescriptionFill.prescription

            //if the prescription is not refillable return false
            if(!currentPrescription.refillable)return false

            //modify the prescription counting variables
            currentPrescription.pickOnce()
            currentPrescriptionFill.summary = summary
            currentPrescriptionFill.status = "picked-up"

            return true
        }
        return false
    }

    /*
     * The generateDrugReport() method
     * parameter:       prescriptionFill ID, summary, PrescriptionFillRepository
     * Functionality:   It changes the staus and add summary to the prescriptionfill and
     *                  changes its corresponding prescription's counting variables
     * returns:         if successful return true
     */
    fun generateDrugReport(prescriptionFillID:UUID,fillRepository: PrescriptionFillRepository,medicineRepository: MedicineRepository): DrugReport? {

        val prescriptionFill = fillRepository.find(prescriptionFillID)
        if( prescriptionFill!=null) {
            val prescription = prescriptionFill.prescription
            val currentMedicine = medicineRepository.find(prescription.medicineDIN)
            if(currentMedicine!=null) {
                val newReport = DrugReport(
                    currentMedicine.numberOfPrescriptionMade,
                    currentMedicine.stock,
                    prescription.patient.identificationNumber,
                    currentMedicine.drugIdentificationNum,
                    prescription.date,
                    prescription.endDate


                    )
                return newReport
            }
        }
        return null
    }


    /*
        The registerPatient() method takes an PatientDto object, patient factory, patient repository
         as parameters and creates a patient instance using the factory, then stores it into the repository.
        it returns the patient's identificationNumber
    */
    fun registerPatient(patientInfo:PatientCreateDto, factory:PatientFactory,repository:PatientRepository):String{
        val newPatient = factory.createPatient(patientInfo)
        repository.save(newPatient)
        return newPatient.identificationNumber
    }

    /*
        The updatePatient() method takes a patientInfo, patient repository as parameters,
        first it finds the corresponding patient using the identification number, then update it
        by calling th update method of the Patient class
     */
    fun updatePatient(patientInfo:PatientCreateDto,repository:PatientRepository):Boolean{
        val patient = repository.find(patientInfo.identificationNumber)
        if(patient!=null){
            patient.update(patientInfo)
            return false
        }
        return true
    }

    /*
     * The preparePrescriptionFill() method
     * parameter:       prescriptionFill Info, PrescriptionFill Factory, PrescriptionFill repository
     * Functionality:   creates a prescriptionFill and stores into repository
     * returns:         if successful return id
     */
    fun preparePrescriptionFill(prescriptionFillInfo: PrescriptionFillCreateDto, factory: PrescriptionFillFactory, repository: PrescriptionFillRepository):UUID{
        val newPrescriptionFill = factory.createPrescriptionFill(prescriptionFillInfo)
        repository.save(newPrescriptionFill)
        return newPrescriptionFill.id
    }

    /*
     * The verifyPrescription() method
     * parameter:       prescription ID, Prescription Repository
     * Functionality:   It changes isVerified to true
     * returns:         if successful return true
     */
    fun verifyPrescription(id:UUID,repository:PrescriptionRepository):Boolean{
        val prescription = repository.find(id)
        if(prescription!=null){
            prescription.isVerified=true;
            return true;
        }
        return false;

    }

    //the verifyPrescriptionFill() method takes the PrescriptionFillRepository and id as the parameter
    //and sets the verified instance to true
    fun verifyPrescriptionFill(repository: PrescriptionFillRepository,id: UUID):Boolean{
        val prescriptionFill = repository.find(id)
        if(prescriptionFill!=null){
            prescriptionFill.verifies()
            return true
        }
        return false
    }

    /*
     * The clinicalCheck() method
     * parameter:       prescriptionFill ID, PrescriptionFill Repository
     * Functionality:   It changes verified variable of prescriptionFill to true
     * returns:         if successful return true
     */
    fun clinicalCheck(prescriptionFillID: UUID,prescriptionFillRepository: PrescriptionFillRepository):Boolean{
        val prescriptionFill = prescriptionFillRepository.find(prescriptionFillID)
        if(prescriptionFill!=null){
            prescriptionFill.verified=true;
            return true;
        }
        return false;
    }

    /*
     * The informReadyForPickUp() method
     * parameter:       prescriptionFill ID, PrescriptionFill Repository
     * Functionality:   It changes readyForPickUp of prescriptionFill to true
     * returns:         if successful return true
     */
    fun informReadyForPickUp(prescriptionFillID: UUID,prescriptionFillRepository: PrescriptionFillRepository):Boolean{
        val prescriptionFill = prescriptionFillRepository.find(prescriptionFillID)
        if(prescriptionFill!=null){
            prescriptionFill.readyForPickup=true;
            return true;
        }
        return false;
    }

    /*
     * The recordSummary() method
     * parameter:       prescriptionFill ID, summary, PrescriptionFill Repository
     * Functionality:   It adds new summary to the summary variable (ADD NOT REPLACE)
     * returns:         if successful return true
     */
    fun recordSummary(prescriptionFillID: UUID,summary:String,repository: PrescriptionFillRepository):Boolean{
        val prescriptionFill = repository.find(prescriptionFillID)
        if(prescriptionFill!=null){
            prescriptionFill.summary+=summary
            return true;
        }
        return false;
    }

    /*
     * The setPrescriptionFillStatus() method
     * parameter:       prescriptionFill ID, status, PrescriptionFill Repository
     * Functionality:   It REPLACES the status of this prescriptionFill
     * returns:         if successful return true
     */
    fun setPrescriptionFillStatus(prescriptionFillID: UUID,status:String,repository: PrescriptionFillRepository):Boolean{
        val prescriptionFill = repository.find(prescriptionFillID)
        if(prescriptionFill!=null){
            prescriptionFill.status = status
            return true;
        }
        return false;
    }
}
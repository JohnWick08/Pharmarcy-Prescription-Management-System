package seg3102.project.domain.user.entities

import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.prescription.factories.PrescriptionFactory
import seg3102.project.domain.prescription.factories.PrescriptionFillFactory
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.prescription.repositories.PrescriptionRepository
import java.util.*

class Prescriber(
    uid: UUID,
    email: String,
    password: String,
    name: String,
    priority: String,
    isSignedIn: Boolean,

): User(uid,email,password,name,priority,isSignedIn){

    //This method updates the information using a Prescriber Object
    fun updateInformation(updated:Prescriber){

        email = updated.email
        password = updated.password
        name = updated.name
        priority = updated.priority
        isSignedIn = updated.isSignedIn
        role = updated.role
        licenceNumber = updated.licenceNumber
        title = updated.title
        address = updated.address
        telephoneNumber = updated.telephoneNumber
    }
    //This method update the information using a PrescriberCreateDto
    fun updateInformation(updated:PrescriberCreateDto){

        email = updated.email
        password = updated.password
        name = updated.name
        priority = updated.priority
        isSignedIn = updated.isSignedIn
        role = updated.role
        licenceNumber = updated.licenceNumber
        title = updated.title
        address = updated.address
        telephoneNumber = updated.telephoneNumber
    }
    var role: String
        get()=role
        set(value){role = value}

    var licenceNumber:String
        get()=licenceNumber
        set(value){licenceNumber = value}

    var title:String
        get()=title
        set(value){title = value}

    var address:String
        get()=address
        set(value){address = value}

    var telephoneNumber:String
        get()=telephoneNumber
        set(value){telephoneNumber = value}

    /*
        This method takes prescriptionDto, prescriptionFactory and prescriptionRepository as parameter
        it creates an instance of the prescription in the factory by using hte Dto, and stores the
        new instance into the repository
     */
    fun createPrescription(prescriptionInfo:PrescriptionCreateDto,factory:PrescriptionFactory,repository:PrescriptionRepository,
    medicineRepository: MedicineRepository):UUID {
        val medicine = medicineRepository.find(prescriptionInfo.medicineDIN)
        if(medicine!=null){
            medicine.numberOfPrescriptionMade+=1
        }
        val newPrescription = factory.createPrescription(prescriptionInfo)
        repository.save(newPrescription)
        return newPrescription.id
    }

    /*
     * The preparePrescriptionFill() method
     * parameter:       prescriptionFill Info, PrescriptionFill Factory, PrescriptionFill repository
     * Functionality:   creates a prescriptionfill and stores into repository
     * returns:         if successful return id
     */
    fun preparePrescriptionFill(prescriptionFillInfo: PrescriptionFillCreateDto,factory:PrescriptionFillFactory,repository: PrescriptionFillRepository):UUID{
        val newPrescriptionFill = factory.createPrescriptionFill(prescriptionFillInfo)
        repository.save(newPrescriptionFill)
        return newPrescriptionFill.id
    }

}
